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
	        //System.out.println("***************");
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
