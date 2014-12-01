package net.ostis.confman.model.registrationform.wordparser.article;

import java.util.List;

import net.ostis.confman.model.registrationform.ArticleInformation;
import net.ostis.confman.model.registrationform.RegistrationForm;
import net.ostis.confman.model.registrationform.wordparser.RegistrationFormConstant;

public class ArticleParser {

    private ArticleInformation articleInformation;

    private RegistrationForm   form;

    public ArticleParser(final RegistrationForm form) {

        this.form = form;
        this.articleInformation = new ArticleInformation();
    }

    public void parse(final List<String> info) {

        int fieldIndex = 0;
        for (final String field : info) {
            parseField(field, fieldIndex);
            ++fieldIndex;
        }
        this.form.setArticleInformation(this.articleInformation);
    }

    private void parseField(final String info, final int fieldIndex) {

        switch (fieldIndex) {
            case RegistrationFormConstant.TITLE_ENTRY: {
                this.articleInformation.setTitleEntry(info);
                break;
            }
            case RegistrationFormConstant.CO_AUTHOR: {
                new CoAuthorsFieldParser(this.articleInformation).parse(info);
                break;
            }
            case RegistrationFormConstant.PARTICIPATION_FORM: {
                this.articleInformation.setParticipationForm(info);
                break;
            }
            case RegistrationFormConstant.SPEAKER: {
                this.articleInformation.setSpeaker(info);
                break;
            }
            case RegistrationFormConstant.PARTICIPATION_IN_CONTEST: {
                this.articleInformation.setContestParticipation(info);
                break;
            }
            default:
                break;
        }
    }
}
