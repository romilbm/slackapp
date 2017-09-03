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
import Move.HumanMove;
import Model.Channel;

public class TicTacToeController {
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
