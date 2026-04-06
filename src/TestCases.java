import static org.junit.Assert.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;



public class TestCases {
	Experiment[] knapsack = Knapsack.loadExperiments();
	ArrayList<permutationGenerator> experiments = new ArrayList<>();
	ArrayList<ArrayList<permutationGenerator>> permutations = new ArrayList<>();
    @BeforeEach
	public void setUp() {
    	//all needed variable/set up for permutationgenerator/Knapsack method testing
    	
    	//array values
        permutationGenerator clouds = new permutationGenerator(1,"Cloud Patterns",36,5);
        permutationGenerator SolarFL = new permutationGenerator(2,"Solar Flares",264,9);
        permutationGenerator SolarPO = new permutationGenerator(3,"Solar Power",188,6);
        permutationGenerator Binary = new permutationGenerator(4,"Binary Stars",203,8);
        permutationGenerator Relativity = new permutationGenerator(5,"Relativity",104,8);
        permutationGenerator Seed = new permutationGenerator(6,"Seed Viability",7,4);
        permutationGenerator SunSpots = new permutationGenerator(7,"Sun Spots",90,2);
        permutationGenerator MiceTumors = new permutationGenerator(8,"Mice Tumors",65,8);
        permutationGenerator PlantGrowth = new permutationGenerator(9,"Microgravity Plant Growth",75,5);
        permutationGenerator Micrometeorites = new permutationGenerator(10,"Micrometeorites",170,9);
        permutationGenerator CosmicRays = new permutationGenerator(11,"Cosmic Rays",80,7);
        permutationGenerator Yeast = new permutationGenerator(12,"Yeast Fermentation",27,4);
    	experiments.add(clouds); experiments.add(Relativity); experiments.add(PlantGrowth);
    	experiments.add(SolarFL); experiments.add(Seed); experiments.add(Micrometeorites);
    	experiments.add(SolarPO); experiments.add(SunSpots); experiments.add(CosmicRays);
    	experiments.add(Binary);  experiments.add(MiceTumors); experiments.add(Yeast);
    	
    	
	}
    
    
    @Test
	void Test_All_Permutations_Are_Created() {	
    	permutations = permutationGenerator.generate(experiments, 0);
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
    	ArrayList<permutationGenerator> selected = new ArrayList<>();
    	permutations =  permutationGenerator.generate(selected, 0);
    	ArrayList<permutationGenerator> empty = new ArrayList<>();
    	ArrayList<ArrayList<permutationGenerator>> expected = new ArrayList<>();
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
