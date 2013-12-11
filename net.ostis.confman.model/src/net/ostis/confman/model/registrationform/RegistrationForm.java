package net.ostis.confman.model.registrationform;

import java.util.ArrayList;
import java.util.List;

public class RegistrationForm {

    private ArticleInformation      articleInformation;

    private List<AuthorInformation> authorsInformation;

    public RegistrationForm() {

        super();
        this.articleInformation = new ArticleInformation();
        this.authorsInformation = new ArrayList<AuthorInformation>();
    }

    public ArticleInformation getArticleInformation() {

        return this.articleInformation;
    }

    public List<AuthorInformation> getAuthorsInformation() {

        return this.authorsInformation;
    }

    public void setArticleInformation(
            final ArticleInformation articleInformation) {

        this.articleInformation = articleInformation;
    }

    public void setAuthorsInformation(
            final List<AuthorInformation> authorsInformation) {

        this.authorsInformation = authorsInformation;
    }
}
