/**
 * Team:    Group 2
 * Members: Logan Chess, Bryant Gonzalez Guzman, Alex Gonzalez Monreal
 * Course:  CS 2430, Section 502
 * Project: Programming Project 3 - Spring 2026
 *
 * JUnit test cases verifying the three greedy strategies and the
 * brute-force subset generation. Includes edge-case tests for
 * empty input arrays.
 */

import static org.junit.Assert.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;



public class TestCases {
	Experiment[] knapsack = Knapsack.loadExperiments();
	ArrayList<PermutationGenerator> experiments = new ArrayList<>();
	ArrayList<ArrayList<PermutationGenerator>> permutations = new ArrayList<>();
    @BeforeEach
	public void setUp() {
    	//all needed variable/set up for PermutationGenerator/Knapsack method testing
    	
    	//array values
    	PermutationGenerator clouds = new PermutationGenerator(1,"Cloud Patterns",36,5);
    	PermutationGenerator SolarFL = new PermutationGenerator(2,"Solar Flares",264,9);
        PermutationGenerator SolarPO = new PermutationGenerator(3,"Solar Power",188,6);
        PermutationGenerator Binary = new PermutationGenerator(4,"Binary Stars",203,8);
        PermutationGenerator Relativity = new PermutationGenerator(5,"Relativity",104,8);
        PermutationGenerator Seed = new PermutationGenerator(6,"Seed Viability",7,4);
        PermutationGenerator SunSpots = new PermutationGenerator(7,"Sun Spots",90,2);
        PermutationGenerator MiceTumors = new PermutationGenerator(8,"Mice Tumors",65,8);
        PermutationGenerator PlantGrowth = new PermutationGenerator(9,"Microgravity Plant Growth",75,5);
        PermutationGenerator Micrometeorites = new PermutationGenerator(10,"Micrometeorites",170,9);
        PermutationGenerator CosmicRays = new PermutationGenerator(11,"Cosmic Rays",80,7);
        PermutationGenerator Yeast = new PermutationGenerator(12,"Yeast Fermentation",27,4);
    	experiments.add(clouds); experiments.add(Relativity); experiments.add(PlantGrowth);
    	experiments.add(SolarFL); experiments.add(Seed); experiments.add(Micrometeorites);
    	experiments.add(SolarPO); experiments.add(SunSpots); experiments.add(CosmicRays);
    	experiments.add(Binary);  experiments.add(MiceTumors); experiments.add(Yeast);
    	
    	
	}
    
    
    @Test
	void Test_All_Permutations_Are_Created() {	
    	permutations = PermutationGenerator.generate(experiments, 0);
    	int actual = permutations.size();
    	int expected = 4096;
    	assertEquals(actual,expected);
	}
    @Test
	void Test_Greedy_By_Rating() {
    	List<Experiment> actual = Knapsack.greedyByRating(knapsack);
    	Experiment[] selected =  new Experiment[] {
            new Experiment(2, "Solar Flares", 264, 9),
            new Experiment(10, "Micrometeorites", 170, 9),
            new Experiment(4, "Binary Stars", 203, 8),
            new Experiment(1, "Cloud Patterns", 36, 5),
            new Experiment(6, "Seed Viability", 7, 4),};
    	
    	List<Experiment> expected = new ArrayList<>();
    	expected.add(selected[0]);expected.add(selected[1]);expected.add(selected[2]);expected.add(selected[3]);expected.add(selected[4]);
    	assertEquals(actual,expected);
	}
    @Test
	void Test_Greedy_By_Lightest() {
    	List<Experiment> actual = Knapsack.greedyByWeight(knapsack);	
    	Experiment[] selected = new Experiment[] {
    			new Experiment(6, "Seed Viability", 7, 4),
    			new Experiment(12, "Yeast Fermentation", 27, 4),
                new Experiment(1, "Cloud Patterns", 36, 5),
                new Experiment(8, "Mice Tumors", 65, 8), 
                new Experiment(9, "Microgravity Plant Growth", 75, 5),
                new Experiment(11, "Cosmic Rays", 80, 7),
                new Experiment(7, "Sun Spots", 90, 2),
                new Experiment(5, "Relativity", 104, 8),
                new Experiment(10, "Micrometeorites", 170, 9),
    		};
    	//loop to add all
    	List<Experiment> expected = new ArrayList<>();
    	for(int i=0;i<selected.length;i++) {
    		expected.add(selected[i]);
    	}
    	assertEquals(actual,expected);
	}
    
    @Test
	void Test_Greedy_By_Ratio() {
    	List<Experiment> actual = Knapsack.greedyByRatio(knapsack);	
    	Experiment[] selected = new Experiment[] {
    			new Experiment(6, "Seed Viability", 7, 4),
    			new Experiment(12, "Yeast Fermentation", 27, 4),
                new Experiment(1, "Cloud Patterns", 36, 5),
                new Experiment(8, "Mice Tumors", 65, 8),
                new Experiment(11, "Cosmic Rays", 80, 7),
                new Experiment(5, "Relativity", 104, 8),
                new Experiment(9, "Microgravity Plant Growth", 75, 5),
                new Experiment(10, "Micrometeorites", 170, 9),
                new Experiment(7, "Sun Spots", 90, 2),
    		};
    	
    	List<Experiment> expected = new ArrayList<>();
    	for(int i=0;i<selected.length;i++) {
    		expected.add(selected[i]);
    	}
    	assertEquals(actual,expected);
	}
    //edge cases
    @Test
	void Empty_permutations() {
    	ArrayList<PermutationGenerator> selected = new ArrayList<>();
    	permutations =  PermutationGenerator.generate(selected, 0);
    	ArrayList<PermutationGenerator> empty = new ArrayList<>();
    	ArrayList<ArrayList<PermutationGenerator>> expected = new ArrayList<>();
    	expected.add(empty);
    	assertEquals(permutations,expected);
	}
    
    @Test
	void Empty_Greedy_Ratio() {
    	Experiment[] selected = new Experiment[0];
    	List<Experiment> actual = Knapsack.greedyByRatio(selected);	
    	List<Experiment> expected = new ArrayList<>();	
    	assertEquals(actual,expected);
	}
    @Test
	void Empty_Greedy_Weight() {
    	Experiment[] selected = new Experiment[0];
    	List<Experiment> actual = Knapsack.greedyByWeight(selected);	
    	List<Experiment> expected = new ArrayList<>();	
    	assertEquals(actual,expected);
	}
    @Test
	void Empty_Greedy_Rating() {
    	Experiment[] selected = new Experiment[0];
    	List<Experiment> actual = Knapsack.greedyByRating(selected);	
    	List<Experiment> expected = new ArrayList<>();	
    	assertEquals(actual,expected);
	}
}
