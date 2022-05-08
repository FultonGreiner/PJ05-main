/**
 * InvalidQuizNameException
 *
 * A custom exception to be thrown
 * when a quiz does not exist.
 *
 * @author Chingyan Huang, L17
 * @author Benn Sesante, L17
 * @author Charles Greiner, L17
 * @author Akash Sridhar, L17
 */
public class InvalidQuizNameException extends Exception {
    public InvalidQuizNameException(String e) {
        super(e);
    }
    public InvalidQuizNameException() {
        super();
    }
}
