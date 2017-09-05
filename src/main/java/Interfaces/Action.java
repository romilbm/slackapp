package Interfaces;

/**
 * The Action Interface
 */
public interface Action {
    /**
     * Provides an implementation on how the action should be run.
     */
    void run();

    /**
     * Allows a request to be set on the action.
     * @param request A Request Object
     */
    void setRequest(Request request);

    /**
     * Returns the Response computed by the Action.
     * @return The Reponse Object.
     */
    Response getResponse();
}
