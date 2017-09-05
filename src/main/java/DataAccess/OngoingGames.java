package DataAccess;

import Interfaces.DataAccessor;
import Model.Channel;
import Model.TicTacToe;

import java.util.HashMap;
import java.util.Map;

/**
 * A simple implementation of the DataAccessor.
 */
public class OngoingGames implements DataAccessor {
    private Map<Channel, TicTacToe> ongoingGamesMap;
    private static OngoingGames ongoingGames;

    private OngoingGames() {
        ongoingGamesMap = new HashMap<>();
    }

    /**
     * Provides a way to get the DataAccessor object.
     * This is a singleton.
     * @return The DataAccessor Object
     */
    public static DataAccessor getInstance() {
        if (ongoingGames == null) {
            ongoingGames = new OngoingGames();
        }
        return ongoingGames;
    }

    private Map<Channel, TicTacToe> getOngoingGamesMap() {
        return ongoingGamesMap;
    }

    /**
     * {@inheritDoc}
     * @param channel The channel in which we want to get the on going game
     * @return
     */
    public TicTacToe getGameForChannel(Channel channel) {
        if (!ongoingGames.getOngoingGamesMap().containsKey(channel)) return null;
        return ongoingGames.getOngoingGamesMap().get(channel);
    }

    /**
     * {@inheritDoc}
     * @param ticTacToe The TTT game.
     * @param channel The channel in which the game is going on.
     * @throws IllegalStateException
     */
    public void setGameForChannel(TicTacToe ticTacToe, Channel channel) throws IllegalStateException {
        if (ongoingGames.getOngoingGamesMap().containsKey(channel)) {
            throw new IllegalStateException();
        }
        ongoingGames.getOngoingGamesMap().put(channel, ticTacToe);
    }

    /**
     * {@inheritDoc}
     * @param channel The channel in which we want to end the game.
     * @throws IllegalStateException
     */
    public void endGameInChannel(Channel channel) throws IllegalStateException {
        if (!ongoingGames.getOngoingGamesMap().containsKey(channel)) {
            throw new IllegalStateException();
        }
        ongoingGames.getOngoingGamesMap().remove(channel);
    }
}
