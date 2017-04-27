package asgn1SoccerCompetition;

import asgn1Exceptions.TeamException;
/**
 * A general purpose interface to model a sporting team. 
 * 
 * @author Alan Woodley
 * @version 1.0
 *
 */

public interface SportsTeam {

	/**
	 * Displays a String that provides details about the team.
	 */
	public void displayTeamDetails();

	
	/**
	 * Returns a string representing the recent results of the team.
	 * @return A string representing the recent results of the team.
	 */
	public String getFormString();	
	
	/**
	 * Plays a match for a team.
	 * 
	 * @param pointsFor The number of points scored by the team.
	 * @param pointsAgainst The number of points conceded by the team.
	 * @throws TeamException If the number of points is unrealistic .
	 */
	public void playMatch(int pointsFor, int pointsAgainst) throws TeamException;	
	
		
	/**
	 * Resets the teams values to their original values.
	 */
	public void resetStats();
	
	
	
}
