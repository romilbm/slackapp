package Actions;

import Exceptions.IncorrectPlayerException;
import Exceptions.NoGameInProgressException;
import Exceptions.TTTExceptionMessage;
import Input.QuitRequest;
import Interfaces.Request;
import Interfaces.Response;
import Model.TTTResult;
import Model.TicTacToe;
import Output.QuitResponse;

public class Quit extends TTTAction {
    private QuitRequest quitRequest;
    private QuitResponse quitResponse;

    public Quit() {

    }

    public void run() {
        TicTacToe ttt = ongoingGames.getGameForChannel(quitRequest.getChannel());
        if (ttt == null) {
            quitResponse = new QuitResponse(new NoGameInProgressException(TTTExceptionMessage.NO_GAME_IN_PROGRESS));
            return;
        }

        if (!ttt.isValidPlayer(quitRequest.getPlayerId())) {
            quitResponse = new QuitResponse(new IncorrectPlayerException(TTTExceptionMessage.INCORRECT_PLAYER_PLAY));
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
