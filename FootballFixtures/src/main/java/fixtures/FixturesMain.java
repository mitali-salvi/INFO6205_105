package main.java.fixtures;

import main.java.helper.Match;
/**
 * The class contains the main class from which the fixtures are generated if all the constraints are met
 * @author Aditi Jalkote, Mitali Salvi, Shubham Sharma
 */

public class FixturesMain {
		
	public static void main(String[] args) 
	{
		//Give input to the Fixture Generator
		Configuration.initializeData();

		Population nextGen ;		
		Population initialPopulation= new Population(Constants.POPULATION_SIZE) ;	
		
		int maxGeneration = Constants.MAX_GENERATION;
		
		do {
			nextGen = GeneticAlgorithm.runGeneticAlgorithm(initialPopulation);
			maxGeneration--;
			if(GeneticAlgorithm.getFlag()==true)
			{
				break;
			}
			initialPopulation = nextGen; 

		}while(maxGeneration >=0);
		
		
		Chromosome[] temp = initialPopulation.getChromosomes();
        for (int i=0;i<temp.length;i++)
        {
        	if (temp[i].getFitness() ==1.0)
        	{
                Match[] h =temp[i].getMatches();
                for (int j=0;j<h.length; j++)
                {
                    System.out.println(h[j]);
                }
                break;
        	}
        }
        System.out.println("Done implementing GA");

	}


}
