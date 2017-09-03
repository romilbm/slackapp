package Actions;

import DataAccess.OngoingGames;
import Enums.WinConfig;
import Exceptions.IncorrectPlayerException;
import Exceptions.InvalidMoveException;
import Exceptions.NoGameInProgressException;
import Exceptions.TTTExceptions;
import Helpers.TicTacToeHelper;
import Input.MoveRequest;
import Interfaces.DataAccessor;
import Model.TicTacToe;
import Output.MoveResponse;

public class Move {
    private MoveRequest moveRequest;
    private MoveResponse moveResponse;
    private DataAccessor ongoingGames;

    public Move(MoveRequest moveRequest) {
        this.moveRequest = moveRequest;
        ongoingGames = OngoingGames.getInstance();
    }

    public void run() {
        TicTacToe ttt = ongoingGames.getGameForChannel(moveRequest.getChannel());
        if (ttt == null) {
            moveResponse = new MoveResponse(new NoGameInProgressException(TTTExceptions.NO_GAME_IN_PROGRESS));
            return;
        }

        if (!ttt.isValidPlayer(moveRequest.getPlayerId())) {
            moveResponse = new MoveResponse(new IncorrectPlayerException(TTTExceptions.INCORRECT_PLAYER_PLAY));
            return;
        }

        if (!ttt.isNextPlayer(moveRequest.getPlayerId())) {
            moveResponse = new MoveResponse(new IncorrectPlayerException(TTTExceptions.INCORRECT_PLAYER_TURN));
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

    public MoveResponse getMoveResponse() {
        return moveResponse;
    }
}
