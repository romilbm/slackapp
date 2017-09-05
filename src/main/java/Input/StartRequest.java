package Input;

import Enums.Symbol;
import Interfaces.Request;
import Model.Channel;
import Model.Player;
import Move.HumanMove;

public class StartRequest implements Request{
    private static String CORRECT_FORMAT = "The correct format is /rottt start <@otherPlayer>";
    private Player p1;
    private Player p2;
    private Channel channel;
    private String commandText;
    private String userId;
    private String userName;
    private String channelId;
    private String channelName;

    public StartRequest(String commandText, String userId, String userName, String channelId, String channelName) {
        this.commandText = commandText;
        this.userId = userId;
        this.userName = userName;
        this.channelId = channelId;
        this.channelName = channelName;
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
        if (commandText.split(" ").length != 2) {
            throw new IllegalArgumentException(CORRECT_FORMAT);
        }
        p1 = new Player(userName, userId, new HumanMove(), Symbol.X);
        try {
            String[] userInfo = extractUser(commandText);
            p2 = new Player(userInfo[0], userInfo[1], new HumanMove(), Symbol.ZERO);
        } catch (Exception e) {
            throw new IllegalArgumentException("Enter the opponent in the correct format. " + CORRECT_FORMAT);
        }

        if (p1.getId().equals(p2.getId())) {
            throw new IllegalArgumentException("You cannot play against yourself. " + CORRECT_FORMAT);
        }
        channel = new Channel(channelId, channelName);
    }

    private String[] extractUser(String commandPart) {
        String[] p = commandPart.split(" ");
        String regex = "\\|";
        String[] user = p[1].substring(1, p[1].length()-1).split(regex);

        String id = user[0].substring(1);
        if (id.charAt(0) != 'U') {
            throw new IllegalArgumentException();
        }
        String name = user[1];

        return new String[] {name, id};
    }
}
