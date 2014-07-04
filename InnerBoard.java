/**
 * Represents the inner Tic-Tac-Toe board, each square of which is a character.
 * 
 * @author Colin Elkin
 */

public class InnerBoard {

	// defines 2D array of chars
	private char[][] s;
	// defines winner variable of type char
	private char innerWinner = '_';

	/**
	 *	Constructor, creates 3x3 array of chars
	 */
	public InnerBoard() {
		// initializes s as blank game board
		s = new char[][] { { '_', '_', '_' }, { '_', '_', '_' },
				{ '_', '_', '_' } };
	} // end default constructor
	
	/**
	 * Copy constructor, replicates every class variable with new copies of each object
	 *
	 * @param b InnerBoard object to be copied
	 */
	public InnerBoard(InnerBoard b) {
		this.s = new char[3][3];
		for (int i = 0; i < 3; i++)
			for (int j = 0; j < 3; j++)
				this.s[i][j] = b.s[i][j];
		this.innerWinner = b.innerWinner;
	} // end copy constructor

	/**
	 * Retrieves the character at given row and column of the board
	 * 
	 * @param m row number from overall board
	 *		PRECONDITION: 0 <= m <= 2
	 *
	 * @param n column number from overall board
	 *		PRECONDITION: 0 <= n <= 2
	 *          
	 * @return character at given row and column
	 *		POSTCONDITION: output is 'x', 'o', 'X', 'O', 'W', '-', or '_'
	 */
	public char getInnerchar(int m, int n) {
		return s[m][n];
	} // end getInnerchar

	/**
	 * Places a character on the board at given row and column
	 * 
	 * @param m row number from overall board
	 *		PRECONDITION: 0 <= m <= 2
	 *
	 * @param n column number from overall board
	 *		PRECONDITION: 0 <= n <= 2
	 *
	 * @param c character to be set at given row and column
	 *		PRECONDITION: c must be 'x' or 'o'
	 */
	public void setInnerchar(int m, int n, char ch) {
		s[m][n] = ch;
	} // end setInnerchar

	/**
	 * Checks to see if a win or a draw has been reached and if so 
	 * sets win status accordingly
	 */
	public void checkInnerWinProgress() {
		// if it is a draw		
		if (checkInnerBlackOut())
			innerWinner = 'W';		
		// downward sloping diagonal
		if (threeInARow(0, 0, 1, 1, 2, 2))
			innerWinner = s[0][0];
		// upward sloping diagonal
		if (threeInARow(0, 2, 1, 1, 2, 0))
			innerWinner = s[0][2];
		// top horizontal
		if (threeInARow(0, 0, 0, 1, 0, 2))
			innerWinner = s[0][0];
		// middle horizontal
		if (threeInARow(1, 0, 1, 1, 1, 2))
			innerWinner = s[1][0];
		// bottom horizontal
		if (threeInARow(2, 0, 2, 1, 2, 2))
			innerWinner = s[2][0];
		// left vertical
		if (threeInARow(0, 0, 1, 0, 2, 0))
			innerWinner = s[0][0];
		// middle vertical
		if (threeInARow(0, 1, 1, 1, 2, 1))
			innerWinner = s[0][1];
		// right vertical
		if (threeInARow(0, 2, 1, 2, 2, 2))
			innerWinner = s[0][2];
	} // end checkInnerWinProgress
	
	/**
	 *	Checks to see if three sets of coordinates are all equal to one another
	 *	
	 *	@param m1 first row coordinate to be compared
	 *		PRECONDITION: 0 <= m1 <= 2
	 *
	 *	@param n1 first column coordinate to be compared
	 *		PRECONDITION: 0 <= n1 <= 2
	 *
	 *	@param m2 second row coordinate to be compared
	 *		PRECONDITION: 0 <= m2 <= 2
	 *
	 *	@param n2 second column coordinate to be compared
	 *		PRECONDITION: 0 <= n2 <= 2
	 *
	 *	@param m3 third row coordinate to be compared
	 *		PRECONDITION: 0 <= m3 <= 2
	 *
	 *	@param n3 third column coordinate to be compared
	 *		PRECONDITION: 0 <= n3 <= 2
	 *
	 *	@return truth value of whether all three coordinates 
	 * 		are equal to one another
	 */
	private boolean threeInARow(int m1, int n1, int m2, int n2, int m3, int n3) {
		return ((s[m1][n1] == s[m2][n2]) & 
			(s[m1][n1] == s[m3][n3]) & (s[m1][n1] != '_') & 
			((s[m1][n1] == 'x') || (s[m1][n1] == 'o')));
	} // end threeInARow
	
	/**
	 * Determines whether entire board is full
	 * 
	 * @return truth value of whether board is full
	 */
	private boolean checkInnerBlackOut() {
		if (checkInnerEndGame())
			return false;
		for (int i = 0; i < 3; i++)
			for (int j = 0; j < 3; j++)
				if ((s[i][j] == '_'))
					return false;
		return true;
	} // end checkInnerBlackOut

	/**
	 * Determines whether the game has ended
	 */
	public void checkInnerWinStatus() {
		// if X is the winner
		if (innerWinner == 'x') {
			setInnerEndGameBoard('X', '-', 'X', '-', 'X', '-', 'X', '-', 'X');
		} 
		// if O is the winner
		if (innerWinner == 'o') {
			setInnerEndGameBoard('O', 'O', 'O', 'O', '-', 'O', 'O', 'O', 'O');
		} 
		// if no one wins
		else if (innerWinner == 'W')
			setInnerEndGameBoard('W', 'W', 'W', 'W', 'W', 'W', 'W', 'W', 'W');
	} // end checkInnerWinStatus

	/**
	 * Sets board to reflect appropriate game over status
	 * 
	 * @param c1 character to occupy row 0 column 0
	 *
	 * @param c2 character to occupy row 0 column 1
	 *
	 * @param c3 character to occupy row 0 column 2
	 *
	 * @param c4 character to occupy row 1 column 0
	 *
	 * @param c5 character to occupy row 1 column 1
	 *
	 * @param c6 character to occupy row 1 column 2
	 *
	 * @param c7 character to occupy row 2 column 0
	 *
	 * @param c8 character to occupy row 2 column 1
	 *
	 * @param c9 character to occupy row 2 column 2
	 *
	 */
	private void setInnerEndGameBoard(char c1, char c2, char c3,
		char c4, char c5, char c6, char c7, char c8, char c9) {
		s[0][0] = c1;
		s[0][1] = c2;
		s[0][2] = c3;
		s[1][0] = c4;
		s[1][1] = c5;
		s[1][2] = c6;
		s[2][0] = c7;
		s[2][1] = c8;
		s[2][2] = c9;
	} // end setInnerEndGameBoard

	/**
	 *	Checks to see if inner game is over
	 *
	 *	@return truth value of whether or not inner game is over
	 */
	public boolean checkInnerEndGame() {
		return ((s[0][0] == 'X') || (s[0][0] == 'O') || (s[0][0] == 'W'));
	} // end checkInnerEndGame
		
	/**
	 * Returns winner status
	 * 
	 * @return character indicating winner status
	 *		POSTCONDITION: output must be 'x', 'o', 'W', or '_'
	 */
	public char getInnerWinner() {
		return innerWinner;
	} // end getInnerWinner

	/**
	 * Sets winner status
	 * 
	 * @param c character to be set as winner status
	 *		PRECONDITION: c must be 'x', 'o', 'W', or '_'
	 */
	public void setInnerWinner(char c) {
		innerWinner = c;
	} // end setInnerWinner

} // end InnerBoard
