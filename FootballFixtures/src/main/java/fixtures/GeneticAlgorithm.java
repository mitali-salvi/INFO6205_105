/**
 * 
 */
package main.java.fixtures;

import java.util.Random;

import main.java.helper.Match;

/**
 * @author shubham sharma
 *
 */
public class GeneticAlgorithm {
	Random random = new Random();
	
	private int populationSize;
	private double mutationRate;
	private double crossOverRate;
	private int tournamentSize;
	
	public GeneticAlgorithm(int poppulationSize , double mutationRate, double crossOverRate,int tournamentSize)
	{
		this.populationSize = populationSize;
		this.mutationRate = mutationRate;
		this.crossOverRate = crossOverRate;
		this.tournamentSize = tournamentSize;
		
	}
	
	
	/**
	 * @param args
	 * Single point crossOver is applied in this method 
	 * genes of two parents are swapped before and after the single point 
	 * single point is found at random between 1 to max-1.
	 * 
	 */
	
	
		public Chromosome CrossOver(Chromosome p1 , Chromosome p2)
		{   	Population population = new Population(this.populationSize)	;				//create new population
			int min = 0;
			int max = p1.size();
			int crossOverPoint = (int)Math.random() * (max-min);
			Chromosome c1 = new Chromosome();    //child1 is created 
			Chromosome c2 = new Chromosome();	//Child2 is created
			Match[] C1match = new Match[p1.size()];
			Match[] C2match = new Match[p1.size()];
			
			System.arraycopy(p1.getClass(), 0, C1match, 0,crossOverPoint );
			System.arraycopy(p2.getClass(), crossOverPoint+1, C1match,crossOverPoint+1 , p2.size()-crossOverPoint);
			System.arraycopy(p1.getClass(), 0, C2match, 0,crossOverPoint );
			System.arraycopy(p2.getClass(), crossOverPoint+1, C2match,crossOverPoint+1 , p2.size()-crossOverPoint);
			c1.SetMatch(C1match);
			c2.SetMatch(C2match);
			
			double rand = Math.random() ;
			if(this.crossOverRate >rand)
			{   			population.UpdatePopulation(c1, c2);				//update new population
				
			}
			
			
			
			
			return null;
			
		}
		
		
		
		
		
		public void mutation(Chromosome c1)
		{		Match tempGene;
				Match[] m = new Match[c1.size()];
		for(int i = 0 ; i <c1.size() ; i++)
		{
		
			 if(Math.random() <=  mutationRate)
			 {     tempGene  = new Match(FootballData.getTeams().get(random.nextInt(FootballData.getTeams().size())),
             		FootballData.getTeams().get(random.nextInt(FootballData.getTeams().size())),
             		FootballData.getDates().get(random.nextInt(FootballData.getDates().size())), 
             		FootballData.getLocations().get(random.nextInt(FootballData.getLocations().size()))) ;
				 //creating a random gene and adding in it in chromo
			 
			 		m =  c1.getMatch();
			 		m[i] = tempGene;
			 		c1.SetMatch(m);
			 
			 }
		}
		
		
		}
		
		
		
	
	public Chromosome selectParent(Population population)
	{
		return null;
		
	}

}
