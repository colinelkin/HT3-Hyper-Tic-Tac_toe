/**
 * Represents the outer Tic-Tac-Toe board, each square of which is an inner TTT board
 *
 * @author Colin Elkin
 */

public class OuterBoard  {

	// defines 2d array of InnerBoard objects, to be defined in constructor
	private InnerBoard[][] board;
	// defines two Player objects, to be defined in constructor
	private Player player1, player2;
	// creates new CoordConversion object
	private CoordConversion c = new CoordConversion();
	
	/**
	 * Constructor, creates 3x3 array of InnerBoards
	 *
	 * @param p1 Player object to represent first player
	 *
	 * @param p2 Player object to represent second player
	 */
	public OuterBoard(Player p1, Player p2) {	
		player1 = p1;
		player2 = p2;
		board = new InnerBoard[][] {
				{ new InnerBoard(), new InnerBoard(), new InnerBoard() },
				{ new InnerBoard(), new InnerBoard(), new InnerBoard() },
				{ new InnerBoard(), new InnerBoard(), new InnerBoard() } };
	} // end constructor
	
	/**
	 * Copy constructor, replicates every class variable with new copies of each object
	 *
	 * @param b OuterBoard object to be copied
	  */
	public OuterBoard(OuterBoard b) {
		this.board = new InnerBoard[3][3];
		for (int i = 0; i < 3; i++)
			for (int j = 0; j < 3; j++)
				this.board[i][j] = new InnerBoard(b.board[i][j]);
		this.player1 = new Player(b.player1);
		this.player2 = new Player(b.player2);
	} // end copy constructor

	/**
	 * Retrieves a character value from outer board at TWO given coordinates
	 * 
	 * @param m row number coordinate of overall board
	 *		PRECONDITION: 0 <= m <= 8
	 * 
	 * @param n column number coordinate of overall board
	 *		PRECONDITION: 0 <= n <= 8
	 * 
	 * @return char value of outer board
	 *		POSTCONDITION: output is 'x', 'o', 'X', 'O', 'W', '-', or '_'
	 */
	public char getOutercharTwo(int m, int n) {
		int[] i = c.twoCoords(m);
		int[] j = c.twoCoords(n);
		return board[i[0]][j[0]].getInnerchar(i[1], j[1]);
	} // end getOutercharTwo
	
	/**
	 * Retrieves a character value from outer board at FOUR given coordinates
	 * 
	 * @param k row number coordinate of overall board
	 *		PRECONDITION: 0 <= k <= 2
	 * 
	 * @param l column number coordinate of overall board
	 *		PRECONDITION: 0 <= l <= 2
	 *
	 * @param m column number coordinate of overall board
	 *		PRECONDITION: 0 <= m <= 2
	 *
	 * @param n column number coordinate of overall board
	 *		PRECONDITION: 0 <= n <= 2
	 * 
	 * @return char value of outer board
	 *		POSTCONDITION: output is 'x', 'o', 'X', 'O', 'W', '-', or '_'
	 */
	public char getOutercharFour (int k, int l, int m, int n) {
		return board[k][l].getInnerchar(m, n);
	} // end getOutercharFour

	/**
	 * Changes value of outer board at TWO given coordinates to given character
	 * 
	 * @param m row number of overall board
	 *		PRECONDITION: 0 <= m <= 8
	 * 
	 * @param n column number of overall board
	 *		PRECONDITION: 0 <= n <= 8
	 *
	 * @param c character to occupy above coordinates
	 *		PRECONDITION: c must be 'x' or 'o'
	 */
	public void setOutercharTwo(int m, int n, char ch) {
		int[] i = c.twoCoords(m);
		int[] j = c.twoCoords(n);
		board[i[0]][j[0]].setInnerchar(i[1], j[1], ch);
	} // end setOutercharTwo
	
	/**
	 * Changes value of outer board at FOUR given coordinates to given character
	 * 
	 * @param k row number of overall board
	 *		PRECONDITION: 0 <= k <= 2
	 *
	 * @param l row number of overall board
	 *		PRECONDITION: 0 <= l <= 2
	 *
	 * @param m row number of overall board
	 *		PRECONDITION: 0 <= m <= 2
	 * 
	 * @param n column number of overall board
	 *		PRECONDITION: 0 <= n <= 2
	 *
	 * @param c character to occupy above coordinates
	 *		PRECONDITION: c must be 'x' or 'o'
	 */
	public void setOutercharFour(int k, int l, int m, int n, char ch) {
		board[k][l].setInnerchar(m, n, ch);
	} // end setOutercharFour

	/**
	 * Retrieves InnerBoard object from index of outer board
	 * 
	 * @param m row number from overall board
	 *		PRECONDITION: 0 <= m <= 2
	 *
	 * @param n column number from overall board
	 *		PRECONDITION: 0 <= n <= 2
	 *            
	 * @return InnerBoard object at row m and column n
	 */
	private InnerBoard getInnerBoard(int m, int n) {
		return board[m][n];
	} // end getInnerBoard

	/**
	 * References current player as next player and vice versa
	 */
	public void switchPlayers() {
		//System.out.println("switching players");
		Player tempPlayer = player1;
		player1 = player2;
		player2 = tempPlayer;
	}
	
	/**
	 * Retrieves current player's char position
	 *
	 * @return char position of current player
	 *		POSTCONDITION: output is 'x' or 'o'
	 */
	public char getTurn() {
		return player1.getchar();
	}
	
	/**
	 * Retrieves name of current player
	 *
	 * @return name of current player
	 */
	public String getPlayer() {
		return player1.getName();
	}
	
	/**
	 * Checks to see if any of the inner games have been won or tied
	 */
	public void checkOuterWinProgress() {
		for (int i = 0; i < 3; i++)
			for (int j = 0; j < 3; j++) {
				getInnerBoard(i, j).checkInnerWinProgress();
				// if X wins
				if (getInnerBoard(i, j).getInnerWinner() == 'x') {
					getInnerBoard(i, j).checkInnerWinStatus();
					player1.setWin(i, j);
				}
				// if O wins
				if (getInnerBoard(i, j).getInnerWinner() == 'o') {
					getInnerBoard(i, j).checkInnerWinStatus();
					player2.setWin(i, j);
				}
				// if no one wins but board is full
				if (getInnerBoard(i, j).getInnerWinner() == 'W') {
					getInnerBoard(i, j).checkInnerWinStatus();
					player1.setWin(i, j);
					player2.setWin(i, j);
				}

			}
	} // end checkOuterWinProgress
	
	/**
	 * Checks to see if a win, draw, or stalemate has been reached
	 * 
	 * @return char resultant character
	 *		POSTCONDITION: output must be 'x', 'o', 'W', 'N', or '_'
	 */
	public char checkOuterWinStatus() {
		// if Player 1 (X) wins, Player 2 (O) does not win, and game is over
		if (player1.threeWins() && (! player2.threeWins())) {
			setOuterEndGameBoard('-', 'X', '-', 'W', 'I', 'N', 'S', '!', '-');
			return 'x';
		}
		// if Player 2 (O) wins, Player 1 (X) does not win, and game is over
		if (player2.threeWins() && (! player1.threeWins())) {
			setOuterEndGameBoard('-', 'O', '-', 'W', 'I', 'N', 'S', '!', '-');
			return 'o';
		}
		// if both Player 1 (X) and Player 2 (O) win, and game is over
		if (player1.threeWins() && player2.threeWins()) {
			setOuterEndGameBoard('T', 'I', 'E', '-', 'M', 'A', 'T', 'C', 'H');
			return 'W';
		}
		// if neither Player 1 (X) nor Player 2 (O) win, and game is over
		if ((checkOuterBlackOut()) & (! player1.threeWins()) & 
			(! player2.threeWins())) {
			setOuterEndGameBoard('S', 'T', 'A', 'L', 'E', 'M', 'A', 'T', 'E');
			return 'N';
		}
		// if game is not over
		else return '_';
	} // end checkOuterWinStatus
	
	/**
	 * Sets overall board to reflect appropriate game over status
	 * 
	 * @param c1 character to occupy rows 3 and 7 at column 0
	 *
	 * @param c2 character to occupy rows 3 and 7 at column 1
	 *
	 * @param c3 character to occupy rows 3 and 7 at column 2
	 *
	 * @param c4 character to occupy rows 3 and 7 at column 3
	 *
	 * @param c5 character to occupy rows 3 and 7 at column 4
	 *
	 * @param c6 character to occupy rows 3 and 7 at column 5
	 *
	 * @param c7 character to occupy rows 3 and 7 at column 6
	 *
	 * @param c8 character to occupy rows 3 and 7 at column 7
	 *
	 * @param c9 character to occupy rows 3 and 7 at column 8
	 *
	 */
	private void setOuterEndGameBoard(char c1, char c2, char c3, 
		char c4, char c5, char c6, char c7, char c8, char c9) {
		for (int i = 0; i < 9; i += 2)
			for (int j = 0; j < 9; j++)
				setOutercharTwo(i, j, '-');
		for (int i = 1; i < 6; i += 4) {
			setOutercharTwo(i, 0, 'G');
			setOutercharTwo(i, 1, 'A');
			setOutercharTwo(i, 2, 'M');
			setOutercharTwo(i, 3, 'E');
			setOutercharTwo(i, 4, '-');
			setOutercharTwo(i, 5, 'O');
			setOutercharTwo(i, 6, 'V');
			setOutercharTwo(i, 7, 'E');
			setOutercharTwo(i, 8, 'R');
		}
		for (int i = 3; i < 8; i += 4) {
			setOutercharTwo(i, 0, c1);
			setOutercharTwo(i, 1, c2);
			setOutercharTwo(i, 2, c3);
			setOutercharTwo(i, 3, c4);
			setOutercharTwo(i, 4, c5);
			setOutercharTwo(i, 5, c6);
			setOutercharTwo(i, 6, c7);
			setOutercharTwo(i, 7, c8);
			setOutercharTwo(i, 8, c9);
		}
	} // end setOuterEndGameBoard
	
	/**
	 * Checks to see if every inner game is over
	 * 
	 * @return truth value of whether every inner game is over
	 */
	public boolean checkOuterBlackOut() {
		for (int i = 0; i < 3; i++)
			for (int j = 0; j < 3; j++)
				if (! getInnerBoard(i, j).checkInnerEndGame())				
					return false;		
		return true;
	} // end checkOuterBlackOut

} //end OuterBoard
