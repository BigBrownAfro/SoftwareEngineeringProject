package application;
	
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
	@Override
	public void start(Stage primaryStage) { //primaryStage is the window
		try {
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
			

			
			//Setting up a color gradient
			Stop[] stops = new Stop[] {
					//new Stop(0, Color.rgb(180, 130, 130)),
					//new Stop(1, Color.rgb(150, 100, 150))
					new Stop(0, Color.rgb(180, 130, 130)),
					new Stop(1, Color.rgb(150, 100, 150))
			};
			LinearGradient gradient = new LinearGradient(1, 0, 0, 1, true, CycleMethod.NO_CYCLE, stops);
			leftPanel.setBackground(new Background(new BackgroundFill(gradient, null, null)));
			
			//adding leftPanel vbox to the borderPane
			//rootNode.setLeft(leftPanel);
			HBox temp = new HBox(leftPanel);
			temp.getChildren().add(new Rectangle(1, HIEGHT, Color.BLACK));
			rootNode.setLeft(temp);
			
			//setup center content
			StackPane centerContent;
			CalendarNode calendarNode = new CalendarNode(WIDTH * 7.0/8.0, HIEGHT); //Homemade, see CalendarNode
			centerContent = calendarNode;
			rootNode.setCenter(centerContent);
			
			//Making settings button open SettingsNode
			settingsButton.setOnAction(new EventHandler<ActionEvent>() {
			    @Override public void handle(ActionEvent e) {
					SettingsNode settingsNode = new SettingsNode(WIDTH * 7.0/8.0, HIEGHT);
					SettingsNode centerContent = settingsNode;
					rootNode.setCenter(centerContent);
			    }
			});
			
			//Make the calendar button open the CalendarNode
			calendarButton.setOnAction(new EventHandler<ActionEvent>() {
			    @Override public void handle(ActionEvent e) {
			    	CalendarNode calendarNode = new CalendarNode(WIDTH * 7.0/8.0, HIEGHT); //Homemade, see CalendarNode
					CalendarNode centerContent = calendarNode;
					rootNode.setCenter(centerContent);
			    }
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
	
	public static void main(String[] args) {
		launch(args);
		System.out.println("Hiss");
	}
}
