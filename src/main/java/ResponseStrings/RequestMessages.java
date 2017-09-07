package ResponseStrings;

public class RequestMessages {
    public static final String HELP_CORRECT_FORMAT = "Incorrect format! The correct format is `/ttt help`";
    public static final String MOVE_CORRECT_FORMAT = "Incorrect format! the correct format is `/ttt move <position>`";
    public static final String START_CORRECT_FORMAT = "Incorrect format! the correct format is `/ttt " +
            "start @otherplayer`";
    public static final String SHOW_CORRECT_FORMAT = "Incorrect format! the correct format is `/ttt show`";
    public static final String QUIT_CORRECT_FORMAT = "Incorrect format! the correct format is `/ttt quit`";

    public static final String NUM_OOR_MOVE = "The position should be a number between 1-9.";

    public static final String INCORRECT_OPPONENT_FORMAT = "Enter the opponent in the correct format. "
             + START_CORRECT_FORMAT;
    public static final String OPPONENT_SELF_NOT_ALLOWED = "You cannot play against yourself. " + START_CORRECT_FORMAT;

}
