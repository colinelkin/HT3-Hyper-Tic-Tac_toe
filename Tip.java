import java.util.LinkedList;

/**
 * Represents an instance of the tip feature being executed in its entirety
 *
 * @author Colin Elkin
 */

public class Tip {
	// defines outer board object to indicate the first trace of possible player moves
	private OuterBoard initialMove;
	// defines array of Stats objects, whose dimensions are specified in constructor
	private Stats[][] stats;
	// defines integer representing number of total calculations
	private int numCalcs = 0;
	// defines integer representing row of position with highest probability of winning
	private int bestRow = 0;
	// defines integer representing column of position with highest probability of winning
	private int bestColumn = 0;
	// defines outer row, outer column, inner row, and inner column, respectively, of initial set
	// of possible moves
	private int i1, j1, k1, l1;
	// defines new CoordConversion object
	private CoordConversion c = new CoordConversion();

	/**
	 * Constructor, initialze initialMove with its input, creates new 9x9 array of Stats objects, and 
	 * invokes createStats method
	 *
	 * @param b OuterBoard object whose values get passed to b
	 */
	public Tip(OuterBoard b) {
		initialMove = b;
		stats = new Stats[9][9];
		createStats();
	} // end constructor

	/**
	 * Three-step procedure for obtaining tip
	 */
	public void getTip() {
		allPresentMoves();
		calcBestSpace();
		printTip();
	} // end getTip
		
	/**
	 * Traverses all possible spaces in the player's present turn
	 */
	private void allPresentMoves() {
		for (i1 = 0; i1 < 3; i1++)
			for (j1 = 0; j1 < 3; j1++)  {
				if (initialMove.getOutercharFour(i1, j1, 0, 0) == 'x' |
				initialMove.getOutercharFour(i1, j1, 0, 0) == 'o' |
				initialMove.getOutercharFour(i1, j1, 0, 0) == '_') {
					for (k1 = 0; k1 < 3; k1++)
						for (l1 = 0; l1 < 3; l1++) 							
							allFutureMoves(initialMove);
				} 
			}
	} // end allPresentMoves
	
	/**	
	 * Traverses all possible spaces in all of the player's possible future moves
	 *
	 * @param move outer board object that gets analyzed for all possible future moves
	 */
	private void allFutureMoves(OuterBoard move) {
		move.checkOuterWinProgress();
		if  (move.checkOuterWinStatus() != '_') {
			if (move.checkOuterWinStatus() == 'x') 
				stats[c.oneCoord(i1, k1)][c.oneCoord(j1, l1)].addWin();
			if (move.checkOuterWinStatus() == 'W')
				stats[c.oneCoord(i1, k1)][c.oneCoord(j1, l1)].addTie();
			if (move.checkOuterWinStatus() == 'o') 
				stats[c.oneCoord(i1, k1)][c.oneCoord(j1, l1)].addLoss();
		}	
		else {
			move.switchPlayers();			
			LinkedList<OuterBoard> nextMove = nextStep(move);
			// This portion of the algorithm does not traverse completely	
			// and it does not look like I will have the time to fix it.
			// Apparently allocating 30 total hours to the project still isn't enough time
			// to fully figure this out :-/
			if (!nextMove.isEmpty()) {
				allFutureMoves(nextMove.getFirst());
			}
		}
	} // end allFutureMoves
	
	/**
	 * Determines every possible next move scenario
	 *
	 * @param move outer board object to be analyzed
	 *
	 * @return list linked list of outer boards, each board a different possible scenario for next move
	 */
	private LinkedList<OuterBoard> nextStep(OuterBoard move) {
		LinkedList<OuterBoard> list = new LinkedList<OuterBoard>();
		for (int i = 0; i < 3; i++)
			for (int j = 0; j < 3; j++) {
				if (initialMove.getOutercharFour(i, j, 0, 0) == 'x' |
				initialMove.getOutercharFour(i, j, 0, 0) == 'o' |
				initialMove.getOutercharFour(i, j, 0, 0) == '_')
					for (int k = 0; k < 3; k++)
						for (int l = 0; l < 3; l++) {
							if (move.getOutercharFour(i, j, k, l) == '_') {
								OuterBoard copyOfMove = new OuterBoard(move);
								copyOfMove.setOutercharFour(i, j, k, l, copyOfMove.getTurn());
								list.addFirst(copyOfMove);
								numCalcs++;
							}
						
						}
						
				}
		return list;
	} // end nextStep
		
	/**
	 * Creates new instance of stats for every index in 3x3 array
	 */
	private void createStats() {
		for (int i = 0; i < 9; i++)
			for (int j = 0; j < 9; j++) {
				stats[i][j] = new Stats();
				}
	} // end createStats
	
	/**
	 * Determines space on initial move with highest win probability
	 */
	private void calcBestSpace() {
		for (int i = 0; i < 9; i++)
			for (int j = 0; j < 9; j++) {
				if (stats[i][j].getWinProb() >= stats[bestRow][bestColumn].getWinProb()) {
					bestRow = i;
					bestColumn = j;
				}
		}
	} // end calcBestSpaces
	
	/**
	 * Prints file analyzing win-lose-draw statistics, best possible move, and number of calculations
	 */
	private void printTip() {
		for (int i = 0; i < 9; i++)
			for (int j = 0; j < 9; j++)
				if (stats[i][j] != null) {
					System.out.println("Row " + (i + 1) + ", Column " + (j + 1));
					System.out.println("    Win Probability: " + stats[i][j].getWinProb());
					System.out.println("    Loss Probability: " + stats[i][j].getLossProb());
					System.out.println("    Tie Probability: " + stats[i][j].getTieProb());
				}
		System.out.println("Number of Calculations: " + numCalcs);
		System.out.println("Best Possible Move: Row " + (bestRow + 1) + ", Column " + (bestColumn + 1));
	} // end printTip

} // end Tip

	
	
	
					
