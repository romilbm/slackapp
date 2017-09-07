package Input;

import Actions.Quit;
import Interfaces.Request;
import Model.Channel;
import ResponseStrings.RequestMessages;

/**
 * The Request wrapper class for the Quit Action.
 */
public class QuitRequest implements Request {
    String playerId;
    Channel channel;
    private String commandText;
    private String userId;
    private String channelId;
    private String channelName;

    public QuitRequest(String commandText, String userId, String channelId, String channelName) {
        this.commandText = commandText;
        this.userId = userId;
        this.channelId = channelId;
        this.channelName = channelName;
    }

    public Channel getChannel() {
        return channel;
    }

    public void validateRequestAndExtract() throws IllegalArgumentException{
        if (!commandText.equals(Quit.OPTION_TEXT)) {
            throw new IllegalArgumentException(RequestMessages.QUIT_CORRECT_FORMAT);
        }
        playerId = userId;
        channel = new Channel(channelId, channelName);
    }

    public String getPlayerId() {
        return playerId;
    }
}
