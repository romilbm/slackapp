package unit.Move;

import Enums.Symbol;
import Exceptions.InvalidMoveException;
import Interfaces.MoveMethod;
import Move.HumanMove;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class HumanMoveUnitTest {
    @DataProvider
    public Object[][] humanMoveData() {
        int[][] board = new int[3][3];
        Symbol symbol = Symbol.X;

        board[0][1] = 1;
        board[0][2] = -1;

        Object[][] args = new Object[][] {
            new Object[] {board, symbol, 0, true},
            new Object[] {board, symbol, 9, true},
            new Object[] {board, symbol, 5, false},
            new Object[] {board, symbol, 1, true},
        };
        return args;
    }

    @Test(dataProvider = "humanMoveData")
    public void moveTest(int[][] board, Symbol symbolToPut, Integer position, boolean isException) {
        MoveMethod humanMove = new HumanMove();
        try {
            humanMove.move(board, symbolToPut, position);
        } catch (Exception e) {
            assertEquals(e.getClass(), InvalidMoveException.class);
            assertTrue(isException);
        }
    }
}
