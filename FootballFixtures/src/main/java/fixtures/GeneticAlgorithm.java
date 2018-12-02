package main.java.fixtures;


import java.lang.reflect.Array;
import java.util.Random;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import main.java.helper.Match;

public class GeneticAlgorithm 
{
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
	
	public static Chromosome mutate (Chromosome c)
	{
		int numberOfMatchesToChange = (int)Constants.MUTATION_FACTOR * c.size();
		//Random random = new Random();
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
  
  static Random random = new Random();
	
	public static Chromosome k_wayParentSelection(Population population){
		//assuming population is sorted
		
		List<Chromosome> list = new ArrayList<Chromosome>();
		Chromosome[] chromosomes = population.getChromosomes();
		
		int maxVal = population.getChromosomes().length - 1;
		
		/*** after sorting population in decreasing order first ELITE_FACTOR value chromosomes 
		will not pick for random selection ***/
		
		int minVal = Constants.ELITE_FACTOR + 1;
		
		for(int i = 0; i < Constants.K_FACTOR; i++){
			int position = random.nextInt(maxVal - minVal + 1) + minVal;
			list.add(chromosomes[position]);
		}
		
		Collections.sort(list, new Comparator<Chromosome>() {
            @Override
            public int compare(Chromosome o1, Chromosome o2) {
                return o1.compareTo(o2);
            }
        });
		
		System.out.println("parent chromosome list: "+list);
		
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

}
