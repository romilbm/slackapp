package Output;

import Enums.WinConfig;
import Interfaces.Response;
import Model.Player;
import Model.TTTResult;

public class MoveResponse implements Response {
    private String gameState;
    private WinConfig winConfig;
    private TTTResult result;
    private Player currentPlayer;
    private Player nextPlayer;
    private String positionDescription;
    private Exception exception;

    public MoveResponse(Exception exception) {
        this.exception = exception;
    }

    public MoveResponse(String s, TTTResult result, WinConfig winConfig, Player currentPlayer, Player nextPlayer, String posDescription) {
        this.gameState = s;
        this.result = result;
        this.winConfig = winConfig;
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

        if (!winConfig.equals(WinConfig.NONE)) {
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
