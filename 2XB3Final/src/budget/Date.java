package budget;

public class Date implements Comparable<Date>{
	private int day;
	private int month;
	private int year;
	
	public Date(int day, int month, int year) {
		this.day = day;
		this.month = month;
		this.year = year; 
	}
	
	@Override
	public String toString() {
		return (this.day + "/" + this.month + "/" + this.year);
	}
	
	public void setDay(int day) {
		this.day = day;
	}
	
	public void setMonth(int month) {
		this.month = month;
	}
	
	public void setYear(int year) {
		this.year = year;
	}
	
	public int getDay() {
		return this.day;
	}
	
	public int getMonth() {
		return this.month;
	}
	
	public int getYear() {
		return this.year;
	}
	
	public boolean equals(Date date) {
		if (this.day == date.day && this.month == date.month && this.year == date.year) {
			return true;
		} else {
			return false;
		}
	}
	
	@Override
	public int compareTo(Date date) //method to compare dates in same year
	{
		if (this.year > date.year)		return +1;
		if (this.year < date.year)		return -1;
		if (this.month > date.month)	return +1;
		if (this.month < date.month)	return -1;
		if (this.day > date.day)		return +1;
		if (this.day < date.day)		return -1;
		return 0;
	}
	
}
