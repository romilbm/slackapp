package Interfaces;

public interface Request {
    /**
     * Provides a way in which we can validate and then extract the argument for the Request.
     * If there is a problem with the arguments it will throw an IAE.
     * Else it will extract the fields needed for the request.
     * @throws IllegalArgumentException If the options are not correctly passed by the players.
     */
    void validateRequestAndExtract() throws IllegalArgumentException;
}
