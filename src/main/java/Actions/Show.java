package Actions;

import Exceptions.NoGameInProgressException;
import Input.ShowRequest;
import Interfaces.Request;
import Interfaces.Response;
import Model.TicTacToe;
import Output.ShowResponse;
import ResponseStrings.TTTExceptionMessages;

public class Show extends TTTAction {
    public static final String OPTION_TEXT = "show";
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
            showResponse = new ShowResponse(new NoGameInProgressException(TTTExceptionMessages.NO_GAME_IN_PROGRESS));
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
