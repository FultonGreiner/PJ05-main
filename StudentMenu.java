import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.io.*;

/**
 * Student Menu (entire program)
 *
 * Shows all of the screens that a student
 * might see when logging into their
 * account. Has functions that lets a student
 * edit their password and take quizzes.
 *
 * @author Chingyan Huang, L17
 * @author Fulton Greiner, L17
 * @author Benn Sesante, L17
 * @author Akash Sridhar, L17
 * @version 5-2-2022
 *
 */

public class StudentMenu {
    JTextField newAcc;
    StudentQuiz sq;


    /**
     * Student Menu
     *
     * The main hub for the Student Menu. Uses a run method
     * and a FlowLayout, with different JPanels for each possible
     * scenario that a student might encounter (ie. the initial
     * student menu is a JPanel, the screen that they will see
     * when asking to edit their account is a separate JPanel, etc.).
     * JPanel that are not currently in use have their visibility
     * set to "false."
     * Uses JComboBoxes when a student needs to make a choice.
     *
     * @param student  The student currently using the system
     * @param in       Class containing all the File Input-related methods
     * @param out      Class containing all the File Output-related methods
     *
     * @author Chingyan Huang
     * @author Fulton Greiner
     * @author Benn Sesante
     * @author Akash Sridhar
     * @version 5-2-2022
     */

    public StudentMenu(Student student, FileInput in, FileOutput out, FileTools tools) throws IOException {

        ArrayList<String> courseContent = new ArrayList<>(readFile("courses.txt"));

        ArrayList<String> courseNames = new ArrayList<>();
        int counter = 1;
        for (String course : courseContent) {
            courseNames.add(counter + ": " + course.substring(0, course.indexOf(",")));
            //prints course list from the course master file
            //correct output w. example file
            //1: Computer Science
            //2: Mathematics
            counter++;
        }
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
                panel.add(label);


                //the edit account menu
                //all menus except the initial menu are initially invisible
                //contains all buttons for the edit account menu
                JPanel changePass = new JPanel();
                JLabel changeAcc = new JLabel("Enter your new password");
                newAcc = new JTextField(12);
                changePass.add(changeAcc);
                changePass.add(newAcc);
                JButton enterButton = new JButton("Enter");
                enterButton.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        editPwd(tools, out, student);
                        frame.setVisible(false);
                        frame.dispose();
                    }
                });
                changePass.add(enterButton);
                frame.add(changePass);

                frame.pack();
                changePass.setVisible(false);

                JPanel courses = new JPanel();
                //shows students their course options
                JLabel chooseCourse = new JLabel("Choose a course");
                JComboBox<String[]> courseList = new JComboBox<>();
                courses.add(chooseCourse);
                courses.add(courseList);
                frame.add(courses);
                frame.pack();
                courses.setVisible(false);


                //the menu that shows a student the quiz list
                JPanel showQuiz = new JPanel();
                JLabel chooseQuiz = new JLabel("Choose a quiz");
                showQuiz.add(chooseQuiz);
                JComboBox<String> quizzes = new JComboBox();
                frame.add(showQuiz);
                frame.pack();
                showQuiz.setVisible(false);

                //the menu where a student can add their quiz question answers
                JPanel menu = new JPanel();
                JLabel type = new JLabel("Would you like to enter your answers as a file or directly?");
                menu.add(type);
                frame.add(menu);
                frame.pack();
                menu.setVisible(false);

                //contains all choices for the initial menu
                //all choices have item listeners attached
                JComboBox<String> comboBox = new JComboBox<>();
                comboBox.addItem(" ");
                comboBox.addItem("Edit account");
                comboBox.addItem("View courses");
                comboBox.addItem("View graded submissions");
                comboBox.addItem("Log out");
                comboBox.addItemListener(listener -> {
                    String choice;
                    JComboBox getSelection = (JComboBox) listener.getSource();
                    choice = (String) getSelection.getSelectedItem();
                    if (choice.equals("Edit account")) {
                        panel.setVisible(false);
                        changePass.setVisible(true);
                    }
                    if (choice.equals("View courses")) {
                        //need method to add courses from an ArrayList to combo box
                        //incomplete
                        panel.setVisible(false);
                        JComboBox<String> newBox = addQuiz(courseNames, courseList);
                        newBox.addItemListener(listen -> {
                            String selectedCourse;
                            JComboBox getSel = (JComboBox) listen.getSource();
                            selectedCourse = (String) getSel.getSelectedItem();
                            int courseNo = getSel.getSelectedIndex() + 1;
                            ArrayList<String> quizList = new ArrayList<>();
                            try {
                                quizList = readFile("c" + courseNo + ".txt");
                            } catch (IOException e) {
                                JOptionPane.showMessageDialog(null,"File not found", "Student menu", JOptionPane.ERROR_MESSAGE);
                            }

                                JComboBox quizFinal = addQuiz(quizList, quizzes);
                                quizFinal.addItemListener(itemListener -> {
                                    String quizID;
                                    JComboBox getSelect = (JComboBox) itemListener.getSource();
                                    int num = getSelect.getSelectedIndex();
                                    quizID = "c" + courseNo + "q" + (num + 1);
                                    String[] quizArray = getQuizData(selectedCourse, courseNo);
                                    String quizData = quizArray[num]; //quizData is null, meaning quizArray is probably null
                                    String quizName = quizData.substring(0, quizData.indexOf(","));
                                    showQuiz.setVisible(false);
                                    JComboBox<String> enterAnswer = new JComboBox<>();
                                    enterAnswer.addItem("Enter as a file");
                                    enterAnswer.addItem("Enter directly");
                                    enterAnswer.addItemListener(itemL -> {
                                        String method;
                                        JComboBox get = (JComboBox) itemL.getSource();
                                        method = (String) get.getSelectedItem();
                                        if (method.equals("Enter as a file")) {
                                            undertaking(courseNames, (courseNo - 1), student, quizID, out);
                                        }
                                        if (method.equals("Enter directly")) {
                                            takeQuiz(quizName, student, quizID, getQuestions(quizName, quizID));
                                        }
                                    });
                                    menu.add(enterAnswer);
                                    menu.setVisible(true);
                                });
                                showQuiz.add(quizzes);
                                showQuiz.setVisible(true);
                        });
                        courses.setVisible(true);
                    }
                    if (choice.equals("View graded submissions")) {
                        seeScores(in, sq);
                        panel.setVisible(false);
                    }
                    if (choice.equals("Log out")) {
                        frame.setVisible(false);
                        exit();
                    }
                        });

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
     * Shows a goodbye message and exits the program.
     *
     */
    public void exit() {
        JOptionPane.showMessageDialog(null, "Goodbye", "Student menu", JOptionPane.PLAIN_MESSAGE);
        return;
    }

    /**
     * Get Questions
     *
     * Gives out all of the questions
     * contained in a particular quiz file.
     *
     * @param quizName the name of the chosen quiz
     * @param quizID the ID of the chosen quiz, eg c1q1
     * @return ArrayList<Question>  An ArrayList containing all of the questions
     * in the quiz.
     * @author Chingyan Huang
     *
     */
    public ArrayList<Question> getQuestions(String quizName, String quizID) {
        ArrayList<Question> questions = new ArrayList<>();
        try {
            Quiz quiz = readQuizFile(quizName, quizID, quizID + ".txt");
            questions = quiz.getQuestions();
        } catch (IOException e) {
            JOptionPane.showMessageDialog(null, "Error", "Student menu", JOptionPane.ERROR_MESSAGE);
        }
        return questions;
    }

    /**
     * Get QuizData
     *
     * Takes in the name of the selected course and the course
     * number and retrieves all the quizzes associated with that course.
     *
     * @param selectedCourse The name of the selected course
     * @param courseNo The course number of the chosen course
     * @author Chingyan Huang
     *
     */
    public String[] getQuizData(String selectedCourse, int courseNo) {

        ArrayList<String> quizList = new ArrayList<>();
        try {
            quizList = readFile("c" + courseNo + ".txt");
        } catch (IOException e) {
            JOptionPane.showMessageDialog(null, "Could not find file", "Student menu", JOptionPane.ERROR_MESSAGE);
        }
        String[] quizArray = new String[quizList.size()];
        int index = 0;
        for (String quiz : quizList) {
            quizArray[index] = (String.valueOf(index) + ": " + quiz.substring(0, quiz.indexOf(",")));
        }
        return quizArray;
    }


    /**
     * Edit Password
     *
     * The method that lets a student edit their password. Deletes
     * the existing student file and replaces it with a new one
     * using the old user info and the new password.
     *
     * @param tools  Contains all of the miscellaneous file changing methods
     * @param out    Contains all the file writing methods
     * @param student The info associated with this particular student.
     * @author Chingyan Huang
     *
     */
    public void editPwd(FileTools tools, FileOutput out, Student student) {
        String newPass = String.valueOf(newAcc.getText());
        tools.removeUser(student.getName(), student.getUsername(), student.getPassword(), student.getUsername() + ".txt", "StudentData.txt");
        try {
            Student newInfo = new Student(student.getName(), student.getUsername(), newPass);
            out.writeStudent(newInfo);
            JOptionPane.showMessageDialog(null, "Successfully changed password. Please log in again", "Student menu", JOptionPane.PLAIN_MESSAGE);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error", "Student menu", JOptionPane.ERROR_MESSAGE);
        }
    }

    /**
     * addQuiz
     *
     *
     * @param quizArray
     * @param quizzes
     * @return JComboBox  Populates a JComboBox with the contents of an arraylist
     *
     * @author Chingyan Huang
     */

    public JComboBox addQuiz(ArrayList<String> quizArray, JComboBox quizzes) {
        for (int i = 0; i < quizArray.size(); i++) {
            quizzes.addItem(quizArray.get(i));
        }
        return quizzes;
    }

    /**
     * SeeScores
     *
     * Students can see their graded quizzes.
     * 
     * @param in            Contains all filereading methods
     * @param studentQuiz   The student's quiz submission
     *
     */
    public void seeScores(FileInput in, StudentQuiz studentQuiz) {
        //check if a StudentQuiz exists, then write it out for the student to see
        try {
            if (studentQuiz != null) {
                in.readSubmission(studentQuiz.getFilename());
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "You have not made any submissions", "Student menu", JOptionPane.ERROR_MESSAGE);
        }
    }



    /**
     * Undertaking
     *
     * This method lets students take a quiz in their chosen course.
     *
     * @param courseNames   The arraylist of Strings containing all the names of the courses
     * @param courseNo      The course number of the chosen course
     * @param student       The info of this student
     * @param quizID        The ID number of this quiz
     * @param out           Contains all file writing methods
     * @author Chingyan Huang
     *
     */
    public void undertaking(ArrayList<String> courseNames, int courseNo, Student student, String quizID, FileOutput out) {
        String input = "";
        do {
            input = JOptionPane.showInputDialog(null, "Enter the name of the file (without .txt)", "Student menu", JOptionPane.QUESTION_MESSAGE);
        } while (input == null || input.contains(".txt"));
        StudentQuiz studentQuiz = new StudentQuiz(courseNames.get(courseNo - 1), student.getUsername(), quizID);
        out.writeSubmission(studentQuiz);
    }

    /**
     * takeQuiz
     *
     * Allows the students to take a quiz of their choice and
     * stores their submission in a studentQuiz object.
     *
     * @param quizName  The name of the quiz being taken
     * @param student   The student user's information
     * @param quizID    The ID of the quiz being taken (eg c1q1)
     * @param questions An arraylist of Questions containing all
     *                  the questions in each quiz.
     *
     * @author Chingyan Huang
     */
    public void takeQuiz(String quizName, Student student, String quizID, ArrayList<Question> questions) {
        boolean correct = false;
        sq = new StudentQuiz(quizName, student.getUsername(), quizID);
        for (Question q : questions) {
            String answer = "";
            do {
                answer = String.valueOf(JOptionPane.showInputDialog(null, q.printQuestion(), "Student menu", JOptionPane.QUESTION_MESSAGE));
            } while (answer == null);
            if (Integer.parseInt(answer) == q.getCorrectChoice()) {
                correct = true;
            } else {
                correct = false;
            }
            sq.addQuestion(q.getQuestionTitle(), correct);
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
                JOptionPane.showMessageDialog(null, "Could not create new file", "Student menu", JOptionPane.ERROR_MESSAGE);
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
            JOptionPane.showMessageDialog(null, "The file doesn't exist!", "Student menu", JOptionPane.ERROR_MESSAGE);
            return null;
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
     * @author Chingyan Huang
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


}
