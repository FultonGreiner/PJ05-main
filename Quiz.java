import java.util.ArrayList;

import javax.swing.*;

/**
 * Quiz
 *
 * This class is used as a constructor for quizzes.
 * It also contains methods that can be used to manipulate and view the data of the quiz.
 * Edited from the original Quiz class from Project 4. Some methods were kept the same
 * while others were adapted.
 *
 * @author Akash Sridhar, L-17
 * @author Chingyan Huang, L-17
 * @author Charles Greiner, L-17
 * @author Benn Sesante, L-17
 *
 * @version May 3, 2022
 */

public class Quiz {
    private String quizTitle;
    private final String quizID;
    private ArrayList<Question> questions;

    public Quiz(String quizTitle, String quizID, ArrayList<Question> questions) {
        this.quizTitle = quizTitle;
        this.quizID = quizID;
        this.questions = questions;
    }

    public String getQuizTitle() { return quizTitle; }
    public String getQuizID() { return quizID; }
    public ArrayList<Question> getQuestions() { return questions; }

    public void setQuizTitle(String quizTitle) { this.quizTitle = quizTitle; }
    public void setQuestions(ArrayList<Question> questions) { this.questions = questions; }


    /**
     * toString
     *
     * Originally used in Project 4
     * Returns the quiz in a specific format as a string.
     *
     * @return String containing the quiz title
     *         and the questions with their choices listed below.
     *
     * @author Akash Sridhar
     */
    public String toString() {
        String result = "";
        result += quizTitle + " - " + quizID + "\n";
        for (Question question : questions) {
            result += question.printQuestion();
        }
        return result;
    }

    /**
     * printQuestions
     *
     * Originally used in Project 4
     * Returns the quiz questions only in a specific format as a string.
     *
     * @return String containing only the questions in the quiz, without the options or answers.
     *
     * @author Akash Sridhar
     */
    public String printQuestions() {
        String result = "";
        int i = 0;
        for (Question question : questions) {
            i += 1;
            result += i + " - " + question.getQuestionTitle() + "\n";
        }
        return result;
    }

    /**
     * printQuiz
     *
     * Originally used in Project 4
     * Returns the quiz in full as a string.
     *
     * @return String containing the quiz title, questions, options, and correct answer.
     *
     * @author Akash Sridhar
     */
    public String printQuiz() {
        String result = "";
        result += quizTitle + "\n";
        for (Question question : questions) {
            result += question.fullQuestion();
        }
        return result;
    }

    /**
     * addQuestion
     *
     * Edited from the original method from PJ04
     * A teacher can add a question to the quiz through a JOptionPane.
     * New questions added will always be at the end of the quiz.
     *
     * @author Chingyan Huang
     */
    public void addQuestion() {
        String question = "";
        String option = "";
        ArrayList<String> options = new ArrayList<>();
        String correct = "";

        ArrayList<Question> questions = new ArrayList<>();
        int yesNo = 0;
        do {
            for (int i = 0; i < 4; i++) {
                do {
                    question = JOptionPane.showInputDialog(null, "Enter a question", "Quiz", JOptionPane.QUESTION_MESSAGE);
                } while (question == null);
                for (int j = 0; j < 4; j++) {
                    do {
                        option = JOptionPane.showInputDialog(null, "Enter an option", "Quiz", JOptionPane.QUESTION_MESSAGE);
                        if (option != null) {
                            options.add(option);
                        }
                    } while (option == null);

                }
                String[] numbers = {"1", "2", "3", "4"};
                do {
                    correct = String.valueOf(JOptionPane.showInputDialog(null, "Enter the number of the correct answer", "Quiz", JOptionPane.PLAIN_MESSAGE, null, numbers, null));
                } while (correct == null);
            }
            questions.add(new Question(question, options, Integer.parseInt(correct)));
            JOptionPane.showMessageDialog(null, "Question added", "Quiz", JOptionPane.PLAIN_MESSAGE);
            yesNo = JOptionPane.showConfirmDialog(null, "Would you like to add another question?");
        } while (yesNo == JOptionPane.YES_OPTION);
    }

    /**
     * editQuestion
     *
     * Allows a teacher to change a particular question of their
     * choice in their selected quiz.
     * @param questionNumber the question in the quiz to be edited.
     *
     *
     * @author Chingyan Huang
     */
    public void editQuestion(int questionNumber) {

        Question selectedQuestion = questions.get(questionNumber);

        String question = "";
        String option = "";
        ArrayList<String> options = new ArrayList<>();
        String correct = "";


        do {
            question = JOptionPane.showInputDialog(null, "Enter the question you want to edit", "Quiz", JOptionPane.QUESTION_MESSAGE);
        } while (question == null);
        for (int j = 0; j < 4; j++) {
            do {
                option = JOptionPane.showInputDialog(null, "Enter an option", "Quiz", JOptionPane.QUESTION_MESSAGE);
                if (option != null) {
                    options.add(option);
                }
            } while (option == null);
            selectedQuestion.setChoices(options);
        }
        String[] numbers = {"1", "2", "3", "4"};
        do {
            correct = String.valueOf(JOptionPane.showInputDialog(null, "Enter the number of the correct answer", "Quiz", JOptionPane.PLAIN_MESSAGE, null, numbers, null));
        } while (correct == null);
        selectedQuestion.setCorrectChoice(Integer.parseInt(correct));

        JOptionPane.showMessageDialog(null, "Question edited successfully", "Quiz", JOptionPane.PLAIN_MESSAGE);

    }

    public String getFilename() {
        String filename = quizID + ".txt";
        return filename;
    }
}
