package Actions;

import Exceptions.NoGameInProgressException;
import Exceptions.TTTExceptionMessage;
import Input.ShowRequest;
import Interfaces.Request;
import Interfaces.Response;
import Model.TicTacToe;
import Output.ShowResponse;

public class Show extends TTTAction {
    private ShowRequest showRequest;
    private ShowResponse showResponse;

    /**
     * {@inheritDoc}
     *
     * Any one is the channel can view the status of an ongoing game if there is one.
     */
    public void run() {
        TicTacToe ttt = ongoingGames.getGameForChannel(showRequest.getChannel());
        if (ttt == null) {
            showResponse = new ShowResponse(new NoGameInProgressException(TTTExceptionMessage.NO_GAME_IN_PROGRESS));
            return;
        }
        showResponse = new ShowResponse(ttt.toString(), ttt.getPlayers(), ttt.getNextPlayer());
    }

    @Override
    public void setRequest(Request request) {
        showRequest = (ShowRequest) request;
    }

    @Override
    public Response getResponse() {
        return showResponse;
    }
}
