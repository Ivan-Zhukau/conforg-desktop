package net.ostis.confman.model.registrationform.wordparser;

public class SymbolCleaner {

    public SymbolCleaner() {

    }

    public String check(final String string) {

        final StringBuilder tempString = new StringBuilder(string);
        for (int index = 0; index < tempString.length(); ++index) {
            if (!(tempString.charAt(index) >= 33 && tempString.charAt(index) <= 122)) {
                if (!(tempString.charAt(index) >= 1040 && tempString
                        .charAt(index) <= 1103)) {
                    if (!(tempString.charAt(index) == 1105 || tempString
                            .charAt(index) == 1025)) {
                        tempString.setCharAt(index, (char) 32);
                    }
                }
            }
        }
        return tempString.toString();
    }
}
