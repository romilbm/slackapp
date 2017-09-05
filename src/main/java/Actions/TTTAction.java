package Actions;

import DataAccess.OngoingGames;
import Input.HelpRequest;
import Input.MoveRequest;
import Input.QuitRequest;
import Input.ShowRequest;
import Input.StartRequest;
import Interfaces.Action;
import Interfaces.DataAccessor;
import Interfaces.Request;

public abstract class TTTAction implements Action {
    protected static DataAccessor ongoingGames;

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
        request.validateRequest();
        action.setRequest(request);
        return action;
    }
}
