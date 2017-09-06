package Output;

import Enums.EndConfig;
import Enums.Symbol;
import Interfaces.Response;
import Model.Player;
import Model.TTTResult;

import java.util.List;

public class MoveResponse implements Response {
    private String gameState;
    private EndConfig endConfig;
    private TTTResult result;
    private Player currentPlayer;
    private Player nextPlayer;
    private List<String> positionDescriptions;
    private Exception exception;

    public MoveResponse(Exception exception) {
        this.exception = exception;
    }

    public MoveResponse(String s, TTTResult result, EndConfig endConfig, Player currentPlayer, Player
            nextPlayer, List<String> positionDescriptions) {
        this.gameState = s;
        this.result = result;
        this.endConfig = endConfig;
        this.currentPlayer = currentPlayer;
        this.nextPlayer = nextPlayer;
        this.positionDescriptions = positionDescriptions;
    }

    public String toString() {
        if (exception != null) {
            return exception.getMessage();
        }

        StringBuilder sb = new StringBuilder();
        sb.append(currentPlayer.getName() + " put a " + currentPlayer.getSymbol() + " in the " +
                positionDescriptions.get(0));
        if (positionDescriptions.size() > 1) {
            sb.append("\n");
            sb.append("Dumb Bot" + " put a " + Symbol.ZERO + " in the " +
                    positionDescriptions.get(1));
        }
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
