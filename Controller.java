import java.util.Scanner;

/**
 * Represents a user-controlled front end for an OuterBoard
 *
 * @author Colin Elkin
 */

public class Controller {
	
	// defines a Scanner object
	private Scanner scan = new Scanner(System.in);
	// defines an OuterBoard object, to be defined in constructor
	private OuterBoard display;
	
	/**
	 * Constructor, creates OuterBoard object using Player parameters
	 *
	 * @param player1 Player object to serve as initial current player
	 *
	 * @param player2 Player object to serve as initial secondary player
	 */
	public Controller(Player player1, Player player2) {
		display = new OuterBoard(player1, player2);
	}

	/**
	 * Cycles through entirety of user-controlled HT3 game
	 *
	 * @return winner status of display
	 *		POSTCNDITION: output must be 'x', 'o', 'W', or '_'
	 */
	public char singleGame() {
		// checks for game over status
		display.checkOuterWinProgress();
		// if game is over, returns winner status
		if (display.checkOuterWinStatus() != '_') {
			printOuterBoard();
			return display.checkOuterWinStatus();
		}
		// if game continues, takes current player's turn
		else {
			printOuterBoard();
			System.out.println(display.getPlayer() + "'s turn (" 
				+ display.getTurn() + ").");
			takeUserTurn();
			// switches active player for next method call
			display.switchPlayers();
			return singleGame();
		}
	} // end playGame

	/**
	 * Prompts player for row and column coordinates and sets character accordingly
	 */
	private void takeUserTurn() {
		System.out.println("Choose a row from 1 (top) to 9 (bottom).");
		System.out.println("<For a tip on what move to make next, type tip.>"); // move these to top and only call them once
		System.out.println("<To quit game, type quit.>");
		int m = inputMove();
		System.out.println("Choose a column from 1 (left) to 9 (right).");
		System.out.println("<For a tip on what move to make next, type tip.>");
		System.out.println("<To quit game, type quit.>");
		int n = inputMove();
		// if the coordinates do not indicate a free space
		if (display.getOutercharTwo(m - 1, n - 1) != '_') {
			System.out.println("Not a free space.");
			// scan.nextLine();
			takeUserTurn();
		} else
			// note that player integer range is 1-9
			// while index integer range is 0-8
			display.setOutercharTwo(m - 1, n - 1, display.getTurn());
	} // end takeManualTurn
	
	/**
	 * Scans keyboard for row or column input
	 * 
	 * @return row or column Integer object as indicated by keyboard
	 *		POSTCONDITION: 0 <= output <= 9
	 */
	private Integer inputMove() { 
		String m = scan.nextLine();
		// checks that an integer was entered
		// change this to while loop
		if (isInteger(m) | m.equals("quit") | m.equals("tip")) {
			//m = scan.nextLine();
			// checks to see if player quits
			if (m.equals("quit")) {
				System.out.println("Game aborted.");
				System.exit(0);
				return 0;
			}
			if (m.equals("tip")) {
			  	OuterBoard copyOfDisplay = new OuterBoard(display);
				Tip tip = new Tip(copyOfDisplay);
			  	tip.getTip();
				//scan.nextLine();
				System.out.println("Still your move.");
				return inputMove();
			}
			// checks that the integer is in player range
			if ((Integer.parseInt(m) < 1) | (Integer.parseInt(m) > 9)) {
				System.out.println(
					"You must select a one-digit, positive number.");
				return inputMove();
			} 
			else
				return Integer.parseInt(m);
		} 
		else if (!isInteger(m) & !m.equals("quit") & !m.equals("tip")) {
			// if input is not an integer
			System.out.print("Not a valid move. \n");
			scan.nextLine();
			return inputMove();
		}
		return inputMove();
	} // end inputNumber
	
	/**
	 * Determines whether the value of a String is an integer
	 *
	 * @param s String whose value may or may not be an integer
	 *
	 * @return truth value of whether value of String is integer
	 */
	private boolean isInteger(String s) {
	    try { 
	        Integer.parseInt(s); 
	    } catch(NumberFormatException e) { 
	        return false; 
	    }
	    return true;
	} // end isInteger
	
	/**
	 * Prints ASCII representation of game board and its contents
	 */
	private void printOuterBoard() {
		for (int i = 0; i < 9; i++) {
			System.out.print("|");
			for (int j = 0; j < 9; j++) 
				System.out.print(display.getOutercharTwo(i, j) + "|");
			System.out.print("\n");
		} // end printOuterBoard
		
	} // end Controller

	
	
} // end Game
