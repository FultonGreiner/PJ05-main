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
 *
 */

public class Menu {


    public static void main(String[] args) {
        try {

            boolean continueOk = true;
            Menu menu = new Menu();
            do {
                String answer = "";
                JOptionPane.showMessageDialog(null, "Welcome", "Main menu", JOptionPane.INFORMATION_MESSAGE);

                do {
                    String[] initialMenu = {"Login", "Create new account", "Exit"};
                    answer = (String) JOptionPane.showInputDialog(null, "Choose one", "Main menu", JOptionPane.PLAIN_MESSAGE, null, initialMenu, null);

                    if (answer.equals("Login")) {
                        continueOk = menu.login();
                        if (continueOk == false) {
                            JOptionPane.showMessageDialog(null, "Goodbye! We hope to see you again.", "Main menu", JOptionPane.PLAIN_MESSAGE);
                            return;
                        }
                    } else if (answer.equals("Create new account")) {
                        continueOk = menu.create();
                        if (continueOk == false) {
                            JOptionPane.showMessageDialog(null, "Goodbye! We hope to see you again.", "Main menu", JOptionPane.PLAIN_MESSAGE);
                            return;
                        }
                    } else if (answer.equals("Exit")) {
                        JOptionPane.showMessageDialog(null, "Goodbye! We hope to see you again.", "Main menu", JOptionPane.PLAIN_MESSAGE);
                        return;
                    }
                } while (answer == null);

            } while (continueOk == true);
        } catch (NullPointerException e) {
            JOptionPane.showMessageDialog(null, "Goodbye! We hope to see you again.", "Main menu", JOptionPane.PLAIN_MESSAGE);
            return;
        }
    }

    /**
     * Login
     *
     * The user inputs a username and a password and the program checks if the
     * inputs match known records.
     *
     * #NOTE: LARGELY UNTESTED. Both Login and Create have some bugs, but I haven't got
     * around to completely fixing them yet. Proceed with caution.
     *
     * @return  returns a boolean indicating whether a matching user has been found.
     */

    public boolean login() {
        try {
            boolean userFound = false;
            String username = "";
            String password = "";
            do {
                username = JOptionPane.showInputDialog(null, "Enter your username", "Main menu", JOptionPane.QUESTION_MESSAGE);
                if (username == String.valueOf(JOptionPane.CANCEL_OPTION)) {
                    userFound = false;
                    return userFound;
                }
                if (username == String.valueOf(JOptionPane.CLOSED_OPTION)) {
                    userFound = false;
                    return userFound;
                }
                if (username == null || username.equals("")) {
                    JOptionPane.showMessageDialog(null, "Username cannot be null!", "Main menu", JOptionPane.ERROR_MESSAGE);
                }
            } while (username == null || username.equals(""));
            do {
                password = JOptionPane.showInputDialog(null, "Enter your password", "Main menu", JOptionPane.QUESTION_MESSAGE);
                if (password == String.valueOf(JOptionPane.CANCEL_OPTION)) {
                    userFound = true;
                    return userFound;
                }
                if (username == String.valueOf(JOptionPane.CLOSED_OPTION)) {
                    userFound = false;
                    return userFound;
                }
                if (password == null || password.equals("")) {
                    JOptionPane.showMessageDialog(null, "Password cannot be null!", "Main menu", JOptionPane.ERROR_MESSAGE);
                }
            } while (password == null || password.equals(""));
            //add a check here to see if username and password match known records; if not, show error screen
            return userFound;

            //check student/teacher records!


        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error", "Main menu", JOptionPane.ERROR_MESSAGE);
            return false;
        }
    }

    /**
     * Create
     * A user chooses if they are a student or teacher, enters their information, and creates
     * an account if the username has not already been taken.
     *
     * @return  returns a boolean indicating whether or not the user wants to continue logging in
     * (basically whether or not they hit the cancel/x button)
     */
    public boolean create() {
        try {
            String[] type = {"student", "teacher"};
            boolean continueOk;
            String user = "";
            String username = "";
            String password = "";
            String name = "";
            do {
                user = (String) JOptionPane.showInputDialog(null, "Are you a teacher or a student?", "Main menu", JOptionPane.PLAIN_MESSAGE, null, type, null);
                if (user == String.valueOf(JOptionPane.CANCEL_OPTION)) {
                    continueOk = true;
                    return continueOk;
                }
                if (user == String.valueOf(JOptionPane.CLOSED_OPTION)) {
                    continueOk = false;
                    return continueOk;
                }
                if (user == null) {
                    JOptionPane.showMessageDialog(null, "Field cannot be null!", "Main menu", JOptionPane.ERROR_MESSAGE);
                }

            } while (user == null);
            do {
                name = JOptionPane.showInputDialog(null, "What is your name?", "Main menu", JOptionPane.QUESTION_MESSAGE);
                if (name == String.valueOf(JOptionPane.CANCEL_OPTION)) {
                    continueOk = true;
                    return continueOk;
                }
                if (name == String.valueOf(JOptionPane.CLOSED_OPTION)) {
                    continueOk = false;
                    return continueOk;
                }
                if (name == null) {
                    JOptionPane.showMessageDialog(null, "Field cannot be null!", "Main menu", JOptionPane.ERROR_MESSAGE);
                }
            } while (name == null);
            do {
                username = JOptionPane.showInputDialog(null, "Enter a username", "Main menu", JOptionPane.QUESTION_MESSAGE);
                if (username == String.valueOf(JOptionPane.CANCEL_OPTION)) {
                    continueOk = true;
                    return continueOk;
                }
                if (username == String.valueOf(JOptionPane.CLOSED_OPTION)) {
                    continueOk = false;
                    return continueOk;
                }
                if (username == null) {
                    JOptionPane.showMessageDialog(null, "Please input a username", "Main menu", JOptionPane.ERROR_MESSAGE);
                }

                //input check for username
            } while (username == null);
            do {
                password = JOptionPane.showInputDialog(null, "Enter a password", "Main menu", JOptionPane.QUESTION_MESSAGE);
                if (password == String.valueOf(JOptionPane.CANCEL_OPTION)) {
                    continueOk = true;
                    return continueOk;
                }
                if (password == String.valueOf(JOptionPane.CLOSED_OPTION)) {
                    continueOk = false;
                    return continueOk;
                }
                if (password == null) {
                    JOptionPane.showMessageDialog(null, "Please input a password", "Main menu", JOptionPane.ERROR_MESSAGE);
                }
                //check for password
            } while (password == null);
            return true;
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error, please reconnect.", "Main menu", JOptionPane.ERROR_MESSAGE);
            return false;
        }
    }

}
