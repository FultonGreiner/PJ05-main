import java.util.ArrayList;

/**
 * Student
 *
 * An object that stores the student's attempts at each quiz
 *
 * @author Charles Greiner, L17
 * @author Benn Sesante, L17
 * @author Chingyan Huang, L17
 * @author Akash Sridhar, L17
 *
 * @version 4-11-2022
 *
 */

public class Student {
    private String name;
    private String username;
    private String password;
    private ArrayList<StudentQuiz> quizzes;
    public String filename;
//    public String password;

    public Student(String name, String username, String password) throws Exception {
        this.name = name;
        this.username = username;
        this.password = password;
        this.filename = String.format("%s,%s,%s,%s.txt", username, password, name, username);
        quizzes = new ArrayList<StudentQuiz>();
    }

    public String toString() {
        return filename;
    }

    public void addStudentQuiz(String quizName, String quizID) {
        quizzes.add(new StudentQuiz(quizName,username,quizID));
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
        this.filename = String.format("%s,%s,%s,%s.txt", username, password, name, username);
    }

    /**
     * Compares every quiz in the StudentQuiz arraylist and returns the StudentQuiz with the same name as the
     * name in the parameter.
     * @param name represents the title of the Student Quiz that is being looked for
     * @return a student quiz with the name of the parameter
     * @throws InvalidQuizNameException if no quiz in the arraylist has the specified name in the parameter
     */
    public StudentQuiz getStudentQuiz(String name) throws InvalidQuizNameException {
        String tempName = name.toLowerCase();
        for (StudentQuiz quiz : quizzes) {
            String tempQuizName = quiz.getSubject().toLowerCase();
            if (tempName.equals(tempQuizName)) {
                return quiz;
            }
        }

        throw new InvalidQuizNameException("This quiz does not exist!");
    }

    /**
     *
     * @return the ArrayList of the quizzes that the student took during the duration
     */
    public ArrayList<StudentQuiz> getQuizzes() {
        return quizzes;
    }

    /**
     *
     * @param i represents the position in the arraylist, used for forloops
     * @return returns a string of the filename for a quiz
     */
    public String getStudentQuizFilename(int i) {
        return quizzes.get(i).getFilename();
    }

    /**
     *
     * @param i represents the position in the arrayist
     * @return a string that will be inserted into the quiz file
     */
    public String toWrite(int i) {
        return quizzes.get(i).toString();
    }

    public StudentQuiz getLatestQuiz() {
        return quizzes.get(quizzes.size() - 1);
    }

    public String getName() {
        return name;
    }

    public String getUsername() {
        return username;
    }
}
