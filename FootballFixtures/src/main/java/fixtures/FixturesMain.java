package main.java.fixtures;
import java.util.Date;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.TimeUnit;

import main.java.helper.Match;
/**
 * The class contains the main class from which the fixtures are generated if all the constraints are met
 * @author Aditi Jalkote, Mitali Salvi, Shubham Sharma
 */

public class FixturesMain {
	
	private static Population population = null;

	public static void main(String[] args) 
	{
		//Give input to the Fixture Generator
		Configuration.initializeData();

//		runAlgorithm(0, Constants.POPULATION_SIZE);
		evaluateAlgorithm();


	}

//	private static void runAlgorithm(int from, int to)
//	{
//
//			evaluateAlgorithm();
//		else {
//			
//			int mid = from + ( (to - from) / 2 );
//			
//			CompletableFuture<Population> colony_1 = generatePopulation(from, mid);
//			CompletableFuture<Population> colony_2 = generatePopulation(mid + 1, to);
//		
//			CompletableFuture<Population> combineColonies = colony_1.
//					thenCombine(colony_2, (c1, c2) -> new Population(c1.getChromosomes(), c2.getChromosomes()));
//		
//			try{
//				combineColonies.whenComplete((population, throwable) -> {
//					if(throwable != null) {
//						System.out.println("Exception throw in thread: " +throwable.getMessage());
//						return;
//					}
//					
//					FixturesMain.population = population;
//				});
//			}
//			catch (Exception e) {
//				System.out.println("Exception throw in thread: " +e.getMessage());
//			}
//			
//			
//			CompletableFuture.allOf(combineColonies).join();
//			
//			combineColonies.thenRun(FixturesMain::evaluateAlgorithm);
//		}
//	}

	/**
	 * Function creates a new thread for given colony size
	 * @param from starting index of colony
	 * @param to last index of colony
	 * @return Created thread fo type CompletableFuture
	 */
//	private static CompletableFuture<Population> generatePopulation(int from, int to) {
//		return CompletableFuture.supplyAsync(() -> {
//			runAlgorithm(from, to);
//			return population;
//		});
//	}

	private static void evaluateAlgorithm() {
		
		Population nextGen ;
		
		double startTime=System.nanoTime();
		double timeInNano=0.0;
		long timeInMilliToGetSolution =0;
		
		Population initialPopulation= new Population(Constants.POPULATION_SIZE) ;	
		
		int maxGeneration = Constants.MAX_GENERATION;
		

		do {
			nextGen = GeneticAlgorithm.runGeneticAlgorithm(initialPopulation);
			maxGeneration--;
			if(GeneticAlgorithm.getFlag()==true) {
				break;
			}
			initialPopulation = nextGen; 
		}while(maxGeneration >=0);
		
		
		Chromosome[] temp = initialPopulation.getChromosomes();
        for (int i=0;i<temp.length;i++)
        {
        	if (temp[i].getFitness() ==1.0)
        	{
        		timeInNano = (System.nanoTime() - startTime)/* Math.pow(10, -6) */;
        		timeInMilliToGetSolution = TimeUnit.MILLISECONDS.convert((long) timeInNano, TimeUnit.NANOSECONDS);
                Match[] h =temp[i].getMatches();
                for (int j=0;j<h.length; j++){
                    System.out.println(h[j]);
                }
                break;
        	}
        }
        
        System.out.println("timeInMilliToGetSolution:"+timeInMilliToGetSolution);
        
        System.out.println("Done implementing GA");
	}

}
