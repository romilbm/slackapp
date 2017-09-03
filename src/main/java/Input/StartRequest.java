package Input;

import Model.Channel;
import Model.Player;

public class StartRequest {
    private Player p1;
    private Player p2;
    private Channel channel;

    public StartRequest(Player p1, Player p2, Channel channel) {
        this.p1 = p1;
        this.p2 = p2;
        this.channel = channel;
    }

    public Player getP1() {
        return p1;
    }

    public Player getP2() {
        return p2;
    }

    public Channel getChannel() {
        return channel;
    }

    public void validateRequest() throws IllegalArgumentException {
        if (!p1.isValid() || !p2.isValid() || !channel.isValid()) {
            throw new IllegalArgumentException();
        }
    }
}
