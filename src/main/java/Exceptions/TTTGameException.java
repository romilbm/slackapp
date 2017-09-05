package Exceptions;

public class TTTGameException extends Exception{
    public TTTGameException(String message) {
        super(message);
    }

    public TTTGameException() {
        super();
    }
}
