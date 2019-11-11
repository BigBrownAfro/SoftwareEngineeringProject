package application;

import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.TilePane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.paint.CycleMethod;
import javafx.scene.paint.LinearGradient;
import javafx.scene.paint.Stop;
import javafx.scene.shape.Box;
import javafx.scene.shape.Line;
import javafx.scene.shape.LineTo;
import javafx.scene.shape.MoveTo;
import javafx.scene.shape.Path;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;

public class CalendarNode extends Group {
	Calendar calendar;
	
	CalendarNode(double width, double height){
		//Setup database --------------------------------------------------------------------------------------------------------
		calendar = new Calendar();
		
		//Setup GUI -------------------------------------------------------------------------------------------------------------
		//create a gradient for the background color of our calendar
		Stop[] stops = new Stop[] {
				new Stop(0, Color.rgb(210, 238, 250)),
				new Stop(1, Color.rgb(177, 228, 250)),
		};
		LinearGradient gradient = new LinearGradient(0, 0, 0, 1, true, CycleMethod.NO_CYCLE, stops);
		
		//adding vBox to the CalendarNode which will contain an hBox for: arrows, month at the top and a grid containing the days at the bottom
		VBox vBox = new VBox();
		vBox.setMinSize(width, height);
		vBox.setBackground(new Background(new BackgroundFill(gradient, null, null)));
		this.getChildren().add(vBox);
		
		//adding an hBox to vBox for the month name and the arrows ---------------------------------------------------------------
		HBox pageTop = new HBox();
		pageTop.minWidth(width);
		pageTop.setSpacing(100);
		vBox.getChildren().add(pageTop);
		
		//Creating arrows and month name in the topPage HBox
		//creating arrow
		StackPane arrow = new StackPane();
		
		//making the lines
		Path arrowLines = new Path();
		MoveTo arrowStart = new MoveTo(25, 25);
		LineTo arrowLine1 = new LineTo(75, 25);
		LineTo arrowLine2 = new LineTo(60, 10);
		LineTo arrowLine3 = new LineTo(75, 25);
		LineTo arrowLine4 = new LineTo(60, 40);
		arrowLines.getElements().add(arrowStart);
		arrowLines.getElements().addAll(arrowLine1, arrowLine2, arrowLine3, arrowLine4);
		
		//make a button that the arrow will be inside
		Button arrowButton = new Button();
		arrowButton.setMinSize(65, 40);
		arrowButton.setBackground(null);
		
		//create event handler for our button
		EventHandler<MouseEvent> buttonEventHandler = new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent e) {
				System.out.println("button was clicked");
			}
		};
		//add event filter to arrow button so event is only triggered on a mouse click
		arrowButton.addEventFilter(MouseEvent.MOUSE_CLICKED, buttonEventHandler);
		
		//add the arrow lines and button to the arrow stackpane
		arrow.getChildren().add(arrowButton);
		arrow.getChildren().add(arrowLines);
		
		//add the final arrow to the top of the page
		pageTop.getChildren().add(arrow);
		
		//create the text for the month
		Text monthNameText = new Text(calendar.getMonth().name);
		pageTop.getChildren().add(monthNameText);
		
		//adding a TilePane to the vBox to act as a grid for our days -----------------------------------------------------------------
		TilePane daysTilePane = new TilePane();
		daysTilePane.setPadding(new Insets(5, 5, 5, 7));
		daysTilePane.setPrefColumns(7);
		daysTilePane.setVgap(1);
		daysTilePane.setHgap(1);
		vBox.getChildren().add(daysTilePane);
		
		//add in the day titles
		daysTilePane.getChildren().addAll(new Text("Sunday"), new Text("Monday"), new Text("Tuesday"), new Text("Wednesday"), new Text("Thursday"), new Text("Friday"), new Text("Saturday"));
		
		//add in day tiles
		for(Day d: calendar.getMonth().days) {
			Rectangle r = new Rectangle(0, 0, (width - 10.0)/7.0 - 2, 140);
			r.setFill(Color.GRAY);
			r.setOpacity(.2);
			daysTilePane.getChildren().add(r);
		}
	}
}
