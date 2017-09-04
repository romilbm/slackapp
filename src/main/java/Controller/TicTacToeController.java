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

    @RequestMapping(value = "/rottt",
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

        String response;

        System.out.println(text);
        String[] commandParts = text.split(" ");

        System.out.println("text parts");
        for (String p: commandParts) {
            System.out.print(p + " ");
        }
        System.out.println();

        if (commandParts[0].equals("start")) {
            String[] userInfo = extractUser(commandParts[1]);
            response = start(userName, userId, userInfo[1], userInfo[0], channelId, channelName);
            System.out.println("start command params:" + userName + " " + userId + " " + userInfo[1] + " " + userInfo[0] + " " + channelId + " " + channelName);
        } else if (commandParts[0].equals("move")) {
            int position = Integer.parseInt(commandParts[1]);
            response = move(userId, position, channelId, channelName);
            System.out.println("move command params:" + userId + " " + position + " " + channelId + " " + channelName);
        } else if (commandParts[0].equals("quit")) {
            response = quit(userId, channelId, channelName);
            System.out.println("quit command params:" + userId + " " + channelId + " " + channelName);
        } else if (commandParts[0].equals("show")) {
            response = show(channelId, channelName);
            System.out.println("show command params:" + channelId + " " + channelName);
        } else {
            response = "Invalid command";
        }


        if (!token.equals(slackToken)) {
            return new RichMessage("Sorry! You're not lucky enough to use our slack command.");
        }

        /** build response */
        RichMessage richMessage = new RichMessage(response);
        richMessage.setResponseType("in_channel");
        return richMessage.encodedMessage();


    }

    private String[] extractUser(String commandPart) {
        String cp = commandPart.substring(1, commandPart.length()-1);
        System.out.println("cp = " + cp);
        String regex = "\\|";
        String[] user = cp.split(regex);

        String id = user[0].substring(1);
        System.out.println("id:" + id);

        String name = user[1];
        System.out.println("name:" + name);

        return new String[] {id, name};
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
