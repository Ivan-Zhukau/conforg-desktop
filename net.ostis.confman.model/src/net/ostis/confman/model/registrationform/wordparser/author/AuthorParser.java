package net.ostis.confman.model.registrationform.wordparser.author;

import java.util.List;

import net.ostis.confman.model.registrationform.AuthorInformation;
import net.ostis.confman.model.registrationform.RegistrationForm;
import net.ostis.confman.model.registrationform.wordparser.RegistrationFormConstant;

public class AuthorParser {

    private AuthorInformation authorInformation;

    private RegistrationForm  form;

    public AuthorParser(final RegistrationForm form) {

        this.authorInformation = new AuthorInformation();
        this.form = form;
    }

    public void parse(final List<String> info) {

        int fieldIndex = 0;
        for (final String field : info) {
            parseFields(field, fieldIndex);
            ++fieldIndex;
        }
        this.form.getAuthorsInformation().add(this.authorInformation);
    }

    private void parseFields(final String info, final int fieldIndex) {

        switch (fieldIndex + RegistrationFormConstant.NUMBER_ARTICLE_ITEMS) {
            case RegistrationFormConstant.SECOND_NAME: {
                this.authorInformation.getPersonalInformation().setSecondName(
                        info);
                break;
            }
            case RegistrationFormConstant.FIRST_NAME: {
                this.authorInformation.getPersonalInformation().setFirstName(
                        info);
                break;
            }
            case RegistrationFormConstant.THIRD_NAME: {
                this.authorInformation.getPersonalInformation().setThirdName(
                        info);
                break;
            }
            case RegistrationFormConstant.ACADEMIC_DEGREE: {
                this.authorInformation.getPersonalInformation()
                        .setAcademicDegree(info);
                break;
            }
            case RegistrationFormConstant.ACADEMIC_TITLE: {
                this.authorInformation.getPersonalInformation()
                        .setAcademicTitle(info);
                break;
            }
            case RegistrationFormConstant.COUNTRY: {
                this.authorInformation.getContactInformation().setCountry(info);
                break;
            }
            case RegistrationFormConstant.CITY: {
                this.authorInformation.getContactInformation().setCity(info);
                break;
            }
            case RegistrationFormConstant.EMAIL: {
                this.authorInformation.getContactInformation().seteMail(info);
                break;
            }
            case RegistrationFormConstant.CONTACT_PHONE_NUMBER: {
                this.authorInformation.getContactInformation()
                        .setContactPhoneNumber(info);
                break;
            }
            case RegistrationFormConstant.AFFLIATION: {
                this.authorInformation.getWorkPlaceInformation().setAffliation(
                        info);
                break;
            }
            case RegistrationFormConstant.POSITION: {
                this.authorInformation.getWorkPlaceInformation().setPosition(
                        info);
                break;
            }
            default:
                break;
        }
    }

}
