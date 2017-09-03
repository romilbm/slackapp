package Output;

import Enums.WinConfig;
import Model.Player;

public class ShowResponse {
    private String gameState;
    private Player[] players;
    private WinConfig winConfig;
    private Exception exception;

    public ShowResponse(Exception exception) {
        this.exception = exception;
    }

    public String getGameState() {
        return gameState;
    }

    public Player[] getPlayers() {
        return players;
    }

    public WinConfig getWinConfig() {
        return winConfig;
    }

    public Exception getException() {
        return exception;
    }

    public ShowResponse(String gameState, Player[] players, WinConfig winConfig) {
        this.gameState = gameState;
        this.players = players;
        this.winConfig = winConfig;
    }
}
