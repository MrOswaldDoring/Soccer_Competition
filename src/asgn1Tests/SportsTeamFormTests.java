package asgn1Tests;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import asgn1SoccerCompetition.SportsTeamForm;
import asgn1SportsUtils.WLD;

/**
 * A set of JUnit tests for the asgn1SoccerCompetition.SoccerTeamForm class
 *
 * @author Oswald Doring
 *
 */
public class SportsTeamFormTests {
	
	SportsTeamForm sportsTeamFormTest;
	
	@Before 
	/* Creating a new instance of SportsTeamForm before each test */
	public void setUp(){
		sportsTeamFormTest = new SportsTeamForm();
	}
	
	/* Testing the functionality of asgn1SoccerCompetition.SportsTeamForm */
	
	@Test 
	/* Testing toString with no result stored in the list */
	public void testZeroGame(){
		assertEquals("-----", sportsTeamFormTest.toString());
	}
	
	@Test 
	/* Testing toString after one results has been added to the list */ 
	public void testAddOneGame(){	
		sportsTeamFormTest.addResultToForm(WLD.WIN);
		assertEquals("W----", sportsTeamFormTest.toString());
	}
	
	@Test 
	/* Testing toString after three results have been added to the list */
	public void testAddThreeGame(){
		sportsTeamFormTest.addResultToForm(WLD.LOSS);
		sportsTeamFormTest.addResultToForm(WLD.WIN);
		sportsTeamFormTest.addResultToForm(WLD.DRAW);
		assertEquals("DWL--", sportsTeamFormTest.toString());
	}
	
	@Test 
	/* Testing toStringafter five results have been added to the list */
	public void testAddFiveGame(){
		sportsTeamFormTest.addResultToForm(WLD.WIN);
		sportsTeamFormTest.addResultToForm(WLD.DRAW);
		sportsTeamFormTest.addResultToForm(WLD.WIN);
		sportsTeamFormTest.addResultToForm(WLD.LOSS);
		sportsTeamFormTest.addResultToForm(WLD.LOSS);
		assertEquals("LLWDW", sportsTeamFormTest.toString());
	}
	
	@Test 
	/* Testing after more than five results have been added to the list to ensure last result get dropped off */
	public void testAddSevenGame(){
		sportsTeamFormTest.addResultToForm(WLD.WIN);
		sportsTeamFormTest.addResultToForm(WLD.WIN);
		sportsTeamFormTest.addResultToForm(WLD.WIN);
		sportsTeamFormTest.addResultToForm(WLD.LOSS);
		sportsTeamFormTest.addResultToForm(WLD.LOSS);
		sportsTeamFormTest.addResultToForm(WLD.DRAW);
		sportsTeamFormTest.addResultToForm(WLD.DRAW);
		assertEquals("DDLLW", sportsTeamFormTest.toString());
	}
	
	@Test 
	/* Testing that getNumGames returns the correct number of results in the list (3) */
	public void testNumberGameReturn(){
		sportsTeamFormTest.addResultToForm(WLD.WIN);
		sportsTeamFormTest.addResultToForm(WLD.DRAW);
		sportsTeamFormTest.addResultToForm(WLD.LOSS);
		assertEquals(3, sportsTeamFormTest.getNumGames());	
	}
	
	@Test 
	/* Testing to ensure the result list resets when resetForm is called */
	public void testResetResultForm(){
		sportsTeamFormTest.addResultToForm(WLD.WIN);
		sportsTeamFormTest.addResultToForm(WLD.DRAW);
		sportsTeamFormTest.addResultToForm(WLD.WIN);
		sportsTeamFormTest.addResultToForm(WLD.LOSS);
		sportsTeamFormTest.addResultToForm(WLD.LOSS);
		sportsTeamFormTest.resetForm();
		assertEquals(0, sportsTeamFormTest.getNumGames());
		assertEquals("-----", sportsTeamFormTest.toString());
	}

}
