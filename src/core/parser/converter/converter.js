const texzilla = require('./TexZilla.min.js');
//const readline = require('readline');
//
//const rl = readline.createInterface({
//    input: process.stdin,
//    output: process.stdout
//});
//
//rl.on('line', function(input){
//    var mathml = texzilla.toMathMLString(input);
//    console.log(mathml);
//    rl.close();
//});



var mathml = texzilla.toMathMLString(process.argv[2]);
console.log(mathml);
