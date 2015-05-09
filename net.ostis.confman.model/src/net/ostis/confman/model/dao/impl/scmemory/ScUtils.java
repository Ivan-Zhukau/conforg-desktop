package net.ostis.confman.model.dao.impl.scmemory;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import by.ostis.common.sctpclient.client.SctpClient;
import by.ostis.common.sctpclient.client.SctpClientImpl;
import by.ostis.common.sctpclient.exception.SctpClientException;
import by.ostis.common.sctpclient.model.ScAddress;
import by.ostis.common.sctpclient.model.ScIterator;
import by.ostis.common.sctpclient.model.ScParameter;
import by.ostis.common.sctpclient.model.ScString;
import by.ostis.common.sctpclient.model.response.SctpResponse;
import by.ostis.common.sctpclient.model.response.SctpResponseHeader;
import by.ostis.common.sctpclient.model.response.SctpResultType;
import by.ostis.common.sctpclient.utils.constants.ScElementType;
import by.ostis.common.sctpclient.utils.constants.ScIteratorType;
import net.ostis.confman.model.dao.exception.DAOException;
import net.ostis.confman.model.dao.exception.ElementNotFoundException;

/* (non-Javadoc)
 * ScUtils 
 */
enum ScUtils {

    INSTANCE;

    private static final String HOST = "localhost";

    private static final int    PORT = 50041;

    private final SctpClient    sctpClient;

    ScUtils() {

        sctpClient = new SctpClientImpl();
        // TODO load settings from .properties
        sctpClient.init(HOST, PORT);
    }

    public ScAddress findElement(UUID uuid) throws DAOException {

        String systemId = uuid.toString();
        return findElement(systemId);
    }

    public ScAddress findElement(String systemId) throws DAOException {

        SctpResponse<ScAddress> response;
        try {
            ScString scId = ScStrings.wrap(systemId);
            response = sctpClient.searchElement(scId);
        } catch (SctpClientException e) {
            throw new DAOException("element not found: " + systemId, e);
        }
        checkHeader(response.getHeader());
        return response.getAnswer();
    }

    public boolean belongsToSpace(ScAddress element, ScSpaces space)
            throws DAOException {

        final String spaceId = space.getSystemId();
        ScAddress spaceAdr = findElement(spaceId);
        List<ScParameter> parameters = new ArrayList<ScParameter>(3);
        parameters.add(spaceAdr);
        parameters.add(ScElementType.SC_TYPE_ARC_POS);
        parameters.add(element);
        try {
            SctpResponse<List<ScIterator>> result = sctpClient
                    .searchByIterator(ScIteratorType.SCTP_ITERATOR_3F_A_F,
                            parameters);
            checkHeader(result.getHeader());
            List<ScIterator> iterators = result.getAnswer();
            return !iterators.isEmpty();
        } catch (SctpClientException e) {
            throw new DAOException("cannot find element using 3FAF iterator", e);
        }
    }

    private void checkHeader(SctpResponseHeader header) throws DAOException {

        SctpResultType resultType = header.getResultType();
        switch (resultType) {
            case SCTP_RESULT_ERROR_NO_ELEMENT:
                throw new DAOException("element not found");
            case SCTP_RESULT_FAIL:
                throw new DAOException("error occurred - assertion failure");
            case SCTP_RESULT_OK:
                break;
            default:
                throw new DAOException("unsupported result type: " + resultType);
        }
    }

    public ScAddress createElWithGivenSystemId(UUID systemId)
            throws DAOException {

        ScAddress newNode = createEmptyNode();
        ScString scId = ScStrings.wrap(systemId);
        try {
            SctpResponse<Boolean> setSysIdResponse = sctpClient
                    .setSystemIdentifier(newNode, scId);
            checkHeader(setSysIdResponse.getHeader());
            if (setSysIdResponse.getAnswer() != Boolean.TRUE) {
                throw new DAOException("cannot set system id for node, id: "
                        + systemId);
            }
        } catch (SctpClientException e) {
            // TODO we have to collect garbage here in order to prevent
            // "memory leaks" (i.e. el was created, but system id wasn't, so we
            // can't use the el)
            throw new DAOException(
                    "cannot set system id for previously created element", e);
        }
        return newNode;
    }

    private ScAddress createEmptyNode() throws DAOException {

        SctpResponse<ScAddress> response;
        try {
            response = sctpClient.createElement(ScElementType.SC_TYPE_NODE);
        } catch (SctpClientException e) {
            throw new DAOException("failed to create a node", e);
        }
        checkHeader(response.getHeader());
        return response.getAnswer();
    }

    public void createRelation(ScAddress parent, ScAddress child,
            ScIdentifiable relationId) throws DAOException {

        ScAddress commonArc = createArc(ScElementType.SC_TYPE_ARC_COMMON,
                parent, child);
        ScAddress relationNode = findElement(relationId.getSystemId());
        createArc(ScElementType.SC_TYPE_ARC_POS, relationNode, commonArc);
    }

    public ScAddress createArc(ScElementType elementType, ScAddress firstEl,
            ScAddress secondEl) throws DAOException {

        try {
            SctpResponse<ScAddress> response;
            response = sctpClient.createScArc(elementType, firstEl, secondEl);
            checkHeader(response.getHeader());
            return response.getAnswer();
        } catch (SctpClientException e) {
            throw new DAOException("cannot create arc, type: " + elementType, e);
        }
    }

    public ScAddress createNodeWithContent(ScString content)
            throws DAOException {

        ScAddress node = createEmptyNode();
        try {
            SctpResponse<Boolean> response = sctpClient.setScRefContent(node,
                    content);
            checkHeader(response.getHeader());
            Boolean successfully = response.getAnswer();
            if (!successfully) {
                throw new DAOException("cannot set node content");
            }
            return node;
        } catch (SctpClientException e) {
            throw new DAOException("cannot set node content", e);
        }
    }

    public ScAddress findUniqueElementByParentAndRelation(ScAddress parent,
            ScAddress relation) throws DAOException {

        try {
            List<ScParameter> parameters = new ArrayList<ScParameter>(5);
            parameters.add(parent);
            parameters.add(ScElementType.SC_TYPE_ARC_COMMON);
            parameters.add(ScElementType.SC_TYPE_NODE);
            parameters.add(ScElementType.SC_TYPE_ARC_POS);
            parameters.add(relation);
            SctpResponse<List<ScIterator>> response = sctpClient
                    .searchByIterator(ScIteratorType.SCTP_ITERATOR_5F_A_A_A_F,
                            parameters);
            checkHeader(response.getHeader());
            List<ScIterator> results = response.getAnswer();
            if (results.isEmpty()) {
                throw new ElementNotFoundException("element not found");
            }
            if (results.size() > 1) {
                throw new DAOException("element is non-unique");
            }
            ScIterator iterator = results.get(Constants.FIRST_ELEMENT);
            ScParameter uniqueNode = iterator
                    .getElement(Constants.ITERATOR_5_3RD);
            if (uniqueNode instanceof ScAddress) {
                return (ScAddress) uniqueNode;
            } else {
                throw new DAOException(
                        "returned parameter is not an instance of sc address, but: "
                                + uniqueNode.getClass());
            }
        } catch (SctpClientException e) {
            throw new DAOException(
                    "cannot get children from parent :" + parent, e);
        }
    }

    public ScAddress findUniqueElementByParentAndRelation(ScAddress parent,
            ScIdentifiable relation) throws DAOException {

        ScAddress relationAdr = findElement(relation.getSystemId());
        return findUniqueElementByParentAndRelation(parent, relationAdr);
    }

    public String findElementContent(ScAddress element) throws DAOException {

        try {
            SctpResponse<String> response = sctpClient
                    .getScLinkContent(element);
            checkHeader(response.getHeader());
            return response.getAnswer();
        } catch (SctpClientException e) {
            throw new DAOException("cannot load content of element", e);
        }
    }
}
