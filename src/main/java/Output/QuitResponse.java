package Output;

import Model.TTTResult;

public class QuitResponse {
    private String gameState;
    private TTTResult result;
    private Exception exception;

    public String getGameState() {
        return gameState;
    }

    public TTTResult getResult() {
        return result;
    }

    public Exception getException() {
        return exception;
    }

    public QuitResponse(Exception exception) {
        this.exception = exception;
    }

    public QuitResponse(String gameState, TTTResult result) {
        this.gameState = gameState;
        this.result = result;
    }
}
