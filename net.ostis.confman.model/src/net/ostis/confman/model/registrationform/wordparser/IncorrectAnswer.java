package net.ostis.confman.model.registrationform.wordparser;

public enum IncorrectAnswer {
    INCORRECT_ANSWER1("нет"),
    INCORRECT_ANSWER2("-"),
    INCORRECT_ANSWER3("нет/no"),
    INCORRECT_ANSWER4("отсутствуют"),
    INCORRECT_ANSWER5(""),
    INCORRECT_ANSWER6("no"),
    INCORRECT_ANSWER7("Нет"),
    INCORRECT_ANSWER8("No"),
    INCORRECT_ANSWER9("Отсутствуют"), ;

    private String incorrectAnswer;

    IncorrectAnswer(final String answer) {

        this.incorrectAnswer = answer;
    }

    public String getAnswer() {

        return this.incorrectAnswer;
    }
}
