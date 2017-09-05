package Move;

import Enums.Symbol;
import Exceptions.InvalidMoveException;
import Exceptions.TTTExceptionMessage;
import Helpers.TicTacToeHelper;
import Interfaces.MoveMethod;

public class HumanMove implements MoveMethod {

    public String move(int[][] board, Symbol symbolToPut, Integer position) throws InvalidMoveException {
        if (position > 9 ||
             position < 1) {
            throw new InvalidMoveException(TTTExceptionMessage.INVALID_MOVE);
        }

        int x_coord = (position - 1) / 3;
        int y_coord = (position - 1) % 3;

        if (board[x_coord][y_coord] != 0) throw new InvalidMoveException(TTTExceptionMessage.INVALID_MOVE);
        board[x_coord][y_coord] = symbolToPut.equals(Symbol.X) ? 1 : -1;
        return TicTacToeHelper.getPosDescription(position);
    }
}
