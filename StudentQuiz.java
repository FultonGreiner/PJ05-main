import java.time.Instant;
import java.util.ArrayList;
import java.sql.Timestamp;

/**
 * Student Quiz
 *
 * This is the student's version of the quiz. Each quiz class contains all the questions
 * that the student worked on and if the student got the question right or not. Tt doesn't
 * contain the entire quiz/subject questions, just the ones that the student has answered.
 *
 * @author Charles Greiner, L17
 * @author Benn Sesante, L17
 * @author Chingyan Huang, L17
 * @author Akash Sridhar, L17
 *
 * @version 4-11-2022
 *
 */

public class StudentQuiz {
    private String subject;
    private ArrayList<StudentQuestion> questions;
    private String filename;

    public StudentQuiz(String subject,String user, String quizID) {
        this.subject = subject;
        this.questions = new ArrayList<>();
        filename = String.format("%s,%s.txt", user, quizID);
    }

    public String getFilename() {
        return this.filename;
    }

    public String getSubject() {
        return subject;
    }

    public StudentQuestion getQuestion(int i) {
        return questions.get(i);
    }

    public void addQuestion(String question, boolean answerRight) {
        questions.add(new StudentQuestion(question, answerRight));
    }

    //Used to save student progress into files
    public String fileWriteQuestions() {
        String toReturn = "";

        for (int i = 0; i < questions.size(); i++) {
            StudentQuestion question = questions.get(i);
            if (questions.get(i).gotAnswerRight()) {
                String bruh = "%d-%s\nCorrect\n";
                bruh = String.format(bruh, i + 1, question.getQuestion());
                toReturn += bruh;
            } else {
                String breh = "%d-%s\nIncorrect\n";
                breh = String.format(breh, i + 1, question.getQuestion());
                toReturn += breh;
            }
        }

        return toReturn;
    }

    //Returns the amount of questions correct over the total amount of questions
    public String amountCorrect() {
        int questionsCorrect = 0;

        for (StudentQuestion question : questions) {
            if (question.gotAnswerRight()) {
                questionsCorrect++;
            }
        }

        return String.format("%d/%d", questionsCorrect, questions.size());
    }

    //Returns a double of the percent grade that the student got for the quiz
    public double getGrade(){
        double questionsCorrect = 0;

        for (StudentQuestion question : questions) {
            if (question.gotAnswerRight()) {
                questionsCorrect++;
            }
        }

        double totalQuestions = questions.size();

        return questionsCorrect / totalQuestions * 100;
    }

    //Returns a double that returns how many points each question is worth
    public double getQuestionPoints() {
        double totalQuestions = questions.size();
        return 100 / totalQuestions;
    }

    /**
     * Creates the string that will be inserted to the file from this quiz
     * @return
     */
    public String toString() {
        String toReturn = "";

        Timestamp timestamp = Timestamp.from(Instant.now());
        toReturn += String.format("Quiz-%s\n", subject);
        toReturn += String.format("Time Taken-%s\n", timestamp);
        toReturn += String.format("Grade-%.2f%%\n", getGrade());
        toReturn += String.format("Points per question-%.2f\n", getQuestionPoints());
        toReturn += fileWriteQuestions();

        return toReturn;
    }
}
