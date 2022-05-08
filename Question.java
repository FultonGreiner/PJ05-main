import java.util.ArrayList;

/**
 * Question
 *
 * This class is used as a constructor for questions.
 * It also contains methods that can be used to manipulate and view the data of the question.
 *
 * @author Akash Sridhar, L-17
 * @author Chingyan Huang, L-17
 * @author Charles Greiner, L-17
 * @author Benn Sesante, L-17
 *
 * @version April 11, 2022
 */

public class Question {
    private String questionTitle;
    private ArrayList<String> choices;
    private int correctChoice;

    public Question(String questionTitle, ArrayList<String> choices, int correctChoice) {
        this.questionTitle = questionTitle;
        this.choices = choices;
        this.correctChoice = correctChoice;
    }

    public String getQuestionTitle() { return questionTitle; }
    public ArrayList<String> getChoices() { return choices; }
    public int getCorrectChoice() { return correctChoice; }

    public void setQuestionTitle(String questionTitle) { this.questionTitle = questionTitle; }
    public void setChoices(ArrayList<String> choices) { this.choices = choices; }
    public void setCorrectChoice(int correctChoice) { this.correctChoice = correctChoice; }

    /**
     * printQuestion
     *
     * Returns a string of the question without the answer visible.
     *
     * @return String with the question and options below, but without the correct answer visible.
     *
     * @author Akash Sridhar
     */
    public String printQuestion() {
        String fullQuestion = questionTitle + "\n";
        for (int i = 0; i < 4; i++) {
            fullQuestion += String.valueOf(i + 1) + ". " + choices.get(i) + "\n";
        }
        return fullQuestion;
    }

    /**
     * fullQuestion
     *
     * Returns a string of the entire question.
     *
     * @return String with the question, options, and correct answer listed below.
     *
     * @author Akash Sridhar
     */
    public String fullQuestion() {
        String result = "";
        result += questionTitle + "\n";
        for (String choice : choices) {
            result += choice + "\n";
        }
        result += correctChoice + "\n";
        return result;
    }
}
