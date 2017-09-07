package Output;

import Interfaces.Response;
import Model.TTTResult;
import ResponseStrings.ResponseMessages;

public class QuitResponse implements Response {
    private String gameState;
    private TTTResult result;
    private Exception exception;

    public QuitResponse(Exception exception) {
        this.exception = exception;
    }

    public QuitResponse(String gameState, TTTResult result) {
        this.gameState = gameState;
        this.result = result;
    }

    public String toString() {
        if (exception != null) return exception.getMessage();
        return String.format(ResponseMessages.GAME_FORMAT, gameState);
    }
}
