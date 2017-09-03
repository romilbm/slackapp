package Controller;

import Actions.Move;
import Actions.Quit;
import Actions.Show;
import Actions.Start;
import Enums.Symbol;
import Input.MoveRequest;
import Input.QuitRequest;
import Input.ShowRequest;
import Input.StartRequest;
import Model.Player;
import Model.RichMessage;
import Move.HumanMove;
import Model.Channel;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TicTacToeController {

    @Value("${slashCommandToken}")
    private String slackToken;

    @RequestMapping(value = "/ttt",
            method = RequestMethod.POST,
            consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
    public RichMessage onReceiveSlashCommand(@RequestParam("token") String token,
                                             @RequestParam("team_id") String teamId,
                                             @RequestParam("team_domain") String teamDomain,
                                             @RequestParam("channel_id") String channelId,
                                             @RequestParam("channel_name") String channelName,
                                             @RequestParam("user_id") String userId,
                                             @RequestParam("user_name") String userName,
                                             @RequestParam("command") String command,
                                             @RequestParam("text") String text,
                                             @RequestParam("response_url") String responseUrl) {

        System.out.println("Came here! =============== ____________ ++++++++++++++++++ ");
        if (!token.equals(slackToken)) {
            return new RichMessage("Sorry! You're not lucky enough to use our slack command.");
        }

        /** build response */
        RichMessage richMessage = new RichMessage("The TTT game");
        richMessage.setResponseType("in_channel");
        return richMessage.encodedMessage();


    }

    public String start(String name1, String id1, String name2, String id2, String channelId, String channelName) {
        Player p1 = new Player(name1, id1, new HumanMove(), Symbol.X);
        Player p2 = new Player(name2, id2, new HumanMove(), Symbol.ZERO);
        Channel channel = new Channel(channelId, channelName);
        StartRequest startRequest = new StartRequest(p1, p2, channel);
        startRequest.validateRequest();
        Start start = new Start(startRequest);
        start.run();
        return start.getStartResponse().toString();
    }

    public String move(String id, int position, String channelId, String channelName) {
        Channel channel = new Channel(channelId, channelName);
        MoveRequest moveRequest = new MoveRequest(id, position, channel);
        moveRequest.validateRequest();
        Move move = new Move(moveRequest);
        move.run();
        return move.getMoveResponse().toString();
    }

    public String quit(String id, String channelId, String channelName) {
        Channel channel = new Channel(channelId, channelName);
        QuitRequest quitRequest = new QuitRequest(id, channel);
        quitRequest.validateRequest();
        Quit quit = new Quit(quitRequest);
        quit.run();
        return quit.getQuitResponse().toString();
    }

    public String show(String channelId, String channelName) {
        Channel channel = new Channel(channelId, channelName);
        ShowRequest showRequest = new ShowRequest(channel);
        showRequest.validateRequest();
        Show show = new Show(showRequest);
        show.run();
        return show.getShowResponse().toString();
    }
}
