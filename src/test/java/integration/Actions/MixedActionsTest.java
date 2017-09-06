package integration.Actions;

import Model.Channel;
import Model.RichMessage;
import Model.TicTacToe;
import integration.TTTTest;
import org.junit.Test;

import static org.junit.Assert.assertNull;

public class MixedActionsTest extends TTTTest {
    @Test
    public void multipleTurnsAgainstBot() {
        text = "start";
        controller.onReceiveSlashCommand(token,
                teamId, teamDomain, channelId,
                channelName, userId,
                userName, command, text, responseUrl);

        text = "move 2";
        controller.onReceiveSlashCommand(token,
                teamId, teamDomain, channelId,
                channelName, userId,
                userName, command, text, responseUrl);

        text = "move 5";
        controller.onReceiveSlashCommand(token,
                teamId, teamDomain, channelId,
                channelName, userId,
                userName, command, text, responseUrl);

        //Final Move
        text = "move 8";
        RichMessage actualMessage = controller.onReceiveSlashCommand(token,
                teamId, teamDomain, channelId,
                channelName, userId,
                userName, command, text, responseUrl);

        TicTacToe game = ongoingTestGames.getGameForChannel(new Channel(channelId, channelName));
        assertNull(game);
    }
}
