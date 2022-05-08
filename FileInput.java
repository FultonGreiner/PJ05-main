// imports
import java.io.*;
import java.util.ArrayList;
/**
 * FileInput
 *
 * This class provides all of the functionality for writing data to files.
 *
 * @author Akash Sridhar, L-17
 * @author Chingyan Huang, L-17
 * @author Charles Greiner, L-17
 * @author Benn Sesante, L-17
 *
 * @version May 1, 2022
 */
public class FileInput {
    /**
     * Returns a Student object if the username and password match
     * an existing user. Returns null if it does not exist.
     * @param  username username of current user
     * @param  password password of current user
     * @return a Student instance containing the current user, returns null
     *         if the user does not exist
     * @author Charles Greiner
     */
    public static Student readStudent(String username, String password) {
        try {
            // check if file exists
            File f = new File("StudentData.txt");
            if(!f.exists()) {
                // create new file if it does not exist
                f.createNewFile();
            }
            // open file stream
            FileReader fr = new FileReader("StudentData.txt");
            BufferedReader bfr = new BufferedReader(fr);
            // declarations
            String line;                                        /* stores the current line */
            String lineArray[] = new String[4];                 /* stores each sequential line */
            // search for user
            while ((line = bfr.readLine()) != null) {
                lineArray = line.split(",");
                if ((lineArray[0].equals(username)) && (lineArray[1].equals(password))) {
                    try {
                        Student student = new Student(lineArray[2], lineArray[0], lineArray[1]);
                        bfr.close();
                        fr.close();
                        return student;
                    } catch (Exception e) {
                        e.printStackTrace();
                        bfr.close();
                        fr.close();
                        break;
                    }
                }
            }
            bfr.close();
            fr.close();
            return null;
        } catch (Throwable e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * Creates a new Teacher instance using the current users login
     * information.
     * @param username The current teacher's username
     * @param password The current teacher's password
     * @return         Teacher instance
     * @author         Charles Greiner
     */
    public Teacher readTeacher(String username, String password) {
        try {
            // check if file exists
            File f = new File("TeacherData.txt");
            if(!f.exists()) {
                // create new file if it does not exist
                f.createNewFile();
            }
            // open file stream
            FileReader fr = new FileReader("TeacherData.txt");
            BufferedReader bfr = new BufferedReader(fr);
            // search for user
            String line;                                        /* stores the current line */
            String lineArray[] = new String[4];                 /* stores each sequential line */
            while ((line = bfr.readLine()) != null) {
                lineArray = line.split(",");
                if ((lineArray[0].equals(username)) && (lineArray[1].equals(password))) {
                    return new Teacher(lineArray[2], lineArray[0], lineArray[1]);
                }
            }
            bfr.close();
            fr.close();
            return null;
        } catch (Throwable e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * Opens the given file and stores the title of the quiz in its own ArrayList. Then parses
     * the file for each question and stores it in its own ArrayList with the question first,
     * then the solutions,and then the correct solution once again. Should be implemented by
     * storing the first element of the ArrayList as quizTitle, removing it, and then saving the
     * ArrayList as questions.
     * @param filename name of quiz file
     * @return         2D ArrayList containing the title of the quiz and then each question in
     *                 its own ArrayList [question, answer, ..., answer, correct answer number]
     * @author         Charles Greiner
     */
    public ArrayList<String> readQuiz(String filename) {
        // initialize ArrayList
        ArrayList<String> quiz = new ArrayList<String>();
        try {
            // check if file exists
            File f = new File(filename);
            if(!f.exists()) {
                // create new file if it does not exist
                f.createNewFile();
            }
            // open file stream
            FileReader fr = new FileReader(filename);
            BufferedReader bfr = new BufferedReader(fr);
            // search for user
            String line;                                        /* stores the current line */
            String question = "";                               /* stores the current question */
            // store quiz title
            quiz.add(bfr.readLine() + "\n");
            int i = 0;
            while ((line = bfr.readLine()) != null) {
                i++;
                if ((i % 6) == 0) {
                    question = question + line + "\n";
                    quiz.add(question);
                    question = "";
                } else {
                    question = question + line + "\n";
                }
            }
            bfr.close();
            fr.close();
            return quiz;
        } catch (Throwable e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * Reads a quiz submission in from a file containig the student's
     * solution to each question.
     * @return a String containing the student's quiz solutions
     * @author Charles Greiner
     */
    public String readSubmission(String filename) throws FileNotFoundException {
        try {
            // check if file exists
            File f = new File(filename);
            // open file stream
            FileReader fr = new FileReader(filename);
            BufferedReader bfr = new BufferedReader(fr);
            // search for user
            String line;                                        /* stores the current line */
            String quiz = "";                                   /* stores the current question */
            while ((line = bfr.readLine()) != null) {
                quiz = quiz + line + "\n";
            }
            bfr.close();
            fr.close();
            return quiz;
        } catch (Throwable e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * Returns an ArrayList constaining the filenames of the user's quizzes.
     * @param filename current user's filename
     * @return         ArrayList containing the filenames of the user's quizzes
     * @author         Charles Greiner
     */
    public ArrayList<String> readUserData(String filename) {
        try {
            // check if file exists
            File f = new File(filename);
            if(!f.exists()) {
                // create new file if it does not exist
                f.createNewFile();
            }
            // open file stream
            FileReader fr = new FileReader(filename);
            BufferedReader bfr = new BufferedReader(fr);
            // search for user
            String line;                                        /* stores the current line */
            ArrayList<String> userData = new ArrayList<String>();
            while ((line = bfr.readLine()) != null) {
                userData.add(line);
            }
            bfr.close();
            fr.close();
            return userData;
        } catch (Throwable e) {
            e.printStackTrace();
            return null;
        }
    }
}
