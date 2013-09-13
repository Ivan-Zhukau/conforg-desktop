package net.ostis.confman.parser_doc;

public class EntryPointParser {
	public static void main(String[] args) {
		try {
				AppDocParser appDocParser = new AppDocParser("PATH");
			} 
		catch (Exception e) {
			e.printStackTrace();
		}
	}

}
