package net.ostis.confman.model.registrationform;

import java.util.ArrayList;
import java.util.List;

public class ArticleInformation {

	private String titleEntry;
	private List<String> coAuthors ;
	private String participationForm;
	private String speaker;
	private String showLaunching ;
	
	public ArticleInformation() {
		super();
		coAuthors = new ArrayList<String>();
	}
	
	public String getTitleEntry() {
		return titleEntry;
	}
	public List<String> getCoAuthors() {
		return coAuthors;
	}
	public String getParticipationForm() {
		return participationForm;
	}
	public String getShowLaunching() {
		return showLaunching;
	}
	public String getSpeaker() {
		return speaker;
	}
	public void setCoAuthors(List<String> coAuthors) {
		this.coAuthors = coAuthors;
	}
	public void setParticipationForm(String participationForm) {
		this.participationForm = participationForm;
	}
	public void setShowLaunching(String showLaunching) {
		this.showLaunching = showLaunching;
	}
	public void setSpeaker(String speaker) {
		this.speaker = speaker;
	}
	public void setTitleEntry(String titleEntry) {
		this.titleEntry = titleEntry;
	}
}
