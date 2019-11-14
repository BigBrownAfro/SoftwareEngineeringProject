package application;

import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class DayTile extends StackPane {
	Day day;

	public DayTile(Day day, double width, double height) {
		this.day = day;
		
		//setup rectangle physical features (tile)
		Rectangle r = new Rectangle(width, height);
		r.setFill(Color.GRAY);
		r.setOpacity(.2);
		r.setStroke(Color.BLACK); //outline
		r.setStrokeWidth(2); //outline width
		
		//add the rectangle to the children node list
		this.getChildren().add(r);
		
		//
	}
}
