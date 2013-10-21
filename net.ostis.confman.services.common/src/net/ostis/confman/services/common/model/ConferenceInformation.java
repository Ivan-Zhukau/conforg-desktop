package net.ostis.confman.services.common.model;

import java.util.ArrayList;
import java.util.List;

public class ConferenceInformation {
	
	private List<AuthorInformation> authors;
	
	private RegistrationInformation article;
	
	public ConferenceInformation() {
		super();
		authors = new ArrayList<AuthorInformation>();
		article = new RegistrationInformation();
	}
	
	public void setAuthors(AuthorInformation author){
		this.authors.add(author);
	}
	
	public void setArticle(RegistrationInformation article){
		this.article = article;
	}
	
	public List<AuthorInformation> getAuthors(){
		return this.authors;
	}
	
	public RegistrationInformation getArticle(){
		return this.article;
	}
}
