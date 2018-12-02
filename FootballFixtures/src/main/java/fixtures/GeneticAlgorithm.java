package main.java.fixtures;


import java.lang.reflect.Array;
import java.util.Random;
import java.util.Map.Entry;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import main.java.helper.Match;
import main.java.helper.Team;

public class GeneticAlgorithm 
{
	static Random random = new Random();
	static boolean flag = false; 
	

	/**
	 * Using HashMap to store Parent P1, Parent P2 with their appropriate position
	 */
	static HashMap<Integer, Chromosome> parents = new HashMap<Integer, Chromosome>();
	
	public static Population runGeneticAlgorithm(Population initialPopulation)
	{

		
		/**
		 * Sort the initial population on the basis of fitness factor
		 */
		Population population = sortPopulation(initialPopulation);
		
		Chromosome[] chromosomes = population.getChromosomes();
		
		for(int i = 0; i < chromosomes.length; i++)
		{
			if(chromosomes[i].getFitness() == 1.0)
			{
				System.out.println("Setting flag as true");
				flag = true;
				return initialPopulation;
			}
			
		}
		
		
		/**
		 * find first parent by using k-way tournament selection  
		 */
		Chromosome parent_1 = k_wayParentSelection(population);
		
		/**
		 * find second parent by using k-way tournament selection  
		 */
		Chromosome parent_2 = null;
		
		
		
		while(parents.size() < 2){
			parent_2 = k_wayParentSelection(population);
		}
		
		Chromosome[] childrens = crossOver(parent_1, parent_2);
		
		Chromosome child_1 = mutate(childrens[0]);
		
		Chromosome child_2 = mutate(childrens[1]);
		
		Chromosome fitestChromosome_1 = pushChromosomeBackInPop(parent_1, child_1);
		Chromosome fitestChromosome_2 = pushChromosomeBackInPop(parent_2, child_2);
		
		for (Entry<Integer, Chromosome> entry : parents.entrySet()){
			if(parent_1.equals(entry.getValue())){
				chromosomes[entry.getKey()] = fitestChromosome_1;
			}else{
				chromosomes[entry.getKey()] = fitestChromosome_2;
			}
		}
		
		parents.clear();
				
		population.setChromosomes(chromosomes);
		//System.out.println("Returning population");
		return population;
	}
	
	public static Chromosome[] crossOver(Chromosome p1, Chromosome p2) 
	{	
		Match[] parentOneMatches = p1.getMatches();
		ArrayList<Match> parentOneMatchesAL = new ArrayList<Match>();
		for (int i=0;i>parentOneMatches.length;i++)
		{
			parentOneMatchesAL.add(parentOneMatches[i]);
		}
		
		
		Match[] parentTwoMatches = p2.getMatches();
		ArrayList<Match> parentTwoMatchesAL = new ArrayList<Match>();
		for (int i=0;i>parentTwoMatches.length;i++)
		{
			parentTwoMatchesAL.add(parentTwoMatches[i]);
		}
		
		int minimumIndex = 0;
		int maximumIndex = p1.size();
		int crossOverPoint = (int) Math.random() * (maximumIndex - minimumIndex);
		
		
		
		//child 1
		ArrayList<Match> childOneMatchesAL = new ArrayList<Match>();
		for (int i=0;i<crossOverPoint;i++)
		{
			childOneMatchesAL.add(parentOneMatchesAL.get(i));
		}
		for (int i=crossOverPoint+1;i<parentTwoMatchesAL.size();i++)
		{
			childOneMatchesAL.add(parentTwoMatchesAL.get(i));
		}
		Match[] childOne = new Match[childOneMatchesAL.size()];
		for (int i=0;i<childOneMatchesAL.size();i++)
		{
			childOne[i] = childOneMatchesAL.get(i);
		}
		Chromosome child1 = new Chromosome(childOne);
		
		
		//child 2
		ArrayList<Match> childTwoMatchesAL = new ArrayList<Match>();
		for (int i=0;i<crossOverPoint;i++)
		{
			childTwoMatchesAL.add(parentTwoMatchesAL.get(i));
		}
		for (int i=crossOverPoint+1;i<parentOneMatchesAL.size();i++)
		{
			childTwoMatchesAL.add(parentOneMatchesAL.get(i));
		}
		Match[] childTwo = new Match[childTwoMatchesAL.size()];
		for (int i=0;i<childTwoMatchesAL.size();i++)
		{
			childTwo[i] = childTwoMatchesAL.get(i);
		}
		Chromosome child2 = new Chromosome(childTwo);
		
		Chromosome[] setOfChildren = new Chromosome[]{child1, child2};
		return setOfChildren;
		
	}
	
	public static Chromosome mutate (Chromosome c)
	{
		int numberOfMatchesToChange = (int)Constants.MUTATION_FACTOR * c.size();
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
		
		//System.out.println("parent chromosome list: "+list);
		
		parents.put(position, list.get(0));
		//return highest fitness chromosome 
		return list.get(0);
	}
	
	//added new method for deciding between child and parent chromo
	public static Chromosome pushChromosomeBackInPop (Chromosome parentChromo, Chromosome childChromo)
	{
		if (Math.random() <= Constants.CROSSOVER_RATE)
			return childChromo;
		else 
			return parentChromo;	
	}

	private static Population sortPopulation(Population initialPopulation) 
	{
		Arrays.sort(initialPopulation.getChromosomes(), new Comparator<Chromosome>() 
		{
	        public int compare(Chromosome o1, Chromosome o2) {
	            return o1.compareTo(o2);
	        }
	    });	
		
		return initialPopulation;
	}
}
