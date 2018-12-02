package main.java.fixtures;

import java.util.Arrays;
import java.util.Comparator;

import main.java.helper.Match;

public class FixturesMain {
	
	//private static GeneticAlgorithm geneticAlgo;
	
	public static void main(String[] args) 
	{
		//Give input to the Fixture Generator
		Configuration.initializeData();
		
		//Initialize initial population and calculate and set fitness of each chromosome
		Population initialPopulation = new Population(Constants.POPULATION_SIZE);	
		
		int maxGeneration = Constants.MAX_GENERATION;
		
		do {
			System.out.println("Max generation: "+maxGeneration);
			initialPopulation = GeneticAlgorithm.runGeneticAlgorithm(initialPopulation);
			maxGeneration--;
			if(GeneticAlgorithm.flag)
				break;
		}
		while(maxGeneration >=0);
		System.out.println("Out of loop");
		
		
		Chromosome[] temp = initialPopulation.getChromosomes();
        for (int i=0;i<temp.length;i++)
        {

                Match[] h =temp[i].getMatches();
                for (int j=0;j<h.length; j++)
                {
                    System.out.println(h[j]);
                }

        }
        System.out.println("Done(((((((((((((((((((9");
//		Chromosome temp[] = initialPopulation.getChromosomes();
//		for (int i=0;i<temp.length;i++)
//		{
//			System.out.println(temp[i] +"fitness::::"+temp[i].getFitness());
//		}
		
		//call k 

	}


}
