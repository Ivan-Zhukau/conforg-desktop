package net.ostis.confman.model.registrationform.wordparser.article;

import java.util.ArrayList;
import java.util.List;

import net.ostis.confman.model.registrationform.ArticleInformation;

public class CoAuthorsFieldParser {

    private ArticleInformation articleInformation;

    public CoAuthorsFieldParser(final ArticleInformation articleInformation) {

        this.articleInformation = articleInformation;
    }

    public void parse(final String field) {

        final List<String> names = getNames(field);
        for (final String name : names) {
            this.articleInformation.getCoAuthors().add(name);
        }
    }

    private List<String> getNames(final String field) {

        final List<String> names = new ArrayList<String>();
        String correctName = "";
        for (final String name : field.split(",")) {
            for (final String subName : name.split(" ")) {
                if (!"".equals(subName)) {
                    correctName += subName + " ";
                }
            }
            if (!"".equals(correctName)) {
                names.add(correctName.substring(0, correctName.length() - 1));
            }
            correctName = "";
        }
        return names;
    }
}
