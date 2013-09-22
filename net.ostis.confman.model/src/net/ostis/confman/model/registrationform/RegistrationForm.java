package net.ostis.confman.model.registrationform;

import java.util.ArrayList;
import java.util.List;

public class RegistrationForm {
	
	private ArticleInformation articleInformation;
	private List<AuthorInformation> authorsInformation;
	
	public RegistrationForm() {
		super();
		articleInformation = new ArticleInformation();
		authorsInformation = new ArrayList<AuthorInformation>();
	}
	
	public ArticleInformation getArticleInformation() {
		return articleInformation;
	}
	public List<AuthorInformation> getAuthorsInformation() {
		return authorsInformation;
	}
	public void setArticleInformation(ArticleInformation articleInformation) {
		this.articleInformation = articleInformation;
	}
	public void setAuthorsInformation(List<AuthorInformation> authorsInformation) {
		this.authorsInformation = authorsInformation;
	}
}
