package Output;

import Interfaces.Response;
import Model.Player;
import ResponseStrings.ResponseMessages;

public class ShowResponse implements Response {
    private String gameState;
    private Player[] players;
    private Player nextPlayer;
    private Exception exception;

    public ShowResponse(Exception exception) {
        this.exception = exception;
    }

    public ShowResponse(String gameState, Player[] players, Player nextPlayer) {
        this.gameState = gameState;
        this.players = players;
        this.nextPlayer = nextPlayer;
    }

    public String toString() {
        if (exception != null)
            return exception.getMessage();
        return
                String.format(ResponseMessages.GAME_FORMAT, gameState)
            +   String.format(ResponseMessages.VERSUS_FORMAT, players[0].getName(), players[0].getSymbol()
                    , players[1].getName(), players[1].getSymbol())
            +   String.format(ResponseMessages.NEXT_PLAYER_FORMAT, nextPlayer.getName());
    }
}
