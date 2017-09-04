package Output;

import Model.TTTResult;

public class QuitResponse {
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
        return gameState + "\n" + result + "\n";
    }
}
