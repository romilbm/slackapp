package Input;

import Model.Channel;

public class ShowRequest {
    public Channel getChannel() {
        return channel;
    }

    public void setChannel(Channel channel) {
        this.channel = channel;
    }

    public ShowRequest(Channel channel) {

        this.channel = channel;
    }

    Channel channel;

    public void validateRequest() throws IllegalArgumentException{
        if (!channel.isValid()) {
            throw new IllegalArgumentException();
        }
    }
}
