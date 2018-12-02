package main.java.fixtures;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Random;

public class GeneticAlgorithm {

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
}
