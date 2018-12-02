package main.java.fixtures;

import java.lang.reflect.Array;
import java.util.Random;

import main.java.helper.Match;

public class GeneticAlgorithm 
{
	public static Chromosome[] crossOver(Chromosome p1, Chromosome p2) 
	{
		int minimumIndex = 0;
		int maximumIndex = p1.size();
		
		int crossOverPoint = (int) Math.random() * (maximumIndex - minimumIndex);
		
		Chromosome c1 = new Chromosome(); // child1 is created
		Chromosome c2 = new Chromosome(); // child2 is created
		
	    Match[] childMatchArray1 = (Match[]) Array.newInstance(p1.getMatches().getClass().getComponentType(), p1.size());
	    System.arraycopy(p1.getMatches(), 0, childMatchArray1, 0, crossOverPoint);
	    System.arraycopy(p2.getMatches(), crossOverPoint + 1, childMatchArray1, crossOverPoint + 1, p1.size() - crossOverPoint);
		
	    Match[] childMatchArray2 = (Match[]) Array.newInstance(c1.getMatches().getClass().getComponentType(), c1.size());
	    System.arraycopy(p2.getMatches(), 0, childMatchArray2, 0, crossOverPoint);
		System.arraycopy(p1.getMatches(), crossOverPoint + 1, childMatchArray2, crossOverPoint + 1, p1.size() - crossOverPoint);
		
		c1.setMatches(childMatchArray1);
		c2.setMatches(childMatchArray2);
		c1.calculateFitness();
		c2.calculateFitness(); 
		
		Chromosome[] children = {c1, c2};
		return children;
	}
	
	public static Chromosome mutate (Chromosome c)
	{
		int numberOfMatchesToChange = (int)Constants.MUTATION_FACTOR * c.size();
		Random random = new Random();
		Match[] matchesPlayed = c.getMatches();
		
		while (numberOfMatchesToChange !=0)
		{
			int positionToChange = random.nextInt(c.size());
			Match matchToChange = new Match(FootballData.getTeams().get(random.nextInt(FootballData.getTeams().size())),
            		FootballData.getTeams().get(random.nextInt(FootballData.getTeams().size())),
            		FootballData.getDates().get(random.nextInt(FootballData.getDates().size())), 
            		FootballData.getLocations().get(random.nextInt(FootballData.getLocations().size()))) ;
			 matchesPlayed[positionToChange] = matchToChange; 
			 numberOfMatchesToChange --;
		}
		c.setMatches(matchesPlayed);
		return c;
	}

}
