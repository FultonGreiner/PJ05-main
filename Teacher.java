/**
 * Teacher
 *
 * An object that will allow the program know which permissions is given
 *
 * @author Charles Greiner, L17
 * @author Benn Sesante, L17
 * @author Chingyan Huang, L17
 * @author Akash Sridhar, L17
 *
 * @version 4-11-2022
 *
 */

public class Teacher {
    private String name;
    private String username;
    private String password;
    private String filename;
    public Teacher(String name, String username, String password) throws Exception {
        this.name = name;
        this.username = username;
        this.password = password;
        this.filename = String.format("%s,%s,%s,%s.txt", username, password, name, username);
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
        this.filename = String.format("%s,%s,%s,%s.txt", username, password, name, username);
    }

    public String getUsername() {
        return username;
    }

    public String getName() {
        return name;
    }

    /**
     * Returns the filename that will store teacher information
     * @return
     */
    public String toString() {
        return this.filename;
    }
}
