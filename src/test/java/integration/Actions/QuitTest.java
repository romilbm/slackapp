package integration.Actions;

import Exceptions.NoGameInProgressException;
import Model.Channel;
import Model.RichMessage;
import Model.TicTacToe;
import Output.QuitResponse;
import ResponseStrings.TTTExceptionMessages;
import integration.TTTTest;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class QuitTest extends TTTTest {
    @Test
    public void firstPlayerQuitTest() {
        text = "start <@U2345|player2>";
        controller.onReceiveSlashCommand(token,
                teamId, teamDomain, channelId,
                channelName, userId,
                userName, command, text, responseUrl);

        TicTacToe game = ongoingTestGames.getGameForChannel(new Channel(channelId, channelName));

        text = "quit";

        RichMessage actualMessage = controller.onReceiveSlashCommand(token,
                teamId, teamDomain, channelId,
                channelName, userId,
                userName, command, text, responseUrl);


        QuitResponse quitResponse = new QuitResponse(game.toString(), game.getResult());

        RichMessage expectedMessage = new RichMessage(quitResponse.toString());
        expectedMessage.encodedMessage();

        assertEquals(expectedMessage.getText(), actualMessage.getText());
    }


    @Test
    public void secondPlayerQuitTest() {
        text = "start <@U2345|player2>";
        controller.onReceiveSlashCommand(token,
                teamId, teamDomain, channelId,
                channelName, userId,
                userName, command, text, responseUrl);

        TicTacToe game = ongoingTestGames.getGameForChannel(new Channel(channelId, channelName));

        text = "quit";

        RichMessage actualMessage = controller.onReceiveSlashCommand(token,
                teamId, teamDomain, channelId,
                channelName, "U2345",
                "player2", command, text, responseUrl);

        QuitResponse quitResponse = new QuitResponse(game.toString(), game.getResult());

        RichMessage expectedMessage = new RichMessage(quitResponse.toString());
        expectedMessage.encodedMessage();

        assertEquals(expectedMessage.getText(), actualMessage.getText());
    }

    @Test
    public void quitNoGameInProgressTest() {
        text = "quit";

        RichMessage actualMessage = controller.onReceiveSlashCommand(token,
                teamId, teamDomain, channelId,
                channelName, userId,
                userName, command, text, responseUrl);

        QuitResponse showResponse= new QuitResponse(new NoGameInProgressException(TTTExceptionMessages
                .NO_GAME_IN_PROGRESS));

        RichMessage expectedMessage = new RichMessage(showResponse.toString());
        expectedMessage.encodedMessage();

        assertEquals(expectedMessage.getText(), actualMessage.getText());
    }

    @Test
    public void quitPlayerNotInGame() {
        text = "start <@U2345|player2>";
        controller.onReceiveSlashCommand(token,
                teamId, teamDomain, channelId,
                channelName, userId,
                userName, command, text, responseUrl);

        TicTacToe game = ongoingTestGames.getGameForChannel(new Channel(channelId, channelName));

        text = "quit";

        RichMessage actualMessage = controller.onReceiveSlashCommand(token,
                teamId, teamDomain, channelId,
                channelName, "U23456",
                "player200", command, text, responseUrl);

        QuitResponse showResponse= new QuitResponse(new NoGameInProgressException(TTTExceptionMessages
                .INCORRECT_PLAYER_PLAY));

        RichMessage expectedMessage = new RichMessage(showResponse.toString());
        expectedMessage.encodedMessage();

        assertEquals(expectedMessage.getText(), actualMessage.getText());
    }
}
