package asgn1SoccerCompetition;

import java.util.ArrayList;
import java.util.Collections;

import asgn1Exceptions.LeagueException;
import asgn1Exceptions.TeamException;

/**
 * A class to model a soccer league. Matches are played between teams and points awarded for a win,
 * loss or draw. After each match teams are ranked, first by points, then by goal difference and then
 * alphabetically. 
 * 
 * @author Oswald Doring
 * @version 1.0
 *
 */
public class SoccerLeague implements SportsLeague{
	// Specifies the number of team required/limit of teams for the league
	private int requiredTeams;
	// Specifies if the league is in the off season
	private boolean offSeason;
	// Creates an ArrayList to store the Soccer Teams in the league
	private ArrayList<SoccerTeam> soccerTeams;
	
	
	/**
	 * Generates a model of a soccer team with the specified number of teams. 
	 * A season can not start until that specific number of teams has been added. 
	 * Once that number of teams has been reached no more teams can be added unless
	 * a team is first removed. 
	 * 
	 * @param requiredTeams The number of teams required/limit for the league.
	 */
	public SoccerLeague (int requiredTeams){
		this.requiredTeams = requiredTeams;
		offSeason = true;
		soccerTeams = new ArrayList<SoccerTeam>();	
	}

	/**
	 * Registers a team to the league.
	 * 
	 * @param team Registers a team to play in the league.
	 * @throws LeagueException If the season has already started, if the maximum number of 
	 * teams allowed to register has already been reached or a team with the 
	 * same official name has already been registered.
	 */
	public void registerTeam(SoccerTeam team) throws LeagueException {
		if(!offSeason){
			throw new LeagueException("You cannot register teams whilst a season is already in progress!");
		}
		
		if(requiredTeams == soccerTeams.size()){
			throw new LeagueException("You have reached the maximum number of teams in a single league!");
		}
		
		for(SoccerTeam thisTeam: soccerTeams){
			if (thisTeam.getOfficialName().equals(team.getOfficialName())){
				throw new LeagueException("This team cannot be registered as its name is already in use!");
			}
		}
		
		soccerTeams.add(team);
	}
	
	/**
	 * Removes a team from the league.
	 * 
	 * @param team The team to remove
	 * @throws LeagueException if the season has not ended or if the team is not registered into the league.
	 */
	public void removeTeam(SoccerTeam team) throws LeagueException{
		if(!offSeason){
			throw new LeagueException("You cannot remove a team whilst a season is already in progress!");
		}
		if(soccerTeams.contains(team)){
			soccerTeams.remove(team);
		}else{
			throw new LeagueException("This team is not currently registered in the league!");
		}
	}
	
	/** 
	 * Gets the number of teams currently registered to the league
	 * 
	 * @return the current number of teams registered
	 */
	public int getRegisteredNumTeams(){
		return soccerTeams.size();
	}
	
	/**
	 * Gets the number of teams required for the league to begin its 
	 * season which is also the maximum number of teams that can be registered
	 * to a league.

	 * @return The number of teams required by the league/maximum number of teams in the league
	 */
	public int getRequiredNumTeams(){
		return requiredTeams;
	}
	
	/** 
	 * Starts a new season by reverting all statistics for each team to initial values.
	 * 
	 * @throws LeagueException if the number of registered teams does not equal the required number of teams or if the season has already started
	 */
	public void startNewSeason() throws LeagueException{
		if (soccerTeams.size() != requiredTeams){
			throw new LeagueException("There is not enough teams registered in the league to start a season!");
		}
		for(SoccerTeam thisTeam: soccerTeams){
			thisTeam.resetStats();
		}
		offSeason = false;
	}
	

	/**
	 * Ends the season.
	 * 
	 * @throws LeagueException if season has not started
	 */
	public void endSeason() throws LeagueException{
		if(offSeason){
			throw new LeagueException("Offseason is already in progress!");
		} else {offSeason = true;}
	}
	
	/**
	 * Specifies if the league is in the off season (i.e. when matches are not played).
	 * @return True If the league is in its off season, false otherwise.
	 */
	public boolean isOffSeason(){
		return this.offSeason;
	}
	
	/**
	 * Returns a team with a specific name.
	 * 
	 * @param name The official name of the team to search for.
	 * @return The team object with the specified official name.
	 * @throws LeagueException if no team has that official name.
	 */
	public SoccerTeam getTeamByOfficalName(String name) throws LeagueException{
		for(SoccerTeam thisTeam: soccerTeams){
			if(name.equals(thisTeam.getOfficialName())){
				return thisTeam;
			} 
		} throw new LeagueException("This team does not exist in this league!");
	}
		
	/**
	 * Plays a match in a specified league between two teams with the respective goals. After each match the teams are
	 * resorted.
     *
	 * @param homeTeamName The name of the home team.
	 * @param homeTeamGoals The number of goals scored by the home team.
	 * @param awayTeamName The name of the away team.
	 * @param awayTeamGoals The number of goals scored by the away team.
	 * @throws LeagueException If the season has not started or if both teams have the same official name. 
	 */
	public void playMatch(String homeTeamName, int homeTeamGoals, String awayTeamName, int awayTeamGoals) throws LeagueException{
		if(offSeason){
			throw new LeagueException("The season has not started yet!");
		}
		if (homeTeamName == awayTeamName ){
			throw new LeagueException("The same team cannot play each other!");
		}
		
		SoccerTeam homeTeam = getTeamByOfficalName(homeTeamName);
		SoccerTeam awayTeam = getTeamByOfficalName(awayTeamName);
		try {
			homeTeam.playMatch(homeTeamGoals, awayTeamGoals);
			awayTeam.playMatch(awayTeamGoals, homeTeamGoals);
		} catch (TeamException e){
			System.out.println(e.getMessage());
			e.printStackTrace();
		}
	}
	
	/**
	 * Displays a ranked list of the teams in the league  to the screen.
	 */
		
	public void displayLeagueTable(){
		java.util.Iterator<SoccerTeam> teamIterator = soccerTeams.iterator();

		while (teamIterator.hasNext()) {
			teamIterator.next().displayTeamDetails();
		}			
	}	
	
	/**
	 * Returns the highest ranked team in the league.
     *
	 * @return The highest ranked team in the league. 
	 * @throws LeagueException if the number of teams is zero or less than the required number of teams.
	 */
	public SoccerTeam getTopTeam() throws LeagueException{
		SoccerTeam topTeam = soccerTeams.get(0);
		
		if (soccerTeams.size() < requiredTeams){
			throw new LeagueException("There is insufficient teams in the league!");
		}
		
		for (SoccerTeam thisTeam: soccerTeams){
			if(topTeam.compareTo(thisTeam) >= 1){
				topTeam = thisTeam;
			}
		}
		
		return topTeam;
	}

	/**
	 * Returns the lowest ranked team in the league.
     *
	 * @return The lowest ranked team in the league. 
	 * @throws LeagueException if the number of teams is zero or less than the required number of teams.
	 */
	public SoccerTeam getBottomTeam() throws LeagueException{
		SoccerTeam bottomTeam = soccerTeams.get(0);
		
		if (soccerTeams.size() < requiredTeams){
			throw new LeagueException("There is insufficient teams in the league!");
		}
		
		for (SoccerTeam thisTeam: soccerTeams){
			if(bottomTeam.compareTo(thisTeam) <= -1){
				bottomTeam = thisTeam;
			}
		}
		return bottomTeam;		
	}

	/** 
	 * Sorts the teams in the league.
	 */
    public void sortTeams(){		
		Collections.sort(soccerTeams); 
    }
    
    /**
     * Specifies if a team with the given official name is registered to the league.
     * 
     * @param name The name of a team.
     * @return True if the team is registered to the league, false otherwise. 
     */
    public boolean containsTeam(String name){
    	boolean containsTeam = false;
    	
    	for(SoccerTeam thisTeam: soccerTeams){
    		if(thisTeam.getOfficialName().equals(name)){
    			containsTeam = true;
    		}
    	} 
    
    	return containsTeam;
    }
    
}

