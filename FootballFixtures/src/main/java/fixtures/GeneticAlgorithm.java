package main.java.fixtures;


import java.lang.reflect.Array;
import java.util.Random;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;

import main.java.helper.Match;

public class GeneticAlgorithm 
{
	static Random random = new Random();
	
	/**
	 * Using HashMap to store Parent P1, Parent P2 with their appropriate position
	 */
	static HashMap<Integer, Chromosome> parents = new HashMap<Integer, Chromosome>();
	
	public static void runGeneticAlgorithm(Population initialPopulation){
		
		/**
		 * Sort the initial population on the basis of fitness factor
		 */
		Population population = sortPopulation(initialPopulation);
		
		/**
		 * find first parent by using k-way tournament selection  
		 */
		Chromosome parent_1 = k_wayParentSelection(population);
		
		/**
		 * find second parent by using k-way tournament selection  
		 */
		Chromosome parent_2 = k_wayParentSelection(population);
		
		Chromosome[] childrens = crossOver(parent_1, parent_2);
		
		Chromosome child_1 = mutate(childrens[0]);
		
		Chromosome child_2 = mutate(childrens[1]);
		
	}

	public static Chromosome[] crossOver(Chromosome p1, Chromosome p2) 
	{
		int minimumIndex = 0;
		int maximumIndex = p1.size();
		
		int crossOverPoint = (int) Math.random() * (maximumIndex - minimumIndex);
		
		Chromosome c1 = new Chromosome(); // child1 is created
		Chromosome c2 = new Chromosome(); // child2 is created
		
	    Match[] childMatchArray1 = (Match[]) Array.newInstance(p1.getMatches().getClass().getComponentType(), p1.size());
	    System.arraycopy(p1.getMatches(), 0, childMatchArray1, 0, crossOverPoint);
	    System.arraycopy(p2.getMatches(), crossOverPoint + 1, childMatchArray1, crossOverPoint + 1, p1.size() - crossOverPoint);
		
	    Match[] childMatchArray2 = (Match[]) Array.newInstance(c1.getMatches().getClass().getComponentType(), c1.size());
	    System.arraycopy(p2.getMatches(), 0, childMatchArray2, 0, crossOverPoint);
		System.arraycopy(p1.getMatches(), crossOverPoint + 1, childMatchArray2, crossOverPoint + 1, p1.size() - crossOverPoint);
		
		c1.setMatches(childMatchArray1);
		c2.setMatches(childMatchArray2);
		c1.calculateFitness();
		c2.calculateFitness(); 
		
		Chromosome[] children = {c1, c2};
		return children;
	}
	
	public static Chromosome mutate(Chromosome c)
	{
		int numberOfMatchesToChange = (int)Constants.MUTATION_FACTOR * c.size();
		Random random = new Random();
		Match[] matchesPlayed = c.getMatches();
		
		while (numberOfMatchesToChange !=0)
		{
			int positionToChange = random.nextInt(c.size());
			Match matchToChange = new Match(FootballData.getTeams().get(random.nextInt(FootballData.getTeams().size())),
            		FootballData.getTeams().get(random.nextInt(FootballData.getTeams().size())),
            		FootballData.getDates().get(random.nextInt(FootballData.getDates().size())), 
            		FootballData.getLocations().get(random.nextInt(FootballData.getLocations().size()))) ;
			 matchesPlayed[positionToChange] = matchToChange; 
			 numberOfMatchesToChange --;
		}
		c.setMatches(matchesPlayed);
		return c;
	}
	
	/**
	 * The following function select a parent from the population
	 * @param population sorted by it's fitness value
	 * @return chromosome with highest fitness
	 */
	public static Chromosome k_wayParentSelection(Population population){
		//assuming population is sorted
		
		List<Chromosome> list = new ArrayList<Chromosome>();
		Chromosome[] chromosomes = population.getChromosomes();
		
		int maxVal = population.getChromosomes().length - 1;
		
		/*** after sorting population in decreasing order first ELITE_FACTOR value chromosomes 
		will not pick for random selection ***/
		
		int minVal = Constants.ELITE_FACTOR + 1;
		int position = 0;
		
		for(int i = 0; i < Constants.K_FACTOR; i++){
			position = random.nextInt(maxVal - minVal + 1) + minVal;
			list.add(chromosomes[position]);
		}
		
		
		Collections.sort(list, new Comparator<Chromosome>() {
            @Override
            public int compare(Chromosome o1, Chromosome o2) {
                return o1.compareTo(o2);
            }
        });
		
		System.out.println("parent chromosome list: "+list);
		
		//store highest fitness chromosome in parents HashMap
		parents.put(position, list.get(0));
		return list.get(0);
	}
	
	private static Population sortPopulation(Population initialPopulation) {
		Arrays.sort(initialPopulation.getChromosomes(), new Comparator<Chromosome>() {
	        public int compare(Chromosome o1, Chromosome o2) {
	            return o1.compareTo(o2);
	        }
	    });	
		
		return initialPopulation;
	}
}
