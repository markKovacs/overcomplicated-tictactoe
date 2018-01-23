
# Overcomplicated TicTacToe

TicTacToe web application with microservices architecture, communicating both with 3rd party
REST APIs and self-developed REST APIs. Able to handle any microservice component malfunction,
having a fallback default behavior.

## Features

Each of the features communicate with a separate application through the network, using REST.
- Random Chuck Norris quote, per request
- Random Comic in the footer, per request
- Random Avatar for each player, per session
- Easy / Hard / Multi Player options

## Technology / Design

- Java Spring Boot
- Microservices Architecture
- REST / HTTP / JSON
- JavaScript / jQuery / AJAX
- HTML, CSS, Bootstrap
- Maven

### funfact-service

This service generates a fun fact for showing it next the game board to make your TicTacToe more fun.
Using Chuck Norris fact generator: https://api.chucknorris.io/.

### comics-service

A service to help showing a comic. Returns random piece from http://xkcd.com/.
Using xkcd's JSON API, http://xkcd.com/1001/info.0.json, where 1001 is a random number between 1 and 1929.

### avatar-service

Generates a profile avatar picture's URI, unchanged during the session.
Source: http://avatars.adorable.io/

### tictactoe-ai-service

The logic behind the computer's move.
Easy: returns a random cell from available ones.
Hard: uses https://github.com/stujo/tictactoe-api (example: http://tttapi.herokuapp.com/api/v1/-O-----X-/O)
