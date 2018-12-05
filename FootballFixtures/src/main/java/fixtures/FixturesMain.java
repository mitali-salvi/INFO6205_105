
package main.java.fixtures;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

import main.java.helper.Match;

/**
 * The class contains the main class from which the fixtures are generated if
 * all the constraints are met
 * 
 * @author Aditi Jalkote, Mitali Salvi, Shubham Sharma
 */

public class FixturesMain {

	public static void main(String[] args) {

		Configuration.initializeData();
		
		Population population = runAlgorithm(0, Constants.POPULATION_SIZE);
		
		evaluateAlgorithm(population);

	}

	private static Population runAlgorithm(int from, int to) {
		int size = to - from;

		if (size < Constants.MAX_COLONY_SIZE) {

			return new Population(size);

		} else {

			int mid = from + ((to - from) / 2);

			CompletableFuture<Population> colony_1 = generatePopulation(from, mid);
			CompletableFuture<Population> colony_2 = generatePopulation(mid, to);

			CompletableFuture<Population> combineColonies = colony_1.thenCombine(colony_2,
					(xs1, xs2) -> new Population(xs1.getChromosomes(), xs2.getChromosomes()));

			combineColonies.whenComplete((population, throwable) -> {
				if (throwable != null) {
					System.out.println("Exception throw in thread: " + throwable.getMessage());
					throwable.printStackTrace();
				}

			});

			CompletableFuture.allOf(combineColonies).join();
			try {
				return combineColonies.get();
			} catch (InterruptedException e) {
				e.printStackTrace();
			} catch (ExecutionException e) {
				e.printStackTrace();
			}
			return null;
		}
	}

	/**
	 * Function creates a new thread for given colony size
	 * 
	 * @param from
	 *            starting index of colony
	 * @param to
	 *            last index of colony
	 * @return Created thread fo type CompletableFuture
	 */
	private static CompletableFuture<Population> generatePopulation(int from, int to) {
		return CompletableFuture.supplyAsync(() -> {
			return runAlgorithm(from, to);
		});
	}

	private static void evaluateAlgorithm(Population population) {

		Population nextGen;

		int maxGeneration = Constants.MAX_GENERATION;

		do {
			nextGen = GeneticAlgorithm.runGeneticAlgorithm(population);
			maxGeneration--;
			if (GeneticAlgorithm.getFlag() == true) {
				break;
			}
			population = nextGen;
		} while (maxGeneration >= 0);

		Chromosome[] temp = population.getChromosomes();

		for (int i = 0; i < temp.length; i++) {

			if (temp[i].getFitness() == 1.0) {

				Match[] h = temp[i].getMatches();
				for (int j = 0; j < h.length; j++) {

					System.out.println(h[j]);
				}
				break;
			}
		}

		System.out.println("Done implementing GA");

	}

}
