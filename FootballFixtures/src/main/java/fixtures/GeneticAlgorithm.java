package main.java.fixtures;


import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map.Entry;
import java.util.Random;

import main.java.helper.Match;

public class GeneticAlgorithm 
{
	static Random random = new Random();
	static boolean flag = false; 
	

	/**
	 * Using HashMap to store Parent P1, Parent P2 with their appropriate position
	 */
	//private static HashMap<Integer, Chromosome> parents = new HashMap<Integer, Chromosome>();
	
	public static Population runGeneticAlgorithm(Population initialPopulation)
	{
		/**
		 * Sort the initial population on the basis of fitness factor
		 */
		sortPopulation(initialPopulation);
		
		Chromosome[] chromosomes = initialPopulation.getChromosomes();
		
		for(int i = 0; i < chromosomes.length; i++)
		{
			//System.out.println(i+"  matches length  "+chromosomes[i].size());
			double fitness = chromosomes[i].getFitness();
			//fitness = BigDecimal.valueOf(fitness).setScale(3, RoundingMode.HALF_UP).doubleValue();
			//System.out.println(i+"  fitness  "+fitness);
//		    int perfectSolution = Double.compare(fitness, 1.000);
//		    int lessThanPerfectSolution = Double.compare(fitness, 0.999);
//			if(perfectSolution ==0 || lessThanPerfectSolution ==0)
			if (fitness > 0.900 && fitness <= 1.000)
			{
				System.out.println("Setting flag as true");
				flag = true;
				return initialPopulation;
			}
		}
		/**
		 * find first parent by using k-way tournament selection  
		 */
		Chromosome parent_1 = k_wayParentSelection(chromosomes);
//		System.out.println("parent1:"+parent_1);
		
		
		/**
		 * find second parent by using k-way tournament selection  
		 */
		Chromosome parent_2 = k_wayParentSelection(chromosomes);
//		System.out.println("BEFORE parent2:"+parent_2);
		
		while (true)
		{
			if (parent_1 != parent_2)
				break;
			parent_2 = k_wayParentSelection(chromosomes);
		}
//		System.out.println("Out of loop");
//		System.out.println("parent2:"+parent_2);
		
		Chromosome[] childrens = crossOver(parent_1, parent_2);
		
		Chromosome child_1 = mutate(childrens[0]);
		Chromosome child_2 = mutate(childrens[1]);
		
		Chromosome fitestChromosome_1 = pushChromosomeBackInPop(parent_1, child_1);
		Chromosome fitestChromosome_2 = pushChromosomeBackInPop(parent_2, child_2);
		
		//putting back fittest chromo in random positions apart from elite
		int minVal = Constants.ELITE_FACTOR + 1;

		int firstPosition =  random.nextInt(chromosomes.length-minVal) + minVal;
		chromosomes[firstPosition] =fitestChromosome_1;
		
		int secondPosition =  random.nextInt(chromosomes.length-minVal) + minVal;
		chromosomes[secondPosition] =fitestChromosome_2;
		
		//System.out.println("chromosome after mutation::"+chromosomes.length);
				
		Population newPopulation = new Population(chromosomes,chromosomes.length);
		//System.out.println("new population created length::"+newPopulation.getChromosomes().length);
		return newPopulation;
	}
	
	public static Chromosome[] crossOver(Chromosome p1, Chromosome p2) 
	{	
		//System.out.println("Inside crossover");
		//System.out.println("p1:"+p1.getMatches().length);
		//System.out.println("p2:"+p2.getMatches().length);
		Match[] parentOneMatches = p1.getMatches();
		ArrayList<Match> parentOneMatchesAL = new ArrayList<Match>();
		for (int i=0;i<parentOneMatches.length;i++)
		{
			parentOneMatchesAL.add(parentOneMatches[i]);
		}
		
		
		Match[] parentTwoMatches = p2.getMatches();
		ArrayList<Match> parentTwoMatchesAL = new ArrayList<Match>();
		for (int i=0;i<parentTwoMatches.length;i++)
		{
			parentTwoMatchesAL.add(parentTwoMatches[i]);
		}
		
		int minimumIndex = 0;
		int maximumIndex = p1.size() -1;
		//System.out.println("maximumIndex:"+maximumIndex);
//		System.out.println("minimumIndex:"+minimumIndex);
//		int crossOverPoint = (int) Math.random() * (maximumIndex - minimumIndex);
		int rand =random.nextInt(maximumIndex);
		//System.out.println("rand:"+rand);
		int crossOverPoint =  rand + minimumIndex;
		//System.out.println("crossOverPoint:"+crossOverPoint);
		
		
		
		//child 1
		ArrayList<Match> childOneMatchesAL = new ArrayList<Match>();
		for (int i=0;i<crossOverPoint;i++)
		{
			//System.out.println("i:"+i);
			childOneMatchesAL.add(parentOneMatchesAL.get(i));
		}
		for (int i=crossOverPoint;i<parentTwoMatchesAL.size();i++)
		{
			//System.out.println("*i:"+i);
			childOneMatchesAL.add(parentTwoMatchesAL.get(i));
		}
		Match[] childOne = new Match[childOneMatchesAL.size()];
		for (int i=0;i<childOneMatchesAL.size();i++)
		{
			childOne[i] = childOneMatchesAL.get(i);
		}
		Chromosome child1 = new Chromosome(childOne);
		//System.out.println("Child 1 length:"+child1.size());
		
		
		//child 2
		ArrayList<Match> childTwoMatchesAL = new ArrayList<Match>();
		for (int i=0;i<crossOverPoint;i++)
		{
			childTwoMatchesAL.add(parentTwoMatchesAL.get(i));
		}
		for (int i=crossOverPoint;i<parentOneMatchesAL.size();i++)
		{
			childTwoMatchesAL.add(parentOneMatchesAL.get(i));
		}
		Match[] childTwo = new Match[childTwoMatchesAL.size()];
		for (int i=0;i<childTwoMatchesAL.size();i++)
		{
			childTwo[i] = childTwoMatchesAL.get(i);
		}
		Chromosome child2 = new Chromosome(childTwo);
		//System.out.println("Child 2 length:"+child2.size());
		
		Chromosome[] setOfChildren = new Chromosome[]{child1, child2};
		return setOfChildren;
		
	}
	
	public static Chromosome mutate (Chromosome c)
	{
		//System.out.println("Inside mutation::"+c.size());
		int maximumIndex = c.size() ;
		int numberOfMatchesToChange =  (int) (Constants.MUTATION_FACTOR * (maximumIndex) );
		//System.out.println("numberOfMatchesToChange::"+numberOfMatchesToChange);
		Match[] matchesPlayed = c.getMatches();
		
		while (numberOfMatchesToChange !=0)
		{
			int positionToChange = random.nextInt(c.size());
			//System.out.println("positionToChange::"+positionToChange);
			Match matchToChange = new Match(FootballData.getTeams().get(random.nextInt(FootballData.getTeams().size())),
            		FootballData.getTeams().get(random.nextInt(FootballData.getTeams().size())),
            		FootballData.getDates().get(random.nextInt(FootballData.getDates().size())), 
            		FootballData.getLocations().get(random.nextInt(FootballData.getLocations().size()))) ;
			 matchesPlayed[positionToChange] = matchToChange; 
			 numberOfMatchesToChange --;
		}
		
		//System.out.println("After mutation::"+matchesPlayed.length);
		c.setMatches(matchesPlayed);
		return c;
	}  
	
	/**
	 * The following function select a parent from the population
	 * @param population sorted by it's fitness value
	 * @return chromosome with highest fitness
	 */
	public static Chromosome k_wayParentSelection(Chromosome[] chromosomes)
	{
		//System.out.println("inside k_wayParentSelection");
		//assuming population is sorted
		
		List<Chromosome> list = new ArrayList<Chromosome>();
//		Chromosome[] chromosomes = population.getChromosomes();
				
		/*** after sorting population in decreasing order first ELITE_FACTOR value chromosomes 
		will not pick for random selection ***/
		
		int minVal = Constants.ELITE_FACTOR + 1;
		int position = 0;
		
		HashMap<Integer, Chromosome> tournaments = new HashMap<Integer, Chromosome>();
		
		for (int i=0; i< Constants.K_FACTOR ;i++)
		{
			position =  random.nextInt(chromosomes.length-minVal) + minVal;
			tournaments.put(position, chromosomes[position]);
		}
		
		for (Entry<Integer, Chromosome> entry : tournaments.entrySet())
			list.add(entry.getValue());
		
		
		Collections.sort(list, new Comparator<Chromosome>() {
            @Override
            public int compare(Chromosome o1, Chromosome o2) {
                return o1.compareTo(o2);
            }
        });
		
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

	private static void sortPopulation(Population initialPopulation) 
	{
		Arrays.sort(initialPopulation.getChromosomes(), new Comparator<Chromosome>() 
		{
	        public int compare(Chromosome o1, Chromosome o2) {
	            return o1.compareTo(o2);
	        }
	    });			
	}
}
