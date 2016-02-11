
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
var number = 1;
var queue = [];

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

app.post('/hello', function(req, res){
	res.send("I was sent here");
	res.send("Hello World");
});

app.post('/add', function incrementNum(req, res){
	console.dir(req.body);
	number = number + parseInt(req.body.number);
	res.send('' + number);
});

app.post('/getState', function getState(req, res){
	res.send('' + number);
	console.log("got state");
});

app.post('/matchmaking', function(user, callback){
	console.log('Finding opponent...');
	
	var opponent = null;

});

app.listen(app.get('port'), function serverListen(){
	console.log('listening on port ', app.get('port'));
})
