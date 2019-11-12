package application;
	
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.stage.Stage;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.paint.CycleMethod;
import javafx.scene.paint.LinearGradient;
import javafx.scene.paint.Stop;


public class Main extends Application {
	@Override
	public void start(Stage primaryStage) { //primaryStage is the window
		try {
			final int WIDTH = 1280;
			final int HIEGHT = 892; //was 720
			
			BorderPane rootNode = new BorderPane(); //The root of our nodes (views)
			
			//setup left panel
			VBox leftPanel = new VBox();
			leftPanel.setPrefSize(WIDTH/8.0, HIEGHT);
			leftPanel.setPadding(new Insets(5,5,5,5));
			
			//create settings button in left panel
			Button settingsButton = new Button("Settings");
			settingsButton.setPrefSize(WIDTH/8.0 - 5, 50);
			leftPanel.getChildren().add(settingsButton);
			
			//Setting up a color gradient
			Stop[] stops = new Stop[] {
					new Stop(0, Color.rgb(140, 130, 150)),
					new Stop(1, Color.rgb(0, 128, 150)),
			};
			LinearGradient gradient = new LinearGradient(0, 0, 0, 1, true, CycleMethod.NO_CYCLE, stops);
			leftPanel.setBackground(new Background(new BackgroundFill(gradient, null, null)));
			
			//adding leftPanel vbox to the borderPane
			rootNode.setLeft(leftPanel);
			
			//setup center content
			Group centerContent;
			CalendarNode calendarNode = new CalendarNode(WIDTH * 7.0/8.0, HIEGHT); //Homemade, see CalendarNode
			centerContent = calendarNode;
			rootNode.setCenter(centerContent);
			
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
