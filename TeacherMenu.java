import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.util.ArrayList;

/**
 * Teacher Menu (entire program)
 * 
 * Contains all of the menus that a teacher might see when logging
 * in. Contains methods that lets a teacher edit their account's
 * password, edit and create courses/quizzes for students, and
 * view student submissions.
 * 
 * @author Chingyan Huang, L17
 * @author Fulton Greiner, L17
 * @author Benn Sesante, L17
 * @author Akash Sridhar, L17
 * @version 5-2-2022
 * 
 */

public class TeacherMenu {

    JTextField newAcc;

    /**
     * TeacherMenu
     * The main operations screen for the teacher menu. Sets up what the
     * main user interface will look like. Uses a run method and
     * FlowLayout menus. All possible actions have a menu of their own,
     * but most menus are not visible at first.
     *
     * @param //socket  Lets the user connect to the server (not currently in use)
     * @param teacher A teacher object containing information for this teacher
     * @param in      Contains File input methods.
     * @param out     Contains File output methods.
     */
    public TeacherMenu(Teacher teacher, FileInput in, FileOutput out, FileTools tools) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                JFrame frame = new JFrame("Project 5 Teacher Menu");
                frame.setPreferredSize(new Dimension(500, 500));
                frame.setLayout(new FlowLayout());
                JPanel panel = new JPanel();
                JLabel label = new JLabel("Welcome, " + teacher.getUsername());

                JPanel changePwd = new JPanel();
                JLabel changeAcc = new JLabel("Enter your new password");
                newAcc = new JTextField(12);
                changePwd.add(changeAcc);
                changePwd.add(newAcc);
                JButton enterButton = new JButton("Enter");
                enterButton.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        editPwd(tools, out, teacher);
                    }
                });
                frame.add(changePwd);
                frame.add(enterButton);

                JComboBox<String> comboBox = new JComboBox<>();
                comboBox.addItem("Edit account");
                comboBox.addItem("Create course");
                comboBox.addItem("View students");
                comboBox.addItem("Log out");
                comboBox.addItemListener(listener -> {
                    String choice;
                    JComboBox getSelection = (JComboBox) listener.getSource();
                    choice = (String) getSelection.getSelectedItem();
                    if (choice.equals("Edit account")) {
                        panel.setVisible(false);
                        changePwd.setVisible(true);
                    }
                    if (choice.equals("Create course")) {
                        panel.setVisible(false);
                        if (!createCourse()) {
                            panel.setVisible(true);
                        }
                        choice = "";
                    }
                    if (choice.equals("View students")) {
                        panel.setVisible(false);
                        viewSubmissions();
                        choice = "";
                    }
                    if (choice.equals("Log out")) {
                        frame.setVisible(false);
                        frame.dispose();
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
     *
     * @param tools     Contains miscellaneous file editing methods
     * @param out       Contains all file writing methods
     * @param teacher   The info for this teacher
     * @author Chingyan Huang
     */
    public void editPwd(FileTools tools, FileOutput out, Teacher teacher) {
        String newPwd = "";
        newPwd = String.valueOf(newAcc.getText());
        tools.removeUser(teacher.getName(), teacher.getUsername(), teacher.getPassword(), teacher.getUsername() + ".txt", "TeacherData.txt");
        try {
            Teacher newInfo = new Teacher(teacher.getName(), teacher.getUsername(), newPwd);
            out.writeTeacher(newInfo);
            JOptionPane.showMessageDialog(null, "Success, please log back in", "Teacher menu", JOptionPane.PLAIN_MESSAGE);
            exit();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error", "Teacher menu", JOptionPane.ERROR_MESSAGE);
        }

    }

  /**
     * CreateCourse
     * <p>
     * A teacher can add a new course.
     * @return Returns a boolean that determines whether or not the program will continue
     *          (false if the user hits the cancel/close button)
     * @author Chingyan Huang
     */
    public boolean createCourse() {
        boolean continueOk = true;
        try {
            ArrayList<String> courseRaw = readFile("courses.txt");
            ArrayList<String> courseNames = new ArrayList<>();
            ArrayList<Course> courses = new ArrayList<>();
            ArrayList<Quiz> quizzes = new ArrayList<>();
            for (String s : courseRaw) {
                String courseTitle = s.substring(0, s.indexOf(","));
                courseNames.add(courseTitle);
                String courseId = s.substring(s.indexOf(",") + 1, s.indexOf("."));
                String courseFile = s.substring(s.indexOf(",") + 1);
                ArrayList<String> quizRaw = readFile(courseFile);
                for (String q : quizRaw) {
                    String quizTitle = q.substring(0, q.indexOf(","));
                    String quizId = q.substring(q.indexOf(",") + 1, q.indexOf("."));
                    String quizFile = q.substring(q.indexOf(",") + 1);
                    quizzes.add(readQuizFile(quizTitle, quizId, quizFile));
                }
                courses.add(new Course(courseTitle, courseId, quizzes));
            }
            JOptionPane.showMessageDialog(null, "Courses written", "Teacher menu", JOptionPane.INFORMATION_MESSAGE);

            String answer = "";
            do {
                answer = String.valueOf((JOptionPane.showConfirmDialog(null, "Would you like to add another course?", "Teacher menu", JOptionPane.YES_NO_OPTION)));
                if (answer != null) {
                    if (Integer.parseInt(answer) == JOptionPane.YES_OPTION) {
                        ArrayList<Quiz> courseQuizzes = new ArrayList<>();
                        String title = JOptionPane.showInputDialog(null, "Enter the course name", "Teacher menu", JOptionPane.QUESTION_MESSAGE);
                        String id = JOptionPane.showInputDialog(null, "What is the course's ID#?", "Teacher menu", JOptionPane.QUESTION_MESSAGE);
                        for (Course c : courses) {
                            if (id.equals(c.getCourseID())) {
                                courseQuizzes = c.getQuizzes();
                            } else {
                                JOptionPane.showMessageDialog(null, "Invalid course ID", "Teacher menu", JOptionPane.ERROR_MESSAGE);
                            }
                        }
                        String courseId = "c" + (courses.size() + 1);
                        Course addition = new Course(title, id, courseQuizzes);
                        courses.add(addition);
                        try {
                            writeCourseFile(courses, "courses.txt");
                            writeAllQuizzes(addition, id + ".txt");
                        } catch (FileNotFoundException e) {
                            JOptionPane.showMessageDialog(null, "Error writing new file", "Teacher menu", JOptionPane.ERROR_MESSAGE);
                        }
                    }
                } else {
                    continueOk = false;
                }
            } while (Integer.parseInt(answer) == JOptionPane.YES_OPTION);
            
            //prompt teacher to select a course, importing files from each course
        } catch (IOException e) {
            JOptionPane.showMessageDialog(null, "Unable to find file", "Teacher menu", JOptionPane.ERROR_MESSAGE);
            return false;
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error, please log in again.", "Teacher menu", JOptionPane.ERROR_MESSAGE);
            return false;
        }

        return continueOk;
    }

    /**
     * writeCourseFile
     *
     * RECYCLED FROM PROJECT 4
     *
     * Write courses to courses.txt from the ArrayList of courses
     * and format it to match parsing logic in teacherAccess method.
     *
     * @param courseArrayList ArrayList containing all courses in the system.
     * @param filename        always courses.txt since that is the master file of the database.
     *
     * @throws FileNotFoundException if courses.txt does not exist.
     *
     * @author Akash Sridhar
     */
    public void writeCourseFile(ArrayList<Course> courseArrayList, String filename)
            throws FileNotFoundException {

        try {
            FileOutputStream fos = new FileOutputStream(filename, false);
            PrintWriter pw = new PrintWriter(fos);

            for (Course course : courseArrayList) {
                String courseData = course.getCourseName() + "," + course.getCourseID() + ".txt";
                pw.println(courseData);
            }
            pw.close();
        } catch (IOException e) {
            JOptionPane.showMessageDialog(null, "Error writing new file", "Teacher menu", JOptionPane.ERROR_MESSAGE);
        }
    }


    /**
     * writeQuizFile
     *
     * RECYCLED FROM PROJECT 4
     *
     * Write the data of all quizzes in a course to the course file.
     *
     * @param openCourse the course in which the quizzes are located.
     * @param filename   the course file (named based on the unique course identifier).
     *
     * @throws FileNotFoundException if the course file does not exist.
     *
     * @author Akash Sridhar
     */
    public void writeAllQuizzes(Course openCourse, String filename)
            throws FileNotFoundException {

        try {
            FileOutputStream fos = new FileOutputStream(filename, false);
            PrintWriter pw = new PrintWriter(fos);

            for (Quiz quiz : openCourse.getQuizzes()) {
                String quizInfo = quiz.getQuizTitle() + "," + quiz.getQuizID() + ".txt";
                pw.println(quizInfo);
            }
            pw.close();
        } catch (IOException e) {
            JOptionPane.showMessageDialog(null, "Error writing new file", "Teacher menu", JOptionPane.ERROR_MESSAGE);
        }
    }

    /**
     * readQuizFile
     * <p>
     *     RECYCLED FROM PROJECT 4
     * reads in a quiz file (from the course file), parses it, and returns a quiz object.
     *
     * @param quizName       the name of the quiz that will be returned. (read in from the course file)
     * @param quizIdentifier the unique ID of the quiz to be returned. (read in from the course file)
     * @param filename       the file containing the quiz.

     * @return a Quiz with the specified name and identifier under a course.
     * @throws FileNotFoundException if the quiz file does not exist.
     * @author Akash Sridhar
     */
    public Quiz readQuizFile(String quizName, String quizIdentifier, String filename)
            throws FileNotFoundException {
        ArrayList<Question> questionArrayList = new ArrayList<>();
        File f = new File(filename);
        if (!f.exists()) {
            throw new FileNotFoundException();
        }
        try {
            FileReader fr = new FileReader(filename);
            BufferedReader bfr = new BufferedReader(fr);
            String line;
            String question = "";
            ArrayList<String> options = new ArrayList<>();
            int correct;
            bfr.readLine();
            int i = 0;
            while ((line = bfr.readLine()) != null) {
                int lineCount = i % 6;
                switch (lineCount) {
                    case 0 -> question = line;
                    case 1, 2, 3, 4 -> options.add(line);
                    case 5 -> {
                        correct = Integer.parseInt(line);
                        ArrayList<String> questionChoices = new ArrayList<>();
                        for (int j = 0; j < 4; j++) {
                            questionChoices.add(options.get(options.size() - 4 + j));
                        }
                        questionArrayList.add(new Question(question, questionChoices, correct));
                        options.clear();
                    }
                }
                i++;
            }

            return new Quiz(quizName, quizIdentifier, questionArrayList);
        } catch (IOException e) {
            JOptionPane.showMessageDialog(null, "The file doesn't exist", "Student menu", JOptionPane.ERROR_MESSAGE);
            return null;
        }
    }

    /**
     * readFile
     * <p>
     *     RECYCLED FROM PROJECT 4
     * reads a text file using a BufferedReader into an
     * ArrayList with each line as a String.
     *
     * @param filename the name of the file to be read in.
     * @return a String ArrayList containing each line of the file as an element.
     * @throws FileNotFoundException if the file to be read does not exist.
     * @author Akash Sridhar
     * @author Chingyan Huang
     */
    public ArrayList<String> readFile(String filename) throws FileNotFoundException {
        ArrayList<String> lines = new ArrayList<>();
        File f = new File(filename);
        if (!f.exists()) {
            try {
                f.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
                JOptionPane.showMessageDialog(null, "Could not create new file", "Teacher menu", JOptionPane.ERROR_MESSAGE);
            }
        }
        try {
            FileReader fr = new FileReader(filename);
            BufferedReader bfr = new BufferedReader(fr);
            String line = bfr.readLine();
            while (line != null) {
                lines.add(line);
                line = bfr.readLine();
            }
            bfr.close();
            return lines;
        } catch (IOException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "The file doesn't exist!", "Teacher menu", JOptionPane.ERROR_MESSAGE);
            return null;
        }
    }



 

    public void addQuiz(FileOutput out) {
        
    }


    /**
     * EditQuiz
     * <p>
     * A teacher can edit the questions/answers in a
     * particular quiz.
     */
    public void editQuiz(FileOutput out) {

    }

    public void deleteQuiz() {

    }


    /**
     * ViewSubmissions
     * <p>
     * A teacher can view the (auto-graded) student submissions
     * for a particular quiz.
     */
    public void viewSubmissions() {

    }


}

