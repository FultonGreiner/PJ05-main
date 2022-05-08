/**
 * InvalidUsernameException
 *
 * A custom exception to be thrown when
 * the program cannot find a correct username.
 *
 * @author Chingyan Huang, L17
 * @author Benn Sesante, L17
 * @author Charles Greiner, L17
 * @author Akash Sridhar, L17
 *
 */
public class InvalidUsernameException extends Exception {
    public InvalidUsernameException(String e) {
        super(e);
    }
    public InvalidUsernameException() { super(); }
}
