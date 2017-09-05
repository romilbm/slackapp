package Actions;

import Enums.WinConfig;
import Exceptions.IncorrectPlayerException;
import Exceptions.InvalidMoveException;
import Exceptions.NoGameInProgressException;
import Exceptions.TTTExceptionMessage;
import Helpers.TicTacToeHelper;
import Input.MoveRequest;
import Interfaces.Request;
import Interfaces.Response;
import Model.TicTacToe;
import Output.MoveResponse;

public class Move extends TTTAction {
    private MoveRequest moveRequest;
    private MoveResponse moveResponse;

    public Move() {

    }

    public void run() {
        TicTacToe ttt = ongoingGames.getGameForChannel(moveRequest.getChannel());

        if (ttt == null) {
            moveResponse = new MoveResponse(new NoGameInProgressException(TTTExceptionMessage.NO_GAME_IN_PROGRESS));
            return;
        }

        if (!ttt.isValidPlayer(moveRequest.getPlayerId())) {
            moveResponse = new MoveResponse(new IncorrectPlayerException(TTTExceptionMessage.INCORRECT_PLAYER_PLAY));
            return;
        }

        if (!ttt.isNextPlayer(moveRequest.getPlayerId())) {
            moveResponse = new MoveResponse(new IncorrectPlayerException(TTTExceptionMessage.INCORRECT_PLAYER_TURN));
            return;
        }

        WinConfig winConfig;
        try {
            winConfig = ttt.playMove(moveRequest.getMove());
        } catch (InvalidMoveException e) {
            moveResponse = new MoveResponse(e);
            return;
        }

        if (!winConfig.equals(WinConfig.NONE)) {
            ongoingGames.endGameInChannel(moveRequest.getChannel());
        }
        moveResponse = new MoveResponse(ttt.toString(), ttt.getResult(), winConfig,
                ttt.getPlayer(moveRequest.getPlayerId()), ttt.getNextPlayer(),
                TicTacToeHelper.getPosDescription(moveRequest.getMove()));
    }

    @Override
    public void setRequest(Request request) {
        moveRequest = (MoveRequest) request;
    }

    @Override
    public Response getResponse() {
        return moveResponse;
    }
}
