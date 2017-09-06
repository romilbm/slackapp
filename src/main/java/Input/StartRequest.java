package Input;

import Enums.Symbol;
import Interfaces.Request;
import Model.Channel;
import Model.Player;
import Move.AIMove;
import Move.HumanMove;

/**
 * The Request wrapper class for the Move Action.
 */
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

    /**
     * {@inheritDoc}
     * The command text should contain a valid player
     * i.e. the id and name should be of the form <@U1234|name>
     * The other player cannot be the first player.
     *
     * @throws IllegalArgumentException
     */
    public void validateRequestAndExtract() throws IllegalArgumentException {
        p1 = new Player(userName, userId, new HumanMove(), Symbol.X);
        channel = new Channel(channelId, channelName);

        if (commandText.equals("start")) {
            p2 = new Player(Player.BOT_NAME, Player.BOT_ID, new AIMove(), Symbol.ZERO);
            return;
        } else if (commandText.split(" ").length != 2) {
            throw new IllegalArgumentException(CORRECT_FORMAT);
        }

        try {
            String[] secondPlayerInfo = extractUser(commandText);
            p2 = new Player(secondPlayerInfo[0], secondPlayerInfo[1], new HumanMove(), Symbol.ZERO);
        } catch (Exception e) {
            throw new IllegalArgumentException("Enter the opponent in the correct format. " + CORRECT_FORMAT);
        }

        if (p1.getId().equals(p2.getId())) {
            throw new IllegalArgumentException("You cannot play against yourself. " + CORRECT_FORMAT);
        }
    }

    /**
     * Extracts the command text in the form "start <@U1234|name>" and returns {U1234, name}.
     * @param commandPart
     * @return
     */
    private String[] extractUser(String commandPart) {
        String[] partsOfCommandText = commandPart.split(" ");
        String regex = "\\|";

        //separate out the <@U1234|name> part
        // then substring to get @U1234|name
        // then split by | to get @U1234 and name
        String[] userInfo = partsOfCommandText[1].substring(1, partsOfCommandText[1].length()-1).split(regex);

        String id = userInfo[0].substring(1);
        //verify it is indeed user id and not some other entity.
        if (id.charAt(0) != 'U') {
            throw new IllegalArgumentException();
        }
        String name = userInfo[1];

        return new String[] {name, id};
    }
}
