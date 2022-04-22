import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.net.*;
import java.util.ArrayList;
import java.io.*;

public class StudentMenu {
    Image image;
    JButton editPwdButton;
    JButton selectCourse;

    JButton viewScores;
    JButton takeQuiz;
    JButton logOutButton;

    StudentMenu studentMenu;

    ActionListener a = new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            if (e.getSource() == editPwdButton) {
                studentMenu.editPwd();
            }
            if (e.getSource() == selectCourse) {
                studentMenu.chooseCourse();
            }
            if (e.getSource() == viewScores) {
                studentMenu.seeScores();
            }
            if (e.getSource() == takeQuiz) {
                studentMenu.undertaking();
            }
            if (e.getSource() == logOutButton) {
                studentMenu.exit();
            }
        }
    };

    public void exit() {
        JOptionPane.showMessageDialog(null, "Goodbye", "Teacher menu", JOptionPane.PLAIN_MESSAGE);
        return;
    }

    public void editPwd() {

    }

    /**
     * ChooseCourse
     *
     * A student chooses the course that they want. Within
     * that course, they can take any published quiz.
     *
     */
    public void chooseCourse() {

    }

    /**
     * SeeScores
     *
     * Students can see their graded quizzes.
     *
     */
    public void seeScores() {

    }

    /**
     * Undertaking
     *
     * This method lets students take a quiz in their chosen course.
     *
     */
    public void undertaking() {

    }

    public StudentMenu(Student student, FileIO io, Socket socket) {
        JFrame frame = new JFrame("Project 5 Student Menu");
        Container content = frame.getContentPane();
        content.setLayout(new BorderLayout());
        JPanel panel = new JPanel();
        panel.add(editPwdButton);
        panel.add(selectCourse);
        panel.add(logOutButton);

        frame.setSize(600, 400);
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }

}
