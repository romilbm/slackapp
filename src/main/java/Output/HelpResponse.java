package Output;

import Interfaces.Response;

public class HelpResponse implements Response {
    public String toString() {
        return
                    "TTT Commands" + "\n"
                +   "/ttt followed by the options below:" + "\n"
                +   "1. To start a new game against your teammate: start @player" + "\n"
                +   "2. To start a new game with a bot: start" + "\n"
                +   "3. To make a move: move <position: number between 1-9>" + "\n"
                +   "4. To see the status of an ongoing game: show" + "\n"
                +   "5. To quit a game you were playing: quit" + "\n"
                +   "6. To see this menu: help"
                ;
    }
}
