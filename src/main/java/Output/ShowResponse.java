package Output;

import Interfaces.Response;
import Model.Player;

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
            gameState + "\n" +
            "Players: @" + players[0] + " vs  @" + players[1] + "\n" +
            "Next Turn: @" + nextPlayer.getName();
    }
}
