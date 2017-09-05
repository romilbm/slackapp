package Model;

import Enums.EndConfig;
import Exceptions.InvalidMoveException;

import java.util.Random;

/**
 * The Tic Tac Toe game. It has the game board, the players who are playing the game
 * and it keeps a track of the next player to play, the final result and the mid (and end) game state.
 */
public class TicTacToe {
    
    private static final int HSPACE = 20;
    private int[][] board;
    private int playerIndex;
    private Player[] players;
    private Player nextPlayer;
    private TTTResult result;
    private EndConfig endConfig;

    /**
     * Initializes the board. Randomly selects a player to play first.
     * @param players
     */
    public TicTacToe(Player[] players) {
        this.players = players;
        board = new int[3][3];
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                board[i][j] = 0;
            }
        }
        this.playerIndex = new Random().nextInt(2);
        nextPlayer = this.players[playerIndex];
    }

    /**
     * Calculates if the current config is a win, draw or none. It set the value in an Enum {@link EndConfig}
     * @return The current value of the {@link EndConfig}.
     */
    private EndConfig calculateEndConfig() {
        endConfig = EndConfig.WIN;
        // rows
        for (int i = 0; i < 3; i++) {
            if ((board[i][0] != 0) && (board[i][0] == board[i][1]) &&
                    (board[i][0] == board[i][2]))

            {
                return endConfig;
            }
        }
        // columns
        for (int i = 0; i < 3; i++) {
            if ((board[0][i] != 0) && (board[0][i] == board[1][i]) &&
                    (board[0][i] == board[2][i]))

            {
                return endConfig;
            }
        }
        // diags
        if ((board[0][0] != 0) && (board[0][0] == board[1][1]) &&
                (board[0][0] == board[2][2])) {
            return endConfig;
        }

        if ((board[2][0] != 0) && (board[2][0] == board[1][1]) &&
                (board[2][0] == board[0][2]))

        {
            return endConfig;
        }

        // draw
        endConfig = EndConfig.DRAW;
        for (int i = 0; i < 3; i++)
            for (int j = 0; j < 3; j++) {
                if (board[i][j] == 0)
                    endConfig = EndConfig.NONE;
            }
        return endConfig;

    }

    /**
     * Gets the string representation of a row of the board.
     * @param row The row whose string representation we want.
     * @return
     */
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

    /**
     * Get the string representation of the entire board.
     * The is enclosed in block representation in Slack so that it appears as is without any change to the formatting.
     * @return
     */
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("```\n");
        // iterate through the rows
        for (int i = 0; i < 3; i++) {
            sb.append(getRowString(i));
        }
        sb.append("\n```");
        return sb.toString();
    }

    /**
     * Checks if the player with id playerId is the next player to play.
     * @param playerId
     * @return true of they are, false if they are not.
     */
    public boolean isNextPlayer(String playerId) {
        if (playerId.equals(nextPlayer.getId())) return true;
        return false;
    }

    /**
     * Takes the next player and plays the current move for them.
     * If it results in a conclusion of the game, then it computes the {@link TTTResult} as well.
     * @param position the position on the board to put their symbol on.
     * @return The current {@link EndConfig}
     * @throws InvalidMoveException if the position value is less than 1
     * or more than 9 or the position is already occupied.
     *
     */
    public EndConfig playMove(int position) throws InvalidMoveException {
        nextPlayer.move(board, position);
        EndConfig endConfig = calculateEndConfig();
        if (!endConfig.equals(EndConfig.NONE)) {
            result = new TTTResult(players, endConfig, nextPlayer);
        } else {
            playerIndex++;
            nextPlayer = players[playerIndex % 2];
        }

//        if (nextPlayer)
        return endConfig;
    }

    private EndConfig playAIMove() throws InvalidMoveException {
        try {
            nextPlayer.move(board, null);
        } catch (InvalidMoveException e) {
            throw new InvalidMoveException("The AI played in invalid move! Log a ticket to the developers!");
        }

        EndConfig endConfig = calculateEndConfig();
        if (!endConfig.equals(EndConfig.NONE)) {
            result = new TTTResult(players, endConfig, nextPlayer);
        } else {
            playerIndex++;
            nextPlayer = players[playerIndex % 2];
        }
        return endConfig;
    }

    /**
     * The Result of the game.
     * @return
     */
    public TTTResult getResult() {
        return result;
    }

    /**
     * The next player whose turn it is.
     * @return
     */
    public Player getNextPlayer() {
        return nextPlayer;
    }

    /**
     * Checks if the player with playerId is one of the players playing the game.
     * @param playerId
     * @return
     */
    public boolean isValidPlayer(String playerId) {
        if (players[0].getId().equals(playerId) || players[1].getId().equals(playerId)) return true;
        return false;
    }

    /**
     * Make the player with playerId lose the game. Then it computes the final result and returns it.
     * @param playerId
     * @return
     */
    public TTTResult quit(String playerId) {
        Player winner = players[0].getId().equals(playerId) ? players[1] : players[0];
        result = new TTTResult(players, EndConfig.WIN, winner);
        return result;
    }

    /**
     * get the players playing the game.
     * @return
     */
    public Player[] getPlayers() {
        return players;
    }

    /**
     * get the player with playerId if they are one of the ones playing the game. Else return null.
     * @param playerId
     * @return
     */
    public Player getPlayer(String playerId) {
        if (players[0].getId().equals(playerId)) return players[0];
        if (players[1].getId().equals(playerId)) return players[1];
        return null;
    }
}
