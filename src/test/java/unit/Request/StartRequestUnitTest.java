package unit.Request;

import Input.StartRequest;
import Interfaces.Request;
import ResponseStrings.RequestMessages;
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

        Object[][] args = new Object[][] {
                new Object[] {"start", userId, userName, channelId, channelName, null},
                new Object[] {"start <@U2222|player2>", userId, userName, channelId, channelName, null},
                new Object[] {"start <@U1234|player2> <@U2345|player1>", userId, userName, channelId,
                        channelName, RequestMessages.START_CORRECT_FORMAT},
                new Object[] {"start player2", userId, userName, channelId, channelName,
                        RequestMessages.INCORRECT_OPPONENT_FORMAT},
                new Object[] {"start <@C1234|channel>", userId, userName, channelId, channelName,
                        RequestMessages.INCORRECT_OPPONENT_FORMAT},
                new Object[] {"start <@" + userId + "|" + userName + ">", userId, userName, channelId,
                        channelName, RequestMessages.OPPONENT_SELF_NOT_ALLOWED},
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
