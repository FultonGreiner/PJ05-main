/**
 * User
 *
 * An object that will store user information.
 *
 * @author Charles Greiner, L17
 * @author Benn Sesante, L17
 * @author Chingyan Huang, L17
 * @author Akash Sridhar, L17
 *
 * @version 4-11-2022
 */

public class User {
    private String name;
    private String username;
    public String password;
    private String filename;

    public User (String name, String username, String password) throws Exception {
        isUsernameValid(username);
        isNameValid(name);
        this.name = name;
        this.username = username;
        this.password = password;
        this.filename = String.format("%s,%s,%s,%s.txt", getUsername(), password, getName(),getUsername());
    }

    public String toString() {
        return this.filename;
    }

    private void isUsernameValid(String username) throws InvalidUsernameException {
        if(username.contains(" ")) {
            throw new InvalidUsernameException("This username is invalid!");
        }
    }

    private void isNameValid(String name) throws InvalidNameException {
        if(!name.contains(" ")) {
            throw new InvalidNameException("This name is invalid!");
        }
    }

    public String getName() {
        return name;
    }

    public String getUsername() {
        return username;
    }

//    public String getPassword() {
//        return password;
//    }
//
//    public void setPassword(String password) {
//        this.password = password;
//        this.filename = String.format("%s,%s,%s.txt", getUsername(), getPassword(), getName());
//    }

    public boolean canEdit() {
        return (this instanceof Teacher);
    }

    public boolean canCreate() {
        return (this instanceof Teacher);
    }

    public boolean canViewGrades() {
        return (this instanceof Teacher);
    }
}
