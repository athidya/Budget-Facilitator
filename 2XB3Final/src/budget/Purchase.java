package budget;

public class Purchase implements Comparable<Purchase>{
	
	private Item item;
	private Category category;
	private Date date;
		
	public Purchase(Item item, Category cat, Date date){
		this.item = item;
		this.category = cat;
		this.date = date;
	}
	
	public Purchase() {
		this.item = new Item();
		this.category = null;
		this.date = null;		
	}
	
	public String toString() {
		return ("You spent " + this.item.getPrice() + " buying " + this.item.getName() + " on " + this.date);
	}
	
	public Item getItem() {
		return this.item;
	}
	
	public Category getCategory() {
		return this.category;
	}
	
	public Date getDate() {
		return this.date;
	}
	
	public void setItem(Item item) {
		this.item = item;
	}
	
	public void setCategory(Category cat) {
		this.category = cat;
	}
	
	public void setDate(Date date) {
		this.date = date;
	}
	
	public boolean equals(Purchase pur) {
		if (this.item.equals(pur.getItem()) && this.category == pur.getCategory() && this.date.equals(pur.getDate())) {
			return true;
		} else {
			return false;
		}
	}
	
	@Override
	public int compareTo(Purchase pur) {
		if (this.item.compareTo(pur.item) == 1 || this.date.compareTo(pur.date) == 1) {
			return 1;
		} else {
			if (this.item.compareTo(pur.item) == -1 || this.date.compareTo(pur.date) == -1) {
				return -1;
			} else {
				return 0;
			}
		}
	}
	

}
