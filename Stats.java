/**
 * Tracks win lose, draw, and game statistics
 *
 * @author Colin Elkin
 */

public class Stats {

	// defines integer representing number of games played
	private int games = 0;
	// defines integer representing number of games won
	private int wins = 0;
	// defines integer representing number of games lost
	private int losses = 0;
	// defines integer representing number of games tied
	private int ties = 0;
	
	/**
	 * Increments number og games and number of wins
	 */
	public void addWin() {
		games++;
		wins++;
	} // end addWin

	/**
	 * Increments number og games and number of loss
	 */
	public void addLoss() {
		games++;		
		losses++;
	} // end addLoss

	/**
	 * Increments number og games and number of ties
	 */
	public void addTie() {
		games++;
		ties++;
	} // end addTie

	/**
	 * Retrieves ratio of number of wins to number of games
	 *
	 * @return aforementioned ratio
	 *	POSTCONDITION: 0 <= output <= 1
	 */
	public double getWinProb() {
		return (double)wins / (double)games;
	} // end getWinProb

	/**
	 * Retrieves ratio of number of losses to number of games
	 *
	 * @return aforementioned ratio
	 *	POSTCONDITION: 0 <= output <= 1
	 */
	public double getLossProb() {
		return (double)losses / (double)games;
	} // end getLossProb

	/**
	 * Retrieves ratio of number of ties to number of games
	 *
	 * @return aforementioned ratio
	 *	POSTCONDITION: 0 <= output <= 1
	 */
	public double getTieProb() {
		return (double)ties / (double)games;
	} // end getTieProb

} // end Stats
