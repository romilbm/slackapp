package Output;

import Enums.EndConfig;
import Enums.Symbol;
import Interfaces.Response;
import Model.Player;
import Model.TTTResult;
import ResponseStrings.ResponseMessages;

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
        sb.append(String.format(ResponseMessages.PLAYER_MOVE_FORMAT, currentPlayer.getName(),
                currentPlayer.getSymbol(), positionDescriptions.get(0)));

        if (positionDescriptions.size() > 1) {
            sb.append(String.format(ResponseMessages.PLAYER_MOVE_FORMAT, Player.BOT_NAME,
                    Symbol.ZERO, positionDescriptions.get(1)));
        }
        sb.append(String.format(ResponseMessages.GAME_FORMAT, gameState));

        if (!endConfig.equals(EndConfig.NONE)) {
            sb.append(String.format(ResponseMessages.GAME_OVER_FORMAT, result));
        } else {
            sb.append(String.format(ResponseMessages.NEXT_PLAYER_FORMAT, nextPlayer.getName()));
        }

        return sb.toString();
    }
}
