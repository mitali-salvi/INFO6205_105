package test.java.fixtures;

import static org.junit.Assert.assertEquals;

import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;

import org.junit.Before;
import org.junit.Test;

import main.java.fixtures.Chromosome;
import main.java.fixtures.FootballData;
import main.java.fixtures.Population;
import main.java.helper.Match;
import main.java.helper.Team;

/**
 * The class contains test cases for Genetic Algorithm functions
 * 
 * @author Aditi Jalkote, Mitali Salvi, Shubham Sharma
 */

public class FixturesTest 
{
	private FootballData footballData;
	
	/*
	 * Initialize population by setting data
	 */
	@Before
	public void setUp() throws Exception{
		System.out.println("step 1");
		
		String location_1 = "Etihad stadium";
		String location_2 = "Anfield";
		String location_3 = "Wembley stadium";
		
		Team team_1 = new Team("Manchester City","Etihad stadium"); 
		Team team_2 = new Team("Liverpool","Anfield");
		Team team_3 = new Team("Tottenham Hotspur","Wembley stadium");
		
		footballData = new FootballData();
		
		footballData.getLocations().clear();
		footballData.locations.add(location_1);
		footballData.locations.add(location_2);
		footballData.locations.add(location_3);
		
		footballData.getTeams().clear();
		footballData.teams.add(team_1);
		footballData.teams.add(team_2);
		footballData.teams.add(team_3);
		
		Date date_1 = new Date();
		Date date_2 = new Date();
		Date date_3 = new Date();
		Date date_4 = new Date();
		Date date_5 = new Date();
		Date date_6 = new Date();
		
		SimpleDateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy");
		try{
			date_1 = dateFormat.parse("12/04/2018");
			date_2 = dateFormat.parse("12/05/2018");
			date_3 = dateFormat.parse("12/06/2018");
			date_4 = dateFormat.parse("12/07/2018");
			date_5 = dateFormat.parse("12/08/2018");
			date_6 = dateFormat.parse("12/09/2018");
			
		} catch (Exception e){
			System.out.println("Excepting for date parsing:" +e.getMessage());
		}
		
		footballData.getDates().addAll(Arrays.asList(date_1, date_2, date_3,
				date_4, date_5, date_6));
	}
	
	
	/*
	 * Test to find fittest chromosome by using calculateFitness method
	 */
	@Test
	public void findFitnessTest_1(){
		
		Team team_1 = footballData.getTeams().get(0);
		Team team_2 = footballData.getTeams().get(1);
		Team team_3 = footballData.getTeams().get(2);
		
		String location_1 = footballData.getLocations().get(0);
		String location_2 = footballData.getLocations().get(1);
		String location_3 = footballData.getLocations().get(2);
		
		Date date_1 = footballData.getDates().get(0);
		Date date_2 = footballData.getDates().get(1);
		Date date_3 = footballData.getDates().get(2);
		Date date_4 = footballData.getDates().get(3);
		Date date_5 = footballData.getDates().get(4);
		Date date_6 = footballData.getDates().get(5);
		
		Match match_1 = new Match(team_1, team_2, date_1, location_1);
		Match match_2 = new Match(team_1, team_3, date_2, location_1);
		Match match_3 = new Match(team_2, team_1, date_3, location_2);
		Match match_4 = new Match(team_2, team_3, date_4, location_2);
		Match match_5 = new Match(team_3, team_1, date_5, location_3);
		Match match_6 = new Match(team_3, team_2, date_6, location_3);
		
		//Chromosome ch = 
		//ch.
		Population population = new Population(1);
		//array chromosomes
		Chromosome[] fixtures = population.getChromosomes();
		Chromosome fixture = fixtures[0];
		
		Match[] matches = {match_1, match_2, match_3, match_4, match_5, match_6};
		fixture.setMatches(matches);
		
		int expectedConflicts = 0;
		double expectedFitness = (double) 1 / (1 + expectedConflicts);
		fixture.calculateFitness();
		assertEquals(expectedFitness, fixture.getFitness(), 0.001);
	}
	

}
