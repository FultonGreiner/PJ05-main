# CS 180 - Project 05 - Group 073
____

## Contents
____

* Contributors
* Description
* Compilation / Running Instructions
* Usage
* Brightspace and Vocareum Submissions

## Contributors
____

* Charles Greiner
* Akash Sridhar
* Chingyan Huang
* Benn Sesante

## Description
____

This program provides students and teachers with a method of accessing and monitoring their quizzes in each course.
Students can login to take quizzes or view scores on completed quizzes. Teachers can create, edit, or delete quizzes
and review student submissions.

## Compilation / Running Instructions
____

### Compilation

1. Download all Java classes from the main branch. Before running, make sure they are all in the same directory.
2. Open the terminal, use the "cd" command to get the correct directory.
3. Use the "javac Menu.java" command to compile the program.

### Running

1. Use the command "java Menu" to run the program.

## Usage
____

### Main Menu

1. When prompted with the welcome message, select one of the following options:
   * "Login" - allows an existing user to login with the correct username and password
   * "Create new account" - allows a new user to create an account
   * "Exit" - displays an exit message and terminates the program

### Login

1. Enter existing username when prompted
2. Enter correct password when prompted

### Create new account

1. Select user type (Student or Teacher)
2. Enter your name in the following format: "FIRSTNAME LASTNAME"
3. Enter a username that is not already taken
4. Enter a password
5. Once sent back to the main menu, follow the Login instructions above.

## Student Interface

### Take a Quiz

1. Select the "Take a quiz" option.
2. Enter the course name when prompted.
3. Enter the quiz name when prompted.
4. Respond to each of the questions with the integer correlated to the answer choice.

### View Quiz Results

1. Select the "View Quiz Results" option.
2. Enter the quiz name when prompted.
3. Enter the quiz name when prompted.

### Log Out

1. Select the "Log out" option.

## Teacher Interface

### Existing Quizzes

1. Select the "Existing Quizzes" option.
2. Enter the student who's scores are to be viewed when prompted.

### Create Quizzes

1. Select the "Create Quizzes" option.
2. Choose to upload a quiz file or manually type the quiz's contents.
3. If manual entry was selected, enter the number of questions the quiz contains.
4. Enter either the filename or quiz contents.

### Delete Quizzes

1. Select the "Delete Quizzes" option.
2. Enter the filename of the quiz to be deleted.

### Log Out
1. Select the "Log out" option.

## Brightspace and Vocareum Submissions
____
### Charles Greiner
* Github Repository
* Video Presentation

### Chingyan Huan
* Final Report

## Class Descriptions
____

### Course

This class is used as a constructor for courses. It also contains methods that
can be used to manipulate the data of the course. Course contains the field "courses",
which is an ArrayList containing instances of the Quiz class.

### FileInput

This class provides all of the functionality for writing data to files. This class is implemented 
by the Menu class.

### FileOutput

This class provides all of the functionality for reading data from files. This class is implemented 
by the Menu class.

### FileTools

This class provides miscellaneous functionality for files. It provides methods allowing the 
program to check if a username and password are correct, and to remove a user from the file 
to rewrite it with their updated password. This class is implemented by the Menu class.

### IncorrectPassword

This class extends Exception and is thrown when a user enters an incorrect
password.

### InvalidName

This class extends Exception and is thrown when a user enters an incorrect
name.

### InvalidQuizNameException

This class extends Exception and is thrown when a user enters an incorrect
quiz name.

### InvalidUsernameException

This class extends Exception and is thrown when a user enters an incorrect
username.

### Menu

This portion of the program contains the initial login screens that a user 
can see. Can let a user sign in as a student or a teacher or have them create
a whole new account. Functionalities differ between user types. This class 
implements both the StudentMenu and TeacherMenu classes as well as FileInput,
FileOutput, and FileTools.

### Question

This class is used as a constructor for questions. It also contains methods
that can be used to manipulate and view the data of the question. This class is
utilized by the Quiz class.

### Quiz

This class is used as a constructor for quizzes. It also contains methods that
can be used to manipulate and view the data of the quiz. This class contains the 
field "questions", which is an ArrayList containing instances of the Question class.

### Student

A class that stores the student's attempts at each quiz. Extends User.

### StudentMenu

This class provides all the functionality for the student menu. This class is 
implemented by the Menu class.

### StudentQuestion

An object that will contain information about the student's response to a question. 
This class is utilized by the StudentQuiz class.

### StudentQuiz

This is the student's version of the quiz. Each quiz class contains all the questions
that the student worked on and if the student got the question right or not. It doesn't
contain the entire quiz/subject questions, just the ones that the student has answered.
This class contains the field "questions", which is an ArrayList containing instances of 
the Question class.

### Teacher

This class allows the program know which permissions are given. Extends User.

### TeacherMenu

This class contains all the main methods for the teacher's side of the Learning Management
System. It reads in all saved data and contains the core teacher implementation for the
project. This class is implemented by the Menu class.

### User

An object that will store user information.

### UserAlreadyExists

This class extends Exception and is thrown when a user enters a username that is already
taken.
