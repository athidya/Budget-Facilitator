package budget;


import java.io.*;
import java.util.*;

import com.opencsv.*;

public class DataStorage {
	public static BinarySearch<Item,Date> bin;
	public String userName;
	
	/*
	 * This stores the budget you have set for yourself in the csv file
	 */
	
	public DataStorage(Budget goal,Budget current) throws IOException {
		
		CSVWriter writer = new CSVWriter(new FileWriter("Data/storage.csv"), '\t');
		String [] data = new String[9];
		data[0] = String.valueOf(goal.getTransportation());
		data[1] = String.valueOf(goal.getEntertainment());
		data[2] = String.valueOf(goal.getFood());
		data[3] = String.valueOf(goal.getUtilities());
		data[4] = String.valueOf(goal.getSchool());
		data[5] = String.valueOf(goal.getClothes());
		data[6] = String.valueOf(goal.getEssentials());
		data[7] = String.valueOf(goal.getOther());
		data[8] = String.valueOf(goal.getTotal());
		writer.writeNext(data);		
		writer.close();
		initCurrent(current);
		bin = new BinarySearch();
	}
	
	
	private void initCurrent(Budget cur) throws IOException {
		CSVWriter writer = new CSVWriter(new FileWriter("Data/storage.csv",true), '\t');
		String [] data = new String[9];
		data[0] = String.valueOf(cur.getTransportation());
		data[1] = String.valueOf(cur.getEntertainment());
		data[2] = String.valueOf(cur.getFood());
		data[3] = String.valueOf(cur.getUtilities());
		data[4] = String.valueOf(cur.getSchool());
		data[5] = String.valueOf(cur.getClothes());
		data[6] = String.valueOf(cur.getEssentials());
		data[7] = String.valueOf(cur.getOther());
		data[8] = String.valueOf(cur.getTotal());
		writer.writeNext(data);
		
		writer.close();
	}
	
	
	/*
	 * This updates your current spending by by adding up all of the purchase information
	 */
	
	public static void updateSpent() throws IOException, FileNotFoundException{
		CSVReader read = new CSVReader(new FileReader("Data/storage.csv"),',','"', 0);
		List<String[]> temp = read.readAll();		
		read.close();
		
		String[] array = temp.get(0)[0].split("[\"]");
		String[] output = new String[9];
		output[0] = array[0];
		output[1] = array[2];
		output[2] = array[4];
		output[3] = array[6];
		output[4] = array[8];
		output[5] = array[10];
		output[6] = array[12];
		output[7] = array[14];
		output[8] = array[16];
		temp.remove(0);
		temp.add(0,output);
		
		Budget budg = addPurchasesTo();
		String [] data1 = new String[9];
		data1[0] = String.valueOf(budg.getTransportation());
		data1[1] = String.valueOf(budg.getEntertainment());
		data1[2] = String.valueOf(budg.getFood());
		data1[3] = String.valueOf(budg.getUtilities());
		data1[4] = String.valueOf(budg.getSchool());
		data1[5] = String.valueOf(budg.getClothes());
		data1[6] = String.valueOf(budg.getEssentials());
		data1[7] = String.valueOf(budg.getOther());
		data1[8] = String.valueOf(budg.getTotal());
		temp.remove(1);
		temp.add(1,data1);
		
		
		bin = new BinarySearch();
		
		for (int i = 2; i < temp.size(); i++) {
			Purchase tempPur = new Purchase();
			String[] newString = splitter(temp.get(i));
			tempPur.setItem(new Item(newString[1],Double.parseDouble(newString[0])));
			String[] purDate = newString[3].split("/");
			tempPur.setDate(new Date(Integer.parseInt(purDate[0]),Integer.parseInt(purDate[1]), Integer.parseInt(purDate[2])));
			bin.put(tempPur.getItem(),tempPur.getDate());
			temp.remove(i);
			temp.add(i,newString);
		}
		
			
		CSVWriter writer = new CSVWriter(new FileWriter("Data/storage.csv"), '\t');
		
		writer.writeAll(temp);
		writer.close();
	}
	
	
	/*
	 * Adds your purchases to the csv file
	 */

	public static void concatPur(Purchase[] pur) throws IOException {
		CSVWriter writer = new CSVWriter(new FileWriter("Data/storage.csv", true), '\t');
		String [] data = new String[4];
		for (int i = 0; i < pur.length; i++) {
			data[0] = String.valueOf(pur[i].getItem().getPrice());
			data[1] = pur[i].getItem().getName();
			data[2] = pur[i].getCategory().toString();
			data[3] = pur[i].getDate().toString();
			writer.writeNext(data);
			bin.put(pur[i].getItem(),pur[i].getDate());
		}
		writer.close();
		updateSpent();
	}
	
	/*
	 * Given a range of dates the method will output an array of purchases
	 * corresponding to that range
	 */
	
	public static Purchase[] readDate(Date beg, Date end) throws FileNotFoundException, IOException {
		CSVReader reader = new CSVReader(new FileReader("Data/storage.csv"),',','"', 2);
		List<String[]> list = reader.readAll();
		List<String[]> tempList = new ArrayList<String[]>();
		String[] helper;
		Purchase tempPur = new Purchase();
		Date tempDate;
		for (int i = 0; i < list.size(); i++) {
			helper = splitter(list.get(i));
			String[] purDate = helper[3].split("/");
			tempDate = new Date(Integer.parseInt(purDate[0]),Integer.parseInt(purDate[1]), Integer.parseInt(purDate[2]));
			if (tempDate.compareTo(beg) >= 0 && tempDate.compareTo(end) <= 0 ) {
				tempList.add(list.get(i));
			}
		}
		Purchase[] output = new Purchase[tempList.size()];
		for (int j = 0; j < tempList.size(); j++) {
			helper = splitter(tempList.get(j));
			tempPur.setItem(new Item(helper[1],Double.parseDouble(helper[0])));
			tempPur.setCategory(Category.valueOf(helper[2]));
			String[] purDate = helper[3].split("/");
			tempPur.setDate(new Date(Integer.parseInt(purDate[0]),Integer.parseInt(purDate[1]), Integer.parseInt(purDate[2])));
			output[j] = new Purchase(tempPur.getItem(),tempPur.getCategory(),tempPur.getDate());
			
		}
		reader.close();
		Merge.mergeSort(output);
		return output;
	}
	
	/*
	 * Given a category type will out put all the purchases for that category
	 */
	
	public static Purchase[] readCategory(Category cat) throws IOException, FileNotFoundException{
		CSVReader reader = new CSVReader(new FileReader("Data/storage.csv"),',','"', 2);
		List<String[]> list = reader.readAll();
		List<String[]> tempList = new ArrayList<String[]>();
		String[] helper;
		Purchase tempPur = new Purchase();
		Category tempCat;
		for (int i = 0; i < list.size(); i++) {
			helper = splitter(list.get(i));
			tempCat = Category.valueOf(helper[2]);
			if (tempCat == cat ) {
				tempList.add(list.get(i));
			}
		}
		Purchase[] output = new Purchase[tempList.size()];
		for (int j = 0; j < tempList.size(); j++) {
			helper = splitter(tempList.get(j));
			Double.parseDouble(helper[0]);
			tempPur.setItem(new Item(helper[1],Double.parseDouble(helper[0])));
			tempPur.setCategory(Category.valueOf(helper[2]));
			String[] purDate = helper[3].split("/");
			tempPur.setDate(new Date(Integer.parseInt(purDate[0]),Integer.parseInt(purDate[1]), Integer.parseInt(purDate[2])));
			output[j] = new Purchase(tempPur.getItem(),tempPur.getCategory(),tempPur.getDate());
			
		}
		reader.close();
		Merge.mergeSort(output);
		return output;
	
	}
	
	public static void remove(Purchase pur) throws IOException, FileNotFoundException {
		
		CSVReader read1 = new CSVReader(new FileReader("Data/storage.csv"),',','"', 0);
		CSVWriter writer = new CSVWriter(new FileWriter("Data/tempStorage.csv"), '\t');
		
		String[] currentLine;
		Purchase tempPur = new Purchase();
		
		for (int i = 0; i < 2; i++) {
			String[] array = read1.readNext()[0].split("[\"]");
			String[] output = new String[9];
			output[0] = array[0];
			output[1] = array[2];
			output[2] = array[4];
			output[3] = array[6];
			output[4] = array[8];
			output[5] = array[10];
			output[6] = array[12];
			output[7] = array[14];
			output[8] = array[16];
			writer.writeNext(output);
		}
		
		while((currentLine = read1.readNext()) != null) {
			String[] purString = currentLine[0].split("[\"]");
			System.out.println(purString[0]);
			tempPur.setItem(new Item(purString[2],Double.parseDouble(purString[0])));
			tempPur.setCategory(Category.valueOf(purString[4]));
			String[] purDate = purString[6].split("/");
			tempPur.setDate(new Date(Integer.parseInt(purDate[0]),Integer.parseInt(purDate[1]), Integer.parseInt(purDate[2])));
			if (pur.equals(tempPur)) {	
				continue;
			} else {
				String[] tempString = {purString[0],purString[2],purString[4],purString[6]};
				writer.writeNext(tempString);
			}
		}
		
		File file1 = new File("Data/storage.csv");
		File file2 = new File("Data/tempStorage.csv");
		file2.renameTo(file1);
		
		read1.close();
		writer.close();
		
		bin.delete(pur.getItem());
		
	}
	
	/*
	 * Removes all stored data
	 */
	
	public static void clearHistory() throws IOException, FileNotFoundException {
		String[] mt = {null, null, null, null,null,null,null,null,null}; 
		CSVReader read = new CSVReader(new FileReader("Data/storage.csv"),',','"', 0);
		CSVIterator it = new CSVIterator(read);
		CSVWriter writer = new CSVWriter(new FileWriter("Data/storage.csv"), '\t');
		while (it.next() != mt) {
			writer.writeNext(mt);
		}
		writer.close();
		read.close();
	}
	
	
	/*
	 * Adds together all the purchases to add to your current spending
	 */
	 
	
	private static Budget addPurchasesTo() throws FileNotFoundException, IOException {
		int i = 0, j = 0;
		CSVReader read1 = new CSVReader(new FileReader("Data/storage.csv"),',','"', 2);
		CSVIterator it1 = new CSVIterator(read1);
		
		while (it1.hasNext() == true && it1.next() != null) {
			i++;
		}
		read1.close();
		
		Budget holder = new Budget();
		Purchase[] purs = new Purchase[i];
		for (int m = 0; m < i; m++) {
			purs[m] = new Purchase();
		}
		
		CSVReader read2 = new CSVReader(new FileReader("Data/storage.csv"),',','"', 2);
		CSVReader read3 = new CSVReader(new FileReader("Data/storage.csv"),',','"', 2);
		CSVIterator it2 = new CSVIterator(read2);
		
		while (it2.next() != null) {
			String[] temp = read3.readNext();
			String[] purString = temp[0].split("[\"]");
			purs[j].setItem(new Item(purString[2],Double.parseDouble(purString[0])));
			purs[j].setCategory(Category.valueOf(purString[4]));
			String[] purDate = purString[6].split("/");
			purs[j].setDate(new Date(Integer.parseInt(purDate[0]),Integer.parseInt(purDate[1]), Integer.parseInt(purDate[2])));
			j = j + 1;
		}
		for (int k = 0; k < purs.length; k++) {
			if (purs[k].getCategory() == Category.TRANSPORTATION) {
				holder.setTransportation(holder.getTransportation() + purs[k].getItem().getPrice());
			}
			if (purs[k].getCategory() == Category.ENTERTAINMENT) {
				holder.setEntertainment(holder.getEntertainment() + purs[k].getItem().getPrice());
			}
			if (purs[k].getCategory() == Category.FOOD) {
				holder.setFood(holder.getFood() + purs[k].getItem().getPrice());
			}
			if (purs[k].getCategory() == Category.UTILITIES) {
				holder.setUtilities(holder.getUtilities() + purs[k].getItem().getPrice());
			}
			if (purs[k].getCategory() == Category.SCHOOL) {
				holder.setSchool(holder.getSchool() + purs[k].getItem().getPrice());
			}
			if (purs[k].getCategory() == Category.CLOTHES) {
				holder.setClothes(holder.getClothes() + purs[k].getItem().getPrice());
			}
			if (purs[k].getCategory() == Category.ESSENTIALS) {
				holder.setEssentials(holder.getEssentials() + purs[k].getItem().getPrice());
			}
			if (purs[k].getCategory() == Category.OTHER) {
				holder.setOther(holder.getOther() + purs[k].getItem().getPrice());
			}
		}
		read2.close();
		read3.close();
		return holder;
		
	}
	
	/*
	 * Compares your set budget to your current spending
	 */
	 
	
	public static int budgetCompare() throws IOException, FileNotFoundException {
		CSVReader read = new CSVReader(new FileReader("Data/storage.csv"));
		String[] array = read.readNext()[0].split("[\"]");
		String[] output1 = new String[9];
		String[] output2 = new String[9];
		output1[0] = array[0];
		output1[1] = array[2];
		output1[2] = array[4];
		output1[3] = array[6];
		output1[4] = array[8];
		output1[5] = array[10];
		output1[6] = array[12];
		output1[7] = array[14];
		output1[8] = array[16];
		output2[0] = array[0];
		output2[1] = array[2];
		output2[2] = array[4];
		output2[3] = array[6];
		output2[4] = array[8];
		output2[5] = array[10];
		output2[6] = array[12];
		output2[7] = array[14];
		output2[8] = array[16];
		Budget goal = new Budget(Double.parseDouble(output1[0]),Double.parseDouble(output1[1]),Double.parseDouble(output1[2]),Double.parseDouble(output1[3]),Double.parseDouble(output1[4]),Double.parseDouble(output1[5]),Double.parseDouble(output1[6]),Double.parseDouble(output1[7]));
		Budget current = new Budget(Double.parseDouble(output2[0]),Double.parseDouble(output2[1]),Double.parseDouble(output2[2]),Double.parseDouble(output2[3]),Double.parseDouble(output2[4]),Double.parseDouble(output2[5]),Double.parseDouble(output2[6]),Double.parseDouble(output2[7]));
		return 0;
	}
	
	
	private static String[] splitter(String[] array) {
		String[] temp = array[0].split("[\"]");
		String[] output = new String[(temp.length+1)/2];
		int j = 0;
		for (int i = 0; i < (temp.length+1)/2; i++) {
			output[i] = temp[j];
			j = j + 2;				
		}
		return output;
	}
	
}
	
	

