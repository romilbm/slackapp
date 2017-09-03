package Model;

import Enums.WinConfig;
import Exceptions.InvalidMoveException;

import java.util.Random;

public class TicTacToe {
    
    private static final int HSPACE = 20;
    private int[][] board;
    private int playerIndex;
    private Player[] players;
    private Player nextPlayer;
    private TTTResult result;
    private WinConfig winConfig;

    public TicTacToe(Player[] p) {
        players = p;
        board = new int[3][3];
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                board[i][j] = 0;
            }
        }
        this.playerIndex = new Random().nextInt(2);
        nextPlayer = players[playerIndex];
    }

    private WinConfig calculateWinConfig() {
        winConfig = WinConfig.WIN;
        // rows
        for (int i = 0; i < 3; i++) {
            if ((board[i][0] != 0) && (board[i][0] == board[i][1]) &&
                    (board[i][0] == board[i][2]))

            {
                return winConfig;
            }
        }
        // columns
        for (int i = 0; i < 3; i++) {
            if ((board[0][i] != 0) && (board[0][i] == board[1][i]) &&
                    (board[0][i] == board[2][i]))

            {
                return winConfig;
            }
        }
        // diags
        if ((board[0][0] != 0) && (board[0][0] == board[1][1]) &&
                (board[0][0] == board[2][2])) {
            return winConfig;
        }

        if ((board[2][0] != 0) && (board[2][0] == board[1][1]) &&
                (board[2][0] == board[0][2]))

        {
            return winConfig;
        }

        // draw
        winConfig = WinConfig.DRAW;
        for (int i = 0; i < 3; i++)
            for (int j = 0; j < 3; j++) {
                if (board[i][j] == 0)
                    winConfig = WinConfig.NONE;
            }
        return winConfig;

    }

    private String getRowString(int row) {
        String s = "";
        for (int i = 0; i < 3; i++) {
            switch (board[row][i]) {
                case 0:
                    s += " ";
                    break;
                case -1:
                    s += "O";
                    break;
                case 1:
                    s += "X";
            }

            if (i != 3 - 1) {
                s += " | ";
            }
        }

        s += String.format("%" + HSPACE + "s", "");

        for (int i = 0; i < 3; i++) {
            s += row * 3 + i + 1;

            if (i == 3 - 1) {
                s += "\n";
            } else {
                s += " | ";
            }
        }
        return s;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        // iterate through the rows
        for (int i = 0; i < 3; i++) {
            sb.append(getRowString(i));
        }
        return sb.toString();
    }
    
    public boolean isNextPlayer(String playerId) {
        if (playerId.equals(nextPlayer.getId())) return true;
        return false;
    }

    public WinConfig playMove(int position) throws InvalidMoveException {
        nextPlayer.move(board, position);
        WinConfig w = calculateWinConfig();
        if (w == WinConfig.WIN) {
            result = new TTTResult(players, w, nextPlayer);
        } else {
            playerIndex++;
            nextPlayer = players[playerIndex % 2];
        }
        return w;
    }

    public TTTResult getResult() {
        return result;
    }

    public Player getNextPlayer() {
        return nextPlayer;
    }

    public boolean isValidPlayer(String playerId) {
        if (players[0].getId().equals(playerId) || players[1].getId().equals(playerId)) return true;
        return false;
    }

    public TTTResult quit(Player player) {
        Player winner = players[0].getId() == player.getId() ? players[1] : players[0];
        result = new TTTResult(players, WinConfig.WIN, winner);
        return result;
    }

    public WinConfig getWinConfig() {
        return winConfig;
    }

    public Player[] getPlayers() {
        return players;
    }

    public Player getPlayer(String playerId) {
        if (players[0].getId().equals(playerId)) return players[0];
        if (players[1].getId().equals(playerId)) return players[1];
        return null;
    }
}
