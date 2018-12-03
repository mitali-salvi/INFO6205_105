package main.java.fixtures;

import java.util.Date;

import main.java.helper.Match;

public class FixturesMain {
	
	//private static GeneticAlgorithm geneticAlgo;
	
	public static void main(String[] args) 
	{
		//Give input to the Fixture Generator
		Configuration.initializeData();
		System.out.println("Initialized data");
		//System.out.println(((FootballData.getTeams().size()) * (FootballData.getTeams().size()-1))/2 * Constants.NUMBER_OF_ROUNDS);

		Population nextGen ;		
		Population initialPopulation ;	
		
		int maxGeneration = Constants.MAX_GENERATION;
		System.out.println("Starting GA");
		
		do {
			System.out.println("current generation: "+maxGeneration);
			initialPopulation = new Population(Constants.POPULATION_SIZE);	
			nextGen = GeneticAlgorithm.runGeneticAlgorithm(initialPopulation);
			maxGeneration--;
			if(GeneticAlgorithm.flag)
			{
				System.out.println("Breaking while loop");
				break;
			}
			//initialPopulation = nextGen; 

		}while(maxGeneration >=0);
		
		
		System.out.println("Out of loop");
		//System.out.println(initialPopulation);
		//System.out.println("length::"+initialPopulation.getChromosomes().length);
		
		
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
