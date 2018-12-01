/**
 * 
 */
package main.java.fixtures;

/**
 * @author mitalisalvi
 *
 */
public class Population 
{
	private static Chromosome[] chromosomes;

	public Population(int populationSize)
	{
		chromosomes = new Chromosome[populationSize];

		for (int i = 0; i < populationSize; i++) 
		{
			Chromosome newIndividual = new Chromosome();
			chromosomes[i]= newIndividual;
		}
	}


	
	public static  int GetPopulationSize()
	{
		return chromosomes.length;
	}
	
	
	
	public void  UpdatePopulation(Chromosome c1 , Chromosome c2)
	{
			//population.sort(); sorting the population 
		int size =Population.GetPopulationSize();
		Population.chromosomes[size-1] = c1;
		Population.chromosomes[size-2] = c2;
		
		
		
		
	}
}
