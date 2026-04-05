import java.util.ArrayList;
public class permutationGenerator extends Experiment {

		//a object used for the permutations(subsets) that inherits attributes from Experiment class
		public permutationGenerator(int number, String name, int weight, int rating) {
			super(number,name,weight,rating);
		}
		
		
		/*This method generates all possible permutations
		 * @param index is the current element (start at at the 0 element)
		 * @param arr is an arrayList made of the permutationGenerator object
		 * @return Returns an arrayList that contains arraylists of permutationgenerator (each possible subset)
		 */
		public static ArrayList<ArrayList<permutationGenerator>> generate(ArrayList<permutationGenerator> arr, int index) {
		    ArrayList<ArrayList<permutationGenerator>> allSubsets = new ArrayList<>();
		    
		    // Base case: when we've reached the last element
		    if (index == arr.size()) {
		        // Add empty subset (first value to start)
		    	allSubsets.add(new ArrayList<>());
		        return allSubsets;
		    } 
		    //get current element
		    permutationGenerator currentElement = arr.get(index);
		    //use recursive method to get all subsets 
		    ArrayList<ArrayList<permutationGenerator>> subsetWithoutCurrent = generate(arr, index + 1);
		    
		    //add all subsets without current element
		    allSubsets.addAll(subsetWithoutCurrent);
		    
		    //create new subset by adding current element to existing subset
		    for(ArrayList<permutationGenerator> subset : subsetWithoutCurrent) {
		    	ArrayList<permutationGenerator> newSubset = new ArrayList<>(subset);
		    	
		    	newSubset.add(currentElement);
		    	allSubsets.add(newSubset);
		    }
		    return allSubsets;
		}

		/*This method first checks an arrayList that contains arrayLists of permutationgenerator (each possible subset) if each element(subset) has a weight of 700kg or less
		 * If the arrayList passes the check it will store in another ArrayList for the 2nd part
		 * 2nd part is to sort total rating by adding all rating values and comparing them to the top 3 current ratings 
		 * @param arr is an arrayList that contains arraylists of permutationgenerator (each possible subset)
		 * @return a sorted arraylist by rating and 700kg or less
		 */
		public static ArrayList<ArrayList<permutationGenerator>> HighestValues(ArrayList<ArrayList<permutationGenerator>> arr) {
			 // Store subsets where weight is 700 or less
		    ArrayList<ArrayList<permutationGenerator>> allValidWeight = new ArrayList<>();
		    // Store best 3 subsets in elements 0,1,2 by rating
		    ArrayList<ArrayList<permutationGenerator>> bestRating = new ArrayList<>();
		    
		    //  Filter by weight less or equal to 700kg
		    for (int i = 0; i < arr.size(); i++) {
		        int Total_kg = 0;
		        ArrayList<permutationGenerator> test = arr.get(i);
		        
		        // Calculate total weight for this subset 
		        for (int j = 0; j < test.size(); j++) {
		            Total_kg = Total_kg + test.get(j).weight;
		        }
		        
		        // Check weight 
		        if (Total_kg <= 700) {
		            allValidWeight.add(test);
		        }
		    }
		    
		    // Track top 3 ratings/weight
		    Integer best_rating1 = 0;
		    Integer bestWeight1 = 0;
		    
		    Integer best_rating2 = 0;
		    Integer bestWeight2 = 0;
		    
		    Integer best_rating3 = 0;
		    Integer bestWeight3 = 0;
		    // Keep track of the actual subsets for top 3
		    ArrayList<permutationGenerator> bestSubset1 = null;
		    ArrayList<permutationGenerator> bestSubset2 = null;
		    ArrayList<permutationGenerator> bestSubset3 = null;
		    
		    // Find top 3 by total rating
		    for (int i = 0; i < allValidWeight.size(); i++) {
		        Integer total_rating = 0;
		        Integer total_weight = 0;
		        ArrayList<permutationGenerator> currentSubset = allValidWeight.get(i);
		        
		        // Calculate total rating for subset
		        for (int j = 0; j < currentSubset.size(); j++) {
		            total_rating = total_rating + currentSubset.get(j).rating;
		            total_weight = total_weight + currentSubset.get(j).weight;
		        }
		        
		        // Compare with top 3 ratings
		        if (total_rating > best_rating1) {
		            // Shift existing top 3 down
		            best_rating3 = best_rating2;
		            bestWeight3 = bestWeight2;
		            bestSubset3 = bestSubset2;
		            
		            best_rating2 = best_rating1;
		            bestWeight2 = bestWeight1;
		            bestSubset2 = bestSubset1;
		            // Set new best
		            best_rating1 = total_rating;
		            bestWeight1 = total_weight;
		            bestSubset1 = currentSubset;
		        } 
		        else if (total_rating > best_rating2) {
		            // Shift positions 2 and 3 down
		            best_rating3 = best_rating2;
		            bestWeight3 = bestWeight2;
		            bestSubset3 = bestSubset2;
		            // Set new second best
		            best_rating2 = total_rating;
		            bestWeight2 = total_weight;
		            bestSubset2 = currentSubset;
		        } 
		        else if (total_rating > best_rating3) {
		            // Set new third best
		            best_rating3 = total_rating;
		            bestWeight3 = total_weight;
		            bestSubset3 = currentSubset;
		        }
		    }
		    
		    // Add top 3 subsets to result (only if they exist)
		    if (bestSubset1 != null) bestRating.add(bestSubset1);
		    if (bestSubset2 != null) bestRating.add(bestSubset2);
		    if (bestSubset3 != null) bestRating.add(bestSubset3);
		    
		    // Print results for debugging
		    ArrayList<Integer> FinalRatings = new ArrayList<Integer>();
		    ArrayList<Integer> FinalWeight = new ArrayList<Integer>();
		    FinalRatings.add(best_rating1); FinalWeight.add(bestWeight1);
		    FinalRatings.add(best_rating2); FinalWeight.add(bestWeight2);
		    FinalRatings.add(best_rating3); FinalWeight.add(bestWeight3);
		    for (int i = 0; i < bestRating.size() ; i++) {
	            System.out.println("Permutation " + (i + 1) + ":");
	            //pull out specific permutation
	            ArrayList<permutationGenerator> singlePermutation = bestRating.get(i);
	            //use for loop to get out values of specific permutation
	            for (permutationGenerator item : singlePermutation) {
	                System.out.println("  " + item.number + ", " + item.name + " " + item.weight +  " " + item.rating);
	            }
	            System.out.println("Total Rating is " + FinalRatings.get(i));
	            System.out.println("Total Weight is " + FinalWeight.get(i));
	            System.out.println();
	        }
		       
		    return bestRating;
		}
		
		/*
		 * This is a testing/example usage for genrate()/HighestValues() methods
		 */
		public static void main(String[] args) {
    	//original array
        ArrayList<permutationGenerator> nums = new ArrayList<>();
        //permutations (outer list holds all permutations and inner list hold one permutation
        ArrayList<ArrayList<permutationGenerator>> permutations = new ArrayList<>();
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
        //add to array
        nums.add(clouds); nums.add(Relativity); nums.add(PlantGrowth);
        nums.add(SolarFL); nums.add(Seed); nums.add(Micrometeorites);
        nums.add(SolarPO); nums.add(SunSpots); nums.add(CosmicRays);
        nums.add(Binary);  nums.add(MiceTumors); nums.add(Yeast);
        //get all permutations
        permutations = generate(nums,0);
        //get best 3 arrays
        ArrayList<ArrayList<permutationGenerator>> good = new ArrayList<>();
        good = HighestValues(permutations);
        }
}

