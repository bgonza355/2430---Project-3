

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

public class Knapsack {
	
	static final int MAX_WEIGHT = 700;
	
	static Experiment[] loadExperiments() {
		return new Experiment[] {
            new Experiment(1, "Cloud Patterns", 36, 5),
            new Experiment(2, "Solar Flares", 264, 9),
            new Experiment(3, "Solar Power", 188, 6),
            new Experiment(4, "Binary Stars", 203, 8),
            new Experiment(5, "Relativity", 104, 8),
            new Experiment(6, "Seed Viability", 7, 4),
            new Experiment(7, "Sun Spots", 90, 2),
            new Experiment(8, "Mice Tumors", 65, 8),
            new Experiment(9, "Microgravity Plant Growth", 75, 5),
            new Experiment(10, "Micrometeorites", 170, 9),
            new Experiment(11, "Cosmic Rays", 80, 7),
            new Experiment(12, "Yeast Fermentation", 27, 4),
		};
	}
	
	/**
	 * This is the wrapper method for implementing the Strategy design pattern
	 * 
	 * The concrete strategies are the rating, weight, and ratio algorithms.
	 * The interface is the Comparator interface implemented by Comparator<Experiment>
	 * 
	 * @param experiments	the full array of available experiments
	 * @param strategy		a Comparator defining the sort order (the concrete strat)
	 * @return	a list of selected experiments
	 */
	private static List<Experiment> greedySelect(Experiment[] experiments,
												 Comparator<Experiment> strategy) {
		// We Create a sorted copy so don't accidently modify original array
		Experiment[] sorted = experiments.clone();
		// We sort it based on the specific greedy strategy (either by weight, rating, or value);
		Arrays.sort(sorted, strategy);
		 
        List<Experiment> selected = new ArrayList<>();
        int currentWeight = 0;

        // Greedily selects elements that are sorted based on criteria as
        // as long as our total weight doesn't exceed MAX_WEIGHT.
        for (Experiment e : sorted) {
            if (currentWeight + e.weight <= MAX_WEIGHT) {
                selected.add(e);
                currentWeight += e.weight;
            }
        }
 
        return selected;
	}
	
	/**
	 * Greedy strategy 1: sort based on highest RATING in DESCENDING order.
	 * This uses DESCENDING as a higher rating is better.
	 * 
	 * @param experiments	the full array of available experiments
	 * @return a list of selected experiments.
	 */
    static List<Experiment> greedyByRating(Experiment[] experiments) {
        return greedySelect(experiments, 
        	// This uses a lambda to sort by rating in descending order
        	(a, b) -> b.rating - a.rating 
        );
    }
    
    /**
	 * Greedy strategy 2: sort based on highest WEIGHT in ASCENDING order.
	 * This uses ASCENDING rather than DESCENDING because lower weight is better.
	 * 
	 * @param experiments	the full array of available experiments
	 * @return a list of selected experiments.
	 */
    static List<Experiment> greedyByWeight(Experiment[] experiments) {
        return greedySelect(experiments,
        	// This uses a lambda to sort by weight in descending order
        	(a, b) -> a.weight - b.weight
        );
    }
    
    /**
	 * Greedy strategy 3: sort based on highest RATIO in DESCENDING order.
	 * This uses DESCENDING as a higher ratio is better.
	 * 
	 * @param experiments	the full array of available experiments
	 * @return a list of selected experiments.
	 */
    static List<Experiment> greedyByRatio(Experiment[] experiments) {
        return greedySelect(experiments,
        	// This uses a lambda to sort by ratio in descending order
        	(a, b) -> Double.compare(b.ratio(), a.ratio())
        );
    }
    
	/** 
	 * This method computes the total weight of a list of 
	 * experiments produced by a specific strategy.
	 */
    static int totalWeight(List<Experiment> experiments) {
        int total = 0;
        for (Experiment e : experiments) {
            total += e.weight;
        }
        return total;
    }
 
    /** 
     * This method computes the total rating of a list of
     * experiments produced by a specific concrete strategy.
     */
    static int totalRating(List<Experiment> experiments) {
        int total = 0;
        for (Experiment e : experiments) {
            total += e.rating;
        }
        return total;
    }
    
    /**
     * This method prints a labeled result set showing the selected 
     * experiments, total weight, and total rating. 
     */
    static void printResults(String strategyName, List<Experiment> selected) {
        System.out.println("=========================================================");
        System.out.println("Strategy: " + strategyName);
        System.out.println("=========================================================");
        System.out.println("Selected experiments:");
 
        for (Experiment e : selected) {
            System.out.println("  " + e);
        }
 
        System.out.println();
        System.out.printf("Total Weight: %d kg %n", totalWeight(selected));
        System.out.printf("Total Rating: %d %n", totalRating(selected));
        System.out.println();
    }
    
    public static void main(String[] args) {
        Experiment[] experiments = loadExperiments();
 
        printResults("Highest Rating First",            greedyByRating(experiments));
        printResults("Lightest First",                  greedyByWeight(experiments));
        printResults("Best Rating-to-Weight Ratio First", greedyByRatio(experiments));
    }
	
}
