package net.ostis.confman.model.registrationform.wordparser;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import net.ostis.confman.model.registrationform.AuthorInformation;
import net.ostis.confman.model.registrationform.RegistrationForm;
import net.ostis.confman.model.registrationform.RegistrationFormConstant;

import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.apache.poi.xwpf.usermodel.XWPFTable;
import org.apache.poi.xwpf.usermodel.XWPFTableCell;
import org.apache.poi.xwpf.usermodel.XWPFTableRow;

public class DocxRegistrationFormParser {
	
	private RegistrationForm registrationForm;	 
	private List<String> allInformation;
	
	public DocxRegistrationFormParser() {
		super();
		registrationForm = new RegistrationForm();
		allInformation = new ArrayList<String>();
	}
	
	public RegistrationForm parse(FileInputStream inputStream) {
            try {
				XWPFDocument document = new XWPFDocument(inputStream);
				List<XWPFTable> tables = document.getTables();
				readTables(tables);
			} 
            catch (IOException e) {
				e.printStackTrace();
			}	
            return registrationForm;
	}

	private void readTables(List<XWPFTable> tables) {
		for(XWPFTable table : tables){
			for(XWPFTableRow row : table.getRows()){
				for(XWPFTableCell cell : row.getTableCells()){
					if(row.getTableCells().indexOf(cell)==1 && table.getRows().indexOf(row)!=0){
						allInformation.add(cell.getText());
					} 
				}
			}
		}
		parseAllInform(allInformation);
	}

	private void parseAllInform(List<String> information) {	
		int counter = 0;
		AuthorInformation authorInformation = new AuthorInformation();
		for(String info : information){
			infoAllocation(counter, info, authorInformation);
			counter++;
		}
	}

	private void infoAllocation(int counter, String info, AuthorInformation authorInformation) {
		if(counter<5) {
			completeArticlePart(counter, info);
		}
		else {
			completeAuthorsPart(counter, info);
		}
	}
	
	private int spotIndex(int counter, int authorNumber) {
		return counter-(authorNumber*RegistrationFormConstant.NUMBER_AUTORS_ITEMS);
	}

	private void completeAuthorsPart(int index, String info) {
		int authorNumber = getAuthorNumber(index);
		int convertIndex = spotIndex(index, authorNumber);
		
		switch (convertIndex) {		
		case RegistrationFormConstant.SECOND_NAME:{
			registrationForm.getAuthorsInformation().get(authorNumber).getPersonalInformation().setSecondName(info);
			break;
		}			
		case RegistrationFormConstant.FIRST_NAME:{
			registrationForm.getAuthorsInformation().get(authorNumber).getPersonalInformation().setFirstName(info);
			break;
		}	
		case RegistrationFormConstant.THIRD_NAME:{
			registrationForm.getAuthorsInformation().get(authorNumber).getPersonalInformation().setThirdName(info);
			break;
		}
		case RegistrationFormConstant.ACADEMIC_DEGREE:{
			registrationForm.getAuthorsInformation().get(authorNumber).getPersonalInformation().setAcademicDegree(info);
			break;
		}	
		case RegistrationFormConstant.ACADEMIC_TITLE:{
			registrationForm.getAuthorsInformation().get(authorNumber).getPersonalInformation().setAcademicTitle(info);
			break;
		}		
		case RegistrationFormConstant.COUNTRY:{
			registrationForm.getAuthorsInformation().get(authorNumber).getContactInformation().setCountry(info);
			break;
		}			
		case RegistrationFormConstant.CITY:{
			registrationForm.getAuthorsInformation().get(authorNumber).getContactInformation().setCity(info);
			break;
		}	
		case RegistrationFormConstant.EMAIL:{
			registrationForm.getAuthorsInformation().get(authorNumber).getContactInformation().seteMail(info);
			break;
		}
		case RegistrationFormConstant.CONTACT_PHONE_NUMBER:{
			registrationForm.getAuthorsInformation().get(authorNumber).getContactInformation().setContactPhoneNumber(info);
			break;
		}	
		case RegistrationFormConstant.AFFLIATION:{
			registrationForm.getAuthorsInformation().get(authorNumber).getWorkPlaceInformation().setAffliation(info);
			break;
		}	
		case RegistrationFormConstant.POSITION:{
			registrationForm.getAuthorsInformation().get(authorNumber).getWorkPlaceInformation().setPosition(info);
			break;
		}	
		default:
			break;
		}	
		
	}

	private int getAuthorNumber(int counter) {
		return (counter-RegistrationFormConstant.NUMBER_ARTICLE_ITEMS)/RegistrationFormConstant.NUMBER_AUTORS_ITEMS;
	}

	private void completeArticlePart(int counter, String info) {
		switch (counter) {		
		case RegistrationFormConstant.TITLE_ENTRY:{
			registrationForm.getArticleInformation().setTitleEntry(info);
			break;
		}			
		case RegistrationFormConstant.CO_AUTHOR:{
			parseCoAuthors(info);
			break;
		}	
		case RegistrationFormConstant.PARTICIPATION_FORM:{
			registrationForm.getArticleInformation().setParticipationForm(info);
			break;
		}
		case RegistrationFormConstant.SPEAKER:{
			registrationForm.getArticleInformation().setSpeaker(info);
			break;
		}	
		case RegistrationFormConstant.SHOW_LAUNCHING:{
			registrationForm.getArticleInformation().setShowLaunching(info);
			break;
		}		
		default:
			break;
		}			
	}

	private void parseCoAuthors(String info) {
		List<String> NamesWithSpaces = selectCertainNames(info);	
		List<String> coAuthorsNames = deleteSpaces(NamesWithSpaces);
		addInResistrationForm(coAuthorsNames);
	}

	private void addInResistrationForm(List<String> coAuthorsNames) {
		for(String name : coAuthorsNames){
			AuthorInformation authorInformation = new AuthorInformation();
			authorInformation.setId_Author(name);
			registrationForm.getAuthorsInformation().add(authorInformation);
			}
	}

	private List<String> deleteSpaces(List<String> namesWithSpaces) {
		List<String> namesWithoutSpaces = new ArrayList<String>();
		int beginName = 0;
		int endName = 0;
		for(String name : namesWithSpaces){
			beginName = findBeginWord(beginName, name);
			endName = findEndWord(endName, name);
			namesWithoutSpaces.add(name.substring(beginName, endName));
		}
		return namesWithoutSpaces;
	}

	private int findEndWord(int endName, String name) {
		for(int index = name.length(); index>=0; index--){
			if(!" ".equals(name.substring(index-1, index))){
				endName=index;
				break;
			}
		}
		return endName;
	}

	private int findBeginWord(int beginName, String name) {
		for(int index = 0; index<name.length(); index++){
			if(!" ".equals(name.substring(index, index+1))){
				beginName=index;
				break;
			}
		}
		return beginName;
	}

	private List<String> selectCertainNames(String info) {
		List<String> NamesWithSpaces = new ArrayList<String>();
		int beginWord = 0;
		for(int index = 0; index<info.length(); index++){
			if(",".equals(info.substring(index, index+1))){
				NamesWithSpaces.add(info.substring(beginWord, index));
				beginWord = index+1;
			}
			if(index+1==info.length()){
				NamesWithSpaces.add(info.substring(beginWord, index+1));
			}
		}
		return NamesWithSpaces;
	}
}
