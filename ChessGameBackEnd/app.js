
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

var callTimesTillCheck = 50;
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
if ('development' == app.get('env')) {
  app.use(express.errorHandler());
}

app.get('/', routes.index);
app.get('/users', user.list);

function sendMessageToUser(res, status, message)
{
	res.status(status);
	res.send(message);
}

app.post('/submitMove', function saveMove(req, res){
	games[req.body.user][0] = 0; 
	games[req.body.user][1] = req.body.update;
});

app.post('/getState', function getState(req, res){
	var numViewed = parseInt(games[req.body.name][0]);
	if(numViewed < 2)
	{
		res.send(games[req.body.user][1]);
		console.log("got state " + games[req.body.user][1]);
		numViewed = numViewed + 1;
		games[req.body.user][0] = numViewed.toString();
	}
});

app.post('/getGameMessage', function(req, res){
	var numViewed = parseInt(games[req.body.name][0]);
	if(numViewed < 2)
	{
		res.send(games[req.body.user][1]);
		console.log("got game message " + games[req.body.user][1]);
		numViewed = numViewed + 1;
		games[req.body.user][0] = numViewed.toString();
	}
	
	checkInTimes[req.body.user] = new Date().getTime()/1000;
	
	if(currentCallTimes >= callTimesTillCheck)
	{
		var currentTime = new Date().getTime() / 1000;
		for (var player in checkInTimes)
		{
			if(currentTime - checkInTimes[player] > timeLimit)
			{
				var opponent = opponents[player];
				var idopt1 = player + " vs " + opponent;
				var idopt2 = opponent + " vs " + player;
				
				if(currentTime - checkInTimes[opponent] > timeLimit)
				{
					if(idopt1 in games)
					{
						delete games[idopt1];
					}
					else if(idopt2 in games)
					{
						delete games[idopt2];
					}
				}
				else
				{
					if(idopt1 in games)
					{
						games[idopt1][0] = 0;
						games[idopt1][1] = "Opponent has left the game";
					}
				}
			}
		}
	}
});

app.post('/join', function(req, res){
	console.log('Finding opponent...');
	
	var opponent = null;
	queueName.push(req.body.user);
	queueCheck.push(new Date().getTime() / 1000);
	res.send('Waiting for opponent');
	
	var player1Name = "";
	var player2Name = "";
	console.log(queueName.length);
	while(queueName.length > 0)
	{
		var currentTime = new Date().getTime() / 1000;
		var candidate = queueName.shift();
		var candidateTime = queueCheck.shift();

		if(player1Name == "")
		{
			if(currentTime - candidateTime < 1.5)
			{
				player1Name = candidate;
			}
			
		}
		else if(player2Name == "")
		{
			console.log('Here we gooooo');
			if(currentTime - candidateTime < 1.5)
			{
				player2Name = candidate;
				var name = player1Name + " vs " + player2Name;
				games[name] = ['0', ''];
				gamesMessages[name] = ['0', ''];
				opponents[player1Name] = name;
				opponents[player2Name] = name;
				checkInTimes[player1Name] = new Date().getTime() / 1000;
				checkInTimes[player2Name] = new Date().getTime()/ 1000;
				player1Name = "";
				player2Name = "";
			}
		}
	}
	
	if(player1Name != "")
	{
		queueName.push(player1Name);
		queueCheck.push(new Date().getTime() / 1000);
	}
});

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

app.listen(app.get('port'), function serverListen(){
	console.log('listening on port ', app.get('port'));
});
