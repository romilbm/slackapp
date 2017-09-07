package Input;

import Actions.Help;
import Interfaces.Request;

import static ResponseStrings.RequestMessages.HELP_CORRECT_FORMAT;

/**
 * The Request wrapper class for the Help Action.
 */
public class HelpRequest implements Request {
    private String commandText;

    public HelpRequest(String commandText) {
        this.commandText = commandText;
    }

    @Override
    public void validateRequestAndExtract() throws IllegalArgumentException {
        if (!commandText.equals(Help.OPTION_TEXT)) {
            throw new IllegalArgumentException(HELP_CORRECT_FORMAT);
        }
    }
}
