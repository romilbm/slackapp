package Input;

import Model.Channel;
import Model.Player;

public class MoveRequest {
    String playerId;
    int move;
    Channel channel;

    public MoveRequest(String playerId, int move, Channel channel) {
        this.playerId = playerId;
        this.move = move;
        this.channel = channel;
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

    public void validateRequest() throws IllegalArgumentException{
        if (playerId == null || playerId.isEmpty() || !channel.isValid()) {
            throw new IllegalArgumentException();
        }
    }
}
