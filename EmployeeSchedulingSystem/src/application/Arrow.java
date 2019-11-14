package application;

import javafx.scene.layout.StackPane;
import javafx.scene.shape.LineTo;
import javafx.scene.shape.MoveTo;
import javafx.scene.shape.Path;
import javafx.scene.transform.Rotate;

public class Arrow extends StackPane {

	/**
	 * A stack pane with a drawing of an arrow with an invisible button behind it.
	 * The constructor takes a String direction ("left" or "right").
	 * The direction defaults to right on an invalid string.
	 * @param direction
	 */
	public Arrow(String direction) {
		//set height and width
		double tempWidth = 65;
		double tempHeight = 40;
		this.minWidth(tempWidth);
		this.minHeight(tempHeight);
		
		//Check the direction the arrow should be facing and draw lines to path
		Path arrowPath = new Path();
		
		if(direction.equals("left")) {
			//making the lines for left facing arrows
			MoveTo arrowStart = new MoveTo(25, 25);
			LineTo arrowLine1 = new LineTo(75, 25);
			LineTo arrowLine2 = new LineTo(60, 10);
			LineTo arrowLine3 = new LineTo(75, 25);
			LineTo arrowLine4 = new LineTo(60, 40);
			arrowPath.getElements().add(arrowStart);
			arrowPath.getElements().addAll(arrowLine1, arrowLine2, arrowLine3, arrowLine4);
			this.getTransforms().add(new Rotate(180, tempWidth/2.0, tempHeight/2.0));
		}else {
			//making the lines for right facing arrows
			MoveTo arrowStart = new MoveTo(25, 25);
			LineTo arrowLine1 = new LineTo(75, 25);
			LineTo arrowLine2 = new LineTo(60, 10);
			LineTo arrowLine3 = new LineTo(75, 25);
			LineTo arrowLine4 = new LineTo(60, 40);
			arrowPath.getElements().add(arrowStart);
			arrowPath.getElements().addAll(arrowLine1, arrowLine2, arrowLine3, arrowLine4);
		}
		
		this.getChildren().add(arrowPath);
	}
	/*EventHandler<MouseEvent> arrowREventHandler = new EventHandler<MouseEvent>() {
		@Override
		public void handle(MouseEvent e) {
			System.out.println("Rbutton was clicked");
			calendar.nextMonth();
			monthNameText.setText(calendar.selectedMonth.name);
		}
	};*/
}
