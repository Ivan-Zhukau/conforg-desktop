package net.ostis.confman.services;

import java.io.StringWriter;

import net.ostis.confman.model.datastore.StorageProvider;

import org.apache.log4j.Logger;
import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.VelocityEngine;
import org.apache.velocity.exception.MethodInvocationException;
import org.apache.velocity.exception.ParseErrorException;
import org.apache.velocity.exception.ResourceNotFoundException;

public class BuildTemplateServiceImpl implements BuildTemplateService {

    private static final String LOG_TAG = "velocity error";

    private static final Logger LOGGER  = Logger.getLogger(StorageProvider.class);

    @Override
    public String processTemplate(final String templateBody,
            final TemplateContextService contextService) {

        final VelocityEngine velocityEngine = new VelocityEngine();
        velocityEngine.init();
        final VelocityContext context = new VelocityContext(
                contextService.getTemplateContext());
        final StringWriter writer = new StringWriter();
        try {
            velocityEngine.evaluate(context, writer, LOG_TAG, templateBody);
        } catch (final ParseErrorException e) {
            LOGGER.error(e);
        } catch (final MethodInvocationException e) {
            LOGGER.error(e);
        } catch (final ResourceNotFoundException e) {
            LOGGER.error(e);
        }
        return writer.toString();
    }

}
