package net.ostis.confman.model.registrationform;

public class PersonalInformation {
	
	private String secondName;
	private String firstName;
	private String thirdName;
	private String academicDegree;
	private String academicTitle;
	
	public PersonalInformation() {
		super();
	}
	
	public String getAcademicDegree() {
		return academicDegree;
	}
	public String getAcademicTitle() {
		return academicTitle;
	}
	public String getFirstName() {
		return firstName;
	}
	public String getSecondName() {
		return secondName;
	}
	public String getThirdName() {
		return thirdName;
	}
	public void setAcademicDegree(String academicDegree) {
		this.academicDegree = academicDegree;
	}
	public void setAcademicTitle(String academicTitle) {
		this.academicTitle = academicTitle;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public void setSecondName(String secondName) {
		this.secondName = secondName;
	}
	public void setThirdName(String thirdName) {
		this.thirdName = thirdName;
	}
	
}
