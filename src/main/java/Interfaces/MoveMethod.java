package Interfaces;

import Enums.Symbol;
import Exceptions.InvalidMoveException;


public interface MoveMethod {

    /**
     * Provide a way in which a player may move. The move can be for human or AI.
     *
     * @param board
     * @param symbolToPut
     * @return
     * @throws InvalidMoveException
     */
    String move(int[][] board, Symbol symbolToPut, Integer position) throws InvalidMoveException;
}