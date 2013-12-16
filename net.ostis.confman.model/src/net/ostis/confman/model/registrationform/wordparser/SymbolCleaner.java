package net.ostis.confman.model.registrationform.wordparser;

public class SymbolCleaner {

    public SymbolCleaner() {

    }

    public String check(final String string) {

        final StringBuilder tempString = new StringBuilder(string);
        for (int index = 0; index < tempString.length(); ++index) {
            if (!(tempString.charAt(index) >= '!' && tempString.charAt(index) <= 'z')) {
                if (!(tempString.charAt(index) >= 'А' && tempString
                        .charAt(index) <= 'я')) {
                    if (!(tempString.charAt(index) == 'ё' || tempString
                            .charAt(index) == 'Ё')) {
                        tempString.setCharAt(index, ' ');
                    }
                }
            }
        }
        return tempString.toString();
    }
}
