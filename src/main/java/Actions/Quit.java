package Actions;

import Exceptions.IncorrectPlayerException;
import Exceptions.NoGameInProgressException;
import Input.QuitRequest;
import Interfaces.Request;
import Interfaces.Response;
import Model.TTTResult;
import Model.TicTacToe;
import Output.QuitResponse;
import ResponseStrings.TTTExceptionMessages;

public class Quit extends TTTAction {
    public static final String OPTION_TEXT = "quit";
    private QuitRequest quitRequest;
    private QuitResponse quitResponse;

    /**
     * {@inheritDoc}
     *
     * A player not a part of the ongoing game is not allowed to quit the game.
     * No one can quit a game when there is no ongoing game.
     * When a user quits a game, the game's association with the channel is removed so that a new game can be started.
     */
    public void run() {
        TicTacToe ttt = ongoingGames.getGameForChannel(quitRequest.getChannel());
        if (ttt == null) {
            quitResponse = new QuitResponse(new NoGameInProgressException(TTTExceptionMessages.NO_GAME_IN_PROGRESS));
            return;
        }

        if (!ttt.isValidPlayer(quitRequest.getPlayerId())) {
            quitResponse = new QuitResponse(new IncorrectPlayerException(TTTExceptionMessages.INCORRECT_PLAYER_PLAY));
            return;
        }

        TTTResult result = ttt.quit(quitRequest.getPlayerId());
        ongoingGames.endGameInChannel(quitRequest.getChannel());
        quitResponse = new QuitResponse(ttt.toString(), result);
    }

    @Override
    public void setRequest(Request request) {
        quitRequest = (QuitRequest) request;
    }

    @Override
    public Response getResponse() {
        return quitResponse;
    }
}
