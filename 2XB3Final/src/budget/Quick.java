package budget;

public class Quick {
	
	public static void quick(Comparable[] dates) {
		shuffle(dates);
		quick(dates, 0, dates.length-1);
	}
	
	private static void quick(Comparable[] dates, int low, int high) {
		if (high <= low) {
			return;
		}
		int lt = low, i = low + 1, gt = high;
		Comparable v = dates[low];
		while (i <= gt) {
			int cmp = dates[i].compareTo(v);
			if (cmp < 0) {
				exch(dates, lt++, i++);
			} else { 
				if (cmp > 0) {
					exch(dates, i, gt--);
				} else {
					i++;
				}
			}
		}
		quick(dates, low, lt-1);
		quick(dates, gt+1, high);
	}
	
	/*
	private static int partition(Comparable[] dates, int low, int high) {
		int i = low, j = high + 1;
		Comparable  v = dates[low];
		while(true) {
			while (less(dates[++i], v)) {
				if (i == high) break;
			}
			while (less(v, dates[--j])) {
				if (j == low) break;
			}
			if (i >= j) break;
			exch(dates, i, j);
		}
		exch(dates, low, j);
		return j;
	}
	
	*/
	
	private static boolean less(Comparable v, Comparable w) {
		return v.compareTo(w) < 0;
	}
	
	private static void exch(Comparable[] dates, int i, int j) {
		Comparable t = dates[i];
		dates[i] = dates[j];
		dates[j] = t;
	}
	
	// The shuffle code was found on the internet
	private static void shuffle(Comparable[] array) {
		int n = array.length;
		for (int i = 0; i < array.length; i++) {
		    int random = i + (int) (Math.random() * (n - i));
		    Comparable randomElement = array[random];
		    array[random] = array[i];
		    array[i] = randomElement;
		}
	}
}
