package Interfaces;

import Model.Channel;
import Model.TicTacToe;

public interface DataAccessor {
    static DataAccessor getInstance() {
        return null;
    }

    TicTacToe getGameForChannel(Channel channel);
    void setGameForChannel(TicTacToe ticTacToe, Channel channel) throws IllegalStateException;
    void endGameInChannel(Channel channel) throws IllegalStateException;
}
