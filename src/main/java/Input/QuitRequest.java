package Input;

import Model.Channel;

public class QuitRequest {
    String playerId;
    Channel channel;

    public QuitRequest(String playerId, Channel channel) {
        this.playerId = playerId;
        this.channel = channel;
    }

    public Channel getChannel() {
        return channel;
    }

    public void validateRequest() throws IllegalArgumentException{
        if (playerId == null || playerId.isEmpty() || !channel.isValid()) {
            throw new IllegalArgumentException();
        }
    }

    public String getPlayerId() {
        return playerId;
    }
}
