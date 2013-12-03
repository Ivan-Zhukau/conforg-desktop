package net.ostis.confman.model.registrationform.wordparser;

public enum IncorrectAnswer {
    INCORRECT_ANSWER1("���"),
    INCORRECT_ANSWER2("-"),
    INCORRECT_ANSWER3("���/no"),
    INCORRECT_ANSWER4("�����������"),
    INCORRECT_ANSWER5(""),
    INCORRECT_ANSWER6("no"),
    INCORRECT_ANSWER7("���"),
    INCORRECT_ANSWER8("No"),
    INCORRECT_ANSWER9("�����������"), ;

    private String incorrectAnswer;

    IncorrectAnswer(final String answer) {

        this.incorrectAnswer = answer;
    }

    public String getAnswer() {

        return this.incorrectAnswer;
    }
}
