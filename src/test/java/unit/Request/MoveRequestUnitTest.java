package unit.Request;

import Input.MoveRequest;
import Interfaces.Request;
import ResponseStrings.RequestMessages;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertNull;

public class MoveRequestUnitTest {
    @DataProvider
    public Object[][] moveRequestData() {
        String userId = "userId";
        String channelId = "channelId";
        String channelName = "channelName";

        Object[][] args = new Object[][] {
                new Object[] {"move 5", userId, channelId, channelName, null},
                new Object[] {"move 1 3", userId, channelId, channelName, RequestMessages.MOVE_CORRECT_FORMAT},
                new Object[] {"move", userId, channelId, channelName, RequestMessages.MOVE_CORRECT_FORMAT},
                new Object[] {"move abc", userId, channelId, channelName, RequestMessages.NUM_OOR_MOVE},
        };
        return args;
    }

    @Test(dataProvider = "moveRequestData")
    public void validateRequestAndExtractTest(String commandText, String userId, String channelId, String
            channelName, String exceptionMessage) {
        Request request = new MoveRequest(commandText, userId, channelId, channelName);
        try {
            request.validateRequestAndExtract();
        } catch (IllegalArgumentException e) {
            assertEquals(e.getMessage(), exceptionMessage);
            return;
        }
        assertNull(exceptionMessage);
    }
}
