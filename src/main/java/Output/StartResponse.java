package Output;

import Enums.Symbol;
import Interfaces.Response;
import Model.Player;
import ResponseStrings.ResponseMessages;

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
        sb.append(ResponseMessages.OPENING_MESSAGE);
        sb.append(String.format(ResponseMessages.VERSUS_FORMAT, participants[0].getName(),
                participants[0].getSymbol(), participants[1].getName(), participants[1].getSymbol()));
        if (description != null) {
            sb.append(String.format(ResponseMessages.PLAYER_MOVE_FORMAT, Player.BOT_NAME, Symbol.ZERO,
                    description));
        }
        sb.append(String.format(ResponseMessages.NEXT_PLAYER_FORMAT, nextPlayer.getName()));
        sb.append(ResponseMessages.INSTRUCTIONS);
        sb.append(String.format(ResponseMessages.GAME_FORMAT, gameState));

        return sb.toString();
    }
}
