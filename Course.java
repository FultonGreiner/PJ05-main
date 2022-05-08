import java.util.ArrayList;
import java.util.Scanner;

/**
 * Course
 *
 * This class is used as a constructor for courses.
 * It also contains methods that can be used to manipulate the data of the course.
 *
 * @author Akash Sridhar, L-17
 * @author Chingyan Huang, L-17
 * @author Charles Greiner, L-17
 * @author Benn Sesante, L-17
 *
 * @version April 11, 2022
 */

public class Course {
    private String courseName;
    private String courseID;
    private ArrayList<Quiz> quizzes;

    public Course(String courseName, String courseID, ArrayList<Quiz> quizzes) {
        this.courseName = courseName;
        this.courseID = courseID;
        this.quizzes = quizzes;
    }

    public String getCourseName() { return courseName; }
    public String getCourseID() { return courseID; }
    public ArrayList<Quiz> getQuizzes() { return quizzes; }

    public void setCourseName(String courseName) { this.courseName = courseName; }
    public void setQuizzes(ArrayList<Quiz> quizzes) { this.quizzes = quizzes; }

    /**
     * toString
     *
     * @return string containing the course details and the names of all quizzes in the course.
     *
     * @author Akash Sridhar
     */
    public String toString() {
        String result = "";
        result += courseName + " - " + courseID + "\n";
        int i = 0;
        for (Quiz quiz : quizzes) {
            i += 1;
            result +=  i + " - " + quiz.getQuizTitle() + "\n";
        }
        return result;
    }

    /**
     * editCourseTitle
     *
     * prompts teacher for the new name of the course and makes the change.
     *
     * @author Akash Sridhar
     */
    public void editCourseTitle() {
        System.out.println("Enter a new name for the course: ");
        String name = "";
        setCourseName(name);
    }

    /**
     * addQuiz
     *
     * Allows the teacher to create a new quiz in the course through the console.
     *
     * @param scanner Scanner object used to read the teacher's input
     *                and to pass as a field in the newly created quiz.
     *
     * @return the newly created Quiz object.
     *
     * @author Akash Sridhar
     */
    public Quiz addQuiz(Scanner scanner) {
        System.out.println("Enter a name for the new quiz: ");
        String title = scanner.nextLine();

        String quizIdentifier = courseID + "q" + String.valueOf(quizzes.size() + 1);

        ArrayList<Question> questionList = new ArrayList<>();
        String moreQuestions;
        do {
            System.out.println("Enter the question: ");
            String question = scanner.nextLine();
            ArrayList<String> options = new ArrayList<>();
            for (int i = 0; i < 4; i++) {
                System.out.println("Enter an option: ");
                options.add(scanner.nextLine());
            }
            System.out.println("Which option number is correct? (1-4)");
            int correctOption = scanner.nextInt();
            scanner.nextLine();

            questionList.add(new Question(question, options, correctOption));

            System.out.println("Do you want to add another question? (y/n)");
            moreQuestions = scanner.nextLine();
        } while (moreQuestions.equals("y"));

        System.out.println("Quiz successfully created");
        return (new Quiz(title, quizIdentifier, questionList));
    }


    /**
     * editQuiz
     *
     * Method used to edit an existing quiz in the course. Contains functionalities
     * to create, edit, and delete quizzes as well as change the quiz's name.
     *
     * @param quizNumber used to identify the quiz that the teacher chose to edit.
     * @param scanner    Scanner object used to read the teacher's edit choices.
     *
     * @author Akash Sridhar
     */
    public void editQuiz(int quizNumber, Scanner scanner) {
        Quiz selectedQuiz = quizzes.get(quizNumber);
        System.out.println(selectedQuiz.toString());

        System.out.println("1 - Add question\n2 - Edit question\n3 - Delete question\n4 - Change quiz name\n");
        int questionChoice = scanner.nextInt();
        scanner.nextLine();

        if (questionChoice == 1) {
            selectedQuiz.addQuestion();
        } else if (questionChoice == 2) {
            System.out.println(selectedQuiz.printQuestions());
            System.out.println("Which question would you like to edit: ");
            int questionNum = scanner.nextInt() - 1;
            scanner.nextLine();
            selectedQuiz.editQuestion(questionNum);
        } else if (questionChoice == 3) {
            System.out.println(selectedQuiz.printQuestions());
            System.out.println("Which question would you like to delete: ");
            int questionNum = scanner.nextInt() - 1;
            scanner.nextLine();
            selectedQuiz.getQuestions().remove(questionNum);
            System.out.println("The question was successfully deleted!");
        } else if (questionChoice == 4) {
            System.out.println("What would you like to change the quiz name to: ");
            String newTitle = scanner.nextLine();
            selectedQuiz.setQuizTitle(newTitle);
            System.out.println("The title was successfully changed!");
        }
    }


}
