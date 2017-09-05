package Output;

import Interfaces.Response;

public class HelpResponse implements Response {
    public String toString() {
        return
                    "TTT Commands" + "\n"
                +   "/rottt followed by the options below:" + "\n"
                +   "1. To start a new game: start @player" + "\n"
                +   "2. To make a move: move <position: number between 1-9>" + "\n"
                +   "3. To see the status of an ongoing game: show" + "\n"
                +   "4. To quit a game you were playing: quit" + "\n"
                +   "5. To see this menu."
                ;
    }
}
