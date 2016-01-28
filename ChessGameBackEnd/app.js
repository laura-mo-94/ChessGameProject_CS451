
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

http.createServer(function(request, response){
	response.write("Hello World");
	response.end();
}).listen(app.get('port'), function(){
  console.log('Express server listening on port ' + app.get('port'));
});

function sendMessageToUser(res, status, message)
{
	res.status(status);
	res.send(message);
};

app.get('/add', function incrementNum(req, res){
	number = number + 1;
	res.send('' + number);
	response.write("" + number)
})
