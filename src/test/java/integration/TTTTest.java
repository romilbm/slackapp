package integration;

import Controller.TicTacToeController;
import DataAccess.OngoingGames;
import Interfaces.DataAccessor;
import Model.Channel;
import org.junit.After;
import org.junit.Before;

public class TTTTest {

    protected String token;
    protected String teamId;
    protected String teamDomain;
    protected String channelId;
    protected String channelName;
    protected String userId;
    protected String userName;
    protected String command;
    protected String text;
    protected String responseUrl;
    protected static final String TEST_TOKEN = "TESTTOKEN";
    protected static final String USER_NAME = "Uname";
    protected static final String USER_ID = "U1234";
    protected static final String CHANNEL_NAME = "testchannel";
    protected static final String CHANNEL_ID = "C1234";
    protected DataAccessor ongoingTestGames;

    protected TicTacToeController controller;

    @Before
    public void setup() {
        controller = new TicTacToeController();
        controller.setTokenSet(TEST_TOKEN);

        token = TEST_TOKEN;
        teamId = "someTeamId";
        teamDomain = "someteamdomain";
        command = "somecommand";
        responseUrl = "someurl";
        userName = USER_NAME;
        userId = USER_ID;
        channelName = CHANNEL_NAME;
        channelId = CHANNEL_ID;
        ongoingTestGames = OngoingGames.getInstance();
//        controller = mock(TicTacToeController.class);
//        when(controller.onReceiveSlashCommand(
//                anyString(),
//                anyString(),
//                anyString(),
//                anyString(),
//                anyString(),
//                anyString(),
//                anyString(),
//                anyString(),
//                anyString(),
//                anyString()
//        )).thenCallRealMethod();

    }

    @After
    public void removeAllGames() {
        try {
            ongoingTestGames.endGameInChannel(new Channel(channelId, channelName));
        } catch (Exception e) {
            //do nothing.
        }
    }


}
