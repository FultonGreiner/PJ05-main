/**
 * IncorrectPasswordException
 *
 * A custom exception to be thrown when
 * a user inputs an incorrect password when
 * trying to log in (username may or may not
 * be correct).
 *
 * @author Chingyan Huang, L17
 * @author Benn Sesante, L17
 * @author Charles Greiner, L17
 * @author Akash Sridhar, L17
 *
 *
 */
public class IncorrectPasswordException extends Exception {
    public IncorrectPasswordException(String message) {super(message);}

    public IncorrectPasswordException() {super();}
}
