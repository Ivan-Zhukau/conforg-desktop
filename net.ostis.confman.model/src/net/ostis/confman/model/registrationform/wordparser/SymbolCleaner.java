package net.ostis.confman.model.registrationform.wordparser;

public class SymbolCleaner {

    public SymbolCleaner() {

    }

    public String check(final String string) {

        final StringBuilder tempString = new StringBuilder(string);
        char c;
        for (int index = 0; index < tempString.length(); ++index) {
            c = tempString.charAt(index);
            if (!(c >= 33 && c <= 122)) {
                if (!(c >= 1040 && c <= 1103)) {
                    if (!(c == 1105 || c == 1025)) {
                        tempString.setCharAt(index, (char) 32);
                    }
                }
            }
        }
        return tempString.toString();
    }
}
