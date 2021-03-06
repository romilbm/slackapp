package integration.Actions;

import Model.Channel;
import Model.RichMessage;
import Model.TicTacToe;
import Output.StartResponse;
import ResponseStrings.RequestMessages;
import integration.TTTTest;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

public class StartTest extends TTTTest {

    @Test
    public void simpleStartTest() {
        text = "start <@U2345|player2>";
        RichMessage actualMessage = controller.onReceiveSlashCommand(token,
                teamId, teamDomain, channelId,
                channelName, userId,
                userName, command, text, responseUrl);

        TicTacToe game = ongoingTestGames.getGameForChannel(new Channel(channelId, channelName));
        StartResponse response = new StartResponse(game.getNextPlayer(), game.getPlayers(),
                game.toString(), null);

        RichMessage expectedMessage = new RichMessage(response.toString());
        expectedMessage.setResponseType("in_channel");
        expectedMessage.encodedMessage();

        assertEquals(expectedMessage.getText(), actualMessage.getText());
        assertEquals(expectedMessage.getChannel(), actualMessage.getChannel());
    }

    @Test
    public void startInvalidOptionTest() {
        text = "start player2";
        RichMessage actualMessage = controller.onReceiveSlashCommand(token,
                teamId, teamDomain, channelId,
                channelName, userId,
                userName, command, text, responseUrl);

        TicTacToe game = ongoingTestGames.getGameForChannel(new Channel(channelId, channelName));
        assertNull(game);

        RichMessage message = new RichMessage(RequestMessages.INCORRECT_OPPONENT_FORMAT);
        message.encodedMessage();

        assertEquals(message.getText(), actualMessage.getText());
    }

}
