package Interfaces;

import Model.Channel;
import Model.TicTacToe;

/**
 * The DataAccessor Interface. It provides the methods needed to access the data needed to run the game.
 */
public interface DataAccessor {
    /**
     * Provides a way to get the DataAccessor object.
     * This is a singleton.
     * @return The DataAccessor Object
     */
    static DataAccessor getInstance() {
        return null;
    }

    /**
     * Provides a way to the onging game in the channel if there is one.
     * @param channel The channel in which we want to get the on going game
     * @return The TicTacToe game going on in the channel. Null if there is no ongoing game.
     */
    TicTacToe getGameForChannel(Channel channel);

    /**
     * Provides a way to set a game as the ongoing game in the channel. If a game is going on and someone tries to set
     * another, it throws an ISE.
     * @param ticTacToe The TTT game.
     * @param channel The channel in which the game is going on.
     * @throws IllegalStateException If there is already a game going on and someone attempts to set another one.
     */
    void setGameForChannel(TicTacToe ticTacToe, Channel channel) throws IllegalStateException;

    /**
     * Provides a way to end an ongoing game in the channel. If there is none it will throw an ISE.
     * @param channel The channel in which we want to end the game.
     * @throws IllegalStateException If there is no game going on and someone tries to end it.
     */
    void endGameInChannel(Channel channel) throws IllegalStateException;
}
