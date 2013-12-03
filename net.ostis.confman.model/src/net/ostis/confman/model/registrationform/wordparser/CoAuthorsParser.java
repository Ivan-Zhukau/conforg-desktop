package net.ostis.confman.model.registrationform.wordparser;

import java.util.ArrayList;
import java.util.List;

import net.ostis.confman.model.registrationform.AuthorInformation;
import net.ostis.confman.model.registrationform.RegistrationForm;

public class CoAuthorsParser {

    private RegistrationForm registrationForm;

    private List<String>     allInformation;

    public CoAuthorsParser() {

    }

    public void parse(final String info, final List<String> allInformation,
            final RegistrationForm registrationForm) {

        this.allInformation = allInformation;
        this.registrationForm = registrationForm;
        parseCoAuthors(info);
    }

    private void parseCoAuthors(final String info) {

        final List<String> coAuthorsNames = new ArrayList<String>();
        String correctName = "";
        for (final String name : info.split(",")) {
            for (final String subName : name.split(" ")) {
                if (!"".equals(subName)) {
                    correctName += subName + " ";
                }
            }
            if (correctName != "") {
                coAuthorsNames.add(correctName.substring(0,
                        correctName.length() - 1));
            }
            correctName = "";
        }
        if (isRecordExist(this.allInformation
                .get(RegistrationFormConstant.SPEAKER))) {
            addSpeakerToAuthorInformation(coAuthorsNames);
        }
        addCoAuthorsInRegistrationForm(coAuthorsNames);
    }

    private void addSpeakerToAuthorInformation(final List<String> coAuthorsNames) {

        if (!isCoAuthorExist(coAuthorsNames)) {
            coAuthorsNames.clear();
            coAuthorsNames.add(this.allInformation
                    .get(RegistrationFormConstant.SPEAKER));
        } else {
            addSpeakerToAuthors(coAuthorsNames,
                    this.allInformation.get(RegistrationFormConstant.SPEAKER));
        }
    }

    private void addSpeakerToAuthors(final List<String> coAuthorsNames,
            final String speakerName) {

        final List<String> tempList = new ArrayList<String>();
        tempList.add(speakerName);
        for (final String name : coAuthorsNames) {
            tempList.add(name);
        }
        coAuthorsNames.clear();
        for (final String name : tempList) {
            coAuthorsNames.add(name);
        }
    }

    private boolean isCoAuthorExist(final List<String> coAuthorsNames) {

        if (coAuthorsNames.size() == 1) {
            return isRecordExist(coAuthorsNames.get(0));
        }
        return true;
    }

    private boolean isRecordExist(final String record) {

        for (final IncorrectAnswer answer : IncorrectAnswer.values()) {
            if (answer.getAnswer().equals(record)) {
                return false;
            }
        }
        return true;
    }

    private void addCoAuthorsInRegistrationForm(
            final List<String> coAuthorsNames) {

        for (final String name : coAuthorsNames) {
            final AuthorInformation authorInformation = new AuthorInformation();
            authorInformation.setIdAuthor(name);
            this.registrationForm.getAuthorsInformation()
                    .add(authorInformation);
        }
    }

}
