package application;

import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;

public class DayTile extends StackPane {
	Day day;
	Rectangle rectangle;
	Text dayNumber;

	public DayTile(Day day, double width, double height) {
		this.day = day;
		
		//setup rectangle physical features (tile)
		rectangle = new Rectangle(width, height);
		rectangle.setFill(Color.GRAY);
		rectangle.setOpacity(.1);
		
		//Setup a black outline around the rectangle
		Rectangle outlineRect = new Rectangle(width, height);
		outlineRect.setFill(Color.TRANSPARENT);
		outlineRect.setStroke(Color.BLACK);
		outlineRect.setStrokeWidth(1);
		
		//add the rectangle and outline to the children node list
		this.getChildren().add(rectangle);
		this.getChildren().add(outlineRect);
		
		//setup the day number at the top left of the tile
		dayNumber = new Text("" + day.getDay());
		dayNumber.setTranslateX(-width/2 + 10);
		dayNumber.setTranslateY(-height/2 + 10);
		
		//Add the number to the stack pane
		this.getChildren().add(dayNumber);
		
		//Write names of employees working shifts in day
		//add a vbox to children to work as a list
		VBox nameList = new VBox();
		nameList.setTranslateY(20);
		nameList.setTranslateX(5);
		this.getChildren().add(nameList);
		
		//add the names to the nameList
		for(TimeSlot timeSlot: day.timeSlots) {
			for(Employee employee: timeSlot.employees) {
				nameList.getChildren().add(new Text(employee.getLastName() + ", " + employee.getFirstName()));
			}
		}
	}
}
