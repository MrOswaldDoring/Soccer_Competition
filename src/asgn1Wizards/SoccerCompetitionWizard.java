package asgn1Wizards;



import java.io.BufferedReader;
import java.io.FileReader;
import java.util.Scanner;

import asgn1SoccerCompetition.SoccerCompetition;
import asgn1SoccerCompetition.SoccerTeam;


/** A class that acts as a public interface to the soccer competition model developed for the
 * CAB302 Assignment. The main method  asks the user to enter in a filename. The file 
 * contains details about a soccer competition including: number of leagues, 
 * number of teams per league and results of matches. First, the program outputs the ranking
 * of teams in each league before the start of the season. Then, it simulates the competition
 * and outputs a ranked list of teams in each league at the end of the season. Finally,
 * it outputs a ranked list of teams in each league at the beginning of the next season.
 * 
 * */

public class SoccerCompetitionWizard {

	final static String COMMA = ",";
	
	public static void main(String[] args) {
        try {
            Scanner scanner = new Scanner(System.in);
            System.out.println("Enter the Data file name");
            String filename = scanner.next();
            BufferedReader br = new BufferedReader(new FileReader(filename));

            // create competition
        	String line = br.readLine();
       	    String[] compArr = line.split(COMMA);
       	    String competitionName = compArr[0];
       	    int numLeagues = Integer.parseInt(compArr[1]);  
       	    int numTeams = Integer.parseInt(compArr[2]);           	    
        	SoccerCompetition sc = new SoccerCompetition(competitionName, numLeagues, numTeams);

        	// create teams
        	for(int i=0; i<(numTeams*numLeagues); i++){
        		line = br.readLine();
        		String[] teamArr = line.split(COMMA);
        		int leagueNum = Integer.parseInt(teamArr[0]);
        		String officialName = teamArr[1];
        		String nickName = teamArr[2];
        		SoccerTeam team = new SoccerTeam(officialName, nickName);
        		sc.getLeague(leagueNum).registerTeam(team);
        	}
        	
        	sc.startSeason();
        	System.out.println("");
        	System.out.println("Initial Standings - Season 1");
        	sc.displayCompetitionStandings();	

    		line = br.readLine();

       	    // do results
       	    while (line != null) {
        		String[] gameArr = line.split(COMMA);
        		int league = Integer.parseInt(gameArr[0]);
        		String homeTeam = gameArr[1];
        		int homeGoals = Integer.parseInt(gameArr[2]);
        		String awayTeam = gameArr[3];
        		int awayGoals = Integer.parseInt(gameArr[4]);
        		sc.getLeague(league).playMatch(homeTeam, homeGoals, awayTeam, awayGoals);        		
       	        line = br.readLine();
       	     }
       	    
        	System.out.println("");       	    
        	System.out.println("Final Standings - Season 1");
       	    sc.displayCompetitionStandings();

         	for(int i=0; i < numLeagues; i++){
        		System.out.println("The premiers of Division " + (i+1) + " are the " + sc.getLeague(i).getTopTeam().getNickName());
        		System.out.println("The wooden spooners of Division  " + (i+1) + " are the " + sc.getLeague(i).getBottomTeam().getNickName());
			
        	}
        
        	sc.endSeason();
        	sc.startSeason();

        	System.out.println("");
        	System.out.println("Initial Standings - Season 2");
        	sc.displayCompetitionStandings();

        	
        } catch (Exception e){
        	System.out.println(e.getMessage());
        	e.printStackTrace();
        }
	}
}