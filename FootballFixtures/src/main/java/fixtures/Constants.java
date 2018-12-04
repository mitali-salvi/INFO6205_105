/**
 * 
 */
package main.java.fixtures;

/**
 * @author mitalisalvi
 *
 */
public class Constants 
{
	public static final int POPULATION_SIZE = 1000;
	
	public static final int NUMBER_OF_ROUNDS = 2; 
	
	public static final int ELITE_FACTOR = (int)(0.09 * POPULATION_SIZE);
	
	public static final int K_FACTOR = 100;  //keep 5 for population 100
	
	public static final double MUTATION_FACTOR = 0.8;
	
	public static final double CROSSOVER_RATE = 0.8;
	
	public static final int MAX_GENERATION = 10000;

	public static final int MAX_COLONY_SIZE = 500;
}
