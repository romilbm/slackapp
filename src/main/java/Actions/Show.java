package Actions;

import DataAccess.OngoingGames;
import Exceptions.NoGameInProgressException;
import Exceptions.TTTExceptions;
import Input.ShowRequest;
import Interfaces.DataAccessor;
import Model.TicTacToe;
import Output.ShowResponse;

public class Show {
    private DataAccessor ongoingGames;
    private ShowRequest showRequest;
    private ShowResponse showResponse;

    public Show(ShowRequest showRequest) {
        this.showRequest = showRequest;
        ongoingGames = OngoingGames.getInstance();
    }

    public void run() {
        TicTacToe ttt = ongoingGames.getGameForChannel(showRequest.getChannel());
        if (ttt == null) {
            showResponse = new ShowResponse(new NoGameInProgressException(TTTExceptions.NO_GAME_IN_PROGRESS));
        }
        showResponse = new ShowResponse(ttt.toString(), ttt.getPlayers(), ttt.getWinConfig());
    }

    public ShowResponse getShowResponse() {
        return showResponse;
    }
}
