package budget;

import java.io.*;
import java.util.*;
import com.opencsv.*;
import budget.Item;

public class BinarySearch<Item extends Comparable<Item>, Date> {
	
	private Date[] dates;
	private Item[] items;
	private int N;
	private static final int initCapacity = 2;
	
	@SuppressWarnings("unchecked")
	public BinarySearch(int capacity) {
		
		items = (Item[]) new Comparable[capacity];
		dates = (Date[]) new Object[capacity];
	}
	
	public BinarySearch() {
		this(initCapacity);
	}

	public void put(Item item, Date date) {
		if (date == null) { 
			delete(item); 
			return; 
		}

        int i = rank(item);

        // key is already in table
        if (i < N && items[i].compareTo(item) == 0) {
            dates[i] = date;
            return;
        }

        // insert new key-value pair
        if (N == items.length) {
        	resize(2*items.length);
        }

        for (int j = N; j > i; j--)  {
            items[j] = items[j-1];
            dates[j] = dates[j-1];
        }
        items[i] = item;
        dates[i] = date;
        N++;

        assert check();
	}
	
	public Date get(Item item) {
		if (isEmpty()) {
			return null;
		}
        int i = rank(item); 
        if (i < N && items[i].compareTo(item) == 0) {
        	return dates[i];
        }
        return null;
	}
	
	public void delete(Item item) {
		if (isEmpty()) {
			return;
		}

        // compute rank
        int i = rank(item);

        // key not in table
        if (i == N || items[i].compareTo(item) != 0) {
            return;
        }

        for (int j = i; j < N-1; j++)  {
            items[j] = items[j+1];
            dates[j] = dates[j+1];
        }

        N--;
        items[N] = null;  // to avoid loitering
        dates[N] = null;

        // resize if 1/4 full
        if (N > 0 && N == items.length/4) resize(items.length/2);

        assert check();
	}
	
	public boolean contains(Item item) {
		return get(item) != null;
	}
	
	public boolean isEmpty() {
		return size() == 0;
	}
	
	public int size() {
		return N;
	}
	
	public Item min() {
		if (isEmpty()) {
			return null;
		}
        return items[0]; 
	}
	
	public Item max() {
		if (isEmpty()) {
			return null;
		}
        return items[N-1];
	}
	
	public Item floor(Item item) {
		int i = rank(item);
        if (i < N && item.compareTo(items[i]) == 0) {
        	return items[i];
        }
        if (i == 0) {
        	return null;
        }
        else {
        	return items[i-1];
        }
	}
	
	public Item ceiling(Item item) {
		int i = rank(item);
        if (i == N) {
        	return null; 
        }
        else {
        	return items[i];
        }
	}
	
	public int rank(Item item) {
		int lo = 0, hi = N-1; 
        while (lo <= hi) { 
            int m = lo + (hi - lo) / 2; 
            int cmp = item.compareTo(items[m]); 
            if      (cmp < 0) hi = m - 1; 
            else if (cmp > 0) lo = m + 1; 
            else return m; 
        } 
        return lo;
	}
	
	public Item select(int k) {
		if (k < 0 || k >= N) {
			return null;
		}
        return items[k];
	}
	
	public void deleteMin() {
		delete(min());
	}
	
	public void deleteMax() {
		delete(max());
	}
	
	public int size(Item lo, Item hi) {
		 if (lo.compareTo(hi) > 0) {
			 return 0;
		 }
	     if (contains(hi)) {
	    	 return rank(hi) - rank(lo) + 1;
	     } else {
	        	return rank(hi) - rank(lo);
	     }
	}
	
	public Iterable<Item> Items(Item lo, Item hi) {
		Queue<Item> queue = new ArrayDeque<Item>(); 
        if (lo == null && hi == null) return queue;
        if (lo == null) throw new NullPointerException("lo is null in keys()");
        if (hi == null) throw new NullPointerException("hi is null in keys()");
        if (lo.compareTo(hi) > 0) return queue;
        for (int i = rank(lo); i < rank(hi); i++) {
            queue.add(items[i]);
        }
        if (contains(hi)) queue.add(items[rank(hi)]);
        return queue;
	}
	
	public Iterable<Item> Items() {
		return Items(min(), max());
	}
	
	@SuppressWarnings("unchecked")
	private void resize (int newSize) {
		assert newSize >= N;
        Item[] tempP = (Item[])   new Comparable[newSize];
        Date[] tempD = (Date[]) new Object[newSize];
        for (int i = 0; i < N; i++) {
            tempP[i] = items[i];
            tempD[i] = dates[i];
        }
        items = tempP;
        dates = tempD;  
	}

	private boolean check() {
        return isSorted() && rankCheck();
    }
	
	private boolean isSorted() {
        for (int i = 1; i < size(); i++)
            if (items[i].compareTo(items[i-1]) < 0) {
            	return false;
            }
        return true;
    }
	
	private boolean rankCheck() {
        for (int i = 0; i < size(); i++)
            if (i != rank(select(i))) {
            	return false;
            }
        for (int i = 0; i < size(); i++)
            if (items[i].compareTo(select(rank(items[i]))) != 0) {
            	return false;
            }
        return true;
    }
}
