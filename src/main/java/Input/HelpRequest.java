package Input;

import Interfaces.Request;

public class HelpRequest implements Request {
    private static String CORRECT_FORMAT = "Incorrect format the correct format is /rottt help";
    private String commandText;

    public HelpRequest(String commandText) {
        this.commandText = commandText;
    }

    @Override
    public void validateRequest() throws IllegalArgumentException {
        if (!commandText.equals("help")) {
            throw new IllegalArgumentException(CORRECT_FORMAT);
        }
    }
}
