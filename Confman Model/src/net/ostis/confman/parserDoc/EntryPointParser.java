package net.ostis.confman.parserDoc;

public class EntryPointParser {
	public static void main(String[] args) {
		try {
			AppDocParser appDocParser = new AppDocParser("D:\\university\\sem_5\\SystemControlConfirentions\\Заявка на участие в конференции OSTIS.doc");
			System.out.println(appDocParser.appInfo.getTitleEntry());
			System.out.println(appDocParser.appInfo.getAcademicDegree());
			System.out.println(appDocParser.appInfo.getAcademicTitle());
			System.out.println(appDocParser.appInfo.getAffliation());
			System.out.println(appDocParser.appInfo.getCity());
			System.out.println(appDocParser.appInfo.getCoAuthor());
			} 
		catch (Exception e) {
			e.printStackTrace();
		}
	}

}
