package Actions;

import DataAccess.OngoingGames;
import Input.*;
import Interfaces.Action;
import Interfaces.DataAccessor;
import Interfaces.Request;

public abstract class TTTAction implements Action {
    protected static DataAccessor ongoingGames;

    /**
     * This method gives the appropriate action with the Request set on it for the given options.
     * @param commandText The text following the main command.
     * @param userId The id of the user calling the command.
     * @param userName The name of the user calling the command.
     * @param channelId The id of the channel in which the command was made.
     * @param channelName The name of the channel in which the command was made.
     * @return The Action object for the command with the given options.
     * @throws IllegalArgumentException When the command options cannot be parsed correctly to form the Request
     */
    public static Action getAction(String commandText,
                                   String userId,
                                   String userName,
                                   String channelId,
                                   String channelName) throws IllegalArgumentException {
        ongoingGames = OngoingGames.getInstance();
        String actionText = commandText.split(" ")[0];
        Request request;
        Action action;
        switch (actionText) {
            case "start" :
                request = new StartRequest(commandText, userId, userName, channelId, channelName);
                action = new Start();
                break;
            case "move" :
                request = new MoveRequest(commandText, userId, channelId, channelName);
                action = new Move();
                break;
            case "show" :
                request = new ShowRequest(commandText, channelId, channelName);
                action = new Show();
                break;
            case "quit" :
                request = new QuitRequest(commandText, userId, channelId, channelName);
                action = new Quit();
                break;
            case "help" :
                request = new HelpRequest(commandText);
                action = new Help();
                break;
            default:
                return null;
        }
        request.validateRequestAndExtract();
        action.setRequest(request);
        return action;
    }
}
