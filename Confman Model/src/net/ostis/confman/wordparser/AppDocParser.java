package net.ostis.confman.wordparser;

import java.io.FileInputStream;

import org.apache.poi.hwpf.HWPFDocument;
import org.apache.poi.hwpf.usermodel.Paragraph;
import org.apache.poi.hwpf.usermodel.Range;
import org.apache.poi.hwpf.usermodel.Table;
import org.apache.poi.hwpf.usermodel.TableCell;
import org.apache.poi.hwpf.usermodel.TableRow;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;

public class AppDocParser {
	
	public AppInfo appInfo;	 
	
	public AppDocParser(String path) {
		appInfo = new AppInfo();
		parseDocFile(path);
	}
	
	private void parseDocFile(String path) {
        POIFSFileSystem fileSystem = null;
        try {
        	fileSystem = new POIFSFileSystem(new FileInputStream(path));
            HWPFDocument doc = new HWPFDocument(fileSystem);
            
            Range range = doc.getRange();             
            Paragraph tableParagraph = range.getParagraph(0); 
            
            if (tableParagraph.isInTable()) {  
                Table table = range.getTable(tableParagraph);                  
                readTable(table);  
            }  	  
        } 
        catch (Exception e) {
            e.printStackTrace();
        }		
	}

	private void readTable(Table table) {
		for (int rowIdx=0; rowIdx < table.numRows(); rowIdx++) {  
		    TableRow row = table.getRow(rowIdx);  
		    
		    for (int colIdx=0; colIdx<row.numCells(); colIdx++) {  
		        TableCell cell = row.getCell(colIdx);                         
		        readCell(rowIdx, colIdx, cell);
		    }  
		}
	}

	private void readCell(int rowIdx, int colIdx, TableCell cell) {
		String info = "";		
		if(colIdx == 1){
		    for(int parInx = 0; parInx < cell.numParagraphs(); parInx++ ){
		    	
		    		info += cell.getParagraph(parInx).text();                     	
		    }
		    fieldComplete(rowIdx+1, info.substring(0, info.length()-1));
		    info = "";
		}
	}

	private void fieldComplete (int rowNum, String info ) {
		switch (rowNum) {
		case 2:{
			appInfo.setTitleEntry(info);
			break;
		}			
		case 3:{
			appInfo.setCoAuthor(info);
			break;
		}	
		case 4:{
			appInfo.setParticipationForm(info);
			break;
		}
		case 5:{
			appInfo.setSpeaker(info);
			break;
		}	
		case 6:{
			appInfo.setShowLaunching(info);
			break;
		}
		case 8:{
			appInfo.setSecondName(info);
			break;
		}
		case 9:{
			appInfo.setFirstName(info);
			break;
		}
		case 10:{
			appInfo.setThirdName(info);
			break;
		}			
		case 11:{
			appInfo.setAcademicDegree(info);
			break;
		}
		case 12:{
			appInfo.setAcademicTitle(info);
			break;
		}
		case 13:{
			appInfo.setCountry(info);
			break;
		}
		case 14:{
			appInfo.setCity(info);
			break;
		}
		case 15:{
			appInfo.seteMail(info);
			break;
		}	
		case 16:{
			appInfo.setContactPhoneNumber(info);
			break;
		}
		case 18:{
			appInfo.setAffliation(info);
			break;
		}
		case 19:{
			appInfo.setPosition(info);
			break;
		}
		default:
			break;
		}
		
	}

}
