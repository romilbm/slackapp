package Move;

import Enums.Symbol;
import Exceptions.InvalidMoveException;
import Helpers.TicTacToeHelper;
import Interfaces.MoveMethod;

class AIMove implements MoveMethod {
    @Override
    public String move(int[][] board, Symbol symbolToPut, Integer position) throws InvalidMoveException {
        int posn = move(board, symbolToPut);
        return TicTacToeHelper.getPosDescription(posn);
    }

    private int move(int[][] board, Symbol symbolToPut) {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (board[i][j] == 0) {
                    board[i][j] = symbolToPut.equals(Symbol.X) ? 1 : -1;
                    return (i * 3 + j + 1);
                }
            }
        }
        return 0;
    }
}
