package application;

import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Group;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
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
import javafx.scene.shape.LineTo;
import javafx.scene.shape.MoveTo;
import javafx.scene.shape.Path;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.scene.transform.Rotate;

public class CalendarNode extends Group {
	Calendar calendar;
	
	CalendarNode(double width, double height){
		//Setup database --------------------------------------------------------------------------------------------------------
		calendar = new Calendar();
		
		//Setup GUI -------------------------------------------------------------------------------------------------------------
		//create a gradient for the background color of the calendar
		Stop[] stops = new Stop[] {
				new Stop(0, Color.rgb(180, 130, 130)),
				new Stop(1, Color.rgb(150, 100, 150)),
		};
		LinearGradient gradient = new LinearGradient(0, 0, 0, 1, true, CycleMethod.NO_CYCLE, stops);
		
		//adding vBox to the CalendarNode which will contain an hBox for: arrows, month at the top and a grid containing the days at the bottom
		VBox vBox = new VBox();
		vBox.setMinSize(width, height);
		vBox.setBackground(new Background(new BackgroundFill(gradient, null, null)));
		this.getChildren().add(vBox);
		
		//adding a BorderPane to vBox for the month name and the arrows ---------------------------------------------------------------
		BorderPane pageTop = new BorderPane();
		pageTop.minWidth(width);
		pageTop.setPadding(new Insets(20,20,10,20));
		vBox.getChildren().add(pageTop);
		
		//Create month name textNode
		Text monthNameText = new Text(calendar.getMonth().name);
		monthNameText.setFont(new Font("times new roman", 32));
		
		//Creating arrows and month name in the topPage HBox
		//creating arrows
		StackPane arrowL = new StackPane();
		StackPane arrowR = new StackPane();
		
		//making the lines for left then right
		Path arrowLLines = new Path();
		MoveTo arrowLStart = new MoveTo(25, 25);
		LineTo arrowLLine1 = new LineTo(75, 25);
		LineTo arrowLLine2 = new LineTo(60, 10);
		LineTo arrowLLine3 = new LineTo(75, 25);
		LineTo arrowLLine4 = new LineTo(60, 40);
		arrowLLines.getElements().add(arrowLStart);
		arrowLLines.getElements().addAll(arrowLLine1, arrowLLine2, arrowLLine3, arrowLLine4);
		
		Path arrowRLines = new Path();
		MoveTo arrowRStart = new MoveTo(25, 25);
		LineTo arrowRLine1 = new LineTo(75, 25);
		LineTo arrowRLine2 = new LineTo(60, 10);
		LineTo arrowRLine3 = new LineTo(75, 25);
		LineTo arrowRLine4 = new LineTo(60, 40);
		arrowRLines.getElements().add(arrowRStart);
		arrowRLines.getElements().addAll(arrowRLine1, arrowRLine2, arrowRLine3, arrowRLine4);
		
		//make a button that the arrow will be inside
		Button arrowLButton = new Button();
		arrowLButton.setMinSize(65, 40);
		arrowLButton.setBackground(null);
		
		Button arrowRButton = new Button();
		arrowRButton.setMinSize(65, 40);
		arrowRButton.setBackground(null);
		
		//create event handler for our buttons
		EventHandler<MouseEvent> buttonLEventHandler = new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent e) {
				System.out.println("Lbutton was clicked");
				calendar.previousMonth();
				monthNameText.setText(calendar.selectedMonth.name);
			}
		};
		
		EventHandler<MouseEvent> buttonREventHandler = new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent e) {
				System.out.println("Rbutton was clicked");
				calendar.nextMonth();
				monthNameText.setText(calendar.selectedMonth.name);
			}
		};
		//add event filter to arrow button so event is only triggered on a mouse click
		arrowLButton.addEventFilter(MouseEvent.MOUSE_CLICKED, buttonLEventHandler);
		
		arrowRButton.addEventFilter(MouseEvent.MOUSE_CLICKED, buttonREventHandler);
		
		//add the arrow lines and button to the arrow stackpane.
		arrowL.getChildren().add(arrowLButton);
		arrowL.getChildren().add(arrowLLines);
		arrowL.getTransforms().add(new Rotate(180, arrowLButton.getMinWidth()/2.0, arrowLButton.getMinHeight()/2.0));
		
		arrowR.getChildren().add(arrowRButton);
		arrowR.getChildren().add(arrowRLines);

		//add the left arrow to the top of the page
		pageTop.setLeft(arrowL);
		
		//add the text for the month to the top of the page
		pageTop.setCenter(monthNameText);

		//add the right arrow to the top of the page
		pageTop.setRight(arrowR);
		
		//adding an hBox to vBox for the month name and the arrows ----------------------------------------------------------------
		HBox dayNamesBox = new HBox();
		dayNamesBox.minWidth(width);
		dayNamesBox.setPadding(new Insets(5,5,5,65));
		dayNamesBox.setSpacing(112);
		dayNamesBox.getChildren().addAll(new Text("Sunday"), new Text("Monday"), new Text("Tuesday"), new Text("Wednesday"), new Text("Thursday"), new Text("Friday"), new Text("Saturday"));
		vBox.getChildren().add(dayNamesBox);
		
		//adding a TilePane to the vBox to act as a grid for our days -------------------------------------------------------------
		TilePane daysTilePane = new TilePane();
		daysTilePane.setPadding(new Insets(5, 5, 5, 7));
		daysTilePane.setPrefColumns(7);
		daysTilePane.setVgap(1);
		daysTilePane.setHgap(1);
		vBox.getChildren().add(daysTilePane);
		
		//add in day tiles
		for(int i = 0; i < calendar.getMonth().days.length; i++) {
			//setup rectangle physical features (tile)
			Rectangle r = new Rectangle(0, 0, (width - 10.0)/7.0 - 4, (width - 10.0)/7.0 - 4);
			r.setFill(Color.GRAY);
			r.setOpacity(.2);
			r.setStroke(Color.BLACK); //outline
			r.setStrokeWidth(2); //outline width
			
			//make an id used to tell which tile is clicked
			r.setId(i + "");
			
			//setup an event handler for clicking on a tile
			r.setOnMouseClicked(event -> {
				System.out.println("Day " + (r.getId()) + " clicked");
				calendar.getDay(Integer.parseInt(r.getId())).printDate();
			});
			
			//add the tile(rectangle) to the tilePane
			daysTilePane.getChildren().add(r);
		}
		
		//add in extra days to the tilePane so that their is an even grid
		int temp_currentDays = daysTilePane.getChildren().size();
		for (int i = 0; i < 35 - temp_currentDays; i++) {
			Rectangle r = new Rectangle(0, 0, (width - 10.0)/7.0 - 4, (width - 10.0)/7.0 - 4);
			r.setFill(Color.GRAY);
			r.setOpacity(.3);
			r.setStroke(Color.BLACK);
			r.setStrokeWidth(2);
			daysTilePane.getChildren().add(r);
			System.out.println("extra day added");
		}
	}
}
