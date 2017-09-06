package Output;

import Enums.Symbol;
import Interfaces.Response;
import Model.Player;

public class StartResponse implements Response {
    private Player nextPlayer;
    private Exception exception;
    private Player[] participants;
    private String gameState;
    private String description;

    public StartResponse(Exception exception) {
        this.exception = exception;
    }

    public StartResponse(Player nextPlayer, Player[] participants, String gameState, String description) {
        this.nextPlayer = nextPlayer;
        this.participants = participants;
        this.gameState = gameState;
        this.description = description;
    }

    public String toString() {
        if (exception != null) {
            return exception.getMessage();
        }

        StringBuilder sb = new StringBuilder();
        sb.append("Tic Tac Toe game started.\n");
        sb.append(participants[0]);
        sb.append(" v/s ");
        sb.append(participants[1]);
        sb.append("\n");
        if (description != null) {
            sb.append(Player.BOT_NAME + " put a " + Symbol.ZERO + " in the " + description);
            sb.append("\n");
        }
        sb.append("Next Turn: @" + nextPlayer.getName());
        sb.append("\n");
        sb.append("Please make your move selection by entering "
                + "a number 1-9 corresponding to the movement "
                + "key on the right.\n");
        sb.append(gameState);

        return sb.toString();
    }
}
