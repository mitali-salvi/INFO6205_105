package main.java.fixtures;

import java.util.Date;
import java.util.concurrent.CompletableFuture;

import main.java.helper.Match;

public class FixturesMain {
	
	private static Population population = null;
	
	public static void main(String[] args) 
	{
		//Give input to the Fixture Generator
		Configuration.initializeData();
		System.out.println("Initialized data");
		//System.out.println(((FootballData.getTeams().size()) * (FootballData.getTeams().size()-1))/2 * Constants.NUMBER_OF_ROUNDS);

		runAlgorithm(0, Constants.POPULATION_SIZE);


	}

	private static void runAlgorithm(int from, int to){
		int size = to - from;
		
		if(size <= Constants.MAX_COLONY_SIZE){
			evaluateAlgorithm();
		}else {
			
			int mid = from + ( (to - from) / 2 );
			
			CompletableFuture<Population> colony_1 = generatePopulation(from, mid);
			CompletableFuture<Population> colony_2 = generatePopulation(mid + 1, to);
		
			CompletableFuture<Population> combineColonies = colony_1.
					thenCombine(colony_2, (c1, c2) -> new Population(c1.getChromosomes(), c2.getChromosomes()));
		
			combineColonies.whenComplete((population, throwable) -> {
				if(throwable != null) {
					System.out.println("Exception throw in thread: " +throwable.getMessage());
					return;
				}
				
				FixturesMain.population = population;
			});
			
			CompletableFuture.allOf(combineColonies).join();
			
			combineColonies.thenRun(FixturesMain::evaluateAlgorithm);
		}
	}

	/**
	 * Function creates a new thread for given colony size
	 * @param from starting index of colony
	 * @param to last index of colony
	 * @return Created thread fo type CompletableFuture
	 */
	private static CompletableFuture<Population> generatePopulation(int from, int to) {
		return CompletableFuture.supplyAsync(() -> {
			runAlgorithm(from, to);
			return population;
		});
	}

	private static void evaluateAlgorithm() {
		
		Population nextGen ;			
		
		int maxGeneration = Constants.MAX_GENERATION;
		System.out.println("Starting GA");
		
		do {
			System.out.println("current generation: "+maxGeneration);
			population = new Population(Constants.POPULATION_SIZE);	
			nextGen = GeneticAlgorithm.runGeneticAlgorithm(population);
			maxGeneration--;
			if(GeneticAlgorithm.flag)
			{
				System.out.println("Breaking while loop");
				break;
			}
			//initialPopulation = nextGen; 

		}while(maxGeneration >=0);
		
		
		System.out.println("Out of loop");
		//System.out.println(initialPopulation);
		//System.out.println("length::"+initialPopulation.getChromosomes().length);
		
		
		Chromosome[] temp = population.getChromosomes();
        for (int i=0;i<temp.length;i++)
        {
        	if (temp[i].getFitness() ==1.0)
        	{
                Match[] h =temp[i].getMatches();
                for (int j=0;j<h.length; j++)
                {
                    System.out.println(h[j]);
                }
                break;
        	}
        }
        System.out.println("Done implementing GA");
		
	}

}
