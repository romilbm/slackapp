package DataAccess;

import Interfaces.DataAccessor;
import Model.Channel;
import Model.TicTacToe;

import java.util.HashMap;
import java.util.Map;

public class OngoingGames implements DataAccessor {
    private Map<Channel, TicTacToe> ongoingGamesMap;
    private static OngoingGames ongoingGames;

    private OngoingGames() {
        ongoingGamesMap = new HashMap<>();
    }

    public static DataAccessor getInstance() {
        if (ongoingGames == null) {
            ongoingGames = new OngoingGames();
        }
        return ongoingGames;
    }

    private Map<Channel, TicTacToe> getOngoingGamesMap() {
        return ongoingGamesMap;
    }

    public TicTacToe getGameForChannel(Channel channel) {
        if (ongoingGames.getOngoingGamesMap().containsKey(channel)) return null;
        return ongoingGames.getOngoingGamesMap().get(channel);
    }

    public void setGameForChannel(TicTacToe ticTacToe, Channel channel) {
        if (ongoingGames.getOngoingGamesMap().containsKey(channel)) {
            throw new IllegalStateException();
        }
        ongoingGames.getOngoingGamesMap().put(channel, ticTacToe);
    }

    public void endGameInChannel(Channel channel) {
        if (ongoingGames.getOngoingGamesMap().containsKey(channel)) {
            throw new IllegalStateException();
        }
        ongoingGames.getOngoingGamesMap().remove(channel);
    }
}
