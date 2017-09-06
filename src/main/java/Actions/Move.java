package Actions;

import Enums.EndConfig;
import Exceptions.IncorrectPlayerException;
import Exceptions.InvalidMoveException;
import Exceptions.NoGameInProgressException;
import Exceptions.TTTExceptionMessage;
import Helpers.TicTacToeHelper;
import Input.MoveRequest;
import Interfaces.Request;
import Interfaces.Response;
import Model.Player;
import Model.TicTacToe;
import Output.MoveResponse;

import java.util.ArrayList;
import java.util.List;

public class Move extends TTTAction {
    private MoveRequest moveRequest;
    private MoveResponse moveResponse;

    /**
     * {@inheritDoc}
     *
     * The move action is not allowed for a user when:
     * - no game is in progress.
     * - the wrong player tried to play their turn.
     * - an unassociated player tried to move.
     *
     * In all other cases it attempts to make a move. If the move is invalid for any reason, it responds accordingly.
     * A move may be invalid if the number is less than 1 or greater than 9.
     * Or the position is already occupied.
     *
     * If the move turns out to be a game concluding move, it removes the game's association with the channel.
     */
    @Override
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

        String nextPlayerId;
        EndConfig endConfig;
        List<String> moveDescriptions = new ArrayList<>();
        do {
            try {
                String description = ttt.playMove(moveRequest.getMove());
                moveDescriptions.add(description);
            } catch (InvalidMoveException e) {
                moveResponse = new MoveResponse(e);
                return;
            }
            nextPlayerId = ttt.getNextPlayer().getId();
            endConfig = ttt.getEndConfig();
        } while (nextPlayerId.equals(Player.BOT_ID) && endConfig.equals(EndConfig.NONE));

        if (!endConfig.equals(EndConfig.NONE)) {
            ongoingGames.endGameInChannel(moveRequest.getChannel());
        }
        moveResponse = new MoveResponse(ttt.toString(), ttt.getResult(), endConfig,
                ttt.getPlayer(moveRequest.getPlayerId()), ttt.getNextPlayer(),
                moveDescriptions);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setRequest(Request request) {
        moveRequest = (MoveRequest) request;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Response getResponse() {
        return moveResponse;
    }
}
