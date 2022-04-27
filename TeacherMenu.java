import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;

public class TeacherMenu {

    JTextField newAcc;
    JTextField quizName;
    JTextField number;

    /**
     * TeacherMenu
     * The main operations screen for the teacher menu. Sets up what the
     * main user interface will look like. Uses a run method and
     * FlowLayout menus. All possible actions have a menu of their own,
     * but most menus are not visible at first.
     *
     * @param teacher A teacher object containing information for this teacher
     * @param in      Contains File input methods.
     * @param out     Contains File output methods.
     * @param edit    Contains File editing methods.
     * @param //socket  Lets the user connect to the server (not currently in use)
     */
    public TeacherMenu(Teacher teacher, FileInput in, FileOutput out, FileEdit edit) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                JFrame frame = new JFrame("Project 5 Teacher Menu");
                frame.setPreferredSize(new Dimension(500, 500));
                frame.setLayout(new FlowLayout());
                JPanel panel = new JPanel();
                JLabel label = new JLabel("Welcome, " + teacher.getUsername());
                JButton enterButton = new JButton("Enter");

                JFrame account = new JFrame("Project 5 Teacher Menu");
                account.setPreferredSize(new Dimension(500, 500));
                account.setLayout(new FlowLayout());
                JPanel pan = new JPanel();
                JLabel changeAcc = new JLabel("Enter your new password");
                newAcc = new JTextField(12);
                pan.add(changeAcc);
                pan.add(newAcc);


                account.add(pan);
                account.pack();
                account.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                account.setVisible(false);

                JFrame createQuiz = new JFrame("Project 5 Teacher Menu");
                createQuiz.setPreferredSize(new Dimension(500, 500));
                createQuiz.setLayout(new FlowLayout());
                JPanel pane = new JPanel();
                JLabel quiz = new JLabel("Enter the filename of the quiz");
                quizName = new JTextField(100);
                pane.add(quiz);
                pane.add(quizName);
                pane.add(enterButton);
                createQuiz.add(pane);
                createQuiz.pack();
                createQuiz.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                createQuiz.setVisible(false);

                JFrame editQuiz = new JFrame("Project 5 Teacher Menu");
                editQuiz.setPreferredSize(new Dimension(500, 500));
                editQuiz.setLayout(new FlowLayout());
                JPanel window = new JPanel();
                JLabel num = new JLabel("Enter the number of the quiz you'd like to edit");
                number = new JTextField(12);
                window.add(num);
                window.add(number);
                window.add(enterButton);
                editQuiz.add(window);
                editQuiz.pack();
                editQuiz.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                editQuiz.setVisible(false);


                JComboBox<String> comboBox = new JComboBox<>();
                comboBox.addItem("Edit account");
                comboBox.addItem("Create quiz");
                comboBox.addItem("Edit quiz");
                comboBox.addItem("Existing quizzes");
                comboBox.addItem("Delete quiz");
                comboBox.addItem("Log out");
                comboBox.addItemListener(listener -> {
                    String choice;
                    JComboBox getSelection = (JComboBox) listener.getSource();
                    choice = (String) getSelection.getSelectedItem();
                    if (choice.equals("Edit account")) {
                        editPwd(edit, out, teacher, account, frame);
                    }
                    if (choice.equals("Create quiz")) {
                        createQuiz(out, createQuiz, frame);
                    }
                    if (choice.equals("Edit quiz")) {
                        editQuiz(out, editQuiz, frame);
                    }
                    if (choice.equals("View students")) {
                        viewSubmissions();
                    }
                    if (choice.equals("Delete quiz")) {
                        deletQuiz();
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
     * A simple logout method that shows the user a message and exits.
     *
     * @author Chingyan Huang, L17
     */
    public void exit() {
        JOptionPane.showMessageDialog(null, "Goodbye", "Teacher menu", JOptionPane.PLAIN_MESSAGE);
        return;
    }

    /**
     * EditPwd
     * Allows a user to edit their password. Their information
     * on the server/file is updated accordingly.
     */
    public void editPwd(FileEdit edit, FileOutput out, Teacher teacher, JFrame account, JFrame frame) {
        String newPwd = "";
        frame.setVisible(false);
        newPwd = String.valueOf(newAcc.getText());
        edit.removeUser(teacher.getName(), teacher.getUsername(), teacher.getPassword(), teacher.getUsername() + ".txt", "TeacherData.txt");
        try {
            Teacher newInfo = new Teacher(teacher.getName(), teacher.getUsername(), newPwd);
            out.writeTeacher(newInfo);
            JOptionPane.showMessageDialog(null, "Success, please log back in");
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error", "Teacher menu", JOptionPane.ERROR_MESSAGE);
        }

    }

    /**
     * CreateQuiz
     * <p>
     * A teacher can add a new quiz to a corresponding course.
     */
    public void createQuiz(FileOutput out, JFrame createQuiz, JFrame frame) {
        frame.setVisible(false);
        createQuiz.setVisible(true);


    }

    /**
     * EditQuiz
     * <p>
     * A teacher can edit the questions/answers in a
     * particular quiz.
     */
    public void editQuiz(FileOutput out, JFrame editQuiz, JFrame frame) {
        frame.setVisible(false);
        editQuiz.setVisible(true);
    }


    /**
     * ViewSubmissions
     * <p>
     * A teacher can view the (auto-graded) student submissions
     * for a particular quiz.
     */
    public void viewSubmissions() {

    }


    /**
     * DeleteQuiz
     * <p>
     * A teacher can delete a quiz in a course, along with all
     * questions associated with that quiz.
     */
    public void deletQuiz() {

    }


}

