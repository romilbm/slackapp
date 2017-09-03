package Actions;

import DataAccess.OngoingGames;
import Exceptions.GameInProgressException;
import Exceptions.TTTExceptions;
import Input.StartRequest;
import Interfaces.DataAccessor;
import Model.Player;
import Model.TicTacToe;
import Output.StartResponse;

public class Start {
    private StartRequest startRequest;
    private DataAccessor ongoingGames;
    private StartResponse startResponse;

    public Start(StartRequest startRequest) {
        this.startRequest = startRequest;
        ongoingGames = OngoingGames.getInstance();
    }

    public void run() {
        TicTacToe ttt = ongoingGames.getGameForChannel(startRequest.getChannel());
        if (ttt != null) {
            startResponse = new StartResponse(new GameInProgressException(TTTExceptions.GAME_IN_PROGRESS));
            return;
        }

        Player[] players = new Player[]{startRequest.getP1(), startRequest.getP2()};
        ttt = new TicTacToe(players);
        ongoingGames.setGameForChannel(ttt, startRequest.getChannel());
        startResponse = new StartResponse(ttt.getNextPlayer(), ttt.getPlayers(), ttt.toString());
    }

    public StartResponse getStartResponse() {
        return startResponse;
    }
}
