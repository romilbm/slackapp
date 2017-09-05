package Exceptions;

/**
 * Created by romil on 9/2/17.
 */
public class GameInProgressException extends TTTGameException {

    public GameInProgressException(String message) {
        super(message);
    }
}
