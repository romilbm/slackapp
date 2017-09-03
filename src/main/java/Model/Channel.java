package Model;

public class Channel {
    private String channelId;
    private String channelName;

    public Channel(String channelId, String channelName) {
        this.channelId = channelId;
        this.channelName = channelName;
    }

    public String getChannelName() {
        return channelName;
    }

    public String getChannelId() {
        return channelId;
    }

    public boolean isValid() {
        if (channelId == null || channelId.isEmpty()
          || channelName == null || channelName.isEmpty()) {
            return false;
        }
        return true;
    }
}
