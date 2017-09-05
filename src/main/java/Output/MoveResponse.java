package Output;

import Enums.EndConfig;
import Interfaces.Response;
import Model.Player;
import Model.TTTResult;

public class MoveResponse implements Response {
    private String gameState;
    private EndConfig endConfig;
    private TTTResult result;
    private Player currentPlayer;
    private Player nextPlayer;
    private String positionDescription;
    private Exception exception;

    public MoveResponse(Exception exception) {
        this.exception = exception;
    }

    public MoveResponse(String s, TTTResult result, EndConfig endConfig, Player currentPlayer, Player nextPlayer, String posDescription) {
        this.gameState = s;
        this.result = result;
        this.endConfig = endConfig;
        this.currentPlayer = currentPlayer;
        this.nextPlayer = nextPlayer;
        this.positionDescription = posDescription;
    }

    public String toString() {
        if (exception != null) {
            return exception.getMessage();
        }

        StringBuilder sb = new StringBuilder();
        sb.append(currentPlayer.getName() + " put a " + currentPlayer.getSymbol() + " in the " + positionDescription);
        sb.append("\n");
        sb.append(gameState);
        sb.append("\n");

        if (!endConfig.equals(EndConfig.NONE)) {
            sb.append("Game Over");
            sb.append("\n");
            sb.append(result);
        } else {
            sb.append("Next Player: " + nextPlayer.getName());
            sb.append("\n");
        }

        return sb.toString();
    }
}
