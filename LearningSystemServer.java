import javax.swing.*;
import java.net.*;
import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

public class LearningSystemServer {

    static final int port = 7654;

    public static void main(String[] args) throws IOException {
        ServerSocket ss = new ServerSocket(port);

        while (true) {
            Socket cs = null;

            try {
                cs = ss.accept();
                System.out.println("New client connected: " + cs);

                DataInputStream incoming = new DataInputStream(cs.getInputStream());
                DataOutputStream outgoing = new DataOutputStream(cs.getOutputStream());

                Thread t = new ConnectionHandler(cs, incoming, outgoing);
                t.start();
            } catch (Exception e) {
                cs.close();
                e.printStackTrace();
            }
        }
    }
}
 class ConnectionHandler extends Thread {

     final Socket sock;
     final DataInputStream inc;
     final DataOutputStream out;

     public ConnectionHandler(Socket sock, DataInputStream inc, DataOutputStream out) {
         this.sock = sock;
         this.inc = inc;
         this.out = out;
     }

//     @Override
//     public void run() {
//
//         String welcome = "Welcome!";
//         String login = "1. Login\n2. Create a new account\n3. Exit";
//         String user = "Enter your username as a combination of letters, numbers, and symbols.";
//         String pass = "Enter your password.";
//         String personNew = "Are you\n1. a student or\n2. a teacher?";
//         String enterName = "Please enter your full name";
//         String newAcc = "Please enter a username (first name, last initial, numbers).";
//         String newAccP = "Please enter a password.";
//         String accSuccess = "Account created! Please log in.";
//         String exit = "Goodbye. Hope to see you again";
//
//         String error = "Please enter a valid option";
//
//         BufferedReader inRead = new BufferedReader(new InputStreamReader(inc));
//         Scanner s = new Scanner(inc);
//
//         try {
//             out.writeUTF(welcome);
//             out.flush();
//             int answer = 0;
//             //variable that will take in all int scanner values
//
//             FileIO io = new FileIO();
//             //for Fulton's methods
//
//             String username;
//             //stores the username of the user
//
//             String password;
//             //stores the password of the user
//
//             String name = "";
//             //the full name of the user will be extracted here
//
//             String type = "";
//             //whether the user is a teacher or a student
//             //used to help create new student/teachers
//             //depending on which one the user is
//
//             System.out.println(answer);
//             while (answer < 1 || answer > 3) {
//                 boolean userFound = false;
//                 do {
//                     try {
//                         out.writeUTF(login);
//                         out.flush();
//                         switch (answer = Integer.parseInt(inRead.readLine())) {
//                             case 1 -> {
//                                 out.writeUTF(user);
//                                 out.flush();
//                                 username = inRead.readLine();
//                                 out.writeUTF(pass);
//                                 out.flush();
//                                 password = inRead.readLine();
//
//
//                                 if (io.readStudent(username, password) != null) {
//
//                                     userFound = true;
//                                     Student student = io.readStudent(username, password);
//                                     //if != null, readStudent should return a valid student
//
//                                     StudentMenu studentMenu = new StudentMenu();
//                                     //new object to make sure the student sees the correct menu
//
//                                     do {
//                                         out.writeUTF(studentMenu.studentWelcome);
//                                         out.flush();
//                                         answer = Integer.parseInt(inRead.readLine());
//                                         if (answer == 2) {
//                                             out.writeUTF(studentMenu.courses);
//                                             out.flush();
//                                             ArrayList<String> courseList = new ArrayList<>(studentMenu.readFile("courses.txt", s));
//                                             //courses.txt should be made by a teacher
//
//
//                                             int counter = 1;
//                                             for (String course : courseList) {
//                                                 out.writeUTF(String.valueOf(counter) + ": " + course.substring(0, course.indexOf(",")));
//                                                 out.flush();
//                                                 //prints course list from the course master file
//                                                 //correct output w. example file
//                                                 //1: Computer Science
//                                                 //2: Mathematics
//                                                 counter++;
//                                             }
//                                             counter = 1;
//                                             //displays options for student
//
//                                             int courseNo;
//                                             courseNo = Integer.parseInt(inRead.readLine());
//                                             //student types in the number of their selected course
//
//                                             out.writeUTF(studentMenu.studentTakeQuiz);
//                                             out.flush();
//                                             do {
//                                                 answer = Integer.parseInt(inRead.readLine());
//                                                 if (answer == 1) {
//                                                     ArrayList<String> quizList = studentMenu.readFile("c" + courseNo + ".txt", s);
//
//                                                     //pulls list of quizzes. quizlist is c1.txt
//
//
//                                                     for (String quiz : quizList) {
//                                                         out.writeUTF(String.valueOf(counter) + ": " + quiz.substring(0, quiz.indexOf(",")));
//                                                         out.flush();
//                                                         counter++;
//                                                         //correct output w. example file
//                                                         //1: Java Quiz
//                                                         //2: Python Quiz
//                                                         //3: C++ Quiz
//                                                     }
//                                                     answer = Integer.parseInt(inRead.readLine());
//                                                     String quizID = "c" + courseNo + "q" + answer;
//                                                     String quizData = quizList.get((answer - 1));
//                                                     String quizName = quizData.substring(0, quizData.indexOf(","));
//
//                                                     //the name of the user's chosen quiz (does not inReadlude file name with answers)
//                                                     //correct output w. example file
//                                                     //answer = 1
//                                                     //Java Quiz
//
//
//                                                     ArrayList<String> questionList = studentMenu.readFile(quizID + ".txt", s);
//                                                     //pulls up the list of questions, c1q1.txt
//                                                     Quiz quiz = studentMenu.readQuizFile(quizName, quizID, quizID + ".txt", s);
//
//                                                     studentMenu.takeQuiz(quiz, quizName, username, quizID);
//                                                 } else if (answer == 2) {
//                                                     out.writeUTF(studentMenu.exit);
//                                                     out.flush();
//                                                     return;
//                                                 } else {
//                                                     out.writeUTF(error);
//                                                     out.flush();
//                                                 }
//                                             } while (answer < 1 || answer > 2);
//
//                                         } else if (answer == 1) {
//                                             out.writeUTF(studentMenu.newPass);
//                                             out.flush();
//                                             io.removeUser(name, username, password, username + ".txt",
//                                                     "StudentData.txt");
//                                             String newPassword = inRead.readLine();
//                                             student.setPassword(newPassword);
//                                             io.writeStudent(student);
//                                             out.writeUTF(studentMenu.passChanged);
//                                             out.flush();
//                                         } else if (answer == 3) {
//                                             out.writeUTF(studentMenu.exit);
//                                             out.flush();
//                                             return;
//                                         }
//                                     } while (answer < 1 || answer > 3);
//
//                                 } else if (io.readTeacher(username, password) != null) {
//                                     userFound = true;
//                                     Teacher teacher = io.readTeacher(username, password);
//                                     TeacherMenu teacherMenu = new TeacherMenu();
//                                     teacherMenu.teacherAccess(s, teacher, io);
//                                 } else {
//                                     out.writeUTF("Unable to find user!");
//                                     out.flush();
//                                 }
//                             }
//                             case 2 -> {
//                                 do {
//                                     out.writeUTF(personNew);
//                                     out.flush();
//                                     answer = Integer.parseInt(inRead.readLine());
//                                     if (answer == 1) {
//                                         type = "student";
//                                     } else if (answer == 2) {
//                                         type = "teacher";
//                                     }
//                                 } while (answer < 1 || answer > 2);
//                                 out.writeUTF(enterName);
//                                 out.flush();
//                                 name = inRead.readLine();
//                                 out.writeUTF(newAcc);
//                                 out.flush();
//                                 username = inRead.readLine();
//                                 out.writeUTF(newAccP);
//                                 out.flush();
//                                 password = inRead.readLine();
//                                 if (!io.checkUsername(username, password)) {
//                                     if (type.equals("student")) {
//                                         Student student = new Student(name, username, password);
//                                         io.writeStudent(student);
//                                     } else if (type.equals("teacher")) {
//                                         Teacher teacher = new Teacher(name, username, password);
//                                         io.writeTeacher(teacher);
//                                     }
//                                     out.writeUTF(accSuccess);
//                                     out.flush();
//                                 } else {
//                                     throw new InvalidUsernameException();
//                                     //the username in question has already been taken
//                                 }
//                             }
//                             case 3 -> {
//                                 out.writeUTF(exit);
//                                 out.flush();
//                                 return;
//                             }
//                             default -> {
//                                 out.writeUTF(error);
//                                 out.flush();
//                             }
//                         }
//                     } catch (InvalidUsernameException e) {
//                         try {
//                             out.writeUTF("This user already exists!");
//                             out.flush();
//                         } catch (IOException er) {
//                             er.printStackTrace();
//                         }
//                     } catch (NumberFormatException nf) {
//                         try {
//                             out.writeUTF("Invalid input, please try again.");
//                             out.flush();
//                         } catch (IOException er) {
//                             er.printStackTrace();
//                         }
//                     } catch (IOException e) {
//                         try {
//                             out.writeUTF("File not found, please try again.");
//                             out.flush();
//                         } catch (IOException er) {
//                             er.printStackTrace();
//                         }
//                     } catch (Exception e) {
//                         try {
//                             out.writeUTF("Error");
//                             out.flush();
//                         } catch (IOException er) {
//                             er.printStackTrace();
//                         }
//                     }
//                 } while (!userFound);
//
//             }
//         } catch (IOException writeError) {
//             writeError.printStackTrace();
//         }
//
//         try {
//             this.inc.close();
//             this.out.close();
//         } catch (IOException closeError){
//             closeError.printStackTrace();
//         }
//
//     }

     @Override
     public void run() {
         try {
             Menu menu = new Menu();

             String answer = "";
             JOptionPane.showMessageDialog(null, "Welcome", "Main menu", JOptionPane.INFORMATION_MESSAGE);


             String[] initialMenu = {"Login", "Create new account", "Exit"};
             answer = (String) JOptionPane.showInputDialog(null, "Choose one",
                     "Main menu", JOptionPane.PLAIN_MESSAGE, null, initialMenu, null);

             if (answer == String.valueOf(JOptionPane.CANCEL_OPTION) || answer == String.valueOf(JOptionPane.CLOSED_OPTION)) {
                 JOptionPane.showMessageDialog(null, "Goodbye! We hope to see you again.",
                         "Main menu", JOptionPane.PLAIN_MESSAGE);
                 JOptionPane.showMessageDialog(null, "Goodbye! We hope to see you again.",
                         "Main menu", JOptionPane.PLAIN_MESSAGE);
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
 }