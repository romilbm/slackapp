package Model;

public class Channel {
    private String channelId;
    private String channelName;

    public Channel(String channelId, String channelName) {
        this.channelId = channelId;
        this.channelName = channelName;
    }

    @Override
    public boolean equals(Object o) {
        if (!o.getClass().equals(this.getClass())) return false;
        Channel c = (Channel)o;

        if (this.channelId.equals(c.getChannelId()) && this.channelName.equals(c.getChannelName())) return true;

        return false;
    }

    @Override
    public int hashCode() {
        return channelId.hashCode() * 2 + channelName.hashCode() * 3;
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
