package main.java.fixtures;

import java.util.Arrays;
import java.util.Comparator;

public class FixturesMain 
{
	public static void main(String[] args) 
	{
		//Give input to the Fixture Generator
		Configuration.initializeData();
		
		//Initialize initial population and calculate and set fitness of each chromosome
		Population initialPopulation = new Population(3);	
		
		//Sort the initial population on the basis of fitness factor
		Arrays.sort(initialPopulation.getChromosomes(), new Comparator<Chromosome>() {
	        public int compare(Chromosome o1, Chromosome o2) {
	            return o1.compareTo(o2);
	        }
	    });
		
//		Chromosome temp[] = initialPopulation.getChromosomes();
//		for (int i=0;i<temp.length;i++)
//		{
//			System.out.println(temp[i] +"fitness::::"+temp[i].getFitness());
//		}
		
		//call k 

	}


}
