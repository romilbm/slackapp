package Interfaces;

import Model.Channel;
import Model.TicTacToe;

public interface DataAccessor {
    static DataAccessor getInstance() {
        return null;
    }

    TicTacToe getGameForChannel(Channel channel);
    void setGameForChannel(TicTacToe ticTacToe, Channel channel);
    void endGameInChannel(Channel channel);
}
