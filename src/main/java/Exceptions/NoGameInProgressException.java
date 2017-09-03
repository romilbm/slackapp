package Exceptions;

/**
 * Created by romil on 9/2/17.
 */
public class NoGameInProgressException extends Exception{
    public NoGameInProgressException(String message) {
        super(message);
    }
}
