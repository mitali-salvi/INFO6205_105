package test.java.fixtures;

import main.java.fixtures.Chromosome;
import main.java.fixtures.Configuration;
import main.java.fixtures.Population;
import main.java.helper.Match;

public class FixturesTest 
{
	public static void main(String[] args) 
	{
		System.out.println("Started");
		Configuration.initializeData();
		Population pop = new Population(1000000);
		Chromosome[] temp = pop.getChromosomes();
		for (int i=0;i<temp.length;i++)
		{
			temp[i].calculateFitness();
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
		System.out.println("Done");
	}

}
