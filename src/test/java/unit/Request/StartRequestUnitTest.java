package unit.Request;

import Input.StartRequest;
import Interfaces.Request;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertNull;

public class StartRequestUnitTest {
    @DataProvider
    public Object[][] moveRequestData() {
        String userId = "U1234";
        String userName = "userName";
        String channelId = "channelId";
        String channelName = "channelName";
        String exceptionMessage1 = "The correct format is /rottt start <@otherPlayer>";
        String exceptionMessage2 = "Enter the opponent in the correct format. " + exceptionMessage1;
        String exceptionMessage3 = "You cannot play against yourself. " + exceptionMessage1;

        Object[][] args = new Object[][] {
                new Object[] {"start", userId, userName, channelId, channelName, null},
                new Object[] {"start <@U2222|player2>", userId, userName, channelId, channelName, null},
                new Object[] {"start <@U1234|player2> <@U2345|player1>", userId, userName, channelId,
                        channelName, exceptionMessage1},
                new Object[] {"start player2", userId, userName, channelId, channelName,
                        exceptionMessage2},
                new Object[] {"start <@C1234|channel>", userId, userName, channelId, channelName,
                        exceptionMessage2},
                new Object[] {"start <@" + userId + "|" + userName + ">", userId, userName, channelId,
                        channelName,
                        exceptionMessage3},
        };
        return args;
    }

    @Test(dataProvider = "moveRequestData")
    public void validateRequestAndExtractTest(String commandText, String userId, String userName,
                                              String channelId, String channelName, String emessages) {
        Request request = new StartRequest(commandText, userId, userName, channelId, channelName);

        try {
            request.validateRequestAndExtract();
        } catch (IllegalArgumentException e) {
            assertEquals(e.getMessage(), emessages);
            return;
        }
        assertNull(emessages);
    }
}
