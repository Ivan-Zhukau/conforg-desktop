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

    private List<String>     allInformation;

    public DocxRegistrationFormParser() {

        super();
        this.registrationForm = new RegistrationForm();
        this.allInformation = new ArrayList<String>();
    }

    public RegistrationForm parse(final FileInputStream inputStream) {

        try {
            final XWPFDocument document = new XWPFDocument(inputStream);
            final List<XWPFTable> tables = document.getTables();
            readTables(tables);
        } catch (final IOException e) {
            e.printStackTrace();
        }
        return this.registrationForm;
    }

    private void readTables(final List<XWPFTable> tables) {

        for (final XWPFTable table : tables) {
            for (final XWPFTableRow row : table.getRows()) {
                for (final XWPFTableCell cell : row.getTableCells()) {
                    if (row.getTableCells().indexOf(cell) == 1
                            && table.getRows().indexOf(row) != 0) {
                        this.allInformation.add(cell.getText());
                    }
                }
            }
        }
        parseAllInform(this.allInformation);
    }

    private void parseAllInform(final List<String> information) {

        int counter = 0;
        final AuthorInformation authorInformation = new AuthorInformation();
        for (final String info : information) {
            infoAllocation(counter, info, authorInformation);
            counter++;
        }
    }

    private void infoAllocation(final int counter, final String info,
            final AuthorInformation authorInformation) {

        if (counter < 5) {
            completeArticlePart(counter, info);
        } else {
            completeAuthorsPart(counter, info);
        }
    }

    private int spotIndex(final int counter, final int authorNumber) {

        return counter
                - (authorNumber * RegistrationFormConstant.NUMBER_AUTORS_ITEMS);
    }

    private void completeAuthorsPart(final int index, final String info) {

        final int authorNumber = getAuthorNumber(index);
        final int convertIndex = spotIndex(index, authorNumber);

        switch (convertIndex) {
            case RegistrationFormConstant.SECOND_NAME: {
                this.registrationForm.getAuthorsInformation().get(authorNumber)
                        .getPersonalInformation().setSecondName(info);
                break;
            }
            case RegistrationFormConstant.FIRST_NAME: {
                this.registrationForm.getAuthorsInformation().get(authorNumber)
                        .getPersonalInformation().setFirstName(info);
                break;
            }
            case RegistrationFormConstant.THIRD_NAME: {
                this.registrationForm.getAuthorsInformation().get(authorNumber)
                        .getPersonalInformation().setThirdName(info);
                break;
            }
            case RegistrationFormConstant.ACADEMIC_DEGREE: {
                this.registrationForm.getAuthorsInformation().get(authorNumber)
                        .getPersonalInformation().setAcademicDegree(info);
                break;
            }
            case RegistrationFormConstant.ACADEMIC_TITLE: {
                this.registrationForm.getAuthorsInformation().get(authorNumber)
                        .getPersonalInformation().setAcademicTitle(info);
                break;
            }
            case RegistrationFormConstant.COUNTRY: {
                this.registrationForm.getAuthorsInformation().get(authorNumber)
                        .getContactInformation().setCountry(info);
                break;
            }
            case RegistrationFormConstant.CITY: {
                this.registrationForm.getAuthorsInformation().get(authorNumber)
                        .getContactInformation().setCity(info);
                break;
            }
            case RegistrationFormConstant.EMAIL: {
                this.registrationForm.getAuthorsInformation().get(authorNumber)
                        .getContactInformation().seteMail(info);
                break;
            }
            case RegistrationFormConstant.CONTACT_PHONE_NUMBER: {
                this.registrationForm.getAuthorsInformation().get(authorNumber)
                        .getContactInformation().setContactPhoneNumber(info);
                break;
            }
            case RegistrationFormConstant.AFFLIATION: {
                this.registrationForm.getAuthorsInformation().get(authorNumber)
                        .getWorkPlaceInformation().setAffliation(info);
                break;
            }
            case RegistrationFormConstant.POSITION: {
                this.registrationForm.getAuthorsInformation().get(authorNumber)
                        .getWorkPlaceInformation().setPosition(info);
                break;
            }
            default:
                break;
        }

    }

    private int getAuthorNumber(final int counter) {

        return (counter - RegistrationFormConstant.NUMBER_ARTICLE_ITEMS)
                / RegistrationFormConstant.NUMBER_AUTORS_ITEMS;
    }

    private void completeArticlePart(final int counter, final String info) {

        switch (counter) {
            case RegistrationFormConstant.TITLE_ENTRY: {
                this.registrationForm.getArticleInformation().setTitleEntry(
                        info);
                break;
            }
            case RegistrationFormConstant.CO_AUTHOR: {
                parseCoAuthors(info);
                break;
            }
            case RegistrationFormConstant.PARTICIPATION_FORM: {
                this.registrationForm.getArticleInformation()
                        .setParticipationForm(info);
                break;
            }
            case RegistrationFormConstant.SPEAKER: {
                this.registrationForm.getArticleInformation().setSpeaker(info);
                break;
            }
            case RegistrationFormConstant.SHOW_LAUNCHING: {
                this.registrationForm.getArticleInformation().setShowLaunching(
                        info);
                break;
            }
            default:
                break;
        }
    }

    private void parseCoAuthors(final String info) {

        final List<String> NamesWithSpaces = selectCertainNames(info);
        final List<String> coAuthorsNames = deleteSpaces(NamesWithSpaces);
        addInResistrationForm(coAuthorsNames);
    }

    private void addInResistrationForm(final List<String> coAuthorsNames) {

        for (final String name : coAuthorsNames) {
            final AuthorInformation authorInformation = new AuthorInformation();
            authorInformation.setId_Author(name);
            this.registrationForm.getAuthorsInformation()
                    .add(authorInformation);
        }
    }

    private List<String> deleteSpaces(final List<String> namesWithSpaces) {

        final List<String> namesWithoutSpaces = new ArrayList<String>();
        int beginName = 0;
        int endName = 0;
        for (final String name : namesWithSpaces) {
            beginName = findBeginWord(beginName, name);
            endName = findEndWord(endName, name);
            namesWithoutSpaces.add(name.substring(beginName, endName));
        }
        return namesWithoutSpaces;
    }

    private int findEndWord(int endName, final String name) {

        for (int index = name.length(); index >= 0; index--) {
            if (!" ".equals(name.substring(index - 1, index))) {
                endName = index;
                break;
            }
        }
        return endName;
    }

    private int findBeginWord(int beginName, final String name) {

        for (int index = 0; index < name.length(); index++) {
            if (!" ".equals(name.substring(index, index + 1))) {
                beginName = index;
                break;
            }
        }
        return beginName;
    }

    private List<String> selectCertainNames(final String info) {

        final List<String> NamesWithSpaces = new ArrayList<String>();
        int beginWord = 0;
        for (int index = 0; index < info.length(); index++) {
            if (",".equals(info.substring(index, index + 1))) {
                NamesWithSpaces.add(info.substring(beginWord, index));
                beginWord = index + 1;
            }
            if (index + 1 == info.length()) {
                NamesWithSpaces.add(info.substring(beginWord, index + 1));
            }
        }
        return NamesWithSpaces;
    }
}
