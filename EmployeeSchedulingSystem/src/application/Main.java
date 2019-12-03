package application;
	
import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.paint.CycleMethod;
import javafx.scene.paint.LinearGradient;
import javafx.scene.paint.Stop;
import javafx.scene.shape.Rectangle;


public class Main extends Application {

	StackPane centerContent;
	CalendarNode calendarNode;
	Setup settings;
	Calendar calendar;
	
	@Override
	public void start(Stage primaryStage) { //primaryStage is the window
		try {
			settings = new Setup();
			calendar = new Calendar(settings);
			
			//Width and Height of scene window
			final int WIDTH = 1100;
			final int HIEGHT = 915; //was 892
			
			BorderPane rootNode = new BorderPane(); //The root of our nodes (views)
			
			//setup left panel
			VBox leftPanel = new VBox();
			leftPanel.setPrefSize(WIDTH/8.0, HIEGHT);
			leftPanel.setPadding(new Insets(5,5,5,5));
			leftPanel.setSpacing(5);
			
			//create settings button in left panel
			Button settingsButton = new Button("Settings");
			settingsButton.setPrefSize(WIDTH/8.0 - 5, 50);
			leftPanel.getChildren().add(settingsButton);
			

			Button calendarButton = new Button("Calendar");
			calendarButton.setPrefSize(WIDTH/8.0 - 5, 50);
			leftPanel.getChildren().add(calendarButton);
			
			Button generateButton = new Button("Generate");
			generateButton.setPrefSize(WIDTH/8.0 - 5, 50);
			leftPanel.getChildren().add(generateButton);
			


			Button outputButton = new Button("Output");
			outputButton.setPrefSize(WIDTH/8.0 - 5, 50);
			leftPanel.getChildren().add(outputButton);
			
			outputButton.setOnMouseClicked(event -> {
				outputToFile(calendar);
					
			});

			
			//Setting up a color gradient
			Stop[] stops = new Stop[] {
					//new Stop(0, Color.rgb(180, 130, 130)),
					//new Stop(1, Color.rgb(150, 100, 150))
					new Stop(0, Color.rgb(180, 130, 130)),
					new Stop(1, Color.rgb(150, 100, 150))
			};
			LinearGradient gradient = new LinearGradient(1, 0, 0, 1, true, CycleMethod.NO_CYCLE, stops);
			leftPanel.setBackground(new Background(new BackgroundFill(gradient, null, null)));
			
			//Create an hBox that has the left panel vBox and a rectangle in it to separate the left panel from the center content
			HBox hBox = new HBox();
			hBox.getChildren().add(leftPanel);
			hBox.getChildren().add(new Rectangle(1, HIEGHT, Color.BLACK));
			
			//set the HBox as the left side content
			rootNode.setLeft(hBox);
			
			//setup center content
			calendarNode = new CalendarNode(calendar, WIDTH * 7.0/8.0, HIEGHT); //Homemade, see CalendarNode
			centerContent = calendarNode;
			rootNode.setCenter(centerContent);
			
			//Making settings button open SettingsNode
			settingsButton.setOnAction(new EventHandler<ActionEvent>() {
			    @Override public void handle(ActionEvent e) {
					SettingsNode settingsNode = new SettingsNode(settings, WIDTH * 7.0/8.0, HIEGHT);
					//centerContent = settingsNode;
					rootNode.setCenter(settingsNode);
			    }
			});
			
			//Make the calendar button open the CalendarNode
			calendarButton.setOnAction(new EventHandler<ActionEvent>() {
			    @Override public void handle(ActionEvent e) {
					//centerContent = calendarNode;
					rootNode.setCenter(calendarNode);
			    }
			});
			
			//Make generate button trigger the calendar generization
			generateButton.setOnMouseClicked(event -> {
				calendar.generateCalendar();
				calendarNode.refreshDayTiles();
			});
			
			//setup scene
			Scene scene = new Scene(rootNode, WIDTH, HIEGHT); //A scene represents the physical contents of a JavaFX application
			scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
			
			
			//setup stage
			primaryStage.setScene(scene);
			primaryStage.setTitle("Employee Scheduling System");
			primaryStage.show(); //display contents of the stage
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	private void outputToFile(Calendar calendar) {
		try (PrintWriter writer = new PrintWriter(new File(calendar.getMonth().name + calendar.year + "Schedule.csv"))) {

		      StringBuilder sb = new StringBuilder();
		      sb.append(' ');
		      sb.append(',');
		      
		      for(TimePeriod timePeriod:calendar.settings.staticShifts) {
		    	  sb.append(timePeriod.getStart() + " to " + timePeriod.getEnd());
		    	  sb.append(',');
		      }
		      sb.append('\n');
		      
		      /*
		      for(Employee employee:calendar.settings.employees) {
		    	  sb.append(employee.getFirstName() + " " + employee.getLastName());
		    	  sb.append(',');
		      }
		      sb.append('\n');
		      */
		      
		      for(Day day: calendar.getMonth().days) {
		    	  sb.append(day.printDateWithoutComma());
		    	  sb.append(',');
		    	  for (TimeSlot timeSlot:day.timeSlots) {
		    		  for(Employee employee: timeSlot.employees) {
		    			  sb.append(employee.getFirstName() + " " + employee.getLastName());
		    			  sb.append(',');
		    		  }
		    	  }
		    	  sb.append('\n');
		      }
		      
		

		      writer.write(sb.toString());

		      System.out.println("done!");

		    } catch (FileNotFoundException e) {
		      System.out.println(e.getMessage());
		    }
		
	}


	
	public static void main(String[] args) {
		launch(args);
		System.out.println("Hiss");
	}
}
