package Model;

import Enums.EndConfig;
import ResponseStrings.ResponseMessages;

/**
 * It is set when the game concludes either in win or Draw.
 * It returns the {@link EndConfig}, the participants and the winner if there was one.
 */
public class TTTResult {
    private Player[] participants;
    private EndConfig w;
    private Player winner;

    public TTTResult(Player[] participants, EndConfig w, Player winner) {
        this.participants = participants;
        this.w = w;
        this.winner = winner;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(String.format(ResponseMessages.VERSUS_FORMAT, participants[0].getName(),
                participants[0].getSymbol(), participants[1].getName(), participants[1].getSymbol()));
        sb.append("Result = " + w + "\n");
        if (!w.equals(EndConfig.DRAW)) {
            sb.append("Winner = " + winner);
        }

        return sb.toString();
    }
}
