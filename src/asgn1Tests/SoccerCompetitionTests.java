package asgn1Tests;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import asgn1Exceptions.CompetitionException;
import asgn1Exceptions.LeagueException;
import asgn1Exceptions.TeamException;
import asgn1SoccerCompetition.SoccerCompetition;
import asgn1SoccerCompetition.SoccerLeague;
import asgn1SoccerCompetition.SoccerTeam;

/**
 * A set of JUnit tests for the asgn1SoccerCompetition.SoccerCompetition class
 *
 * @author Oswald Doring
 *
 */
public class SoccerCompetitionTests {
		
	SoccerCompetition testCompetition;
	SoccerTeam testTeamOne, testTeamTwo, testTeamThree, testTeamFour, 
	testTeamFive,	testTeamSix, testTeamSeven, testTeamEight, testTeamNine;
	SoccerLeague leagueOne, leagueTwo, leagueThree;
	
	@Before
	/* Creates a soccer competition with a set number of leagues and teams within those leagues for testing */
	public void setupCompetition() throws CompetitionException{
		try {
			testCompetition = new SoccerCompetition("National League", 3, 3);
			
			leagueOne = testCompetition.getLeague(0);
			leagueTwo = testCompetition.getLeague(1);
			leagueThree = testCompetition.getLeague(2);

			testTeamOne = new SoccerTeam("Asgard City", "Thunderstrikes"); 
			testTeamTwo = new SoccerTeam("Central City", "Speedsters");
			testTeamThree = new SoccerTeam("Gotham City", "Dark Knights"); 
			testTeamFour = new SoccerTeam("Paradise Island", "Wicked Wonders"); 
			testTeamFive = new SoccerTeam("Canadian", "Wolverines"); 
			testTeamSix = new SoccerTeam("Metropolis", "Men of Steel"); 
			testTeamSeven = new SoccerTeam("Queens Bld", "Websters"); 
			testTeamEight = new SoccerTeam("Serenity", "Fireflys"); 
			testTeamNine = new SoccerTeam("Brooklyn", "Patriots");

			leagueOne.registerTeam(testTeamOne);
			leagueOne.registerTeam(testTeamTwo);
			leagueOne.registerTeam(testTeamThree);
			leagueTwo.registerTeam(testTeamFour);
			leagueTwo.registerTeam(testTeamFive);
			leagueTwo.registerTeam(testTeamSix);
			leagueThree.registerTeam(testTeamSeven);
			leagueThree.registerTeam(testTeamEight);
			leagueThree.registerTeam(testTeamNine);
			
		} catch (LeagueException | TeamException e){
			System.out.println(e.getMessage());
			e.printStackTrace();
			}
		}
		
	/* Testing all the exceptions contained within asgn1SoccerCompetition.SoccerCompetition */
	
	@Test(expected = CompetitionException.class)
	/* Testing for exception when a negative league num is entered */
	public void testGetLeagueExceptionNegative() throws CompetitionException{
		testCompetition.getLeague(-1);
	}
		
	@Test(expected = CompetitionException.class)
	/* Testing for an exception when a league num higher than number of leagues in entered */
	public void testGetLeagueExceptionOutOfBounds() throws CompetitionException{
		testCompetition.getLeague(4);
	}	
	
	/* Testing the functionality of asgn1SoccerCompetition.SoccerCompetition */
	
	@Test
	/* Testing if competition is started, checked the bool Offseason 
	 * in each (should be false) and the registered teams in each league */ 
	public void testStartNewSeason() throws CompetitionException{
		testCompetition.startSeason();
		
		assertFalse(leagueOne.isOffSeason());
		assertFalse(leagueTwo.isOffSeason());
		assertFalse(leagueThree.isOffSeason());
		assertEquals(3, leagueOne.getRegisteredNumTeams());
		assertEquals(3, leagueTwo.getRegisteredNumTeams());
		assertEquals(3, leagueThree.getRegisteredNumTeams());
	}
	
	@Test 
	/* Start the competition seasons with two leagues than plays a few matches, 
	 * then checks the promotion and demotion of teams between leagues */
	public void testEndSeasonWithTwoLeagues() throws CompetitionException{
		try {
			testCompetition.startSeason();
			
			leagueOne.playMatch("Asgard City", 7, "Central City", 5);
			leagueOne.playMatch("Asgard City", 4, "Gotham City", 0);
			leagueOne.playMatch("Central City", 4, "Gotham City", 2);
			
			leagueTwo.playMatch("Paradise Island", 7, "Canadian", 5);
			leagueTwo.playMatch("Paradise Island", 4, "Metropolis", 0);
			leagueTwo.playMatch("Canadian", 4, "Metropolis", 2);
			
			testCompetition.endSeason();
		
			assertTrue(leagueOne.isOffSeason());
			assertTrue(leagueTwo.isOffSeason());
	
			assertTrue(leagueOne.containsTeam("Paradise Island"));
			assertTrue(leagueTwo.containsTeam("Gotham City"));
		
		} catch(LeagueException e){
			System.out.println(e.getMessage());
			e.printStackTrace();
		}
	}
	
	@Test 
	/* Start the competition season with three leagues than plays a few matches, 
	 * then checks the promotion and demotion of teams between leagues */
	public void testEndSeasonWithThreeLeagues() throws CompetitionException{
		try {
			testCompetition.startSeason();
			
			leagueOne.playMatch("Asgard City", 7, "Central City", 5);
			leagueOne.playMatch("Asgard City", 4, "Gotham City", 0);
			leagueOne.playMatch("Central City", 4, "Gotham City", 2);
			
			leagueTwo.playMatch("Paradise Island", 7, "Canadian", 5);
			leagueTwo.playMatch("Paradise Island", 4, "Metropolis", 0);
			leagueTwo.playMatch("Canadian", 4, "Metropolis", 2);
			
			leagueThree.playMatch("Queens Bld", 7, "Serenity", 5);
			leagueThree.playMatch("Queens Bld", 4, "Brooklyn", 0);
			leagueThree.playMatch("Serenity", 4, "Brooklyn", 2);

			testCompetition.endSeason();
		
			assertTrue(leagueOne.isOffSeason());
			assertTrue(leagueTwo.isOffSeason());
			assertTrue(leagueThree.isOffSeason());
	
			assertTrue(leagueOne.containsTeam("Paradise Island"));
			assertTrue(leagueTwo.containsTeam("Gotham City"));
			assertTrue(leagueTwo.containsTeam("Queens Bld"));
			assertTrue(leagueThree.containsTeam("Metropolis"));
		
		} catch(LeagueException e){
			System.out.println(e.getMessage());
			e.printStackTrace();
		}
	}
	
}


