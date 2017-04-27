package asgn1Tests;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import asgn1Exceptions.TeamException;
import asgn1SoccerCompetition.SoccerTeam;
import asgn1SoccerCompetition.SportsTeamForm;
import asgn1SportsUtils.WLD;

/**
 * A set of JUnit tests for the asgn1SoccerCompetition.SoccerLeage class
 *
 * @author Oswald Doring
 *
 */
public class SoccerTeamTests {

	SoccerTeam testSoccerTeam;
	SoccerTeam otherSoccerTeam;

	@Before 
	/* Sets up SoccerTeam and plays a few matches for testing purposes*/
	public void setup() throws TeamException{
		testSoccerTeam = new SoccerTeam("Brisbane Roar", "The Roar");
		otherSoccerTeam = new SoccerTeam("Gotham City", "Batmen");
		testSoccerTeam.playMatch(3, 5);
		testSoccerTeam.playMatch(7, 3);
		testSoccerTeam.playMatch(0, 4);
		testSoccerTeam.playMatch(2, 2);
	}

	/* Testing exceptions in asgn1SoccerCompetition.SoccerTeam */

	@Test(expected = TeamException.class) 
	/* Testing if exception is thrown when Official Name is empty */
	public void emptyOfficalTeamString() throws TeamException{
		SoccerTeam exceptionTestConstructer = new SoccerTeam("", "The Roar");
	}

	@Test(expected = TeamException.class) 
	/* Testing if exception is thrown when Nickname is empty */
	public void emptyNickNameString() throws TeamException{
		SoccerTeam exceptionTestConstructer = new SoccerTeam("Brisbane Roar", "");
	}

	@Test(expected = TeamException.class) 
	/* Testing if exception is thrown when goals scored is unrealistically high */
	public void unrealisticGoalsScored() throws TeamException{
		testSoccerTeam.playMatch(22, 3);
	}

	@Test(expected = TeamException.class) 
	/* Testing if exception is thrown when goals conceded is unrealistically high */
	public void unrealisticGoalsConceded() throws TeamException{
		testSoccerTeam.playMatch(2, 21);
	}

	@Test(expected = TeamException.class) 
	/* Testing if exception is thrown when goals scored is a negative number */
	public void negativeGoalsScored() throws TeamException{
		testSoccerTeam.playMatch(-22, 3);
	}

	@Test(expected = TeamException.class) 
	/* Testing if exception is thrown when goals conceded is a negative number */
	public void negativeGoalsConceded() throws TeamException{
		testSoccerTeam.playMatch(2, -21);
	}

	/* Testing the functionality of asgn1SoccerCompetition.SoccerTeam */

	@Test 
	/* Testing that getOfficialName returns the expected Official Name ("Brisbane Roar") */
	public void testGetOfficialName(){
		assertEquals("Brisbane Roar", testSoccerTeam.getOfficialName());
	}

	@Test 
	/* Testing that getNickName returns the expected Nickname ("The Roar") */
	public void testGetNickName(){
		assertEquals("The Roar", testSoccerTeam.getNickName());
	}

	@Test 
	/* Testing that getGoalsScoredSeason returns the expected value (12) */
	public void testGoalsScoredSeason() throws TeamException{
		assertEquals(12, testSoccerTeam.getGoalsScoredSeason());
	}


	@Test 
	/* Testing that getGoalsConcededSeason returns the expected value (14) */
	public void testGoalsConcededSeason() throws TeamException{
		assertEquals(14, testSoccerTeam.getGoalsConcededSeason());
	}


	@Test 
	/* Testing that getMatchesWon returns the expected value (1) */
	public void testGetMatchesWon() throws TeamException{
		assertEquals(1, testSoccerTeam.getMatchesWon());
	}

	@Test 
	/* Testing that getMatchesLost returns the expected value (2) */
	public void testGetMatchesLost() throws TeamException{
		assertEquals(2, testSoccerTeam.getMatchesLost());
	}

	@Test 
	/* Testing that getMatchesDrawn returns the expected value (1) */
	public void testGetMatchesDrawn() throws TeamException{
		assertEquals(1, testSoccerTeam.getMatchesDrawn());
	}

	@Test 
	/* Testing that getCompetitionPoints returns the expected value (4) */
	public void testGetCompetitionPoints() throws TeamException{
		assertEquals(4, testSoccerTeam.getCompetitionPoints());
	}

	@Test 
	/* Testing that getGoalDifference returns the expected value (-2) */
	public void testGetGoalDifference() throws TeamException{
		assertEquals(-2, testSoccerTeam.getGoalDifference());
	}

	@Test 
	/* Testing that getFormString returns the expected string ("DLWL-") */
	public void testGetFormString() throws TeamException{
		assertEquals("DLWL-", testSoccerTeam.getFormString());
	}
	
	@Test
	/* Testing compareTo where one team has greater competition points expected -ve int (-4) */
	public void testCompareToOne() throws TeamException{
		assertEquals(-4, testSoccerTeam.compareTo(otherSoccerTeam));
	}
	
	@Test
	/* Testing compareTo where both teams have same comp points but one has great goal diff, expected +ve int (14) */
	public void testCompareToTwo() throws TeamException{
		otherSoccerTeam.playMatch(13, 1);
		otherSoccerTeam.playMatch(2, 2);
		assertEquals(14, testSoccerTeam.compareTo(otherSoccerTeam));
	}
	
	@Test
	/* Testing compareTo where both teams have same comp and same goal diff but sorts alphabetically, */
	public void testCompareToThree() throws TeamException{
		otherSoccerTeam.playMatch(2, 0);
		otherSoccerTeam.playMatch(2, 2);
		otherSoccerTeam.playMatch(0, 4);
		assertEquals(-5, testSoccerTeam.compareTo(otherSoccerTeam));
	}

	@Test 
	/*Runs a instance of resetStats then checks that all values have reset to original value */
	public void testResetStats() throws TeamException{
		testSoccerTeam.resetStats();
		assertEquals(0, testSoccerTeam.getCompetitionPoints());
		assertEquals("-----", testSoccerTeam.getFormString());
		assertEquals(0, testSoccerTeam.getGoalsConcededSeason());
		assertEquals(0, testSoccerTeam.getGoalsScoredSeason());
		assertEquals(0, testSoccerTeam.getMatchesDrawn());
		assertEquals(0, testSoccerTeam.getMatchesLost());
		assertEquals(0, testSoccerTeam.getMatchesWon());
	}

	@Test 
	/*Testing to ensure data can be added back in after reset stats has been run*/
	public void testRunSoccerTeamAfterResetStats() throws TeamException{
		testSoccerTeam.resetStats();
		testSoccerTeam.playMatch(3, 1);
		testSoccerTeam.playMatch(1, 1);
		testSoccerTeam.playMatch(0, 17);
		assertEquals(4, testSoccerTeam.getCompetitionPoints());
		assertEquals("LDW--", testSoccerTeam.getFormString());
		assertEquals(19, testSoccerTeam.getGoalsConcededSeason());
		assertEquals(4, testSoccerTeam.getGoalsScoredSeason());
		assertEquals(1, testSoccerTeam.getMatchesDrawn());
		assertEquals(1, testSoccerTeam.getMatchesLost());
		assertEquals(1, testSoccerTeam.getMatchesWon());
		assertEquals(-15, testSoccerTeam.getGoalDifference());	
	}

}
