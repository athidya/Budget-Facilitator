package budget;

public class Item implements Comparable<Item> {
	
	private String name;
	private double price;
	
	public Item(String name, double price) {
		this.name = name;
		this.price = price;
	}
	
	public Item() {
		this.name = null;
		this.price = 0.0;
	}
	
	public String getName() {
		return this.name;
	}
	
	public double getPrice() {
		return this.price;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public void setPrice(double price) {
		this.price = price;
	}
	
	public boolean equals(Item item) {
		if (this.name.equalsIgnoreCase(item.name) && this.price == item.price) {
			return true;
		} else {
			return false;
		}
	}
	
	public int compareTo(Item good) {
		if (this.price > good.price) {
			return 1;
		} else {
			if (this.price < good.price) {
				return -1;
			} else {
				return 0;
			}
		}
	}

}
