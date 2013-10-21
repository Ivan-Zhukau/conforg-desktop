package net.ostis.confman.services.common.model;

import java.util.ArrayList;
import java.util.List;

public class RegistrationInformation {

    private List<AuthorInformation> authorInfo;

    private ArticleInformation      articleInformation;

    public RegistrationInformation() {

        super();
        this.authorInfo = new ArrayList<AuthorInformation>();
        this.articleInformation = new ArticleInformation();
    }

    public void setAuthorInfo(final List<AuthorInformation> authorInfo) {

        this.authorInfo = authorInfo;
    }

    public List<AuthorInformation> getAuthorInfo() {

        return this.authorInfo;
    }

    public ArticleInformation getArticleInformation() {

        return this.articleInformation;
    }

    public void setArticleInformation(
            final ArticleInformation articleInformation) {

        this.articleInformation = articleInformation;
    }
}
