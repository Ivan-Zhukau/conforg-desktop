package net.ostis.confman.model.registrationform;

import java.util.ArrayList;
import java.util.List;

public class ArticleInformation {

	private String titleEntry;
	private String participationForm;
	private String speaker;
	private String showLaunching ;
	
	public ArticleInformation() {
		super();
	}
	
	public String getTitleEntry() {
		return titleEntry;
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
