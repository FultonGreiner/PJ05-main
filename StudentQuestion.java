/**
 * Student Question
 *
 * An object that will contain information about the student's response to a question
 *
 * @author Charles Greiner, L17
 * @author Benn Sesante, L17
 * @author Chingyan Huang, L17
 * @author Akash Sridhar, L17
 *
 * @version 4-11-2022
 *
 */

public class StudentQuestion {
    private String question;
    private boolean answerRight;

    public StudentQuestion(String question, boolean answerRight) {
        this.question = question;
        this.answerRight = answerRight;
    }

    public String getQuestion() {
        return question;
    }

    public boolean gotAnswerRight() {
        return answerRight;
    }
}
