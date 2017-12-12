var http = require('http');
var audioMetaData = require('audio-metadata');
var fs = require('fs');
var url = require('url');

http.createServer(function (req, res) {
	let parsed = url.parse(req.url, true);
	if (parsed.pathname == "/request.html") {
		res.writeHead(200, {'Content-Type': 'text/html'});
		res.write(parsed.query.par + ' of ' + parsed.query.file + '<br>');
		res.write(JSON.stringify(readData(parsed.query.file)[parsed.query.par]));
		res.end('');	
	} else {
		res.writeHead(200, {'Content-Type': 'text/html'});
		res.write('Hello, World<br>');
		res.write(JSON.stringify(readData("D:/musixs/AC_DC/Highway to Hell/01 Highway to Hell.mp3")));
		res.end('<br>Bye, World!');	
	}
}).listen(8080);


function readData(mp3Path) {
  var mp3Data = fs.readFileSync(mp3Path);
  var metadata = audioMetaData.id3v2(mp3Data);
  
  return metadata;
}