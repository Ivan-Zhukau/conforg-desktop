package net.ostis.confman.wordparser;

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
