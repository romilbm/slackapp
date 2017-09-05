package Input;

import Interfaces.Request;
import Model.Channel;

public class MoveRequest implements Request {
    private static String CORRECT_FORMAT = "Incorrect format the correct format is /rottt move <position>";
    private String userId;
    private String channelId;
    private String channelName;
    String playerId;
    int move;
    Channel channel;
    private String commandText;

    public MoveRequest(String commandText, String userId, String channelId, String channelName) {
        this.commandText = commandText;
        this.userId = userId;
        this.channelId = channelId;
        this.channelName = channelName;
    }

    public String getPlayerId() {
        return playerId;
    }

    public int getMove() {
        return move;
    }

    public Channel getChannel() {
        return channel;
    }

    public void validateRequest() throws IllegalArgumentException {
        String[] p = commandText.split(" ");
        if (p.length != 2) throw new IllegalArgumentException(CORRECT_FORMAT);

        try {
            move = Integer.parseInt(p[1]);
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("The position should be a number between 1-9.");
        }
        playerId = userId;
        channel = new Channel(channelId, channelName);
    }
}
