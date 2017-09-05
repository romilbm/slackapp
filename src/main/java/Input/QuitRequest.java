package Input;

import Interfaces.Request;
import Model.Channel;

/**
 * The Request wrapper class for the Quit Action.
 */
public class QuitRequest implements Request {
    private static String CORRECT_FORMAT = "Incorrect format the correct format is /rottt quit";
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
        if (!commandText.equals("quit")) {
            throw new IllegalArgumentException(CORRECT_FORMAT);
        }
        playerId = userId;
        channel = new Channel(channelId, channelName);
    }

    public String getPlayerId() {
        return playerId;
    }
}
