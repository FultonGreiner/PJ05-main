import javax.swing.*;
import java.net.*;
import java.io.*;

/**
 * Project 5 Menu
 *
 * This portion of the program contains the initial login screens
 * that a user can see. Can let a user sign in as a student or a teacher
 * or have them create a whole new account. Functionalities differ
 * between user types.
 *
 * @author Chingyan Huang
 * @author Charles Greiner
 * @author Akash Sridhar
 * @author Benn Sesante
 * @version 5-2-2022
 */

public class Menu {


    public static void main(String[] args) {
        try {
            Menu menu = new Menu();

            String answer = "";
            JOptionPane.showMessageDialog(null, "Welcome", "Main menu", JOptionPane.INFORMATION_MESSAGE);


            String[] initialMenu = {"Login", "Create new account", "Exit"};
            answer = (String) JOptionPane.showInputDialog(null, "Choose one", "Main menu", JOptionPane.PLAIN_MESSAGE, null, initialMenu, null);

            if (answer == String.valueOf(JOptionPane.CANCEL_OPTION) || answer == String.valueOf(JOptionPane.CLOSED_OPTION)) {
                JOptionPane.showMessageDialog(null, "Goodbye! We hope to see you again.", "Main menu", JOptionPane.PLAIN_MESSAGE);
                JOptionPane.showMessageDialog(null, "Goodbye! We hope to see you again.", "Main menu", JOptionPane.PLAIN_MESSAGE);
                return;

            }
            if (answer.equals("Login")) {
                menu.login();
            } else if (answer.equals("Create new account")) {
                menu.create();
            } else if (answer.equals("Exit")) {
                JOptionPane.showMessageDialog(null, "Goodbye! We hope to see you again.", "Main menu", JOptionPane.PLAIN_MESSAGE);
                return;
            }


        } catch (NullPointerException e) {
            JOptionPane.showMessageDialog(null, "Goodbye! We hope to see you again.", "Main menu", JOptionPane.PLAIN_MESSAGE);
            return;
        }
    }

    /**
     * Login
     * <p>
     * The user inputs a username and a password and the program checks if the
     * inputs match known records. If the user chooses the cancel or close buttons
     * (which both return null), the program closes.
     *
     * @author Chingyan Huang
     */

    public void login() {
        try {
            String username = "";
            String password = "";
            boolean userFound = false;

            do {
                username = JOptionPane.showInputDialog(null, "Enter your username", "Main menu", JOptionPane.QUESTION_MESSAGE);
                if (username == null) {
                    JOptionPane.showMessageDialog(null, "Exiting...", "Main menu", JOptionPane.PLAIN_MESSAGE);
                    return;
                }

                password = JOptionPane.showInputDialog(null, "Enter your password", "Main menu", JOptionPane.QUESTION_MESSAGE);
                if (password == null) {
                    JOptionPane.showMessageDialog(null, "Exiting...", "Main menu", JOptionPane.PLAIN_MESSAGE);
                    return;
                }
                FileInput in = new FileInput();
                FileTools tools = new FileTools();
                if (in.readStudent(username, password) != null) {
                    userFound = true;
                    Student student = in.readStudent(username, password);
                    FileOutput out = new FileOutput();
                    StudentMenu studentMenu = new StudentMenu(student, in, out, tools);
                    JOptionPane.showMessageDialog(null, "Login successful!", "Main menu", JOptionPane.PLAIN_MESSAGE);
                } else if (in.readTeacher(username, password) != null) {
                    userFound = true;
                    Teacher teacher = in.readTeacher(username, password);
                    FileOutput out = new FileOutput();
                    TeacherMenu teacherMenu = new TeacherMenu(teacher, in, out, tools);
                    JOptionPane.showMessageDialog(null, "Login successful!", "Main menu", JOptionPane.PLAIN_MESSAGE);
                } else if (!tools.checkPassword(username, password, "TeacherData.txt") || !tools.checkPassword(username, password, "StudentData.txt")) {
                    throw new IncorrectPasswordException();
                } else {
                    JOptionPane.showMessageDialog(null, "Unable to find user.", "Main menu", JOptionPane.ERROR_MESSAGE);
                }
            } while (!userFound);
        } catch (IncorrectPasswordException e) {
            JOptionPane.showMessageDialog(null, "Invalid password", "Main menu", JOptionPane.ERROR_MESSAGE);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error", "Main menu", JOptionPane.ERROR_MESSAGE);
            return;
        }
    }

    /**
     * Create
     * A user chooses if they are a student or teacher, enters their information, and creates
     * an account if the username has not already been taken.
     * <p>
     * note: showInputDialog does not distinguish between the cancel/close buttons. Both
     * are considered null values. Therefore, if the user chooses either of these two options,
     * they just exit the program.
     *
     * @author Chingyan Huang
     */
    public void create() {
        try {
            String[] type = {"student", "teacher"};
            String user = "";
            String username = "";
            String password = "";
            String name = "";

            user = (String) JOptionPane.showInputDialog(null, "Are you a teacher or a student?", "Main menu", JOptionPane.PLAIN_MESSAGE, null, type, null);
            if (user == null) {
                JOptionPane.showMessageDialog(null, "Exiting...", "Main menu", JOptionPane.PLAIN_MESSAGE);
                return;
            }


            name = JOptionPane.showInputDialog(null, "What is your name?", "Main menu", JOptionPane.QUESTION_MESSAGE);
            if (name == null) {
                JOptionPane.showMessageDialog(null, "Exiting...", "Main menu", JOptionPane.PLAIN_MESSAGE);
                return;
            }

            FileTools tools = new FileTools();
            do {
                username = JOptionPane.showInputDialog(null, "Enter a username", "Main menu", JOptionPane.QUESTION_MESSAGE);
                if (username == null) {
                    JOptionPane.showMessageDialog(null, "Exiting...", "Main menu", JOptionPane.PLAIN_MESSAGE);
                    return;
                }
                if (tools.checkUsername(username, username + ".txt")) {
                    JOptionPane.showMessageDialog(null, "That username has been taken", "Main menu", JOptionPane.ERROR_MESSAGE);
                }
            } while (tools.checkUsername(username, username + ".txt"));

            password = JOptionPane.showInputDialog(null, "Enter a password", "Main menu", JOptionPane.QUESTION_MESSAGE);
            if (password == null) {
                JOptionPane.showMessageDialog(null, "Exiting...", "Main menu", JOptionPane.PLAIN_MESSAGE);
                return;
            }
            FileOutput out = new FileOutput();
            if (user.equals("student")) {
                Student student = new Student(name, username, password);
                out.writeStudent(student);
            } else if (user.equals("teacher")) {
                Teacher teacher = new Teacher(name, username, password);
                out.writeTeacher(teacher);
            }
            JOptionPane.showMessageDialog(null, "Success! Please log back in", "Main menu", JOptionPane.PLAIN_MESSAGE);

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error, please reconnect.", "Main menu", JOptionPane.ERROR_MESSAGE);
            return;
        }
    }
}
