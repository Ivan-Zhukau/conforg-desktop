package net.ostis.confman.model.registrationform.wordparser;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import net.ostis.confman.model.registrationform.RegistrationForm;

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
                    if (row.getTableCells().indexOf(cell) == RegistrationFormConstant.INFORMATION_COLUNM
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
        for (final String info : information) {
            infoAllocation(counter, info);
            counter++;
        }
    }

    private void infoAllocation(final int counter, final String info) {

        if (counter < RegistrationFormConstant.NUMBER_ARTICLE_ITEMS) {
            completeArticlePart(counter, info);
        } else {
            completeAuthorsPart(counter, info);
        }
    }

    private void completeArticlePart(final int counter, final String info) {

        switch (counter) {
            case RegistrationFormConstant.TITLE_ENTRY: {
                this.registrationForm.getArticleInformation().setTitleEntry(
                        info);
                break;
            }
            case RegistrationFormConstant.CO_AUTHOR: {
                new CoAuthorsParser().parse(info, this.allInformation,
                        this.registrationForm);
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

    private void completeAuthorsPart(final int index, final String info) {

        final int authorNumber = getAuthorNumber(index);
        final int convertIndex = spotIndex(index, authorNumber);

        if (authorNumber < this.registrationForm.getAuthorsInformation().size()) {

            switch (convertIndex) {
                case RegistrationFormConstant.SECOND_NAME: {
                    this.registrationForm.getAuthorsInformation()
                            .get(authorNumber).getPersonalInformation()
                            .setSecondName(info);
                    break;
                }
                case RegistrationFormConstant.FIRST_NAME: {
                    this.registrationForm.getAuthorsInformation()
                            .get(authorNumber).getPersonalInformation()
                            .setFirstName(info);
                    break;
                }
                case RegistrationFormConstant.THIRD_NAME: {
                    this.registrationForm.getAuthorsInformation()
                            .get(authorNumber).getPersonalInformation()
                            .setThirdName(info);
                    break;
                }
                case RegistrationFormConstant.ACADEMIC_DEGREE: {
                    this.registrationForm.getAuthorsInformation()
                            .get(authorNumber).getPersonalInformation()
                            .setAcademicDegree(info);
                    break;
                }
                case RegistrationFormConstant.ACADEMIC_TITLE: {
                    this.registrationForm.getAuthorsInformation()
                            .get(authorNumber).getPersonalInformation()
                            .setAcademicTitle(info);
                    break;
                }
                case RegistrationFormConstant.COUNTRY: {
                    this.registrationForm.getAuthorsInformation()
                            .get(authorNumber).getContactInformation()
                            .setCountry(info);
                    break;
                }
                case RegistrationFormConstant.CITY: {
                    this.registrationForm.getAuthorsInformation()
                            .get(authorNumber).getContactInformation()
                            .setCity(info);
                    break;
                }
                case RegistrationFormConstant.EMAIL: {
                    this.registrationForm.getAuthorsInformation()
                            .get(authorNumber).getContactInformation()
                            .seteMail(info);
                    break;
                }
                case RegistrationFormConstant.CONTACT_PHONE_NUMBER: {
                    this.registrationForm.getAuthorsInformation()
                            .get(authorNumber).getContactInformation()
                            .setContactPhoneNumber(info);
                    break;
                }
                case RegistrationFormConstant.AFFLIATION: {
                    this.registrationForm.getAuthorsInformation()
                            .get(authorNumber).getWorkPlaceInformation()
                            .setAffliation(info);
                    break;
                }
                case RegistrationFormConstant.POSITION: {
                    this.registrationForm.getAuthorsInformation()
                            .get(authorNumber).getWorkPlaceInformation()
                            .setPosition(info);
                    break;
                }
                default:
                    break;
            }
        }
    }

    private int spotIndex(final int counter, final int authorNumber) {

        return counter
                - (authorNumber * RegistrationFormConstant.NUMBER_AUTORS_ITEMS);
    }

    private int getAuthorNumber(final int counter) {

        return (counter - RegistrationFormConstant.NUMBER_ARTICLE_ITEMS)
                / RegistrationFormConstant.NUMBER_AUTORS_ITEMS;
    }
}