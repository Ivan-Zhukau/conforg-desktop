package net.ostis.confman.model.registrationform;

public class ContactInformation {
	
	private String country;
	private String city;
	private String eMail ;
	private String contactPhoneNumber;
	
	public ContactInformation() {
		super();
	}
	
	public String getCity() {
		return city;
	}
	public String getContactPhoneNumber() {
		return contactPhoneNumber;
	}
	public String getCountry() {
		return country;
	}
	public String geteMail() {
		return eMail;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public void setContactPhoneNumber(String contactPhoneNumber) {
		this.contactPhoneNumber = contactPhoneNumber;
	}
	public void setCountry(String country) {
		this.country = country;
	}
	public void seteMail(String eMail) {
		this.eMail = eMail;
	}
}
