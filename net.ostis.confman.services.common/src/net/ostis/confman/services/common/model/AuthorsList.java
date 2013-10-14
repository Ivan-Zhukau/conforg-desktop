package net.ostis.confman.services.common.model;

import java.util.ArrayList;
import java.util.List;

public class AuthorsList {
	private List<AuthorInformation> authorInfo;
	
	public AuthorsList(){
		this.authorInfo = new ArrayList<AuthorInformation>();
	}
	
	public void setAuthorInfo(List<AuthorInformation> authorInfo){
		this.authorInfo = authorInfo;
	}
	
	public List<AuthorInformation> getAuthorInfo(){
		return this.authorInfo;
	}
}
