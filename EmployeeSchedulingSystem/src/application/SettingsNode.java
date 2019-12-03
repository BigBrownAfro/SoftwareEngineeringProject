package application;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.TilePane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.paint.CycleMethod;
import javafx.scene.paint.LinearGradient;
import javafx.scene.paint.Stop;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.scene.control.TextField;

public class SettingsNode extends StackPane {

	Employee employee;
	double width;
	double height;
	TilePane buttonPane;
	TilePane EmployeePane;
	Setup setup;
	
	SettingsNode(double width, double height){
		this.width = width;
		this.height = height;
		setup = new Setup();
		
		//Color stuff thanks Elijah :)
		Stop[] stops = new Stop[] {
				new Stop(0, Color.rgb(180, 130, 130)),
				new Stop(1, Color.rgb(150, 100, 150))
		};
		LinearGradient gradient = new LinearGradient(0, 0, 0, 1, true, CycleMethod.NO_CYCLE, stops);
		
		//create a vBox which we will insert the top, middle, and bottom content into
		VBox vBox = new VBox();
				
		//setup vBox size and background color
		vBox.setMinSize(width, height);
		vBox.setBackground(new Background(new BackgroundFill(gradient, null, null)));
		
		//add the vBox the settings node's children
		this.getChildren().add(vBox);
		
		//Build the top of the calendar in the first slot of the vBox
		this.buildButtons(vBox);
		
		
	}
	
	private void buildButtons(VBox vBox) {
		
		//makes the HBox and adds spacing stuff
		HBox buttonBox = new HBox();
		buttonBox.setPadding(new Insets(5,5,5,5));
		buttonBox.setSpacing(5);
		
		//makes the button
		Button addButton = new Button("Add Employee");
		addButton.setPrefSize(width/8.0 - 5, 50);
		buttonBox.getChildren().add(addButton);
		
		//makes the button
		Button workButton = new Button("Company Work Days");
		workButton.setPrefSize(width/8.0 - 5, 50);
		
		//adds editButton to the box
		buttonBox.getChildren().add(workButton);
		
		//makes the button
		Button timeButton = new Button("Company Work Times");
		timeButton.setPrefSize(width/8.0 - 5, 50);
		
		//adds editButton to the box
		buttonBox.getChildren().add(timeButton);
		
		//makes the button
		//Button editTimeButton = new Button("Edit Shift Time");
		//editTimeButton.setPrefSize(width/8.0 - 5, 50);
		
		//adds editTimeButton to the box
		//buttonBox.getChildren().add(editTimeButton);
		
		//makes the button
		Button makeButton = new Button("Create Schedule");
		makeButton.setPrefSize(width/8.0 - 5, 50);
		
		//adds editTimeButton to the box
		buttonBox.getChildren().add(makeButton);

		//puts the Hbox in the Vbox 
		vBox.getChildren().add(buttonBox);
		
		//Making add employee button open add employee menu
		addButton.setOnAction(new EventHandler<ActionEvent>() {
		    @Override public void handle(ActionEvent e) {
		    	addEmployeeMenu(vBox);
		    }
		});
		
		//Making company work days button open work days menu
		workButton.setOnAction(new EventHandler<ActionEvent>() {
		    @Override public void handle(ActionEvent e) {
		    	workDaysMenu(vBox);
		    }
		});
		
		//Making company work times button open work times menu
		timeButton.setOnAction(new EventHandler<ActionEvent>() {
		    @Override public void handle(ActionEvent e) {
		    	workTimesMenu(vBox);
		    }
		});
	}
	
	private void addEmployeeMenu(VBox vBox) {
		
		
		//makes the HBox and adds spacing stuff
		VBox EmployeeBox = new VBox();
		
		EmployeeBox.setPadding(new Insets(0,5,0,5));
		EmployeeBox.setSpacing(5);

		//Sets up first name field 
		TextField fnameField = new TextField();
		fnameField.setMaxSize(160, 20);
		fnameField.setPromptText("Enter employee first name.");
		
		//adds fname to employeebox
		EmployeeBox.getChildren().add(fnameField);
		
		//Sets up middle name field 
		TextField mnameField = new TextField();
		mnameField.setMaxSize(100, 20);
		mnameField.setPromptText("Middle Initial");
		
		EmployeeBox.getChildren().add(mnameField);
		
		//Sets up last name field 
		TextField lnameField = new TextField();
		lnameField.setMaxSize(160, 20);
		lnameField.setPromptText("Enter employee last name.");
		
		EmployeeBox.getChildren().add(lnameField);
		
		//Sets up last name field 
		TextField idField = new TextField();
		idField.setMaxSize(50, 20);
		idField.setPromptText("ID#");
		
		EmployeeBox.getChildren().add(idField);
		
		//Sets up days they can work field 
		TextField daysField = new TextField();
		daysField.setMaxSize(250, 20);
		daysField.setPromptText("Days to work: Ex: 'Monday, Tuesday'");
		
		EmployeeBox.getChildren().add(daysField);
		
		//Sets up times they can work field 
		TextField timeField = new TextField();
		timeField.setMaxSize(250, 20);
		timeField.setPromptText("Times to work in military: Ex: '0000, 1000'");
		
		EmployeeBox.getChildren().add(timeField);
		
		//makes the button
		Button confButton = new Button("Confirm Employee");
		confButton.setPrefSize(width/8.0 - 5, 50);
		
		EmployeeBox.getChildren().add(confButton);
		
		//puts the Hbox in the VBox
		vBox.getChildren().add(EmployeeBox);
		
		//saves employee
		confButton.setOnAction(new EventHandler<ActionEvent>() {
		    @Override public void handle(ActionEvent e) {
		    	
		    	//turns the text into a string array
		    	String num = timeField.getText();
		    	String str[] = num.split(",");
		    	
		    	//turns string array into ints
		    	int tp1 = Integer.parseInt(str[0]);
		    	int tp2 = Integer.parseInt(str[1]);
		    	
		    	//turns ints into a time period
		    	TimePeriod timep = new TimePeriod(tp1, tp2);
		    	
		    	//adds the time period to the employees time period array
		    	ArrayList<TimePeriod> tp = new ArrayList<TimePeriod>();
		    	tp.add(timep);
		    	
		    	//makes the days into a array list string
		    	ArrayList<String> days = new ArrayList<String>();
		    	days.addAll(Arrays.asList(daysField.getText()));
		    	
		    	employee = new Employee(fnameField.getText() , lnameField.getText() , mnameField.getText().charAt(0), Integer.parseInt(idField.getText()), tp, days);
		    	EmployeeBox.getChildren().clear();
		    	addEmployeeMenu(vBox);
		    }
		});
	}
	
	private void workDaysMenu(VBox vBox) {
		
		//makes the HBox and adds spacing stuff
		HBox daysBox = new HBox();
		
		daysBox.setPadding(new Insets(0,5,0,5));
		daysBox.setSpacing(5);
		
		//Sets up first name field 
		TextField daysField = new TextField();
		daysField.setPrefSize(350, 20);
		daysField.setPromptText("Enter the work days of the company: ex: 'Monday,Tuesday'");
				
		//adds daysField to daysBSox
		daysBox.getChildren().add(daysField);
		
		//makes the button
		Button confButton = new Button("Confirm Days");
		confButton.setPrefSize(width/8.0 - 5, 50);
				
		daysBox.getChildren().add(confButton);
				
		//puts the VBox in the VBox
		vBox.getChildren().add(daysBox);
		
		//saves work days
		confButton.setOnAction(new EventHandler<ActionEvent>() {
			@Override public void handle(ActionEvent e) {
		    	
				//makes the days into a array list string
				ArrayList<String> days = new ArrayList<String>();
				days.addAll(Arrays.asList(daysField.getText()));
				days = setup.workDays;
		
				daysBox.getChildren().clear();
				workDaysMenu(vBox);
			 }
		});
	}
	
	private void workTimesMenu(VBox vBox) {
		
		//makes the HBox and adds spacing stuff
		HBox timesBox = new HBox();
		
		timesBox.setPadding(new Insets(0,5,0,5));
		timesBox.setSpacing(5);
		
		//Sets up first name field 
		TextField timeField = new TextField();
		timeField.setPrefSize(350, 20);
		timeField.setPromptText("Enter the shift times of the company in military: ex: '0000,1000'");
				
		//adds daysField to daysBSox
		timesBox.getChildren().add(timeField);
		
		//makes the button
		Button confButton = new Button("Confirm Times");
		confButton.setPrefSize(width/8.0 - 5, 50);
				
		timesBox.getChildren().add(confButton);
				
		//puts the VBox in the VBox
		vBox.getChildren().add(timesBox);
		
		//saves work days
		confButton.setOnAction(new EventHandler<ActionEvent>() {
			@Override public void handle(ActionEvent e) {
				
				//creates TimePeriod ArrayList. Epic.
		    	ArrayList<TimePeriod> tp = new ArrayList<TimePeriod>();
		    	
				//turns the text into a string array
		    	String num = timeField.getText();
		    	String str[] = num.split(",");
		    	
		    	//turns string array into ints
		    	for(int i=0; i<str.length; i++) {
		    		int[] a = new int[str.length];
		    		a[i] = Integer.parseInt(str[i]);
		    		if(i%2==1) {
		    			//turns ints into a time period and add it to the TimePeriod list
		    			TimePeriod timep = new TimePeriod(a[i-1], a[i]);
				    	tp.add(timep);
		    		}
		    	}
				
		    	//adds the time period to the employees time period array
		    	tp = setup.staticShifts;
		
				timesBox.getChildren().clear();
				workTimesMenu(vBox);
			 }
		});
	}
	
}
