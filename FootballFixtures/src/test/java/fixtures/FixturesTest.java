package test.java.fixtures;

import main.java.fixtures.Chromosome;
import main.java.fixtures.Configuration;
import main.java.fixtures.Population;

public class FixturesTest 
{
	public static void main(String[] args) 
	{
//		System.out.println("Hello");
		Configuration.initializeData();
		Population pop = new Population(1);
		Chromosome[] temp = pop.getChromosomes();
		for (int i=0;i<temp.length;i++)
		{
			temp[i].calculateFitness();
			System.out.println(temp[i]);
			System.out.println("Fitness::::"+temp[i].getFitness());
		}
		
	}

}
