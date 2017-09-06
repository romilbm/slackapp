package Controller;

import Actions.TTTAction;
import Interfaces.Action;
import Model.RichMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

/**
 * The main controller for the slash command.
 * It authenticates the token of the POST. If it is valid,
 * It initiates the action for the user and runs that action.
 * Then it gets the response for that action, encodes it and returns it back to the channel.
 */
@SpringBootApplication
@RestController
public class TicTacToeController {

    private Set<String> slackTokenSet;

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
        if (!slackTokenSet.contains(token)) {
            return new RichMessage("Sorry! You're not lucky enough to use our slack command.");
        }

        String response;
        try {
            Action action = TTTAction.getAction(text, userId, userName, channelId, channelName);
            if (action != null) {
                action.run();
                response = action.getResponse().toString();
            } else {
                response = "Invalid option. Try `/ttt help` to get a list of all options.";
            }
        } catch (IllegalArgumentException e) {
            response = e.getMessage();
        }

        RichMessage richMessage = new RichMessage(response);
        richMessage.setResponseType("in_channel");
        return richMessage.encodedMessage();
    }

    @Autowired
    public void setTokenSet(@Value("${slashCommandToken}") final String stringOfTokens) {
        slackTokenSet = new HashSet<>(Arrays.asList(stringOfTokens.split(",")));
    }

    public static void main(String[] args) {
        SpringApplication.run(TicTacToeController.class, args);
    }
}
