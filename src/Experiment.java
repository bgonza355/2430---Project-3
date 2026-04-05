

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
}
