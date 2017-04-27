package asgn1SoccerCompetition;

import asgn1Exceptions.LeagueException;


/**
 * A general purpose interface to model a sporting league. 
 * 
 * @author Alan Woodley
 * @version 1.0
 *
 */

public interface SportsLeague {

	
	/** 
	 * Gets the number of teams currently registered to the league.
	 * 
	 * @return the current number of teams registered.
	 */
	public int getRegisteredNumTeams();
	
	/**
	 * Gets the number of teams required for the league to begin its 
	 * season which is also the maximum number of teams that can be registered
	 * to a league.

	 * @return The number of teams required by the league/maximum number of teams in the league.
	 */
	public int getRequiredNumTeams();
	/** 
	 * Starts a new season by reverting all statistics for each times to initial values.
	 * 
	 * @throws LeagueException if the number of registered teams does not equal the required number of teams or if the season has already started
	 */
	public void startNewSeason() throws LeagueException;
	
	/**
	 * Ends the season.
	 * 
	 * @throws LeagueException if season has not started
	 */
	public void endSeason() throws LeagueException;	
	/**
	 * Specifies if the league is in the off season (i.e. when matches are not played).
	 * @return True If the league is in its off season, false otherwise.
	 */
	public boolean isOffSeason();
		
	/**
	 * Plays a match in a specified league between two teams with the respective points. After each match the teams are
	 * resorted
     *
	 * @param homeTeamName The name of the home team.
	 * @param homeTeamPoints The number of points scored by the home team.
	 * @param awayTeamName The name of the away team.
	 * @param awayTeamPoints The number of points scored by the away team.
	 * @throws LeagueException If the season has not started or if both teams have the same official name.
	 */
	public void playMatch(String homeTeamName, int homeTeamPoints, String awayTeamName, int awayTeamPoints) throws LeagueException;
	
	/**
	 * Displays a ranked list of the teams in the league  to the screen.
	 */
	public void displayLeagueTable();

	/** 
	 * Sorts the teams in the league.
	 */
    public void sortTeams();
    
    /** Specifies if a team with the given official name is registerd to the league.
     * 
     * @param name The name of a team.
     * @return True if the team is registered to the league, false otherwise. 
     */
    public boolean containsTeam(String name);	
	
}
