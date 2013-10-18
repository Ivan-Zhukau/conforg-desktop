package net.ostis.confman.model.entity;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class Authors {
    
	private List<Author> authorInfo;
	
	public Authors(){
		this.authorInfo = new ArrayList<Author>();
	}
	
	public void setAuthorInfo(List<Author> authorInfo){
		this.authorInfo = authorInfo;
	}
	
	public List<Author> getAuthorInfo(){
		return this.authorInfo;
	}
}
