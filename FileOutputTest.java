import java.io.*;

public class FileOutputTest {
    /**
     * Takes two files as input, one containing the expected output and the
     * other the one to be written to. Compares their contents and returns
     * true if it matches the expected output, false if not.
     * @param actualOutputFile   file written by FileIO method
     * @param expectedOutputFile file containing expected output
     * @return                   boolean representing whether or not the
     *                           file's contents match expected
     * @author                   Charles Greiner
     */
    public String validateTest(String actualOutputFile, String expectedOutputFile) {
        try {
            // open file stream
            FileReader fr = new FileReader(actualOutputFile);
            BufferedReader bfr = new BufferedReader(fr);
            // initialize String
            String actualOutput = "";
            String expectedOutput = "";
            // load actual output file to String
            String line;
            while ((line = bfr.readLine()) != null) {
                actualOutput = actualOutput + line + "\n";
            }
            bfr.close();
            fr.close();
            // open new file stream
            fr = new FileReader(expectedOutputFile);
            bfr = new BufferedReader(fr);
            // load expected output file to String
            while ((line = bfr.readLine()) != null) {
                expectedOutput = expectedOutput + line + "\n";
            }
            bfr.close();
            fr.close();
            return String.valueOf(actualOutput.equals(expectedOutput));
        } catch (Throwable e) {
            e.printStackTrace();
            return null;
        }
    }

    public static void main(String args[]) {
        // instantiate FileOutput class
        FileOutput fo = new FileOutput();
        // instantiate FileOutputTest class
        FileOutputTest fot = new FileOutputTest();

        /**
         * writeStudent Test Cases - Passed
         */
//        try {
//            Student student1 = new Student("Charles Greiner", "greinerc", "123456");
//            fo.writeStudent(student1);
//            Student student2 = new Student("Chingyan Huang", "chingyanh", "123456");
//            fo.writeStudent(student2);
//            Student student3 = new Student("Akash Sridhar", "akashs", "123456");
//            fo.writeStudent(student3);
//            Student student4 = new Student("Benn Sesante", "benns", "123456");
//            fo.writeStudent(student4);
//            System.out.println(fot.validateTest("StudentData.txt", "StudentDataExpected.txt"));
//        } catch (Throwable e) {
//            e.printStackTrace();
//        }

        /**
         * writeTeacher Test Cases - Passed
         */
//        try {
//            Teacher teacher1 = new Teacher("Charles Greiner", "greinerc", "123456");
//            fo.writeTeacher(teacher1);
//            Teacher teacher2 = new Teacher("Chingyan Huang", "chingyanh", "123456");
//            fo.writeTeacher(teacher2);
//            Teacher teacher3 = new Teacher("Akash Sridhar", "akashs", "123456");
//            fo.writeTeacher(teacher3);
//            Teacher teacher4 = new Teacher("Benn Sesante", "benns", "123456");
//            fo.writeTeacher(teacher4);
//            System.out.println(fot.validateTest("TeacherData.txt", "TeacherDataExpected.txt"));
//        } catch (Throwable e) {
//            e.printStackTrace();
//        }

        /**
         * writeUserData Test Cases - Passed
         */
//        try {
//            fo.writeUserData("greinerc.txt", "C1Q1.txt");
//            fo.writeUserData("akashs.txt", "C1Q1.txt");
//            fo.writeUserData("chingyamh.txt", "C1Q1.txt");
//            fo.writeUserData("benns.txt", "C1Q1.txt");
//        } catch (Throwable e) {
//            e.printStackTrace();
//        }

    }
}
