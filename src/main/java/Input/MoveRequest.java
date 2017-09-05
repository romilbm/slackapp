package Input;

import Interfaces.Request;
import Model.Channel;

/**
 * The Request wrapper class for the Move Action.
 */
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

    /**
     * {@inheritDoc}
     * If the command text contains more than 2 strings,
     * Or the second string cannot be parsed into a number,
     * It considers the user input as invalid and throws an exception.
     * Barring that, it extracts the channel, the userId of the player and the move position choice.
     * @throws IllegalArgumentException
     */
    public void validateRequestAndExtract() throws IllegalArgumentException {
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
