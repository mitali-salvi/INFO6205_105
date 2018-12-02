package main.java.fixtures;

import java.util.Arrays;
import java.util.Comparator;

public class FixturesMain {
	
	private static GeneticAlgorithm geneticAlgo;
	
	public static void main(String[] args) 
	{
		//Give input to the Fixture Generator
		Configuration.initializeData();
		
		//Initialize initial population and calculate and set fitness of each chromosome
		Population initialPopulation = new Population(3);	
		geneticAlgo.runGeneticAlgorithm(initialPopulation);
		
		
//		Chromosome temp[] = initialPopulation.getChromosomes();
//		for (int i=0;i<temp.length;i++)
//		{
//			System.out.println(temp[i] +"fitness::::"+temp[i].getFitness());
//		}
		
		//call k 

	}


}
