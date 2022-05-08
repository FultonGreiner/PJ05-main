import java.io.*;
/**
 * FileOutput
 *
 * This class provides all of the functionality for reading data from files.
 *
 * @author Akash Sridhar, L-17
 * @author Chingyan Huang, L-17
 * @author Charles Greiner, L-17
 * @author Benn Sesante, L-17
 *
 * @version May 1, 2022
 */
public class FileOutput {
    /**
     * Takes a Student object as an argument and writes it to a file.
     * @param  new student user
     * @author Charles Greiner
     */
    public void writeStudent(Student student) {
        try {
            // check if file exists
            File f = new File("StudentData.txt");
            if(!f.exists()) {
                // create new file if it does not exist
                f.createNewFile();
            }
            // open file stream
            FileOutputStream fos = new FileOutputStream(f,true);
            PrintWriter pw = new PrintWriter(fos);
            // write to file
            pw.println(student.toString());
            // close file stream
            pw.close();
        } catch (Throwable e) {
            e.printStackTrace();
        }
    }

    /**
     * Takes a Teacher object as an argument and writes it to a file.
     * @param  teacher
     * @author Charles Greiner
     */
    public void writeTeacher(Teacher teacher) {
        try {
            // check if file exists
            File f = new File("TeacherData.txt");
            if(!f.exists()) {
                // create new file if it does not exist
                f.createNewFile();
            }
            // open file stream
            FileOutputStream fos = new FileOutputStream(f,true);
            PrintWriter pw = new PrintWriter(fos);
            // write to file
            pw.println(teacher.toString());
            // close file stream
            pw.close();
        } catch (Throwable e) {
            e.printStackTrace();
        }
    }

    /**
     * Takes a Quiz object as an argument and writes it to a file.
     * @param quiz     quiz instance
     * @param filename name of file that will contain the quiz
     * @author         Charles Greiner
     */
    public void writeQuiz(Quiz quiz, String filename) {
        try {
            // check if file exists
            File f = new File(filename);
            if(!f.exists()) {
                // create new file if it does not exist
                f.createNewFile();
            }
            // open file stream
            FileOutputStream fos = new FileOutputStream(f);
            PrintWriter pw = new PrintWriter(fos);
            // write to file
            pw.println(quiz.toString());
            // close file stream
            pw.close();
        } catch (Throwable e) {
            e.printStackTrace();
        }
    }

    /**
     *  Writes a student's quiz data to a file. Saves each question, the
     *  student's solution, and whether their solution was correct or
     *  incorrect.
     * @param  studentQuiz student's quiz data
     * @author Charles Greiner
     */
    public void writeSubmission(StudentQuiz studentQuiz) {
        try {
            // check if file exists
            File f = new File(studentQuiz.getFilename());
            if(!f.exists()) {
                // create new file if it does not exist
                f.createNewFile();
            }
            // open file stream
            FileOutputStream fos = new FileOutputStream(f);
            PrintWriter pw = new PrintWriter(fos);
            // write to file
            pw.println(studentQuiz.toString());
            // close file stream
            pw.close();
        } catch (Throwable e) {
            e.printStackTrace();
        }
    }
}
