package budget;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.geometry.VPos;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.PieChart;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.TilePane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.paint.CycleMethod;
import javafx.scene.paint.LinearGradient;
import javafx.scene.paint.Stop;
import javafx.scene.Node;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import javafx.stage.Stage;

import java.io.*;
import java.util.*;

import org.apache.commons.lang3.ArrayUtils;

import com.opencsv.*;


public class GUI extends Application {
	
	private Stage stage;
	
	
	public static void main(String[] args) throws IOException, FileNotFoundException {
		
		Date time1 = new Date(27,11,1761);
		Date time2 = new Date(12,10, 1832);
		Date time3 = new Date(2,3,818);
		Date[] array1 = {time1,time2,time3};
		
	
		
		Date time4 = new Date(27,11,1761);
		Date time5 = new Date(12,10, 1832);
		Date time6 = new Date(2,3,818);
		Date time7 = new Date(4,6,915);
		Date time8 = new Date(5,9,1762);
		Date time9 = new Date(7,12,1432);
		Date time10 = new Date(4,6, 1834);
		Date[] array2 = {time4,time5,time6,time7,time8,time9,time10};
		
		
				
		
		Purchase ach1 = new Purchase(new Item("coffee",1.80),Category.FOOD,time4);
		Purchase ach2 = new Purchase(new Item("gas",40.80),Category.TRANSPORTATION,time5);
		Purchase ach3 = new Purchase(new Item("book",34.72),Category.ENTERTAINMENT,time6);
		Purchase ach4 = new Purchase(new Item("cd",21.39),Category.ENTERTAINMENT,time4);
		Purchase ach5 = new Purchase(new Item("monkey",1.80),Category.ESSENTIALS,time4);
		Purchase ach6 = new Purchase(new Item("hai",40.80),Category.OTHER,time5);
		Purchase ach7 = new Purchase(new Item("burp",34.72),Category.SCHOOL,time6);
		Purchase ach8 = new Purchase(new Item("blah",65.43),Category.ENTERTAINMENT,time4);
		Purchase[] array3 = {ach1,ach2,ach3,ach4,ach5,ach6,ach7,ach8};
				
		
		Budget budg = new Budget(40,60,70,30,20,40,90,25);
		
		DataStorage store = new DataStorage(budg,budg);
		
		
		DataStorage.concatPur(array3);
		
		
		
		
		launch(args);
    }

	@Override
    public void start(Stage primaryStage) throws IOException, FileNotFoundException {

		stage = primaryStage;
		Scene scene = homeScene();
	    primaryStage.setScene(scene);
        primaryStage.setTitle("Budget Facilitator");
        primaryStage.setHeight(700);
        primaryStage.setWidth(700);
        primaryStage.show();
    }
	
	public Scene homeScene() throws IOException, FileNotFoundException {
		BorderPane border = new BorderPane();
        HBox hbox = addHBox();
        Text scenetitle1 = new Text("Welcome Back!");
        scenetitle1.setFill(Color.WHITE);
        scenetitle1.setFont(Font.font("Arial", FontWeight.NORMAL, 30));
        scenetitle1.setTextAlignment(TextAlignment.CENTER);
        hbox.getChildren().addAll(scenetitle1);
        border.setTop(hbox);
        border.setLeft(addVBox());
        border.setCenter(addGridPane());
        
        DataStorage.updateSpent();
        return new Scene(border);
	}
	
	
	public Scene welcomeScene() throws IOException, FileNotFoundException {
		BorderPane border = new BorderPane();
        HBox hbox = addHBox();
        Text scenetitle1 = new Text("Welcome To Your Personal Budget Facilitator");
        scenetitle1.setFill(Color.WHITE);
        scenetitle1.setFont(Font.font("Arial", FontWeight.NORMAL, 30));
        scenetitle1.setTextAlignment(TextAlignment.CENTER);
        hbox.getChildren().addAll(scenetitle1);
        GridPane grid = new GridPane();
        border.setTop(hbox);
        border.setCenter(grid);
        grid.setHgap(10);
        grid.setVgap(10);
        grid.setPadding(new Insets(50, 10, 0, 10));
        
        Button buttonHome = new Button("Home");
        buttonHome.setPrefSize(100, 20);
        buttonHome.setOnAction(new EventHandler<ActionEvent>() {
        	@Override public void handle(ActionEvent e) {
        		try {
					stage.setScene(homeScene());
				} catch (FileNotFoundException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
        	}
        });
        
        Text budgetText = new Text("Set Up Your Budget");
        budgetText.setFont(Font.font("Arial", FontWeight.BOLD, 25));
        grid.add(budgetText, 0, 0, 3, 1);
        
        Label clothes = new Label("Clothes");
        grid.add(clothes, 1,1);
        TextField clothesTextField = new TextField();
        grid.add(clothesTextField, 2,1);
        Label ent = new Label("Entertainment");
        grid.add(ent, 1, 2);
        TextField entTextField = new TextField();
        grid.add(entTextField,2,2);
        Label essen = new Label("Essentials");
        grid.add(essen,1,3);
        TextField essenTextField = new TextField();
        grid.add(essenTextField,2, 3);
        Label food = new Label("Food");
        grid.add(food, 1, 4);
        TextField foodTextField = new TextField();
        grid.add(foodTextField,2,4);
        Label other = new Label("Other");
        grid.add(other,1,5);
        TextField otherTextField = new TextField();
        grid.add(otherTextField,2,5);
        Label school = new Label("School");
        grid.add(school, 1, 6);
        TextField schoolTextField = new TextField();
        grid.add(schoolTextField,2,6);
        Label trans = new Label("Transportation");
        grid.add(trans, 1, 7);
        TextField transTextField = new TextField();
        grid.add(transTextField, 2, 7);
        Label util = new Label("Utilities");
        grid.add(util, 1, 8);
        TextField utilTextField = new TextField();
        grid.add(utilTextField, 2, 8);
        
        Button buttonSubmit = new Button("Submit");
        buttonSubmit.setPrefSize(100, 20);
        buttonSubmit.setOnAction(new EventHandler<ActionEvent>() {
        	@Override public void handle(ActionEvent e) {
        		CharSequence clothesText = clothesTextField.getCharacters();
                String clothesString = clothesText.toString();
                CharSequence entText = entTextField.getCharacters();
                String entString = entText.toString();
                CharSequence essenText = essenTextField.getCharacters();
                String essenString = essenText.toString();
                CharSequence foodText = foodTextField.getCharacters();
                String foodString = foodText.toString();
                CharSequence otherText = otherTextField.getCharacters();
                String otherString = otherText.toString();
                CharSequence schoolText = schoolTextField.getCharacters();
                String schoolString = schoolText.toString();
                CharSequence transText = transTextField.getCharacters();
                String transString = transText.toString();
                CharSequence utilText = utilTextField.getCharacters();
                String utilString = utilText.toString();
                
                Budget goal = new Budget(Double.parseDouble(transString),
                		Double.parseDouble(entString),
                		Double.parseDouble(foodString),
                		Double.parseDouble(utilString),
                		Double.parseDouble(schoolString),
                		Double.parseDouble(clothesString),
                		Double.parseDouble(essenString),
                		Double.parseDouble(otherString)
                		);
                Budget current = new Budget(0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0);
                
					try {
						new DataStorage(goal,current);
						stage.setScene(homeScene());
					} catch (IOException e2) {
						// TODO Auto-generated catch block
						e2.printStackTrace();
					}
        	}
        	
        });
        grid.add(buttonSubmit,0,3);
        
        grid.add(buttonHome,0,0);
       
        Scene scene = new Scene(border);
	    return scene;
	}
	
	
	public Scene pieScene() throws IOException, FileNotFoundException {
	    ObservableList<PieChart.Data> pieChartData = FXCollections.observableArrayList(
	    		new PieChart.Data("Transportation", calcCat()[1]),
	    		new PieChart.Data("Entertainment", calcCat()[0]),
	            new PieChart.Data("Food", calcCat()[2]),
	            new PieChart.Data("Essentials", calcCat()[4]),
	            new PieChart.Data("Utilities", calcCat()[3]),
	            new PieChart.Data("School", calcCat()[6]),
	            new PieChart.Data("Clothes", calcCat()[5]),
	    		new PieChart.Data("Other", calcCat()[7]));
	    final PieChart chart = new PieChart(pieChartData);
	    chart.setTitle("Monthly Spending");
	    
	    Button buttonHome = new Button("Home");
        buttonHome.setPrefSize(100, 20);
        buttonHome.setOnAction(new EventHandler<ActionEvent>() {
        	@Override public void handle(ActionEvent e) {
        		try {
					stage.setScene(homeScene());
				} catch (FileNotFoundException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
        	}
        });
       
        BorderPane border = new BorderPane();
        HBox hbox = addHBox();
        Text scenetitle1 = new Text("Categories");
        scenetitle1.setFill(Color.WHITE);
        scenetitle1.setFont(Font.font("Arial", FontWeight.NORMAL, 30));
        scenetitle1.setTextAlignment(TextAlignment.CENTER);
        hbox.getChildren().addAll(scenetitle1);
        GridPane grid = new GridPane();
        border.setTop(hbox);
        border.setCenter(grid);
        grid.setHgap(10);
        grid.setVgap(10);
        grid.setPadding(new Insets(50, 10, 0, 10));
        grid.add(buttonHome,0,0);
        grid.add(chart,0,1,2,3);
       
        Scene scene = new Scene(border);
	    return scene;
	}

	public Scene lineScene() throws IOException, FileNotFoundException {
        //defining the axes
        final NumberAxis xAxis = new NumberAxis();
        final NumberAxis yAxis = new NumberAxis();
        xAxis.setLabel("Month");
        yAxis.setLabel("Spending ($)");
        //creating the chart
        final LineChart<Number,Number> lineChart = 
                new LineChart<Number,Number>(xAxis,yAxis);
                
        lineChart.setTitle("Yearly Total Spending");
        //defining a series
        XYChart.Series series1 = new XYChart.Series();
        XYChart.Series series2 = new XYChart.Series();
        series1.setName("Actual Spending");
        series2.setName("Projected Spending");
        
        //populating the series1 with data
        for (int i = 1; i < calcSpending().length; i++) {
        	series1.getData().add(new XYChart.Data(i,calcSpending()[i]));
        	series2.getData().add(new XYChart.Data(i, calcSpending()[0]));
        }
      
        lineChart.getData().addAll(series1,series2);
        
        Button buttonHome = new Button("Home");
        buttonHome.setPrefSize(100, 20);
        buttonHome.setOnAction(new EventHandler<ActionEvent>() {
        	@Override public void handle(ActionEvent e) {
        		try {
					stage.setScene(homeScene());
				} catch (FileNotFoundException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
        	}
        });
       
        BorderPane border = new BorderPane();
        HBox hbox = addHBox();
        Text scenetitle1 = new Text("Budget");
        scenetitle1.setFill(Color.WHITE);
        scenetitle1.setFont(Font.font("Arial", FontWeight.NORMAL, 30));
        scenetitle1.setTextAlignment(TextAlignment.CENTER);
        hbox.getChildren().addAll(scenetitle1);
        GridPane grid = new GridPane();
        border.setTop(hbox);
        border.setCenter(grid);
        grid.setHgap(10);
        grid.setVgap(10);
        grid.setPadding(new Insets(50, 10, 0, 10));
        grid.add(lineChart,0,1,2,3);
        grid.add(buttonHome,0,0);
        
        //grid.setGridLinesVisible(true);
        
        Scene scene  = new Scene(border);
       
        return scene;
	}
	
	/*
	 * View sorted date range
	 */
	
	public Scene byDateScene() throws IOException, FileNotFoundException {
		BorderPane border = new BorderPane();
        HBox hbox = addHBox();
        Text scenetitle1 = new Text("Dates");
        scenetitle1.setFill(Color.WHITE);
        scenetitle1.setFont(Font.font("Arial", FontWeight.NORMAL, 30));
        scenetitle1.setTextAlignment(TextAlignment.CENTER);
        hbox.getChildren().addAll(scenetitle1);
        GridPane grid = new GridPane();
        border.setTop(hbox);
        border.setCenter(grid);
        grid.setHgap(30);
        grid.setVgap(10);
        grid.setPadding(new Insets(50, 10, 0, 10));
      
        
        Button buttonHome = new Button("Home");
        buttonHome.setPrefSize(100, 20);
        buttonHome.setOnAction(new EventHandler<ActionEvent>() {
        	@Override public void handle(ActionEvent e) {
        		try {
					stage.setScene(homeScene());
				} catch (FileNotFoundException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
        	}
        });
        grid.add(buttonHome,0,0);
          
        
        TextField day1TextField = new TextField();
        TextField month1TextField = new TextField();
        TextField year1TextField = new TextField();
        TextField day2TextField = new TextField();
        TextField month2TextField = new TextField();
        TextField year2TextField = new TextField();
        
        Label beginDate = new Label("Begin Date (dd/mm/yyyy)");
        grid.add(beginDate,0 , 1);
        
        Label endDate = new Label("End Date (dd/mm/yyyy)");
        grid.add(endDate, 0, 2);

        
        day1TextField.setPrefWidth(40);
        grid.add(day1TextField, 1, 1);
        
        Label slash1 = new Label("/");
        grid.add(slash1,2,1);
        
        
        month1TextField.setPrefWidth(40);
        grid.add(month1TextField, 3, 1);
        
        Label slash2 = new Label("/");
        grid.add(slash2,4 , 1);
        
        
        year1TextField.setPrefWidth(60);
        grid.add(year1TextField,5, 1);
        
        day2TextField.setPrefWidth(40);
        grid.add(day2TextField, 1, 2);
        
        Label slash3 = new Label("/");
        grid.add(slash3,2,2);
        
        
        month2TextField.setPrefWidth(40);
        grid.add(month2TextField, 3, 2);
        
        Label slash4 = new Label("/");
        grid.add(slash4,4 , 2);
        
        
        year2TextField.setPrefWidth(60);
        grid.add(year2TextField, 5, 2);
        
        Button buttonSubmit = new Button("Submit");
        buttonSubmit.setPrefSize(100, 20);
        buttonSubmit.setOnAction(new EventHandler<ActionEvent>() {
        	@Override public void handle(ActionEvent e) {
        		CharSequence day1Text = day1TextField.getCharacters();
                String day1String = day1Text.toString();
                CharSequence day2Text = day2TextField.getCharacters();
                String day2String = day2Text.toString();
                CharSequence month1Text = month1TextField.getCharacters();
                String month1String = month1Text.toString();
                CharSequence month2Text = month2TextField.getCharacters();
                String month2String = month2Text.toString();
                CharSequence year1Text = year1TextField.getCharacters();
                String year1String = year1Text.toString();
                CharSequence year2Text = year2TextField.getCharacters();
                String year2String = year2Text.toString();
                
                int day1 = Integer.parseInt(day1String);
                int month1 = Integer.parseInt(month1String);
                int year1 = Integer.parseInt(year1String);
                int day2 = Integer.parseInt(day2String);
                int month2 = Integer.parseInt(month2String);
                int year2 = Integer.parseInt(year2String);
                
                Date begin = new Date(day1,month1,year1);
                Date end = new Date(day2,month2,year2);
                
                
					try {
						Purchase[] purs = DataStorage.readDate(begin,end);
						stage.setScene(dateTableScene(purs));
					} catch (IOException e2) {
						// TODO Auto-generated catch block
						e2.printStackTrace();
					}
        	}
        	
        });
        grid.add(buttonSubmit,0,3);
        
        
        Scene scene  = new Scene(border);
        
        return scene;
	}
	
	public Scene dateTableScene(Purchase[] purs) throws IOException, FileNotFoundException {
		BorderPane border = new BorderPane();
        HBox hbox = addHBox();
        Text scenetitle1 = new Text("Dates");
        scenetitle1.setFill(Color.WHITE);
        scenetitle1.setFont(Font.font("Arial", FontWeight.NORMAL, 30));
        scenetitle1.setTextAlignment(TextAlignment.CENTER);
        hbox.getChildren().addAll(scenetitle1);
        GridPane grid = new GridPane();
        border.setTop(hbox);
        border.setCenter(grid);
        grid.setHgap(30);
        grid.setVgap(10);
        grid.setPadding(new Insets(50, 10, 0, 10));
      
        
        Button buttonHome = new Button("Home");
        buttonHome.setPrefSize(100, 20);
        buttonHome.setOnAction(new EventHandler<ActionEvent>() {
        	@Override public void handle(ActionEvent e) {
        		try {
					stage.setScene(homeScene());
				} catch (FileNotFoundException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
        	}
        });
        grid.add(buttonHome,0,0);
        
        
      //Column Names
        Text priceTitle = new Text("Price");
        priceTitle.setFont(Font.font("Arial", FontWeight.NORMAL, 20));
        grid.add(priceTitle, 1, 1);
        Text nameTitle = new Text("Name");
        nameTitle.setFont(Font.font("Arial", FontWeight.NORMAL, 20));
        grid.add(nameTitle, 2, 1);
        Text catTitle = new Text("Category");
        catTitle.setFont(Font.font("Arial", FontWeight.NORMAL, 20));
        grid.add(catTitle, 3, 1);
        Text dateTitle = new Text("Date");
        dateTitle.setFont(Font.font("Arial", FontWeight.NORMAL, 20));
        grid.add(dateTitle, 4, 1);
        
        for (int i = 0; i < purs.length; i++) {
        	Label price = new Label("" + purs[i].getItem().getPrice());
        	grid.add(price, 1, i+2);
        	Label name = new Label(purs[i].getItem().getName());
        	grid.add(name,2,i+2);
        	Label cat = new Label(purs[i].getCategory().toString());
        	grid.add(cat,3,i+2);
        	Label dates = new Label(purs[i].getDate().toString());
        	grid.add(dates,4,i+2);
        }
        
        
        Scene scene  = new Scene(border);
        
        return scene;
	}
	
	/*
	 * View all your purchases
	 */
	
	public Scene byPurchaseScene() throws IOException, FileNotFoundException {
		BorderPane border = new BorderPane();
        HBox hbox = addHBox();
        Text scenetitle1 = new Text("Purchases");
        scenetitle1.setFill(Color.WHITE);
        scenetitle1.setFont(Font.font("Arial", FontWeight.NORMAL, 30));
        scenetitle1.setTextAlignment(TextAlignment.CENTER);
        hbox.getChildren().addAll(scenetitle1);
        GridPane grid = new GridPane();
        border.setTop(hbox);
        border.setCenter(grid);
        grid.setHgap(10);
        grid.setVgap(10);
        grid.setPadding(new Insets(50, 10, 0, 10));
        
        Button buttonHome = new Button("Home");
        buttonHome.setPrefSize(100, 20);
        buttonHome.setOnAction(new EventHandler<ActionEvent>() {
        	@Override public void handle(ActionEvent e) {
        		try {
					stage.setScene(homeScene());
				} catch (FileNotFoundException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
        	}
        });
        grid.add(buttonHome,0,0);
        
        Text priceTitle = new Text("Price");
        priceTitle.setFont(Font.font("Arial", FontWeight.NORMAL, 20));
        grid.add(priceTitle, 1, 1);
        Text nameTitle = new Text("Name");
        nameTitle.setFont(Font.font("Arial", FontWeight.NORMAL, 20));
        grid.add(nameTitle, 2, 1);
        Text catTitle = new Text("Category");
        catTitle.setFont(Font.font("Arial", FontWeight.NORMAL, 20));
        grid.add(catTitle, 3, 1);
        Text dateTitle = new Text("Date");
        dateTitle.setFont(Font.font("Arial", FontWeight.NORMAL, 20));
        grid.add(dateTitle, 4, 1);
        
        Purchase[] trans = DataStorage.readCategory(Category.TRANSPORTATION);
        Purchase[] ent = DataStorage.readCategory(Category.ENTERTAINMENT);
        Purchase[] essen = DataStorage.readCategory(Category.ESSENTIALS);
        Purchase[] clothes = DataStorage.readCategory(Category.CLOTHES);
        Purchase[] food = DataStorage.readCategory(Category.FOOD);
        Purchase[] other = DataStorage.readCategory(Category.OTHER);
        Purchase[] util = DataStorage.readCategory(Category.UTILITIES);
        Purchase[] school = DataStorage.readCategory(Category.SCHOOL);
        
        Purchase[] purs = ArrayUtils.addAll(trans,ent);
        purs = ArrayUtils.addAll(purs, essen);
        purs = ArrayUtils.addAll(purs,clothes);
        purs = ArrayUtils.addAll(purs,food);
        purs = ArrayUtils.addAll(purs,other);
        purs = ArrayUtils.addAll(purs,util);
        purs = ArrayUtils.addAll(purs,school);
        
        Merge.mergeSort(purs);
        
        for (int i = 0; i < purs.length; i++) {
        	Label price = new Label("" + purs[i].getItem().getPrice());
        	grid.add(price, 1, i+2);
        	Label name = new Label(purs[i].getItem().getName());
        	grid.add(name,2,i+2);
        	Label cat = new Label(purs[i].getCategory().toString());
        	grid.add(cat,3,i+2);
        	Label dates = new Label(purs[i].getDate().toString());
        	grid.add(dates,4,i+2);
        }
        
     //   grid.setGridLinesVisible(true);
        
        Scene scene  = new Scene(border);
        
        return scene;
	}
	
	public Scene byCategoryScene() throws IOException, FileNotFoundException {
		BorderPane border = new BorderPane();
        HBox hbox = addHBox();
        Text scenetitle1 = new Text("Categories");
        scenetitle1.setFill(Color.WHITE);
        scenetitle1.setFont(Font.font("Arial", FontWeight.NORMAL, 30));
        scenetitle1.setTextAlignment(TextAlignment.CENTER);
        hbox.getChildren().addAll(scenetitle1);
        GridPane grid = new GridPane();
        border.setTop(hbox);
        border.setCenter(grid);
        grid.setHgap(10);
        grid.setVgap(10);
        grid.setPadding(new Insets(50, 10, 0, 10));
        
        Button buttonHome = new Button("Home");
        buttonHome.setPrefSize(100, 20);
        buttonHome.setOnAction(new EventHandler<ActionEvent>() {
        	@Override public void handle(ActionEvent e) {
        		try {
					stage.setScene(homeScene());
				} catch (FileNotFoundException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
        	}
        });
        grid.add(buttonHome,0,0);
        
      //Category Buttons
        ComboBox catBox = new ComboBox();
        catBox.getItems().addAll(
        		"CLOTHES",
        		"ENTERTAINMENT",
        		"ESSENTIALS",
        		 "FOOD",
        		 "OTHER",
        		 "SCHOOL",
        		 "TRANSPORTATION",
        		 "UTILITIES");
        catBox.setPromptText("Category");
        grid.add(catBox, 0, 1,1,1);
        
        Button buttonSubmit = new Button("Submit");
        buttonSubmit.setPrefSize(100, 20);
        buttonSubmit.setOnAction(new EventHandler<ActionEvent>() {
        	@Override public void handle(ActionEvent e) {
        		String categoryString = catBox.getValue().toString();
        		Category cat = Category.valueOf(categoryString);
					try {
						Purchase[] purs = DataStorage.readCategory(cat);
						stage.setScene(dateTableScene(purs));
					} catch (IOException e2) {
						// TODO Auto-generated catch block
						e2.printStackTrace();
					}
        	}
        	
        });
        grid.add(buttonSubmit,0,3);
        
        
        Scene scene  = new Scene(border);
        
        return scene;
	}
	
	public Scene categoryTableScene(Purchase[] purs) throws IOException, FileNotFoundException {
		BorderPane border = new BorderPane();
        HBox hbox = addHBox();
        Text scenetitle1 = new Text(purs[0].getCategory().toString());
        scenetitle1.setFill(Color.WHITE);
        scenetitle1.setFont(Font.font("Arial", FontWeight.NORMAL, 30));
        scenetitle1.setTextAlignment(TextAlignment.CENTER);
        hbox.getChildren().addAll(scenetitle1);
        GridPane grid = new GridPane();
        border.setTop(hbox);
        border.setCenter(grid);
        grid.setHgap(30);
        grid.setVgap(10);
        grid.setPadding(new Insets(50, 10, 0, 10));
      
        
        Button buttonHome = new Button("Home");
        buttonHome.setPrefSize(100, 20);
        buttonHome.setOnAction(new EventHandler<ActionEvent>() {
        	@Override public void handle(ActionEvent e) {
        		try {
					stage.setScene(homeScene());
				} catch (FileNotFoundException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
        	}
        });
        grid.add(buttonHome,0,0);
        
        
      //Column Names
        Text priceTitle = new Text("Price");
        priceTitle.setFont(Font.font("Arial", FontWeight.NORMAL, 20));
        grid.add(priceTitle, 1, 1);
        Text nameTitle = new Text("Name");
        nameTitle.setFont(Font.font("Arial", FontWeight.NORMAL, 20));
        grid.add(nameTitle, 2, 1);
        Text catTitle = new Text("Category");
        catTitle.setFont(Font.font("Arial", FontWeight.NORMAL, 20));
        grid.add(catTitle, 3, 1);
        Text dateTitle = new Text("Date");
        dateTitle.setFont(Font.font("Arial", FontWeight.NORMAL, 20));
        grid.add(dateTitle, 4, 1);
        
        for (int i = 0; i < purs.length; i++) {
        	Label price = new Label("" + purs[i].getItem().getPrice());
        	grid.add(price, 1, i+2);
        	Label name = new Label(purs[i].getItem().getName());
        	grid.add(name,2,i+2);
        	Label cat = new Label(purs[i].getCategory().toString());
        	grid.add(cat,3,i+2);
        	Label dates = new Label(purs[i].getDate().toString());
        	grid.add(dates,4,i+2);
        }
        
        
        Scene scene  = new Scene(border);
        
        return scene;
	}
	
	public Scene addScene() throws IOException, FileNotFoundException {
		BorderPane border = new BorderPane();
        HBox hbox = addHBox();
        Text scenetitle1 = new Text("Add Purchase");
        scenetitle1.setFill(Color.WHITE);
        scenetitle1.setFont(Font.font("Arial", FontWeight.NORMAL, 30));
        scenetitle1.setTextAlignment(TextAlignment.CENTER);
        hbox.getChildren().addAll(scenetitle1);
        GridPane grid = new GridPane();
        border.setTop(hbox);
        border.setCenter(grid);
        grid.setHgap(10);
        grid.setVgap(10);
        grid.setPadding(new Insets(50, 10, 0, 10));
        
        Button buttonHome = new Button("Home");
        buttonHome.setPrefSize(100, 20);
        buttonHome.setOnAction(new EventHandler<ActionEvent>() {
        	@Override public void handle(ActionEvent e) {
        		try {
					stage.setScene(homeScene());
				} catch (FileNotFoundException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
        	}
        });
        grid.add(buttonHome,0,0);
        
        TextField priceTextField = new TextField();
        TextField itemTextField = new TextField();
        TextField dayTextField = new TextField();
        TextField monthTextField = new TextField();
        TextField yearTextField = new TextField();
        
        TextField[] input = new TextField[5];
        input[0] = priceTextField;
        input[1] = itemTextField;
        input[2] = dayTextField;
        input[3] = monthTextField;
        input[4] = yearTextField;
                
        Label searchPrice = new Label("Price ($XX.XX)");
        grid.add(searchPrice, 0, 1);

        
        priceTextField.setPrefWidth(80);
        grid.add(priceTextField, 1, 1,3,1);
        
        Label searchItem = new Label("Item");
        grid.add(searchItem, 0, 2);

       
        itemTextField.setPrefWidth(80);
        grid.add(itemTextField, 1, 2,3,1);
        
        //Category Buttons
        ComboBox cat = new ComboBox();
        cat.getItems().addAll(
        		"CLOTHES",
        		"ENTERTAINMENT",
        		"ESSENTIALS",
        		 "FOOD",
        		 "OTHER",
        		 "SCHOOL",
        		 "TRANSPORTATION",
        		 "UTILITIES");
        cat.setPromptText("Category");
        grid.add(cat, 0, 3,1,1);
        
        Label searchDate = new Label("Date (dd/mm/yyyy)");
        grid.add(searchDate,0 , 4);

        
        dayTextField.setPrefWidth(40);
        grid.add(dayTextField, 1, 4);
        
        Label slash1 = new Label("/");
        grid.add(slash1,2,4);
        
        
        monthTextField.setPrefWidth(40);
        grid.add(monthTextField, 3, 4);
        
        Label slash2 = new Label("/");
        grid.add(slash2,4 , 4);
        
        
        yearTextField.setPrefWidth(40);
        grid.add(yearTextField, 5, 4);
        
        
        Button buttonSubmit = new Button("Submit");
        buttonSubmit.setPrefSize(100, 20);
        buttonSubmit.setOnAction(new EventHandler<ActionEvent>() {
        	@Override public void handle(ActionEvent e) {
        		CharSequence priceText = priceTextField.getCharacters();
                String priceString = priceText.toString();
                CharSequence itemText = itemTextField.getCharacters();
                String itemString = itemText.toString();
                String categoryString = cat.getValue().toString();
                CharSequence dayText = dayTextField.getCharacters();
                String dayString = dayText.toString();
                CharSequence monthText = monthTextField.getCharacters();
                String monthString = monthText.toString();
                CharSequence yearText = yearTextField.getCharacters();
                String yearString = yearText.toString();
                
                double price = Double.parseDouble(priceString);
                Category cat = Category.valueOf(categoryString);
                int day = Integer.parseInt(dayString);
                int month = Integer.parseInt(monthString);
                int year = Integer.parseInt(yearString);
                
                Purchase[] pur = new Purchase[1];
                pur[0] = new Purchase(new Item(itemString,price),cat,new Date(day,month,year));
                
					try {
						DataStorage.concatPur(pur);
						stage.setScene(homeScene());
					} catch (IOException e2) {
						// TODO Auto-generated catch block
						e2.printStackTrace();
					}
        	}
        	
        });
        grid.add(buttonSubmit,0,6);
        
  
        
        Scene scene  = new Scene(border);
        
       // grid.setGridLinesVisible(true);
        return scene;
	}
	
	public Scene removeScene() throws IOException, FileNotFoundException {
		BorderPane border = new BorderPane();
        HBox hbox = addHBox();
        Text scenetitle1 = new Text("Categories");
        scenetitle1.setFill(Color.WHITE);
        scenetitle1.setFont(Font.font("Arial", FontWeight.NORMAL, 30));
        scenetitle1.setTextAlignment(TextAlignment.CENTER);
        hbox.getChildren().addAll(scenetitle1);
        GridPane grid = new GridPane();
        border.setTop(hbox);
        border.setCenter(grid);
        grid.setHgap(10);
        grid.setVgap(10);
        grid.setPadding(new Insets(50, 10, 0, 10));
        
        Button buttonHome = new Button("Home");
        buttonHome.setPrefSize(100, 20);
        buttonHome.setOnAction(new EventHandler<ActionEvent>() {
        	@Override public void handle(ActionEvent e) {
        		try {
					stage.setScene(homeScene());
				} catch (FileNotFoundException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
        	}
        });
        grid.add(buttonHome,0,0);
                
        Label searchPrice = new Label("Price ($XX.XX)");
        grid.add(searchPrice, 0, 1);

        TextField priceTextField = new TextField();
        priceTextField.setPrefWidth(80);
        grid.add(priceTextField, 1, 1,3,1);
        
        Label searchItem = new Label("Item");
        grid.add(searchItem, 0, 2);

        TextField itemTextField = new TextField();
        itemTextField.setPrefWidth(80);
        grid.add(itemTextField,1, 2,3,1);
        
        //Category Buttons
        ComboBox cat = new ComboBox();
        cat.getItems().addAll(
        		"CLOTHES",
        		"ENTERTAINMENT",
        		"ESSENTIALS",
        		 "FOOD",
        		 "OTHER",
        		 "SCHOOL",
        		 "TRANSPORTATION",
        		 "UTILITIES");
        cat.setPromptText("Category");
        grid.add(cat, 0, 3);
        
        Label searchDate = new Label("Date (dd/mm/yyyy)");
        grid.add(searchDate,0 , 4);

        TextField dayTextField = new TextField();
        dayTextField.setPrefWidth(40);
        grid.add(dayTextField, 1, 4);
        Label slash1 = new Label("/");
        grid.add(slash1,2 , 4);
        TextField monthTextField = new TextField();
        monthTextField.setPrefWidth(40);
        grid.add(monthTextField, 3, 4);
        Label slash2 = new Label("/");
        grid.add(slash2,4 , 4);
        TextField yearTextField = new TextField();
        yearTextField.setPrefWidth(60);
        grid.add(yearTextField, 5, 4);
        
        Button buttonSubmit = new Button("Submit");
        buttonSubmit.setPrefSize(100, 20);
        buttonSubmit.setOnAction(new EventHandler<ActionEvent>() {
        	@Override public void handle(ActionEvent e) {
        		CharSequence priceText = priceTextField.getCharacters();
                String priceString = priceText.toString();
                CharSequence itemText = itemTextField.getCharacters();
                String itemString = itemText.toString();
                String categoryString = cat.getValue().toString();
                CharSequence dayText = dayTextField.getCharacters();
                String dayString = dayText.toString();
                CharSequence monthText = monthTextField.getCharacters();
                String monthString = monthText.toString();
                CharSequence yearText = yearTextField.getCharacters();
                String yearString = yearText.toString();
                
                double price = Double.parseDouble(priceString);
                Category cat = Category.valueOf(categoryString);
                int day = Integer.parseInt(dayString);
                int month = Integer.parseInt(monthString);
                int year = Integer.parseInt(yearString);
                
                Purchase pur = new Purchase();
                pur = new Purchase(new Item(itemString,price),cat,new Date(day,month,year));
                
					try {
						DataStorage.remove(pur);
						stage.setScene(homeScene());
					} catch (IOException e2) {
						// TODO Auto-generated catch block
						e2.printStackTrace();
					}
        	}
        	
        });
        grid.add(buttonSubmit,0,6);
        
        
        Scene scene  = new Scene(border);
        
        return scene;
	}
	
	public Scene searchPurchaseScene() throws IOException, FileNotFoundException {
		BorderPane border = new BorderPane();
        HBox hbox = addHBox();
        Text scenetitle1 = new Text("Categories");
        scenetitle1.setFill(Color.WHITE);
        scenetitle1.setFont(Font.font("Arial", FontWeight.NORMAL, 30));
        scenetitle1.setTextAlignment(TextAlignment.CENTER);
        hbox.getChildren().addAll(scenetitle1);
        GridPane grid = new GridPane();
        border.setTop(hbox);
        border.setCenter(grid);
        grid.setHgap(10);
        grid.setVgap(10);
        grid.setPadding(new Insets(50, 10, 0, 10));
        
        Button buttonHome = new Button("Home");
        buttonHome.setPrefSize(100, 20);
        buttonHome.setOnAction(new EventHandler<ActionEvent>() {
        	@Override public void handle(ActionEvent e) {
        		try {
					stage.setScene(homeScene());
				} catch (FileNotFoundException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
        	}
        });
        grid.add(buttonHome,0,0);
        
        Label searchPrice = new Label("Price ($XX.XX)");
        grid.add(searchPrice, 0, 1);

        TextField priceTextField = new TextField();
        priceTextField.setPrefWidth(40);
        grid.add(priceTextField, 1, 1);
        
        Label searchItem = new Label("Item");
        grid.add(searchItem, 0, 2);

        TextField itemTextField = new TextField();
        itemTextField.setPrefWidth(40);
        grid.add(itemTextField, 1, 2);
        
        //Category Buttons
        ComboBox cat = new ComboBox();
        cat.getItems().addAll(
        		"CLOTHES",
        		"ENTERTAINMENT",
        		"ESSENTIALS",
        		 "FOOD",
        		 "OTHER",
        		 "SCHOOL",
        		 "TRANSPORTATION",
        		 "UTILITIES");
        cat.setPromptText("Category");
        grid.add(cat, 0, 3);
        
        
        Button buttonSubmit = new Button("Submit");
        buttonSubmit.setPrefSize(100, 20);
        buttonSubmit.setOnAction(new EventHandler<ActionEvent>() {
        	@Override public void handle(ActionEvent e) {
        		CharSequence priceText = priceTextField.getCharacters();
                String priceString = priceText.toString();
                CharSequence itemText = itemTextField.getCharacters();
                String itemString = itemText.toString();
                String categoryString = cat.getValue().toString();
                
                double price = Double.parseDouble(priceString);
                Category cat = Category.valueOf(categoryString);
                
                Item item = new Item(itemString,price);  
                
					try {
						
						stage.setScene(searchedScene(new Purchase(item,cat,DataStorage.bin.get(item))));
					} catch (IOException e2) {
						// TODO Auto-generated catch block
						e2.printStackTrace();
					}
        	}
        	
        });
        grid.add(buttonSubmit,0,6);
        
               
        //grid.setGridLinesVisible(true);
        
        Scene scene  = new Scene(border);
        
        return scene;
		
	}
	
	public Scene searchedScene(Purchase pur) throws IOException, FileNotFoundException {
		BorderPane border = new BorderPane();
        HBox hbox = addHBox();
        Text scenetitle1 = new Text("Search By Date");
        scenetitle1.setFill(Color.WHITE);
        scenetitle1.setFont(Font.font("Arial", FontWeight.NORMAL, 30));
        scenetitle1.setTextAlignment(TextAlignment.CENTER);
        hbox.getChildren().addAll(scenetitle1);
        GridPane grid = new GridPane();
        border.setTop(hbox);
        border.setCenter(grid);
        grid.setHgap(10);
        grid.setVgap(10);
        grid.setPadding(new Insets(50, 10, 0, 10));
        
        Button buttonHome = new Button("Home");
        buttonHome.setPrefSize(100, 20);
        buttonHome.setOnAction(new EventHandler<ActionEvent>() {
        	@Override public void handle(ActionEvent e) {
        		try {
					stage.setScene(homeScene());
				} catch (FileNotFoundException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
        	}
        });
        grid.add(buttonHome,0,0);
        
        Text priceTitle = new Text("Price");
        priceTitle.setFont(Font.font("Arial", FontWeight.NORMAL, 20));
        grid.add(priceTitle, 1, 1);
        Text nameTitle = new Text("Name");
        nameTitle.setFont(Font.font("Arial", FontWeight.NORMAL, 20));
        grid.add(nameTitle, 2, 1);
        Text catTitle = new Text("Category");
        catTitle.setFont(Font.font("Arial", FontWeight.NORMAL, 20));
        grid.add(catTitle, 3, 1);
        Text dateTitle = new Text("Date");
        dateTitle.setFont(Font.font("Arial", FontWeight.NORMAL, 20));
        grid.add(dateTitle, 4, 1);

        Label price = new Label("" + pur.getItem().getPrice());
    	grid.add(price, 1, 2);
    	Label name = new Label(pur.getItem().getName());
    	grid.add(name,2,2);
    	Label cat = new Label(pur.getCategory().toString());
    	grid.add(cat,3,2);
    	Label dates = new Label(pur.getDate().toString());
    	grid.add(dates,4,2);
        
        Scene scene = new Scene(border);
        
        return scene;
	}
	
	public Scene searchDateScene() throws IOException, FileNotFoundException {
		BorderPane border = new BorderPane();
        HBox hbox = addHBox();
        Text scenetitle1 = new Text("Search By Date");
        scenetitle1.setFill(Color.WHITE);
        scenetitle1.setFont(Font.font("Arial", FontWeight.NORMAL, 30));
        scenetitle1.setTextAlignment(TextAlignment.CENTER);
        hbox.getChildren().addAll(scenetitle1);
        GridPane grid = new GridPane();
        border.setTop(hbox);
        border.setCenter(grid);
        grid.setHgap(10);
        grid.setVgap(10);
        grid.setPadding(new Insets(50, 10, 0, 10));
        
        Button buttonHome = new Button("Home");
        buttonHome.setPrefSize(100, 20);
        buttonHome.setOnAction(new EventHandler<ActionEvent>() {
        	@Override public void handle(ActionEvent e) {
        		try {
					stage.setScene(homeScene());
				} catch (FileNotFoundException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
        	}
        });
        grid.add(buttonHome,0,0);
        
        Label searchDate = new Label("Date (dd/mm/yyyy)");
        grid.add(searchDate,0 , 1);

        TextField dayTextField = new TextField();
        dayTextField.setPrefWidth(40);
        grid.add(dayTextField, 1, 1);
        Label slash1 = new Label("/");
        grid.add(slash1,2 , 1);
        TextField monthTextField = new TextField();
        monthTextField.setPrefWidth(40);
        grid.add(monthTextField, 3, 1);
        Label slash2 = new Label("/");
        grid.add(slash2,4 , 1);
        TextField yearTextField = new TextField();
        yearTextField.setPrefWidth(40);
        grid.add(yearTextField, 5, 1);
        
        Button buttonSubmit = new Button("Submit");
        buttonSubmit.setPrefSize(100, 20);
        buttonSubmit.setOnAction(new EventHandler<ActionEvent>() {
        	@Override public void handle(ActionEvent e) {
        		CharSequence dayText = dayTextField.getCharacters();
                String dayString = dayText.toString();
                
                CharSequence monthText = monthTextField.getCharacters();
                String monthString = monthText.toString();
                
                CharSequence yearText = yearTextField.getCharacters();
                String yearString = yearText.toString();
                
                
                int day = Integer.parseInt(dayString);
                int month = Integer.parseInt(monthString);
                int year = Integer.parseInt(yearString);
                
                
                Date date = new Date(day,month,year);
                
                try {
					Purchase[] output = DataStorage.readDate(date,date);
					stage.setScene(dateTableScene(output));
				} catch (FileNotFoundException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
        	}
        });
        grid.add(buttonSubmit,0,6);
        
      //  grid.setGridLinesVisible(true);
        
        Scene scene  = new Scene(border);
        
        return scene;
		
	}
	
/*
 * Creates an HBox with two buttons for the top region
 */
    
    private HBox addHBox() throws IOException, FileNotFoundException {

        HBox hbox = new HBox();
        hbox.setPadding(new Insets(15, 12, 15, 12));
        hbox.setStyle("-fx-background-color: #CC0000;");        
        return hbox;
    }
    
/*
 * Creates a VBox with a list of links for the left region
 */
    private VBox addVBox() throws IOException, FileNotFoundException {
        
        VBox vbox = new VBox();
        vbox.setPadding(new Insets(10)); // Set all sides to 10
        vbox.setSpacing(8);              // Gap between nodes

        Text title = new Text("Review");
        title.setFont(Font.font("Arial", FontWeight.BOLD, 14));
        vbox.getChildren().add(title);
        
        Hyperlink options[] = new Hyperlink[] {
            new Hyperlink("Purchases"),
            new Hyperlink("Dates"),
        	new Hyperlink("Category")};
        
        options[0].setTextFill(Color.RED);
        options[0].setOnAction(new EventHandler<ActionEvent>() {
        	@Override public void handle(ActionEvent e) {
        		try {
					stage.setScene(byPurchaseScene());
				} catch (FileNotFoundException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
        	}
        });
        
        options[1].setTextFill(Color.RED);
        options[1].setOnAction(new EventHandler<ActionEvent>() {
        	@Override public void handle(ActionEvent e) {
        		try {
					stage.setScene(byDateScene());
				} catch (FileNotFoundException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
        	}
        });
        
        options[2].setTextFill(Color.RED);
        options[2].setOnAction(new EventHandler<ActionEvent>() {
        	@Override public void handle(ActionEvent e) {
        		try {
					stage.setScene(byCategoryScene());
				} catch (FileNotFoundException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
        	}
        });

        for (int i=0; i<3; i++) {
            // Add offset to left side to indent from title
            VBox.setMargin(options[i], new Insets(0, 0, 0, 8));
            vbox.getChildren().add(options[i]);
        }
        
        return vbox;
    }


    private GridPane addGridPane() throws IOException, FileNotFoundException {

        GridPane grid = new GridPane();
        grid.setHgap(10);
        grid.setVgap(10);
        grid.setPadding(new Insets(50, 10, 0, 10));
        
        Text category = new Text("What would you like to do?");
        category.setFont(Font.font("Arial", FontWeight.BOLD, 25));
        grid.add(category, 0, 0, 3, 1);
        
        Button buttonAdd = new Button("Add Purchase");
        buttonAdd.setPrefSize(140, 20);
        buttonAdd.setOnAction(new EventHandler<ActionEvent>() {
        	@Override public void handle(ActionEvent e) {
        		try {
					stage.setScene(addScene());
				} catch (FileNotFoundException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
        	}
        });
        grid.add(buttonAdd, 0, 1,1,1);
        
        Button buttonRemove = new Button("Remove Purchase");
        buttonRemove.setPrefSize(140, 20);
        buttonRemove.setOnAction(new EventHandler<ActionEvent>() {
        	@Override public void handle(ActionEvent e) {
        		try {
					stage.setScene(removeScene());
				} catch (FileNotFoundException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
        	}
        });
        grid.add(buttonRemove, 2, 1,3,1);
       
        
        /*
         * Buttons that show charts
         */
        
        Text sort = new Text("Compare");
        sort.setFont(Font.font("Arial", FontWeight.BOLD, 15));
        grid.add(sort, 0, 4);
        
        Button buttonBudget = new Button("Budget");
        buttonBudget.setPrefSize(140, 20);
        buttonBudget.setOnAction(new EventHandler<ActionEvent>() {
        	@Override public void handle(ActionEvent e) {
        		try {
					stage.setScene(lineScene());
				} catch (FileNotFoundException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
        	}
        });
        grid.add(buttonBudget, 0, 5);
        
        Button buttonCategory = new Button("Category");
        buttonCategory.setPrefSize(140, 20);
        buttonCategory.setOnAction(new EventHandler<ActionEvent>() {
        	@Override public void handle(ActionEvent e) {
        		try {
					stage.setScene(pieScene());
				} catch (FileNotFoundException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
        	}
        });
        grid.add(buttonCategory, 0, 6);
        
        
        /*
         * Searching Options
         */
        
        Text search = new Text("Search");
        search.setFont(Font.font("Arial", FontWeight.BOLD, 15));
        grid.add(search, 2, 4);
        
        Button buttonSearchDate = new Button("Date");
        buttonSearchDate.setPrefSize(140, 20);
        buttonSearchDate.setOnAction(new EventHandler<ActionEvent>() {
        	@Override public void handle(ActionEvent e) {
        		try {
					stage.setScene(searchDateScene());
				} catch (FileNotFoundException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
        	}
        });
        grid.add(buttonSearchDate, 2, 5);
        
        Button buttonSearchPurchase = new Button("Purchase");
        buttonSearchPurchase.setPrefSize(140, 20);
        buttonSearchPurchase.setOnAction(new EventHandler<ActionEvent>() {
        	@Override public void handle(ActionEvent e) {
        		try {
					stage.setScene(searchPurchaseScene());
				} catch (FileNotFoundException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
        	}
        });
        grid.add(buttonSearchPurchase, 2, 6);

        
        /*
         * Clear History button
         */
        
        Button buttonClear = new Button("Clear History");
        buttonClear.setPrefSize(120, 20);
        buttonClear.setOnAction(new EventHandler<ActionEvent>() {
        	@Override public void handle(ActionEvent e) {
        		try {
					DataStorage.clearHistory();
				} catch (FileNotFoundException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
        	}
        });
        grid.add(buttonClear, 1, 7);
        
        //grid.setGridLinesVisible(true);
        return grid;
    }
	
	private Double[] calcCat() throws IOException, FileNotFoundException {
		Purchase[] ent = DataStorage.readCategory(Category.ENTERTAINMENT); 
		Purchase[] trans = DataStorage.readCategory(Category.TRANSPORTATION);
		Purchase[] util = DataStorage.readCategory(Category.UTILITIES);
		Purchase[] essen = DataStorage.readCategory(Category.ESSENTIALS);
		Purchase[] food = DataStorage.readCategory(Category.FOOD);
		Purchase[] cloth = DataStorage.readCategory(Category.CLOTHES);
		Purchase[] school = DataStorage.readCategory(Category.SCHOOL);
		Purchase[] other = DataStorage.readCategory(Category.OTHER);
		double entInt = 0, transInt = 0, utilInt = 0, essenInt = 0, foodInt = 0, clothInt = 0,
				schoolInt = 0, otherInt = 0;
		Double[] output = new Double[8];
		for (int i = 0; i < ent.length; i++) {
			entInt = ent[i].getItem().getPrice() + entInt;
		}
		for (int i = 0; i < trans.length; i++) {
			transInt = trans[i].getItem().getPrice() + transInt;
		}
		for (int i = 0; i < util.length; i++) {
			utilInt = util[i].getItem().getPrice() + utilInt;
		}
		for (int i = 0; i < food.length; i++) {
			foodInt = food[i].getItem().getPrice() + foodInt;
		}
		for (int i = 0; i < essen.length; i++) {
			essenInt = essen[i].getItem().getPrice() + essenInt;
		}
		for (int i = 0; i < cloth.length; i++) {
			clothInt = cloth[i].getItem().getPrice() + clothInt;
		}
		for (int i = 0; i < school.length; i++) {
			schoolInt = school[i].getItem().getPrice() + schoolInt;
		}
		for (int i = 0; i < other.length; i++) {
			otherInt = other[i].getItem().getPrice() + otherInt;
		}
		double tot = entInt + transInt + foodInt + utilInt + essenInt + clothInt + schoolInt + otherInt;
		double entOut = entInt/tot, transOut = transInt/tot, utilOut = utilInt/tot, essenOut = essenInt/tot,
				foodOut = foodInt/tot, clothOut = clothInt/tot, schoolOut = schoolInt/tot, otherOut = otherInt/tot;
		output[0] = entOut;
		output[1] = transOut;
		output[2] = foodOut;
		output[3] = utilOut;
		output[4] = essenOut;
		output[5] = clothOut;
		output[6] = schoolOut;
		output[7] = otherOut;
		
		return output;		
	}
 
	 private Double[] calcSpending() throws IOException, FileNotFoundException {
	    	Purchase[] purs = DataStorage.readDate(new Date(0,0,0), new Date(0,0,2015));
	    	Double[] dbl = new Double[purs.length + 1];
	    	double tot = 0;
	    	for (int i = 1; i < purs.length + 1; i++) {
	    		tot = tot + purs[i-1].getItem().getPrice();
	    		dbl[i] = tot; 
	    	}
	    	dbl[0] = 76.0;
	    	return dbl;
	    }
	
}