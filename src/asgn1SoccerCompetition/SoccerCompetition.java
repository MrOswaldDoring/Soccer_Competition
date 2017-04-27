/**
 * 
 */
package asgn1SoccerCompetition;

import java.util.ArrayList;

import asgn1Exceptions.CompetitionException;
import asgn1Exceptions.LeagueException;

/**
 * A class to model a soccer competition. The competition contains one or more number of leagues, 
 * each of which contain a number of teams. Over the course a season matches are played between
 * teams in each league. At the end of the season a premier (top ranked team) and wooden spooner 
 * (bottom ranked team) are declared for each league. If there are multiple leagues then relegation 
 * and promotions occur between the leagues.
 * 
 * @author Alan Woodley
 * @version 1.0
 *
 */
public class SoccerCompetition implements SportsCompetition{
	
	String name;
	// Creates an array list to stores the leagues within the competition
	ArrayList<SoccerLeague> competitionLeagues = new ArrayList<SoccerLeague>();
	
	/**
	 * Creates the model for a new soccer competition with a specific name,
	 * number of leagues and number of teams in each league
	 * 
	 * @param name The name of the competition.
	 * @param numLeagues The number of leagues in the competition.
	 * @param numTeams The number of teams in each league.
	 */
	public SoccerCompetition(String name, int numLeagues, int numTeams){
		this.name = name;
		int registerLeague;
		
		for(registerLeague = 0; registerLeague < numLeagues; registerLeague++){
			competitionLeagues.add(new SoccerLeague(numTeams));
		}
	}
	
	/**
	 * Retrieves a league with a specific number (indexed from 0). Returns an exception if the 
	 * league number is invalid.
	 * 
	 * @param leagueNum The number of the league to return.
	 * @return A league specified by leagueNum.
	 * @throws CompetitionException if the specified league number is less than 0.
	 *  or equal to or greater than the number of leagues in the competition.
	 */
	public SoccerLeague getLeague(int leagueNum) throws CompetitionException{
		if (leagueNum < 0 || leagueNum > competitionLeagues.size()){
			throw new CompetitionException("This is an invalid League number");
		}
		
		return competitionLeagues.get(leagueNum);	
	}

	/**
	 * Starts a new soccer season for each league in the competition.
	 */
	public void startSeason() {
		for(SoccerLeague league: competitionLeagues){
			try {
				league.startNewSeason();
			} catch (LeagueException e) {
				System.out.println(e.getMessage());
				e.printStackTrace();
			}
		}
	}

	/** 
	 * Ends the season of each of the leagues in the competition. 
	 * If there is more than one league then it handles promotion
	 * and relegation between the leagues.  
	 * 
	 */
	public void endSeason() {	
		java.util.Iterator<SoccerLeague> leagueIterator = competitionLeagues.iterator();
		java.util.LinkedList<SoccerTeam> promotionTeams = new java.util.LinkedList<SoccerTeam>();
		java.util.LinkedList<SoccerTeam> demotionTeams = new java.util.LinkedList<SoccerTeam>();

		try {
			while (leagueIterator.hasNext()) {
				SoccerLeague valueStore = leagueIterator.next();
				valueStore.endSeason();
				promotionTeams.add(valueStore.getTopTeam());
				demotionTeams.add(valueStore.getBottomTeam());
			}
		} catch (LeagueException e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
		}

		promotionTeams.removeFirst();
		demotionTeams.removeLast();

		for (int league = 1; league < competitionLeagues.size(); league++) {
			SoccerTeam promoteTeam = promotionTeams.removeFirst();
			SoccerTeam demoteTeam = demotionTeams.removeFirst();

			try {
				competitionLeagues.get(league).removeTeam(promoteTeam);
				competitionLeagues.get(league - 1).removeTeam(demoteTeam);

				competitionLeagues.get(league - 1).registerTeam(promoteTeam);
				competitionLeagues.get(league).registerTeam(demoteTeam);
			} catch (LeagueException e) {
				System.out.println(e.getMessage());
				e.printStackTrace();
			}
		}
	}

	/** 
	 * For each league displays the competition standings.
	 */
	public void displayCompetitionStandings(){
		for(SoccerLeague league: competitionLeagues){
			league.sortTeams();
		}

		int a = 0;
		System.out.println("+++++" + this.name + "+++++");
		java.util.Iterator<SoccerLeague> leagueIterator = competitionLeagues.iterator();

		while (leagueIterator.hasNext()) {
			System.out.println("---- League" + (a++) + " ----");
			System.out.println("Official Name" +  '\t' +  "Nick Name" + '\t' + "Form" + '\t' +  "Played" + '\t' + "Won" + '\t' + "Lost" + '\t' + "Drawn" + '\t' + "For" + '\t' + "Against" + '\t' + "GlDiff" + '\t' + "Points");
			leagueIterator.next().displayLeagueTable();
		}
	}
	

}

