/**
 * Team:    Group 2
 * Members: Logan Chess, Bryant Gonzalez Guzman, Alex Gonzalez Monreal
 * Course:  CS 2430, Section 502
 * Project: Programming Project 3 - Spring 2026
 *
 * This class represents a single science experiment that could be
 * loaded onto the space shuttle. Each experiment has a unique number,
 * a descriptive name, a weight in kilograms, and a scientific rating
 * (1–10 scale). This serves as the base data class used by both the
 * greedy strategies in Knapsack and the brute-force search in
 * PermutationGenerator.
 */

import java.util.Objects;

public class Experiment {
	int number;
	String name;
	int weight;
	int rating;
	
	Experiment(int number, String name, int weight, int rating) {
        this.number = number;
        this.name = name;
        this.weight = weight;
        this.rating = rating;
	}
    double ratio() {
        return (double) rating / weight;
    }
 
    @Override
    public String toString() {
        return String.format("#%-2d %-30s  Weight: %3d kg  Rating: %d",
                number, name, weight, rating);
    }
    
	   
    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        
        Experiment other = (Experiment) obj;
        
        return number == other.number &&
               weight == other.weight &&
               rating == other.rating &&
               Objects.equals(name, other.name);
    }
    
    @Override
    public int hashCode() {
        return Objects.hash(number, name, weight, rating);
    }
}
