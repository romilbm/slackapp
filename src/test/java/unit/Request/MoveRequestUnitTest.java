package unit.Request;

import Input.MoveRequest;
import Interfaces.Request;
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
        String exceptionMessage1 = "Incorrect format the correct format is /rottt move <position>";
        String exceptionMessage2 = "The position should be a number between 1-9.";

        Object[][] args = new Object[][] {
                new Object[] {"move 5", userId, channelId, channelName, null},
                new Object[] {"move 1 3", userId, channelId, channelName, exceptionMessage1},
                new Object[] {"move", userId, channelId, channelName, exceptionMessage1},
                new Object[] {"move abc", userId, channelId, channelName, exceptionMessage2},
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
