package Input;

import Interfaces.Request;

/**
 * The Request wrapper class for the Help Action.
 */
public class HelpRequest implements Request {
    private static String CORRECT_FORMAT = "Incorrect format the correct format is /rottt help";
    private String commandText;

    public HelpRequest(String commandText) {
        this.commandText = commandText;
    }

    @Override
    public void validateRequestAndExtract() throws IllegalArgumentException {
        if (!commandText.equals("help")) {
            throw new IllegalArgumentException(CORRECT_FORMAT);
        }
    }
}
