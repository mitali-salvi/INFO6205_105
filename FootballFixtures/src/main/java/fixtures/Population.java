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
	private Chromosome[] chromosomes;
	
	public Population(int populationSize)
	{
		chromosomes = new Chromosome[populationSize];

	    for (int i = 0; i < populationSize; i++) 
	    {
	        Chromosome newIndividual = new Chromosome();
	        chromosomes[i]= newIndividual;
	      }
	}
	


}
