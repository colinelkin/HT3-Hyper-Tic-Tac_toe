/**
 * Represents HT3 player, equipped with name, character assignment,
 * and win statistics for inner and outer games
 *
 * @author Colin Elkin
 */

public class Player {

	// 3x3 boolean array to track inner wins
	private boolean[][] innerWin;
	// character representing X or O
	private char ch;
	// status of whether player has won outer game
	private boolean isWinner = false;
	// name of player
	private String name;
	
	/**
	 * Constructor, assigns name and character to player
	 *		creates 3x3 boolean array of entirely false values
	 *
	 * @param playerName name to be given to player
	 *
	 * @param c1 character to be assigned to player
	 *		PRECONDITION: c1 must be 'x' or 'o'
	 */
	public Player(String playerName, char ch1) {
		name = playerName;		
		innerWin = new boolean[][] { { false, false, false },
				{ false, false, false }, { false, false, false } };
		ch = ch1;
	} // end constructor
	
	/**
	 * Copy constructor, replicates every class variable with new copies of each object
	 *
	 * @param p Player object to be copied
	 */
	public Player(Player p) {
		this.innerWin = new boolean[3][3];
		for (int i = 0; i < 3; i++)
			for (int j = 0; j < 3; j++)
				this.innerWin[i][j] = p.innerWin[i][j];
		this.ch = p.ch;
		this.isWinner = p.isWinner;
		this.name = new String(p.name);
	} // end copy constructor

	/**
	 * Returns player name
	 *
	 * @return player name
	 */
	public String getName() {
		return name;
	} // end getName

	/**
	 * Returns player character
	 *
	 * @return player character
	 *		POSTCONDITION: output will be either 'x' or 'o'
	 */
	public char getchar() {
		return ch;
	} // end getchar

	/**
	 * Checks to see if an inner game has been won in 
	 * the respective outer board position
	 * 
	 * @param m row number
	 * 		PRECONDITION: 0 <= m <= 2
	 *		
	 * @param n column number
	 *		PRECONDITION: 0 <= n <= 2
	 *            
	 * @return truth value at given row and column
	 */
	public boolean getWin(int m, int n) {
		return innerWin[m][n];
	} // end getWin

	/**
	 * Sets inner game win at given row and column of outer board
	 * 
	 * @param m row number
	 * 		PRECONDITION: 0 <= m <= 2
	 *		
	 * @param n column number
	 *		PRECONDITION: 0 <= n <= 2
	 *   
	 */
	public void setWin(int m, int n) {
		innerWin[m][n] = true;
	} // end setWin

	/**
	 * Sets outer game win status to true
	 */
	public void setWinner() {
		isWinner = true;
	} // end setWinner

	/**
	 * Checks to see if three inner games in a row have been won
	 * 
	 * @return truth value of whether three wins in a row have been reached
	 */
	public boolean threeWins() {
		return
		// downward sloping diagonal
		((innerWin[0][0] & innerWin[1][1] & innerWin[2][2]) ||
		// upward sloping diagonal
		(innerWin[2][0] & innerWin[1][1] & innerWin[0][2]) ||
		// top horizontal
		(innerWin[0][0] & innerWin[0][1] & innerWin[0][2]) ||
		// middle horizontal
		(innerWin[1][0] & innerWin[1][1] & innerWin[1][2]) ||
		// bottom horizontal
		(innerWin[2][0] & innerWin[2][1] & innerWin[2][2]) ||
		// left vertical
		(innerWin[0][0] & innerWin[1][0] & innerWin[2][0]) ||
		// middle vertical
		(innerWin[0][1] & innerWin[1][1] & innerWin[2][1]) ||
		// right vertical
		(innerWin[0][2] & innerWin[1][2] & innerWin[2][2]));
	} // end threeWins

} // end Player
