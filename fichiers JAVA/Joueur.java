public class Joueur {
	private double puissance;
	private double endurance;
	private String name;
	private double points;
	private int rank;
	
	public Joueur(int puissance, int endurance, String name, double points, int rank) {
		this.puissance=puissance;
		this.endurance=endurance;
		this.name=name;
		this.points=points;
		this.rank=rank;
	}
	
	public double getPuissance() {
		return puissance;
	}
	public void setPuissance(int puissance) {
		this.puissance = puissance;
	}
	public double getEndurance() {
		return endurance;
	}
	public void setEndurance(int endurance) {
		this.endurance = endurance;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public double getPoints() {
		return points;
	}
	public void setPoint(int points) {
		this.points = points;
	}
	public int getRank() {
		return rank;
	}
	public void setRank(int rank) {
		this.rank = rank;// This is the rank!
	}

}
