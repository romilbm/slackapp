package Actions;

import Input.HelpRequest;
import Interfaces.Request;
import Interfaces.Response;
import Output.HelpResponse;

/**
 * Invoked when /rottt help is called by a user.
 * This action gives serves the help menu for the TTT game.
 */
public class Help extends TTTAction {
    HelpRequest helpRequest;
    HelpResponse helpResponse;

    @Override
    public void run() {
        helpResponse = new HelpResponse();
    }

    @Override
    public void setRequest(Request request) {
        helpRequest = (HelpRequest) request;
    }

    @Override
    public Response getResponse() {
        return helpResponse;
    }
}
