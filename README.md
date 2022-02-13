Console-based implementation of the game HT3: Hyper Tic-Tac Toe. 

This is a game in which each of the nine squares is an inner tic-tac-toe game. Each of the inner games is a standard tic-tac-toe game in which a player wins by getting three X's or O's in a row. Then, the outer game is won by winning three inner games in a row. If an inner game cannot be won by anyone, then it becomes "wild", in which either player can use it to win three inner games in a row.

To play the game, run StartGame.java. From there, a manu will appear. A player can type "play" to start the game or "mc" to perform a Monte Carlo simulation (by running a user-selected number of games and determining the win-loss-tie statistics).

When playing, each player takes turns entering the row and column number (1-9) in which to place an x or o. 

An active inner game uses lowecase x's and o's to denote each player's positions. When an inner game is won, each inner space has a capital X or O corresponding to the winner or a W if no one has won.

Served as a series of projects in CS 2003: Fundamentals of Algorithms and Computing Applications at the University of Tulsa in Fall 2013.
