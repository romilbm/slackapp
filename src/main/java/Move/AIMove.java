package Move;

import Enums.Symbol;
import Exceptions.InvalidMoveException;
import Interfaces.MoveMethod;

class AIMove implements MoveMethod {
    @Override
    public String move(int[][] board, Symbol symbolToPut, Integer position) throws InvalidMoveException {
        return null;
    }

//    public int move(int[][] board, Symbol symbolToPut) {
//        for (int i = 0; i < TicTacToe.N; i++) {
//            for (int j = 0; j < TicTacToe.N; j++) {
//                if (board[i][j] == 0) {
//                    board[i][j] = symbolToPut.equals(Symbol.X) ? 1 : -1;
//                    return (i * 3 + j + 1);
//                }
//            }
//        }
//        return 0;
//    }
}
