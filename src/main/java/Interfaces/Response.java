package Interfaces;

public interface Response {
    /**
     * If there was a problem while executing the action this field is set to a non null value.
     */
    Exception exception = null;

    /**
     * Provides a way to get the string representation of the Response.
     * If the exception field is not null, the exception's message is returned.
     * @return The String representation of the Response.
     */
    String toString();
}
