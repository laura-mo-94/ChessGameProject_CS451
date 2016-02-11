
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
var opponents = {};
var games = {};

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

app.post('/add', function incrementNum(req, res){
	console.log('' + games[req.body.user] + ' ' + req.body.user)
	games[req.body.user] = games[req.body.user] + parseInt(req.body.number);
	res.send('' + games[req.body.user]);
});

app.post('/getState', function getState(req, res){
	res.send('' + games[req.body.user]);
	console.log("got state " + games[req.body.user]);
});

app.post('/join', function(req, res){
	console.log('Finding opponent...');
	
	var opponent = null;
	queueName.push(req.body.user);
	
	res.send('Waiting for opponent');
	
	while(queueName.length >= 2){
		var player1Name = queueName.shift();
		var player2Name = queueName.shift();
		
		var name = player1Name + " vs " + player2Name;
		games[name] = 1;
		opponents[player1Name] = name;
		opponents[player2Name] = name;
	}
});

app.post('/getOpponent', function(req, res){
	console.log('get the opponent');
	if(req.body.user in opponents)
	{
		var name = opponents[req.body.user];
		res.send(name);
	}
	else
	{
		res.send("Waiting...");
	}
});

app.listen(app.get('port'), function serverListen(){
	console.log('listening on port ', app.get('port'));
});
