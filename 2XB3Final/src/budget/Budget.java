package budget;

public class Budget implements Comparable<Budget>{
	private double trans, ent, food, util, school, clothes, essen, other, total; 
	
	
	public Budget(double trans, double ent, double food, double util, double school, double clothes, double essen, double other) {
		this.trans = trans;
		this.ent = ent;
		this.food = food;
		this.util = util;
		this.school = school;
		this.clothes = clothes;
		this.essen = essen;
		this.other = other;
		updateTot();
	}
	
	public Budget() {
		this.trans = 0.0;
		this.ent = 0.0;
		this.food = 0.0;
		this.util = 0.0;
		this.school = 0.0;
		this.clothes = 0.0;
		this.essen = 0.0;
		this.other = 0.0;
		updateTot();
	}
	
	private void updateTot() {
		this.total = this.trans + this.ent + this.food + this.util + 
		this.school + this.clothes + this.essen + this.other;
	}
	
	public double getTransportation() {
		return this.trans;
	}
	
	public double getEntertainment() {
		return this.ent;
	}
	
	public double getFood() {
		return this.food;
	}

	public double getUtilities() {
		return this.util;
	}

	public double getSchool() {
		return this.school;
	}
	
	public double getClothes() {
		return this.clothes;
	}
	
	public double getEssentials() {
		return this.essen;
	}
	
	public double getOther() {
		return this.other;
	}

	public double getTotal() {
		return this.total;
	}
	
	public void setTransportation(double trans) {
		this.trans = trans;
		updateTot();
	}
	
	public void setEntertainment(double ent) {
		this.ent = ent;
		updateTot();
	}
	
	public void setFood(double food) {
		this.food = food;
		updateTot();
	}

	public void setUtilities(double util) {
		this.util = util;
		updateTot();
	}

	public void setSchool(double school) {
		this.school = school;
		updateTot();
	}
	
	public void setClothes(double clothes) {
		this.clothes = clothes;
		updateTot();
	}
	
	public void setEssentials(double essen) {
		this.essen = essen;
		updateTot();
	}
	
	public void setOther(double other) {
		this.other = other;
		updateTot();
	}

	public int compareTo(Budget comp) {
		if (this.clothes == comp.clothes && this.ent == comp.ent && this.essen ==
		comp.essen && this.food == comp.food && this.other == comp.other && this.school 
		== comp.school && this.trans == comp.trans && this.util == comp.trans) {
			return 0;
		} else {
			if (this.total < comp.total) {
				return -1;
			} else {
				return 1;
			}
		}
	}
}
