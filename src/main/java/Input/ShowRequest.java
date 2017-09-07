package Input;

import Actions.Show;
import Interfaces.Request;
import Model.Channel;
import ResponseStrings.RequestMessages;

/**
 * The Request wrapper class for the Move Action.
 */
public class ShowRequest implements Request {
    private static String CORRECT_FORMAT = "Incorrect format the correct format is /rottt show";
    Channel channel;
    private String channelId;
    private String commandText;
    private String channelName;

    public ShowRequest(String commandText, String channelId, String channelName) {
        this.channelId = channelId;
        this.commandText = commandText;
        this.channelName = channelName;
    }

    public Channel getChannel() {
        return channel;
    }

    public void validateRequestAndExtract() throws IllegalArgumentException{
        if (!commandText.equals(Show.OPTION_TEXT)) {
            throw new IllegalArgumentException(RequestMessages.SHOW_CORRECT_FORMAT);
        }
        channel = new Channel(channelId, channelName);
    }
}
