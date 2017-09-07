Tic Tac Toe Slack Commands

Install the Tic Tac Toe Slack App.
Then you can play the Tic Tac Toe game in any channel against your team mate or against a bot.

Here is how you get started -

Type /ttt followed by one the options below:
1. To start a new game against your teammate: start @player
2. To start a new game with a bot: start
3. To make a move: move <position: number between 1-9>
4. To see the status of an ongoing game: show
5. To quit a game you were playing: quit
6. To see this menu: help

Further Improvements:

1. Provide an implementation for the DataAccessor which will actually act like a data access layer.
You can connect the database and make it serve as client which will help run all CRUD queries against a
games table which will consist of all the games in the channel.

2. An AI Bot has been provided for fun. It is a very basic Dumb Bot. It started as a way for me to
test. A MoveMethod implementation can be provided which will move more smartly.