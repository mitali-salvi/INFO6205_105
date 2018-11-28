/**
 * 
 */
package main.java.fixtures;

import java.util.Random;

import main.java.helper.Match;

/**
 * @author mitalisalvi
 *
 */
public class Chromosome 
{
	private Match[] matches =new Match[((FootballData.getTeams().size()) * (FootballData.getTeams().size()-1))];
	
	public Chromosome ()
	{
		generateChromosome();
	}
	
	private void generateChromosome() 
	{
		
		Random random = new Random();
	    for (int i = 0; i < size(); i++) 
        {
             matches[i] = new Match(FootballData.getTeams().get(random.nextInt(FootballData.getTeams().size()+1)),
                		FootballData.getTeams().get(random.nextInt(FootballData.getTeams().size()+1)),
                		FootballData.getDates().get(random.nextInt(FootballData.getDates().size()+1)), 
                		FootballData.getLocations().get(random.nextInt(FootballData.getLocations().size()+1))) ;
        }
	 }
	
	 public int size() 
	 {
		    return matches.length;
	 }

}
