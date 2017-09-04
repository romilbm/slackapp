package Output;

import Model.Player;

public class StartResponse {
    private Player nextPlayer;
    private Exception exception;
    private Player[] participants;
    private String gameState;

    public StartResponse(Exception exception) {
        this.exception = exception;
    }

    public StartResponse(Player nextPlayer, Player[] participants, String gameState) {
        this.nextPlayer = nextPlayer;
        this.participants = participants;
        this.gameState = gameState;
    }

    public Player getNextPlayer() {
        return nextPlayer;
    }

    public Exception getException() {
        return exception;
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
        sb.append("Next Turn: @" + nextPlayer.getName());
        sb.append("\n");
        sb.append("Please make your move selection by entering "
                + "a number 1-9 corresponding to the movement "
                + "key on the right.\n");
        sb.append(gameState);

        return sb.toString();
    }
}
