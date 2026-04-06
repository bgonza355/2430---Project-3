

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
	//overiding for junit testing
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
