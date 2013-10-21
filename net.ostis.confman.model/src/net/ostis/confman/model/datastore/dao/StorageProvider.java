package net.ostis.confman.model.datastore.dao;

import java.util.ArrayList;
import java.util.List;

import net.ostis.confman.model.convert.EntityConverter;
import net.ostis.confman.model.entity.Authors;
import net.ostis.confman.services.common.model.ArticleInformation;
import net.ostis.confman.services.common.model.AuthorInformation;
import net.ostis.confman.services.common.model.RegistrationInformation;

import org.apache.log4j.Logger;

public class StorageProvider {

    public static final Logger LOGGER = Logger.getLogger(StorageProvider.class);

    private AuthorsDAO         authorsDAO;

    private ArticlesDAO        articlesDAO;

    public StorageProvider() {

        super();
        initDAO();
    }

    private void initDAO() {

        this.authorsDAO = new AuthorsDAO();
        this.articlesDAO = new ArticlesDAO();
    }

    public List<AuthorInformation> getAuthors() {

        final Authors authors = this.authorsDAO.getAuthors();
        final List<AuthorInformation> result = EntityConverter.getInstance()
                .entityToAuthors(authors);
        return result;
    }

    public void saveAuthors(final RegistrationInformation information) {

        final Authors authors = EntityConverter.getInstance().authorsToEntity(
                information);
        this.authorsDAO.saveAuthors(authors);
    }

    public List<ArticleInformation> getArticles() {

        // TODO kfs: add article storage support.
        return new ArrayList<>();
    }

    public void saveArticles(final List<ArticleInformation> articles) {

        // TODO kfs: add article storage support.
    }
}
