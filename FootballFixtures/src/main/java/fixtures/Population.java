/**
 * 
 */
package main.java.fixtures;

/**
 * The class contains the population on which the genetic algorithm is applied.
 * In our case, it contains multiple fixtures (chromosome) with varying fitness
 * 
 * @author Aditi Jalkote, Mitali Salvi, Shubham Sharma
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

	
	public Population(Chromosome[] chromosomes , int populationSize)
	{
		this.chromosomes = new Chromosome[populationSize];
		System.arraycopy(chromosomes, 0, this.chromosomes, 0, chromosomes.length);
	}

	/**
	 * @return the chromosomes
	 */
	public Chromosome[] getChromosomes() {
		return chromosomes;
	}
	

	/**
	 * @param chromosomes the chromosomes to set
	 */
	public void setChromosomes(Chromosome[] chromosomes) {
		this.chromosomes = chromosomes;
	}
	
	
	


}
