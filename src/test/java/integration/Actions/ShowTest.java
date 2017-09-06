package integration.Actions;

import Exceptions.NoGameInProgressException;
import Exceptions.TTTExceptionMessage;
import Model.Channel;
import Model.RichMessage;
import Model.TicTacToe;
import Output.ShowResponse;
import integration.TTTTest;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class ShowTest extends TTTTest {
    @Test
    public void simpleShowTest() {
        text = "start <@U2345|player2>";
        controller.onReceiveSlashCommand(token,
                teamId, teamDomain, channelId,
                channelName, userId,
                userName, command, text, responseUrl);

        TicTacToe game = ongoingTestGames.getGameForChannel(new Channel(channelId, channelName));

        text = "show";

        RichMessage actualMessage = controller.onReceiveSlashCommand(token,
                teamId, teamDomain, channelId,
                channelName, userId+"567",
                userName, command, text, responseUrl);


        ShowResponse showResponse= new ShowResponse(game.toString(), game.getPlayers(), game
                .getNextPlayer());

        RichMessage expectedMessage = new RichMessage(showResponse.toString());
        expectedMessage.encodedMessage();

        assertEquals(expectedMessage.getText(), actualMessage.getText());
    }

    @Test
    public void showNoGameInProgressTest() {
        text = "show";

        RichMessage actualMessage = controller.onReceiveSlashCommand(token,
                teamId, teamDomain, channelId,
                channelName, userId,
                userName, command, text, responseUrl);

        ShowResponse showResponse= new ShowResponse(new NoGameInProgressException(TTTExceptionMessage.NO_GAME_IN_PROGRESS));

        RichMessage expectedMessage = new RichMessage(showResponse.toString());
        expectedMessage.encodedMessage();

        assertEquals(expectedMessage.getText(), actualMessage.getText());
    }
}
