import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.net.*;
import java.util.ArrayList;
import java.io.*; //need fileIO methods

public class TeacherMenu {
    Image image;

    JButton editPwdButton;
    JButton createCourseButton;
    JButton viewCourseButton;
    JButton deletCourseButton;
    JButton logOutButton;

    JButton createQuizButton;
    JButton editQuizButton;
    JButton viewSubmissionsButton;
    JButton viewQuizButton;
    JButton deletQuizButton;

    TeacherMenu teacherMenu;

    ActionListener a = new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            if (e.getSource() == editPwdButton) {
                teacherMenu.editPwd();
            }
            if (e.getSource() == createCourseButton) {
                teacherMenu.createCourse();
            }
            if (e.getSource() == viewCourseButton) {
                teacherMenu.viewCourse();
            }
            if (e.getSource() == deletCourseButton) {
                teacherMenu.deletCourse();
            }
            if (e.getSource() == createQuizButton) {
                teacherMenu.createQuiz();
            }
            if (e.getSource() == editQuizButton) {
                teacherMenu.editQuiz();
            }
            if (e.getSource() == viewSubmissionsButton) {
                teacherMenu.viewSubmissions();
            }
            if (e.getSource() == viewQuizButton) {
                teacherMenu.quizList();
            }
            if (e.getSource() == deletQuizButton) {
                teacherMenu.deletQuiz();
            }
            if (e.getSource() == logOutButton) {
                teacherMenu.logOut();
            }
        }
    };

    /**
     * LogOut
     * A simple logout method that shows the user a message and exits.
     *
     * @author Chingyan Huang, L17
     *
     */
    public void logOut() {
        JOptionPane.showMessageDialog(null, "Goodbye", "Teacher menu", JOptionPane.PLAIN_MESSAGE);
        return;
    }

    /**
     * EditPwd
     * Allows a user to edit their password. Their information
     * on the server/file is updated accordingly.
     *
     */
    public void editPwd() {

    }

    /**
     * CreateCourse
     *
     * Teachers can create a new course and
     * attach a file containing a quiz/quiz
     * questions for that course.
     *
     */
    public void createCourse() {

    }

    /**
     * ViewCourse
     *
     * Teachers can view existing courses and
     * see the list of quizzes in a particular course.
     *
     */
    public void viewCourse() {

    }

    /**
     * DeleteCourse
     *
     * Teachers can delete a course and all
     * quizzes associated with that course if they so choose.
     *
     */
    public void deletCourse() {

    }

    /**
     * CreateQuiz
     *
     * A teacher can add a new quiz to a corresponding course.
     *
     */
    public void createQuiz() {

    }

    /**
     * EditQuiz
     *
     * A teacher can edit the questions/answers in a
     * particular quiz.
     *
     */
    public void editQuiz() {

    }


    /**
     * ViewSubmissions
     *
     * A teacher can view the (auto-graded) student submissions
     * for a particular quiz.
     *
     */
    public void viewSubmissions() {

    }

    /**
     * QuizList
     *
     * A teacher can look at the current quiz list in a particular
     * course without editing anything.
     *
     */
    public void quizList() {

    }


    /**
     * DeleteQuiz
     *
     * A teacher can delete a quiz in a course, along with all
     * questions associated with that quiz.
     *
     */
    public void deletQuiz() {

    }

    /**
     * TeacherMenu
     * The main operations screen for the teacher menu. Sets up what the
     * main user interface will look like.
     *
     * @param teacher A teacher object containing information for this teacher
     * @param io    An io object that contains the FileIO methods
     * @param socket    Lets the user connect to the server
     */
    public TeacherMenu(Teacher teacher, FileIO io, Socket socket) {
        JFrame frame = new JFrame("Project 5 Teacher Menu");
        Container content = frame.getContentPane();
        content.setLayout(new BorderLayout());
        /*
        panel can be cleared for each new screen to show the new button menus
         */
        JPanel panel = new JPanel();
        //maybe we can add a JTextField saying "Welcome, *username*" like last time?
        panel.add(editPwdButton);
        panel.add(createCourseButton);
        panel.add(viewCourseButton);
        panel.add(deletCourseButton);
        panel.add(logOutButton);
        frame.setSize(600, 400);
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
        //empty the panel after a button is clicked and update the interface accordingly.

    }

}
