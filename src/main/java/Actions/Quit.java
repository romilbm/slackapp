package Actions;

import DataAccess.OngoingGames;
import Exceptions.IncorrectPlayerException;
import Exceptions.NoGameInProgressException;
import Exceptions.TTTExceptions;
import Input.QuitRequest;
import Interfaces.DataAccessor;
import Model.TTTResult;
import Model.TicTacToe;
import Output.QuitResponse;

public class Quit {
    private DataAccessor ongoingGames;
    private QuitRequest quitRequest;
    private QuitResponse quitResponse;


    public Quit(QuitRequest quitRequest) {
        this.quitRequest = quitRequest;
        ongoingGames = OngoingGames.getInstance();
    }

    public void run() {
        TicTacToe ttt = ongoingGames.getGameForChannel(quitRequest.getChannel());
        if (ttt == null) {
            quitResponse = new QuitResponse(new NoGameInProgressException(TTTExceptions.NO_GAME_IN_PROGRESS));
        }

        if (!ttt.isValidPlayer(quitRequest.getPlayer().getId())) {
            quitResponse = new QuitResponse(new IncorrectPlayerException(TTTExceptions.INCORRECT_PLAYER_PLAY));
        }

        TTTResult result = ttt.quit(quitRequest.getPlayer());
        ongoingGames.endGameInChannel(quitRequest.getChannel());
        quitResponse = new QuitResponse(ttt.toString(), result);
    }

    public QuitResponse getQuitResponse() {
        return quitResponse;
    }
}
