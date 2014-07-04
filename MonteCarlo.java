import java.util.Random;

/**
 * Represents an entire instance of a Monte Carlo simulation
 *
 * @author Colin Elkin
 */

public class MonteCarlo {

	// defines number of games to simulate
	private int numberOfGames;
	// defines number of times that player 1 wins
	private int p1WinCounter;
	// defines number of times that player 2 wins	
	private int p2WinCounter;
	// defines number of times that both players tie
	private int drawCounter;
	// creates new Random object
	private Random rand = new Random();

	/**
	 * Constructor, assigns number of games to simulate
	 *
	 * @param number of games to simulate
	 */
	public MonteCarlo(int number) {
		numberOfGames = number;
	} // end constructor

	/**
	 * Runs complete Monte Carlo simulation
	 */
	public void runSimulation() {
		for (int i = 1; i <= numberOfGames; i++) {
			Player p1 = new Player("Player1", 'x');
			Player p2 = new Player("Player2", 'o');			
			System.out.println("Game " + i);
			OuterBoard game = new OuterBoard(p1, p2);
			char result = playMC(game);
			checkOutcome(result);
		}
		printReport();
	} // end runSimulation

	/**
	 * Checks win status of a completed game and increments appropriate counter
	 *
	 * @param outcome win status of a completed game
	 *		PRECONDITION: outcome must be 'x', 'o', 'W', 'N', or '_'
	 */
	private void checkOutcome(char outcome) {
		// if Player 1 (X) won
		if (outcome == 'x')
			p1WinCounter++;
		// if Player 2 (O) won
		if (outcome == 'o')
			p2WinCounter++;
		// if a draw has been reached, either by tie match or by stalemate
		if ((outcome != 'x') && (outcome != 'o'))
			drawCounter++;
	} // end checkOutcome

	/**
	 * Cycles through automated randomly-generated HT3 game
	 *
	 * @return winner status of display
	 *		POSTCONDITION: output must be 'x', 'o', 'W', or '_'
	 */
	private char playMC(OuterBoard b) {
		b.checkOuterWinProgress();
		if (b.checkOuterWinStatus() != '_')
			return b.checkOuterWinStatus();
		else {
			takeRandomTurn(b);
			b.switchPlayers();
			return playMC(b);
		}
	} // end playMC
		
	/**
	 * Randomly sets row and column coordinates and sets character accordingly
	 */	
	private void takeRandomTurn(OuterBoard b) {
		int[] coord = randomizer();
		int p = coord[0];
		int q = coord[1];
		
		// reassigns coordinates if they do not correspond to a free space
		while (b.getOutercharTwo(p, q) != '_') {
			coord = randomizer();
			p = coord[0];
			q = coord[1];
		}
		// note that player integer range is 1-9
		// while index integer range is 0-8
		b.setOutercharTwo(p, q, b.getTurn());
	} // end takeAutoTurn
		
	/**
	 * Generates and returns random row and column coordinates
	 *
	 * @return randomly generated row and column number in form of size 2 array
	 *		POSTCONDITION: 1 <= both indices of output <= 9
	 *		POSTCONDITION: output must be array of size 2
	 */
	private int[] randomizer() {
		int m = rand.nextInt(9);
		int n = rand.nextInt(9);
		return new int[] {m, n};
	} // end randomizer
	
	/**
	 * Prints report of each of the four class variables
	 */
	private void printReport() {
		System.out.println("Simulation over.");
		System.out.println("Number of games: " + numberOfGames);
		System.out.println("Number of Player 1 wins: " + p1WinCounter);
		System.out.println("Number of Player 2 wins: " + p2WinCounter);
		System.out.println("Number of draws: " + drawCounter);
	} // end printReport

} // end MonteCarlo
