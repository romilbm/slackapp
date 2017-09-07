package Model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class RichMessage {
    public static final String RESPONSE_TYPE_IN_CHANNEL = "in_channel";
    private String username;
    @JsonProperty("icon_emoji")
    private String iconEmoji;
    private String channel;
    private String text;
    @JsonProperty("response_type")
    private String responseType;

    public RichMessage() {
    }

    public RichMessage(String text) {
        this.text = text;
    }

    public RichMessage encodedMessage() {
        this.setText(this.getText().replace("&", "&amp;").replace("<", "&lt;").replace(">", "&gt;"));
        return this;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getIconEmoji() {
        return iconEmoji;
    }

    public void setIconEmoji(String iconEmoji) {
        this.iconEmoji = iconEmoji;
    }

    public String getChannel() {
        return channel;
    }

    public void setChannel(String channel) {
        this.channel = channel;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getResponseType() {
        return responseType;
    }

    public void setResponseType(String responseType) {
        this.responseType = responseType;
    }
}
