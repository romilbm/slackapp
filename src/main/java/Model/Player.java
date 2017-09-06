package Model;

import Enums.Symbol;
import Exceptions.InvalidMoveException;
import Interfaces.MoveMethod;

public class Player {
    public static String BOT_NAME = "DUMB BOT";
    public static String BOT_ID = "TTTBOT";
    private String name;
    private String id;
    private MoveMethod moveStrategy;
    private Symbol symbol;

    public Player(String name, String id, MoveMethod moveStrategy, Symbol symbol) {
        this.name = name;
        this.id = id;
        this.moveStrategy = moveStrategy;
        this.symbol = symbol;
    }

    public Symbol getSymbol() {
        return symbol;
    }


    public String getName() {
        return name;
    }

    public String move(int[][] board, Integer position) throws InvalidMoveException {
        return moveStrategy.move(board, symbol, position);
    }

    public String toString() {
        return name + " " + (symbol.equals(Symbol.X) ? "X" : "0");
    }

    public String getId() {
        return id;
    }
}
