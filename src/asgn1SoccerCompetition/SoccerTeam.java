/**
 * A concrete class that represents a soccer team.
 * 
 * @author Alan Woodley
 * @version 1.0
 *
 */

package asgn1SoccerCompetition;

import asgn1Exceptions.TeamException;
import asgn1SportsUtils.WLD;


/**
 * A class to model a soccer team. The team contains a series of statistics which are altered throughout
 * a season depending on the results from their matches. 
 * 
 * @author Oswald Doring
 * @version 1.0
 *
 */
public class SoccerTeam implements SportsTeam, Comparable<SoccerTeam>{
	
	private String officialName;
	private String nickName;
	private int goalsScoredSeason;
	private int goalsConcededSeason;
	private int matchesWon;
	private int matchesLost;
	private int matchesDrawn;	
	private int competitionPoints;
	private SportsTeamForm form; 
		
	/**
	 * Creates a Soccer Team with the specified official name and nick name.
	 * 
	 * @param official The official name of the soccer team.
	 * @param nick The nick name of the soccer team.
	 * @throws TeamException If either the official or nick are empty strings.
	 */
	public SoccerTeam(String official, String nick) throws TeamException{
		if (official == ""){
			throw new TeamException("An Official Name was not entered!");
		}
		
		if (nick == ""){
			throw new TeamException("A Nickname was not entered!");
		}
		
		this.officialName = official;
		this.nickName = nick;
		this.goalsScoredSeason = 0;
		this.goalsConcededSeason = 0;
		this.matchesWon = 0;
		this.matchesLost = 0;
		this.matchesDrawn = 0;
		this.competitionPoints = 0;
		form = new SportsTeamForm();
		
	}
	
	/**
	 * Displays a String that provides details about the team. The format of the string is as follows
	 * (where - indicates a tab) :
	 * 
	 * official name - nick name - form - matches played - matches won - matches lost - matches drawn - season goals scores - season goals conceded - season goal difference - points
	 * 
	 */
	public void displayTeamDetails(){
		System.out.println(officialName  + '\t' + nickName + '\t' + form + '\t' + (+ matchesWon + matchesLost + matchesDrawn) + '\t' + matchesWon + '\t' + matchesLost + '\t' + matchesDrawn +'\t' + goalsScoredSeason + '\t' + goalsConcededSeason + '\t' + this.getGoalDifference() + '\t' + competitionPoints);
	}	
	
	/**
	 * Returns the team's official name.
	 * @return The team's official name.

	 */
	public String getOfficialName(){
		return officialName;
	}

	/**
	 * Returns the team's nick name.
	 * @return The teams's nick name.
	 */
	public String getNickName(){
		return nickName;
	}
	
	/**
	 * Returns the goals scored by the team so far this season.
	 * @return The goals scored this season.
	 */
	public int getGoalsScoredSeason(){
		return goalsScoredSeason;
	}

	/**
	 * Returns the goals conceded (goals scored by other teams against this team) by the team so far this season.
	 * @return The goals conceded this season.
	 */
	public int getGoalsConcededSeason(){
		return goalsConcededSeason;
	}

	/**
	 * Returns the number of matches won by the team so far this season.
	 * @return The number of matches won by the team so far this season.
	 */
	public int getMatchesWon(){
		return matchesWon;
	}

	/**
	 * Returns the number of matches lost by the team so far this season.
	 * @return The number of matches lost by the team so far this season.
	 */
	public int getMatchesLost(){
		return matchesLost;
	}

	/**
	 * Returns the number of matches drawn by the team so far this season.
	 * @return The number of matches drawn by the team so far this season.
	 */
	public int getMatchesDrawn(){
		return matchesDrawn;
	}
	
	/**
	 * Returns the number competition points of the team so far this season.
	 * @return The number competition points of the team so far this season.
	 */
	public int getCompetitionPoints(){
		return competitionPoints;
	}
		
	/**
	 * Returns the goal difference (goals scored minus goals conceded) for the team
	 * across the course of a season.
	 * @return The goal difference.
	 */
	public int getGoalDifference(){
		return (goalsScoredSeason-goalsConcededSeason);
	}
	
	/**
	 * Returns a string representing the recent results of the team.
	 * @return A string representing the recent results of the team.
	 */
	public String getFormString(){
		return form.toString();
	}
	
	/**
	 * Plays a match for a team
	 * 
	 * @param goalsFor The number of goals scored by the team.
	 * @param goalsAgainst The number of goals conceded by the team.
	 * @throws TeamException If the number of goals scored or conceded is an unrealistic number (less than 0 or greater than 20).
	 */
	public void playMatch(int goalsFor, int goalsAgainst) throws TeamException{
		if(goalsFor < 0 || goalsFor > 20){
			throw new TeamException("This is an unrealistic amount of goals scored!");
		}
		
		if(goalsAgainst < 0 || goalsAgainst >20){
			throw new TeamException("This is an unrealistic amount of goals conceded!");
		}
		
		goalsScoredSeason += goalsFor;
		goalsConcededSeason += goalsAgainst;
		
		if (goalsFor > goalsAgainst){
			matchesWon += 1;
			competitionPoints += 3;
			form.addResultToForm(WLD.WIN);
			
		} else if(goalsFor < goalsAgainst){
			matchesLost += 1;
			form.addResultToForm(WLD.LOSS);
			
		} else if(goalsFor == goalsAgainst){
			matchesDrawn += 1;
			competitionPoints += 1;
			form.addResultToForm(WLD.DRAW);
		}
	}	
	
	/**
	 * Compares one team to another team.
	 * 
	 * A positive number is returned if the other team has more points than this team.
	 * A negative number is returned if this team has more points than the other team.
	 * If both teams have same number of points then: 
	 *      A positive number is returned if the other team has a greater goal difference than this team.
	 *      A negative number is returned if this team has a greater goal difference than the other team.
     * If both teams have the same number of points and the same goal difference then:
	 *      A positive number is returned if the other team's official name occurs before this team alphabetically.
	 *      A negative number is returned if this team official name occurs before the other team alphabetically.
	 * If teams have the same number of points, same goal difference, and same name then 0 is returned
	 *      
 	 * @param other The other team 
	 * @return a number that is positive, negative or 0 as described above. 
	 */
	public int compareTo(SoccerTeam other){	
		if(other.competitionPoints == this.competitionPoints){
			if((other.getGoalDifference()) == (this.getGoalDifference())){ 
				return this.officialName.compareTo(other.officialName);
			} return (other.getGoalDifference()) - (this.getGoalDifference());
		} else return other.competitionPoints -  this.competitionPoints;			
	}	
		
	/**
	 * Resets the teams values to their original values.
	 */
	public void resetStats(){
		this.goalsScoredSeason = 0;
		this.goalsConcededSeason = 0;
		this.matchesWon = 0;
		this.matchesLost = 0;
		this.matchesDrawn = 0;
		this.competitionPoints = 0;	
		form.resetForm();
	}

}
