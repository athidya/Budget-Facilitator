package budget;

import java.io.*;

import javax.swing.JFrame;

import com.opencsv.CSVIterator;
import com.opencsv.CSVReader;

public class Client {

	public static void main(String[] args) throws IOException {
		Date time1 = new Date(27,11,1761);
		Date time2 = new Date(12,10, 1832);
		Date time3 = new Date(2,3,818);
		Date[] array1 = {time1,time2,time3};

		/*
		Quick.quick(array1);
		for (int i = 0; i < array1.length; i++) {
			System.out.println(array1[i].toString());
		}
		*/	
		
		Date time4 = new Date(27,11,1761);
		Date time5 = new Date(12,10, 1832);
		Date time6 = new Date(2,3,818);
		Date time7 = new Date(4,6,915);
		Date time8 = new Date(5,9,1762);
		Date time9 = new Date(7,12,1432);
		Date time10 = new Date(4,6, 1834);
		Date[] array2 = {time4,time5,time6,time7,time8,time9,time10};

		/*
		Merge.mergeSort(array2);
		for (int i = 0; i < array2.length; i++) {
			System.out.println(array2[i].toString());
		}
		*/
		
		
		Purchase ach1 = new Purchase(new Item("coffee",1.80),Category.FOOD,time4);
		Purchase ach2 = new Purchase(new Item("gas",40.80),Category.TRANSPORTATION,time5);
		Purchase ach3 = new Purchase(new Item("book",34.72),Category.ENTERTAINMENT,time6);
		Purchase ach4 = new Purchase(new Item("cd",21.39),Category.ENTERTAINMENT,time4);
		Purchase ach5 = new Purchase(new Item("monkey",1.80),Category.ESSENTIALS,time4);
		Purchase ach6 = new Purchase(new Item("hai",40.80),Category.OTHER,time5);
		Purchase ach7 = new Purchase(new Item("burp",34.72),Category.SCHOOL,time6);
		Purchase ach8 = new Purchase(new Item("blah",65.43),Category.ENTERTAINMENT,time4);
		Purchase[] array3 = {ach1,ach2,ach3,ach4,ach5,ach6,ach7,ach8};
		
		
		//BinarySearch bin = new BinarySearch(5);
		//bin.put(ach1, time4);
		//bin.put(ach2,time5);
		//bin.put(ach3,time6);
		//bin.put(ach4,time4);
		
		//System.out.println(bin.get(time5));
		//System.out.println(bin.size());
		//System.out.println(bin.get(ach3));
	
		
		
		/*
		Quick.quick(array3);
		for (int i = 0; i < array3.length; i++) {
			System.out.println(array3[i].toString());
		}
		*/
				
		
		Budget budg = new Budget(40,60,70,30,20,40,90,25);
		
		DataStorage store = new DataStorage(budg,budg);
		
		System.out.println(ach8.equals(ach7));
		System.out.println(ach8.equals(ach8));
		
		store.concatPur(array3);
		
		store.concatPur(array3);
		
		DataStorage.clearHistory();
		
		
		//store.remove(ach8);
		
		
		//Purchase[] herp = store.readDate(new Date(7,12,1432),new Date(12,10, 1832));
		//for (int i = 0; i < herp.length; i++) {
			//System.out.println(herp[i]);
		//}
		//Purchase[] derp = store.readCategory(Category.ENTERTAINMENT); 
		//for (int i = 0; i < derp.length; i++) {
			//System.out.println(derp[i]);
		//}
		
		//store.readDate(time1, time2);
		
	//	store.clearHistory();
	}
	
}
