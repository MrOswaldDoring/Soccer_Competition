package asgn1SoccerCompetition;

/**
 * A general purpose interface to model a sporting competition. 
 * 
 * @author Alan Woodley
 * @version 1.0
 *
 */
public interface SportsCompetition {
	
	/**
	 * Starts a new sports season.
	 */
	public void startSeason();

	/**
	 * Displays the standings for the competition in a tabular format.
	 * If the competition contains multiple leagues then multiple
	 * tables are presented.
	 */
	public void displayCompetitionStandings();

	/** 
	 * Ends a sports season.
	 */
	public void endSeason();
	
}
