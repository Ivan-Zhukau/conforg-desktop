package net.ostis.confman.model.registrationform.wordparser;

import java.util.ArrayList;
import java.util.List;

import net.ostis.confman.model.registrationform.RegistrationForm;
import net.ostis.confman.model.registrationform.wordparser.article.ArticleParser;
import net.ostis.confman.model.registrationform.wordparser.author.AuthorParser;

public class InfoParser {

    private RegistrationForm form;

    private List<String>     information;

    public InfoParser() {

        this.form = new RegistrationForm();
    }

    public RegistrationForm parse(final List<String> information) {

        this.information = information;
        parseArticle(information);
        parseAuthors(information);
        return this.form;
    }

    private void parseAuthors(final List<String> information) {

        List<String> authorFields = new ArrayList<String>();
        final int startIndex = RegistrationFormConstant.NUMBER_ARTICLE_ITEMS;
        int numCheckedFields = 0;
        for (int index = startIndex; index < information.size(); ++index) {
            if (numCheckedFields == RegistrationFormConstant.NUMBER_AUTORS_ITEMS - 1) {
                authorFields.add(information.get(index));
                new AuthorParser(this.form).parse(authorFields);
                authorFields = new ArrayList<String>();
                numCheckedFields = 0;
                continue;
            }
            authorFields.add(information.get(index));
            ++numCheckedFields;
        }
    }

    private void parseArticle(final List<String> information) {

        final List<String> articleFields = new ArrayList<String>();
        for (int index = 0; index < RegistrationFormConstant.NUMBER_ARTICLE_ITEMS; ++index) {
            articleFields.add(information.get(index));
        }
        new ArticleParser(this.form).parse(articleFields);
    }
}
