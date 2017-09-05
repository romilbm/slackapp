package Actions;

import Exceptions.GameInProgressException;
import Exceptions.TTTExceptionMessage;
import Input.StartRequest;
import Interfaces.Request;
import Interfaces.Response;
import Model.Player;
import Model.TicTacToe;
import Output.StartResponse;

public class Start extends TTTAction {
    private StartRequest startRequest;
    private StartResponse startResponse;

    public Start() {

    }

    /**
     * {@inheritDoc}
     * If a game is in progress in the channel, a new game cannot be started.
     * Barring that, a new game will be started between two players.
     * That game will be associated with that channel and no other game can start till this game is ongoing.
     */
    public void run() {
        TicTacToe ttt = ongoingGames.getGameForChannel(startRequest.getChannel());
        if (ttt != null) {
            startResponse = new StartResponse(new GameInProgressException(TTTExceptionMessage.GAME_IN_PROGRESS));
            return;
        }

        Player[] players = new Player[]{startRequest.getP1(), startRequest.getP2()};
        ttt = new TicTacToe(players);
        ongoingGames.setGameForChannel(ttt, startRequest.getChannel());
        startResponse = new StartResponse(ttt.getNextPlayer(), ttt.getPlayers(), ttt.toString());
    }

    @Override
    public void setRequest(Request request) {
        startRequest = (StartRequest) request;
    }

    @Override
    public Response getResponse() {
        return startResponse;
    }
}
