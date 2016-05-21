package exception;

/**
 * Created by rafael on 20/05/16.
 */
public class IndiceInvalidoException extends ArrayIndexOutOfBoundsException {
    public IndiceInvalidoException(String message) {
        super(message);
    }
}