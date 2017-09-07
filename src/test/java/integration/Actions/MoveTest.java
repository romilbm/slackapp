package integration.Actions;

import Exceptions.InvalidMoveException;
import Helpers.TicTacToeHelper;
import Model.Channel;
import Model.RichMessage;
import Model.TicTacToe;
import Output.MoveResponse;
import ResponseStrings.TTTExceptionMessages;
import integration.TTTTest;
import org.junit.Test;

import java.util.Arrays;

import static org.junit.Assert.assertEquals;

public class MoveTest extends TTTTest {
    @Test
    public void simpleMoveTest() {
        text = "start <@U2345|player2>";
        controller.onReceiveSlashCommand(token,
                teamId, teamDomain, channelId,
                channelName, userId,
                userName, command, text, responseUrl);

        text = "move 5";
        TicTacToe game = ongoingTestGames.getGameForChannel(new Channel(channelId, channelName));
        game.setNextPlayerFirstPlayer();

        RichMessage actualMesage = controller.onReceiveSlashCommand(token,
                teamId, teamDomain, channelId,
                channelName, userId,
                userName, command, text, responseUrl);


        MoveResponse moveResponse = new MoveResponse(game.toString(), game.getResult(), game
                .getEndConfig(), game.getPlayer(userId), game.getNextPlayer(), Arrays.asList(TicTacToeHelper
                .getPosDescription(5)));

        RichMessage expectedMessage = new RichMessage(moveResponse.toString());
        expectedMessage.encodedMessage();

        assertEquals(expectedMessage.getText(), actualMesage.getText());
    }

    @Test
    public void invalidMoveTest() {
        text = "start <@U2345|player2>";
        controller.onReceiveSlashCommand(token,
                teamId, teamDomain, channelId,
                channelName, userId,
                userName, command, text, responseUrl);

        text = "move 10";
        TicTacToe game = ongoingTestGames.getGameForChannel(new Channel(channelId, channelName));
        game.setNextPlayerFirstPlayer();

        RichMessage actualMesage = controller.onReceiveSlashCommand(token,
                teamId, teamDomain, channelId,
                channelName, userId,
                userName, command, text, responseUrl);

        MoveResponse moveResponse = new MoveResponse(new InvalidMoveException(TTTExceptionMessages.INVALID_MOVE));

        RichMessage expectedMessage = new RichMessage(moveResponse.toString());
        expectedMessage.encodedMessage();

        assertEquals(expectedMessage.getText(), actualMesage.getText());
    }
}
