package net.ostis.confman.parserDoc;

import java.io.FileInputStream;

import org.apache.poi.hwpf.HWPFDocument;
import org.apache.poi.hwpf.extractor.WordExtractor;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;

public class AppDocParser {
	
	public AppInfo appInfo;
	
	public AppDocParser(String path) {
		appInfo = new AppInfo();
		openDocFile(path);
	}
	
	private void openDocFile(String path) {
        POIFSFileSystem fileSystem = null;
        try {
        	fileSystem = new POIFSFileSystem(new FileInputStream(path));
            HWPFDocument doc = new HWPFDocument(fileSystem);
            
            WordExtractor wordExtractor = new WordExtractor(doc);
            String[] paragraphs = wordExtractor.getParagraphText();
            
            for(int i = 0; i < paragraphs.length; i++) { 
            	fieldComplete (i+1, paragraphs[i].toString().substring(0, paragraphs[i].toString().length()-1));
            }         
        } 
        catch (Exception e) {
            e.printStackTrace();
        }		
	}

	private void fieldComplete (int paragraphNumber, String info ) {
		switch (paragraphNumber) {
		case 5:{
			appInfo.setTitleEntry(info);
			break;
		}			
		case 8:{
			appInfo.setCoAuthor(info);
			break;
		}	
		case 13:{
			appInfo.setParticipationForm(info);
			break;
		}
		case 16:{
			appInfo.setSpeaker(info);
			break;
		}	
		case 21:{
			appInfo.setShowLaunching(info);
			break;
		}
		case 26:{
			appInfo.setSecondName(info);
			break;
		}
		case 29:{
			appInfo.setFirstName(info);
			break;
		}
		case 32:{
			appInfo.setThirdName(info);
			break;
		}			
		case 38:{
			appInfo.setAcademicDegree(info);
			break;
		}
		case 45:{
			appInfo.setAcademicTitle(info);
			break;
		}
		case 48:{
			appInfo.setCountry(info);
			break;
		}
		case 51:{
			appInfo.setCity(info);
			break;
		}
		case 54:{
			appInfo.seteMail(info);
			break;
		}	
		case 58:{
			appInfo.setContactPhoneNumber(info);
			break;
		}
		case 63:{
			appInfo.setAffliation(info);
			break;
		}
		case 66:{
			appInfo.setPosition(info);
			break;
		}
		default:
			break;
		}
		
	}

}
