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

	public Population(Chromosome[] c1 , Chromosome[] c2)
	{
		int totalSize = c1.length + c2.length;
		this.chromosomes = new Chromosome[totalSize];
		
		System.arraycopy(c1, 0, this.chromosomes, 0, c1.length);
		System.arraycopy(c2, 0, this.chromosomes, c1.length, c2.length);
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
