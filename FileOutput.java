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
            FileOutputStream fos = new FileOutputStream(f,false);
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
            FileOutputStream fos = new FileOutputStream(f,false);
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
            FileOutputStream fos = new FileOutputStream(f,false);
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
            FileOutputStream fos = new FileOutputStream(f,false);
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