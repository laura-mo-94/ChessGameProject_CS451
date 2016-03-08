
/**
 * Module dependencies.
 */

var express = require('express')
  , routes = require('./routes')
  , user = require('./routes/user')
  , http = require('http')
  , path = require('path')
  , bodyParser = require('body-parser');

var app = express();

var queueName = [];
var queueCheck = [];
var opponents = {};
var checkInTimes = {};
var games = {};
var gamesMessages = {};

var callTimesTillCheck = 10;
var currentCallTimes = 0;
var timeLimit = 300;

// all environments
app.set('port', process.env.PORT || 3000);
app.set('views', __dirname + '/views');
app.set('view engine', 'ejs');
app.use(express.favicon());
app.use(express.logger('dev'));

// allow JSON requests
app.use(express.bodyParser());
app.use(bodyParser.json());
app.use(bodyParser.urlencoded ({extended: true}));
app.use(express.methodOverride());
app.use(app.router);
app.use(express.static(path.join(__dirname, 'public')));

// development only
if ('development' === app.get('env')) {
  app.use(express.errorHandler());
}

app.get('/', routes.index);
app.get('/users', user.list);

// a game is identified by a name, and each game contains
// two parts: 1) how many have seen this new update 2) the new move update
// so if a name is in the list of games, then the new move is put in there
// and the number of people who have seen the update is marked as zero

app.post('/submitMove', function saveMove(req, res)
{
	console.log("submitted a move");
	if(req.body.user in games)
	{
		games[req.body.user][0] = 0; 
		games[req.body.user][1] = req.body.update;
	}
	res.send("Move saved");
});

//a message is posted in gamesMessages under a game id
app.post('/postMessage', function message(req, res){
	
	if(req.body.user in gamesMessages)
	{
		gamesMessages[req.body.user][0] = 0;
		gamesMessages[req.body.user][1] = req.body.update;
	}

	res.send("Waiting for response...");
});

// gets chess board updates
// if numViewed is 2 or greater, then this is an old update
// and nothing is returned

app.post('/getState', function getState(req, res){
	
	if(games.hasOwnProperty(req.body.user))
	{
		var numViewed = parseInt(games[req.body.user][0]);
		if(numViewed < 2)
		{
			res.send(games[req.body.user][1]);
			console.log("got state " + games[req.body.user][1]);
			numViewed = numViewed + 1;
			games[req.body.user][0] = numViewed.toString();
		}
		else
		{
			res.send("");
		}
	}
	else
	{
		res.send("");
	}

});

// gets the game message if there is one
// calling this function will also check in the user
// once this function has been called enough time it will go through and check all active users
// if it been too long since that user has checked in, then they are purged from the system
app.post('/getGameMessage', function(req, res){
	checkInTimes[req.body.userName] = new Date().getTime()/1000;
	console.log(req.body.userName + " checked in at " + checkInTimes[req.body.userName]);
	currentCallTimes = currentCallTimes + 1;
	
	if(currentCallTimes >= callTimesTillCheck)
	{
		currentCallTimes = 0;
		console.log("I'm checking....");
		var currentTime = new Date().getTime() / 1000;
		for (var player in checkInTimes)
		{
			if(checkInTimes.hasOwnProperty(player))
			{
				console.log(player + " checked in at " + checkInTimes[player]);
				if(currentTime - checkInTimes[player] > timeLimit)
				{
					var gameName = opponents[player];
					var names = gameName.split(" ");
					var opponent;
					console.log(player);
					if(player !== names[0])
					{
						opponent = names[0];
					}
					else
					{
						opponent = names[2];
					}
					
					console.log(player + " " + opponent);
					
					if(currentTime - checkInTimes[opponent] > timeLimit)
					{
						console.log("The opponent is gone too!");
						if(gameName in games)
						{
							delete games[gameName];
							delete gamesMessages[gameName];
						}
					}
					else
					{
						console.log("But the opponent is still here " + opponent);
						
						if(gameName in gamesMessages)
						{
							console.log(opponent + " is done");
							gamesMessages[gameName][0] = 0;
							gamesMessages[gameName][1] = "Player has forfeit";
						}
					}
				}
			}
		}
	}
	
	if(gamesMessages.hasOwnProperty(req.body.gameName))
	{
		var numViewed = parseInt(gamesMessages[req.body.gameName][0]);
		if(numViewed < 2)
		{
			res.send(gamesMessages[req.body.gameName][1]);
			console.log("got game message " + gamesMessages[req.body.gameName][1]);
			numViewed = numViewed + 1;
			gamesMessages[req.body.gameName][0] = numViewed.toString();
		}
		else
		{
			res.send("");
		}
	}
	else
	{
		res.send("");
	}
});

// given a name, it is put into the queue to be matched into a game
// if the name given is already in use, a message is returned stating that
// once more than 2 players have joined the queue, this function will start pairing
// off the players
app.post('/join', function(req, res){
	console.log(queueName.indexOf(req.body.user));
	
	var opponent = null;
	var valid = true;
	var currentTime = new Date().getTime() / 1000;
	
	if(queueName.indexOf(req.body.user) >= 0)
	{
		valid = false;
		res.send('Name is already in use!');
	}
	else if(req.body.user in opponents)
	{
		
		if(currentTime - checkInTimes[req.body.user] > timeLimit)
		{
			delete checkInTimes[req.body.user];
			delete opponents[req.body.user];
		}
		else
		{
			console.log(req.body.user + " nope");
			res.send('Name is already in use!');
			valid = false;
		}
		
	}
	
	if(valid)
	{
		queueName.push(req.body.user);
		queueCheck.push(currentTime);
		res.send('Waiting for opponent');
		
		var player1Name = "";
		var player2Name = "";
		console.log(queueName.length);
		
		// pairs off players in queue
		while(queueName.length > 0)
		{
			var candidate = queueName.shift();
			var candidateTime = queueCheck.shift();
	
			if(player1Name === "")
			{
				if(currentTime - candidateTime < 1.5)
				{
					player1Name = candidate;
				}
				
			}
			else if(player2Name === "")
			{
				if(currentTime - candidateTime < 1.5)
				{
					player2Name = candidate;
					var name = player1Name + " vs " + player2Name;
					games[name] = ['0', ''];
					gamesMessages[name] = ['0', ''];
					opponents[player1Name] = name;
					opponents[player2Name] = name;
					
					var time = new Date().getTime() / 1000;
					checkInTimes[player1Name] = time;
					checkInTimes[player2Name] = time;
					
					console.log(player1Name + " " + checkInTimes[player1Name]);
					console.log(player2Name + " " + checkInTimes[player2Name]);
					player1Name = "";
					player2Name = "";
				}
			}
		}
		
		if(player1Name !== "")
		{
			queueName.push(player1Name);
			queueCheck.push(new Date().getTime() / 1000);
		}
	}
});

// if the player has been placed into a game, then this function, given a player's name
// will return that game id, otherwise it notifies the user that it's still waiting to
// be paired off

app.post('/getOpponent', function(req, res){
	if(req.body.user in opponents)
	{
		var name = opponents[req.body.user];
		res.send(name);
	}
	else
	{
		queueCheck[queueName.indexOf(req.body.user)] = new Date().getTime() / 1000;
		res.send("Waiting...");
	}
});

// all information about the game is removed from the system
app.post('/removeGame', function(req, res){
	if(req.body.game in games)
	{
		delete games[req.body.game];
		delete gamesMessages[req.body.game];
		console.log(req.body.game + "Removed this game");
	}
	
	res.send("Finished!");
});

// player leaves the game
app.post('/leaveGame', function(req, res){
	if(req.body.userName in opponents)
	{
		delete opponents[req.body.userName];
		delete checkInTimes[req.body.userName];
		console.log(req.body.userName + " left this game");
	}
	
	res.send("Removed");
});

app.listen(app.get('port'), function serverListen(){
	console.log('listening on port ', app.get('port'));
});
