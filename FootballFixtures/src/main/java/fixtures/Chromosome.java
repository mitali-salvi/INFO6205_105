/**
 * 
 */
package main.java.fixtures;

import java.util.ArrayList;
import java.util.Random;

import main.java.helper.Match;

/**
 * @author mitalisalvi
 *
 */
public class Chromosome 
{
	private Match[] matches;
	
	public Chromosome ()
	{
		matches = new Match[((FootballData.getTeams().size()) * (FootballData.getTeams().size()-1))/2];
		generateChromosome();
	}
	
	private void generateChromosome() 
	{
		ArrayList<String> teams = (ArrayList<String>) FootballData.getTeams();
		Random random = new Random();
		int counter = 0;
	    for (int i = 0; i < size(); i++) 
        {
            for (int j = i + 1; j < size(); j++)
            {
                matches[counter] = new Match(teams.get(i), teams.get(j), FootballData.getDates().get(random.nextInt(FootballData.getDates().size())),	
                		FootballData.getLocations().get(random.nextInt(FootballData.getLocations().size())))  ;
                counter ++;
            }
        }
	 }
	
	 public int size() 
	 {
		    return matches.length;
	 }

}
