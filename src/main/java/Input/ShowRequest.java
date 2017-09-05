package Input;

import Interfaces.Request;
import Model.Channel;

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

    public void validateRequest() throws IllegalArgumentException{
        if (!commandText.equals("show")) {
            throw new IllegalArgumentException(CORRECT_FORMAT);
        }
        channel = new Channel(channelId, channelName);
    }
}
