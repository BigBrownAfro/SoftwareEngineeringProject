package application;

import java.util.List;

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

public class SettingsNode extends StackPane {

	double width;
	double height;
	TilePane buttonPane;
	
	
	SettingsNode(double width, double height){
		this.width = width;
		this.height = height;
		
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
		
		//adds addButton to the box
		buttonBox.getChildren().add(addButton);
		
		//makes the button
		Button editButton = new Button("Edit Employee");
		editButton.setPrefSize(width/8.0 - 5, 50);
		
		//adds editButton to the box
		buttonBox.getChildren().add(editButton);
		
		//makes the button
		Button editTimeButton = new Button("Edit Shift Time");
		editTimeButton.setPrefSize(width/8.0 - 5, 50);
		
		//adds editTimeButton to the box
		buttonBox.getChildren().add(editTimeButton);
		
		//makes the button
		Button makeButton = new Button("Create Schedule");
		makeButton.setPrefSize(width/8.0 - 5, 50);
		
		//adds editTimeButton to the box
		buttonBox.getChildren().add(makeButton);

		//puts the box in the box 
		vBox.getChildren().add(buttonBox);
	}
}
