import java.util.Scanner;
import java.util.Random;

/**
 * Holds all menus that lead to the start of a player-controlled HT3 game or Monte Carlo simulation
 *
 * @author Colin Elkin
 */

public class MainMenu {

	// creates new Scanner object
	private Scanner scan1 = new Scanner(System.in);
	private Scanner scan2 = new Scanner(System.in);
	// creates new Random object
	private Random rand = new Random();

	private String menuChoice = "";
	
	/**
	 * Prints start menu and prompts user for game mode
	 */
	public void menu() {
		while (! menuChoice.equals("quit")) { 		
			System.out.println("Type one of the following to begin:");
			System.out.println("   play - start new player-controlled game");
			System.out.println("   mc - run game in Monte Carlo mode");
			System.out.println("   quit - quit game");
			menuChoice = scan1.nextLine();
			if (menuChoice.equals("play"))
				playMenu();
			if (menuChoice.equals("mc"))
				mcMenu();
			if ((! menuChoice.equals("play")) & (! menuChoice.equals("mc"))
				& (! menuChoice.equals("quit"))) 
				System.out.print("Not a valid menu choice \n");
		}
	} // end menu
	
	/**
	 * Prints menu for player-controlled game and prompts user for player names
	 */
	private void playMenu() {
		System.out.println("Player 1, enter your name:");
		String name1 = scan1.nextLine();
		System.out.println("Player 2, enter your name:");
		String name2 = scan1.nextLine();
		String n1, n2;		
		// randomly assigns player names to X and O
		int n = rand.nextInt(2);
		if (n == 1) {
			n1 = name1;
			n2 = name2;
		} else {
			n1 = name2;
			n2 = name1;
		}
		System.out.println(n1 + " is X. " + n2 + " is O.");
		Player player1 = new Player(n1, 'x');
		Player player2 = new Player(n2, 'o');
		//OuterBoard game = new OuterBoard(player1, player2);
		Controller controller = new Controller(player1, player2);
		controller.singleGame();
	} // end playMenu
	
	/**
	 * Prints menu for Monte Carlo mode and prompts user for number of games to simulate
	 */
	private void mcMenu() {
		int m;
		System.out.println("How many games do you want to run?");
		
		while (! scan2.hasNextInt());
			m = scan2.nextInt();
		
		MonteCarlo mc = new MonteCarlo(m);
		mc.runSimulation();
		
		
	} // end mcMenu

} // end MainMenu
	
	
		
