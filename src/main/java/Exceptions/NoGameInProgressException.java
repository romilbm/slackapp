package Exceptions;

public class NoGameInProgressException extends TTTGameException {
    public NoGameInProgressException(String message) {
        super(message);
    }
}
