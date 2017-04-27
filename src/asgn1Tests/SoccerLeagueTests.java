package asgn1Tests;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import asgn1Exceptions.LeagueException;
import asgn1Exceptions.TeamException;
import asgn1SoccerCompetition.SoccerLeague;
import asgn1SoccerCompetition.SoccerTeam;


/**
 * A set of JUnit tests for the asgn1SoccerCompetition.SoccerLeage class
 *
 * @author Oswald Doring
 *
 */
public class SoccerLeagueTests {
	
	/* Creates a league, an exception testing league and a couple of teams for testing */
	SoccerLeague testLeague, exceptionConstructor;
	SoccerTeam testTeamOne, testTeamTwo, testTeamThree, testTeamFour, testTeamFive;
	
	@Before /* Sets up the leagues and team and registers teams into the league */
	public void setup() throws LeagueException{
		exceptionConstructor = new SoccerLeague(2);
		testLeague = new SoccerLeague(4);
		
		try {
			testTeamOne = new SoccerTeam("Manchester United", "Man");
			testTeamTwo = new SoccerTeam("Central City", "Speedsters");
			testTeamThree = new SoccerTeam("Gotham City", "Dark Knights");
			testTeamFour = new SoccerTeam("Metropolis", "Men of Steel");
			testTeamFive = new SoccerTeam("Paradise Island", "Wicked Wonders");
			
			testLeague.registerTeam(testTeamOne);
			testLeague.registerTeam(testTeamTwo);
			testLeague.registerTeam(testTeamThree);
			testLeague.registerTeam(testTeamFour);
		} catch (TeamException e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
		}
	}
	
	/* Testing all the exceptions contained within asgn1SoccerCompetition.SoccerLeague */
	
	@Test(expected = LeagueException.class) 
	/* Testing if exception is thrown when a team is registered when season is in progress */
	public void testRegisterTeamExceptionOne() throws LeagueException{
		exceptionConstructor.startNewSeason();
		exceptionConstructor.registerTeam(testTeamOne);
	}
	
	@Test(expected = LeagueException.class)
	/* Testing if exception is thrown when more teams are registered than allowed in league */ 
	public void testRegisterTeamExceptionTwo() throws LeagueException{
		exceptionConstructor.endSeason();
		exceptionConstructor.registerTeam(testTeamOne);
		exceptionConstructor.registerTeam(testTeamTwo);
		exceptionConstructor.registerTeam(testTeamThree);
	}
	
	@Test(expected = LeagueException.class)
	/* Testing if an exception is thrown when the same team is attempted to be registered twice */
	public void testRegisterTeamExceptionThree() throws LeagueException{
		exceptionConstructor.endSeason();
		exceptionConstructor.registerTeam(testTeamOne);
		exceptionConstructor.registerTeam(testTeamOne);
	}
	
	@Test(expected = LeagueException.class)
	/* Testing if an exception is thrown when a team is removed when a season is in progress */
	public void testRemoveTeamExceptionOne() throws LeagueException{
		exceptionConstructor.endSeason();
		exceptionConstructor.registerTeam(testTeamFour);
		exceptionConstructor.registerTeam(testTeamOne);
		exceptionConstructor.startNewSeason();
		exceptionConstructor.removeTeam(testTeamFour);
	}
	
	@Test(expected = LeagueException.class)
	/* Testing if an exception is thrown when a team is removed but isn't initially in the league */
	public void testRemoveTeamExceptionTwo() throws LeagueException{
		exceptionConstructor.endSeason();
		exceptionConstructor.registerTeam(testTeamOne);
		exceptionConstructor.removeTeam(testTeamFive);
	}
	
	@Test(expected = LeagueException.class)
	/* Testing if an exception is thrown when a season is started without enough teams */
	public void testStartNewSeasonException() throws LeagueException{
		exceptionConstructor.endSeason();
		exceptionConstructor.registerTeam(testTeamFive);
		exceptionConstructor.startNewSeason();
	}
	
	@Test(expected = LeagueException.class)
	/* Testing if an exception is thrown when endSeason is called whilst it is already in progress */
	public void testEndSeasonException() throws LeagueException{
		exceptionConstructor.endSeason();
		exceptionConstructor.endSeason();
	}
	
	@Test(expected = LeagueException.class)
	/* Testing if an exception is thrown when an invalid 
	 * Official Name is entered into getTeamByOfficialName */
	public void testGetOfficialNameException() throws LeagueException{
		exceptionConstructor.endSeason();
		exceptionConstructor.registerTeam(testTeamOne);
		exceptionConstructor.registerTeam(testTeamFive);
		exceptionConstructor.getTeamByOfficalName("Gotham City");
	}
	
	@Test(expected = LeagueException.class)
	/* Testing if an exception is thrown when a match is played in the Offseason */
	public void testPlayMatchExceptionOne() throws LeagueException{
		exceptionConstructor.endSeason();
		exceptionConstructor.playMatch("Manchester City", 4, "Gotham City", 5);
	}
	
	@Test(expected = LeagueException.class)
	/* Testing if an exception is thrown when a match is played between the same two teams */
	public void testPlayMatchExceptionTwo() throws LeagueException{
		exceptionConstructor.endSeason();
		exceptionConstructor.registerTeam(testTeamOne);
		exceptionConstructor.registerTeam(testTeamTwo);
		exceptionConstructor.startNewSeason();
		exceptionConstructor.playMatch("Manchester United", 3, "Manchester United", 2);
	}
	
	@Test(expected = LeagueException.class)
	/* Testing if an exception is thrown when their is 
	 * insufficient teams in the league and getTopTeam is called */
	public void testGetTopTeamException() throws LeagueException{
		exceptionConstructor.endSeason();
		exceptionConstructor.registerTeam(testTeamFive);
		exceptionConstructor.getTopTeam();
	}
	
	@Test(expected = LeagueException.class)
	/* Testing if an exception is thrown when their is 
	 * insufficient teams in the league when getBottomTeam is called */
	public void testGetBottomTeamException() throws LeagueException{
		exceptionConstructor.endSeason();
		exceptionConstructor.registerTeam(testTeamFive);
		exceptionConstructor.getBottomTeam();
	}
	
	/*Testing the functionality asgn1SoccerCompetition.SoccerLeague */
	
	@Test 
	/* Testing to ensure the all teams have been registered (4) */
	public void testingRegisterTeam() throws LeagueException{
		assertEquals(4, testLeague.getRegisteredNumTeams());
	}
	
	@Test 
	/* Testing to ensure a team is able to be removed from the league, 
	 * then returns correct registered teams (3) */
	public void testRemoveTeam() throws LeagueException{
		testLeague.removeTeam(testTeamOne);
		assertEquals(3, testLeague.getRegisteredNumTeams());
	}
	
	@Test 
	/* Testing the value of required teams (4) */
	public void testRequiredTeam() throws LeagueException{
		assertEquals(4, testLeague.getRequiredNumTeams());
	}
	
	@Test 
	/* Testing to ensure status of Offseason is changed when season starts */
	public void testStartSeason() throws LeagueException{
		testLeague.startNewSeason();
		assertFalse(testLeague.isOffSeason());
	}
	
	@Test 
	/* Testing get team by official name returns correct value ("Manchester United") */
	public void testGetOfficialName() throws LeagueException{
		assertEquals(testTeamOne, testLeague.getTeamByOfficalName("Manchester United"));
	}
	
	@Test 
	/* Testing a simple get top team where one team has more competition points */
	public void testTopTeamOne() throws LeagueException{
		testLeague.startNewSeason();
		testLeague.playMatch("Manchester United", 3, "Central City", 0);
		testLeague.playMatch("Gotham City", 2, "Metropolis", 1);
		testLeague.playMatch("Manchester United", 2, "Gotham City", 4);
		assertEquals(testTeamThree, testLeague.getTopTeam());
	}
	
	@Test 
	/* Testing a get top team where two teams have same 
	 * competition points but one has greater goal diff */
	public void testTopTeamTwo() throws LeagueException{
		testLeague.startNewSeason();
		testLeague.playMatch("Manchester United", 0, "Central City", 4);
		testLeague.playMatch("Gotham City", 1, "Metropolis", 3);
		testLeague.playMatch("Metropolis", 1, "Central City", 1);
		assertEquals(testTeamTwo, testLeague.getTopTeam());
	}
	
	@Test 
	/* Testing a get top team where two teams have the same competition points 
	 * and goaldifference, should sort alphabetically */
	public void testTeamTeamThree() throws LeagueException{
		testLeague.startNewSeason();
		testLeague.playMatch("Manchester United", 3, "Gotham City", 1);
		testLeague.playMatch("Metropolis", 3, "Central City", 1);
		assertEquals(testTeamOne, testLeague.getTopTeam());
	}
	
	@Test 
	/* Testing a simple get bottom team where one team has less competition points */
	public void testBottomTeamOne() throws LeagueException{
		testLeague.startNewSeason();
		testLeague.playMatch("Manchester United", 3, "Central City", 0);
		testLeague.playMatch("Gotham City", 2, "Metropolis", 1);
		testLeague.playMatch("Metropolis", 2, "Central City", 4);
		assertEquals(testTeamFour, testLeague.getBottomTeam());
	}
	
	@Test 
	/* Testing a get bottom team where two teams have 
	 * same competition points but one has less goal diff */
	public void testBottomTeamTwo() throws LeagueException{
		testLeague.startNewSeason();
		testLeague.playMatch("Manchester United", 1, "Central City", 4);
		testLeague.playMatch("Gotham City", 0, "Metropolis", 4);
		assertEquals(testTeamThree, testLeague.getBottomTeam());
	}
	
	@Test 
	/* Testing a get bottom team where two teams have the same 
	competition points and goaldifference, should sort alphabetically */
	public void testBottomTeamThree() throws LeagueException{
		testLeague.startNewSeason();
		testLeague.playMatch("Manchester United", 3, "Gotham City", 1);
		testLeague.playMatch("Metropolis", 3, "Central City", 1);
		assertEquals(testTeamThree, testLeague.getBottomTeam());
	}
	
	@Test 
	/* Testing if league contains team, should return true */
	public void testContainsTeamTrue() throws LeagueException{
		assertTrue(testLeague.containsTeam("Manchester United"));
	}
	
	@Test 
	/* Testing is a league contains a team, should return false */
	public void testContainsTeamFalse() throws LeagueException{
		assertFalse(testLeague.containsTeam("Paradise Island"));
	}
	
	@Test
	/* Testing if league is sorted, no matches played for should sort 
	 * alphabetically, tested by returning top team and bottom team */
	public void testSortTeams() throws LeagueException{
		testLeague.sortTeams();
		assertEquals(testTeamTwo, testLeague.getTopTeam());
		assertEquals(testTeamFour, testLeague.getBottomTeam());
	}
	
	@Test
	/* Testing if league is sorted properly, after matches have been played, 
	 * should be sorted by comp points, then goal diff, then alphabettically */
	public void testSortTeamsAfterMatchesPlayed() throws LeagueException{
		testLeague.startNewSeason();
		testLeague.playMatch("Manchester United", 3, "Gotham City", 0);
		testLeague.playMatch("Metropolis", 3, "Central City", 1);
		testLeague.sortTeams();
		assertEquals(testTeamOne, testLeague.getTopTeam());
		assertEquals(testTeamThree, testLeague.getBottomTeam());
	}
}

