import javax.swing.*;
import java.awt.*;
import java.net.*;
import java.util.ArrayList;
import java.io.*;

public class StudentMenu {
    JTextField newAcc;

    /**
     * Student Menu
     *
     * The main hub for the Student Menu. Uses a run method
     * and a FlowLayout, with different JFrames for each possible
     * scenario that a student might encounter (ie. the initial
     * student menu is a JFrame, the screen that they will see
     * when asking to edit their account is a separate JFrame, etc.).
     * JFrames that are not currently in use have their visibility
     * set to "false."
     * Uses JComboBoxes when a student needs to make a choice.
     *
     * @param student  The student currently using the system
     * @param in       Class containing all the File Input-related methods
     * @param out      Class containing all the File Output-related methods
     * @param edit     Class containing all File editing methods
     *
     * @author Chingyan Huang
     */

    public StudentMenu(Student student, FileInput in, FileOutput out, FileEdit edit) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                //the initial menu
                JFrame frame = new JFrame("Project 5 Student Menu");
                frame.setPreferredSize(new Dimension(500, 500));
                frame.setLayout(new FlowLayout());
                //contains all buttons for the initial menu
                JPanel panel = new JPanel();
                JLabel label = new JLabel("Welcome, " + student.getUsername());


                //the edit account menu
                //all menus except the initial menu are initially invisible
                JFrame account = new JFrame("Project 5 Student Menu");
                account.setPreferredSize(new Dimension(500, 500));
                account.setLayout(new FlowLayout());
                //contains all buttons for the edit account menu
                JPanel pan = new JPanel();
                JLabel changeAcc = new JLabel("Enter your new password");
                newAcc = new JTextField(12);
                pan.add(changeAcc);
                pan.add(newAcc);
                account.add(pan);
                account.pack();
                account.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                account.setVisible(false);

                //the menu that shows a student the quiz list
                JFrame quizInitialMenu = new JFrame("Project 5 Student Menu");
                quizInitialMenu.setPreferredSize(new Dimension(500, 500));
                quizInitialMenu.setLayout(new FlowLayout());
                JPanel pane = new JPanel();
                JLabel quiz = new JLabel("Choose a quiz");
                pane.add(quiz);
                quizInitialMenu.add(pane);
                quizInitialMenu.pack();
                quizInitialMenu.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                quizInitialMenu.setVisible(false);

                //the menu where a student can add their quiz question answers
                JFrame takeQuiz = new JFrame("Project 5 Student Menu");
                takeQuiz.setPreferredSize(new Dimension(500, 500));
                takeQuiz.setLayout(new FlowLayout());
                JPanel menu = new JPanel();
                JLabel type = new JLabel("Would you like to enter your answers as a file or directly?");
                menu.add(type);
                takeQuiz.add(menu);
                quizInitialMenu.pack();
                quizInitialMenu.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                takeQuiz.setVisible(false);


                //contains all choices for the initial menu
                //all choices have item listeners attached
                JComboBox<String> comboBox = new JComboBox<>();
                comboBox.addItem("Edit account");
                comboBox.addItem("View quizzes");
                comboBox.addItem("Log out");
                comboBox.addItemListener(listener -> {
                    String choice;
                    JComboBox getSelection = (JComboBox) listener.getSource();
                    choice = (String) getSelection.getSelectedItem();
                    if (choice.equals("Edit account")) {
                        editPwd(edit, out, student, account, frame);
                    }
                    if (choice.equals("View quizzes")) {
                        chooseQuiz(in, student, quizInitialMenu, takeQuiz, frame, comboBox);
                    }
                    if (choice.equals("Log out")) {
                        exit();
                    }
                        });

                panel.add(label);
                panel.add(comboBox);
                frame.add(panel);
                frame.pack();
                frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                frame.setVisible(true);
            }


        });
    }

    /**
     * Exit
     * 
     * A simple method that lets the student sign out.
     * 
     */
    public void exit() {
        JOptionPane.showMessageDialog(null, "Goodbye", "Student menu", JOptionPane.PLAIN_MESSAGE);
        return;
    }

    /**
     * Edit Password
     * 
     * The method that lets a student edit their password. Deletes
     * the existing student file and replaces it with a new one
     * using the old user info and the new password.
     * 
     * @param edit      Contains all File edit methods
     * @param out       Contains all File output methods
     * @param student   The info of this student
     * @param account   The account menu, initially invisible.
     * @param frame     The main menu, initially visible.
     */
    public void editPwd(FileEdit edit, FileOutput out, Student student, JFrame account, JFrame frame) {
        frame.setVisible(false);
        account.setVisible(true);
        String newPass = String.valueOf(newAcc.getText());
        edit.removeUser(student.getName(), student.getUsername(), student.getPassword(), student.getUsername() + ".txt", "StudentData.txt");
        try {
            Student newInfo = new Student(student.getName(), student.getUsername(), newPass);
            out.writeStudent(newInfo);
            JOptionPane.showMessageDialog(null, "Successfully changed password. Please log in again", "Student menu", JOptionPane.PLAIN_MESSAGE);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error", "Student menu", JOptionPane.ERROR_MESSAGE);
        }
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

    /**
     * Choose Quiz
     *
     * A student chooses the quiz that they want.
     *
     */
    public void chooseQuiz(FileInput in, Student student, JFrame quizInitialMenu, JFrame takeQuiz, JFrame frame, JComboBox comboBox) {
        frame.setVisible(false);
        //incomplete

    }


}
