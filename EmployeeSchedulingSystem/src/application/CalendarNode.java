package application;

import javafx.geometry.Insets;
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
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

public class CalendarNode extends StackPane {
	Calendar calendar;
	double width;
	double height;
	TilePane dayTilePane;
	Text monthNameText;
	
	CalendarNode(double width, double height){
		this.width = width;
		this.height = height;
		
		//Setup database --------------------------------------------------------------------------------------------------------------
		calendar = new Calendar();
		
		//Setup GUI -------------------------------------------------------------------------------------------------------------------
		//create a gradient for the background color of the calendar
		Stop[] stops = new Stop[] {
				new Stop(0, Color.rgb(180, 130, 130)),
				new Stop(1, Color.rgb(150, 100, 150)),
		};
		LinearGradient gradient = new LinearGradient(0, 0, 0, 1, true, CycleMethod.NO_CYCLE, stops);
		
		//create a vBox which we will insert the top, middle, and bottom content into
		VBox vBox = new VBox();
		
		//setup vBox size and background color
		vBox.setMinSize(width, height);
		vBox.setBackground(new Background(new BackgroundFill(gradient, null, null)));
		
		//add the vBox the calendar node's children
		this.getChildren().add(vBox);
		
		//Build the top of the calendar in the first slot of the vBox
		this.buildHeader(vBox);
		
		//Build the middle of the calendar with the day names
		this.buildDayNames(vBox);
		
		//Build the bottom of the calendar with a tile pane for the days
		this.buildDayTiles(vBox);
	}
	
	/**
	 * Builds the head of the calendar with a left arrow, month name, and right arrow.
	 * @param vBox
	 */
	private void buildHeader(VBox vBox) {
		//adding a BorderPane to vBox for the month name and the arrows
		BorderPane pageTop = new BorderPane();
		//pageTop.minWidth(width);
		pageTop.setPadding(new Insets(20,20,10,20));
		vBox.getChildren().add(pageTop);
		
		//setup month name textNode
		monthNameText = new Text(calendar.getMonth().name);
		monthNameText.setFont(new Font("times new roman", 32));
		
		//creating arrows
		StackPane arrowL = new Arrow("left");
		StackPane arrowR = new Arrow("right");
		
		//set functions for click events on arrows
		arrowL.setOnMouseClicked(event -> {
			System.out.println("Lbutton was clicked");
			this.previousMonth();
		});
		
		arrowR.setOnMouseClicked(event -> {
			System.out.println("Rbutton was clicked");
			this.nextMonth();
		});

		//add the left arrow to the top of the page
		pageTop.setLeft(arrowL);
		
		//add the text for the month to the top of the page
		pageTop.setCenter(monthNameText);

		//add the right arrow to the top of the page
		pageTop.setRight(arrowR);
	}
	
	/**
	 * Builds the middle of the calendar with the names of the days of the week
	 * @param vBox
	 */
	private void buildDayNames(VBox vBox) {
		//adding an hBox to vBox for the day names
		HBox dayNamesBox = new HBox();
		//dayNamesBox.minWidth(width);
		dayNamesBox.setPadding(new Insets(5,5,5,65));
		dayNamesBox.setSpacing(112);
		dayNamesBox.getChildren().addAll(new Text("Sunday"), new Text("Monday"), new Text("Tuesday"), new Text("Wednesday"), new Text("Thursday"), new Text("Friday"), new Text("Saturday"));
		vBox.getChildren().add(dayNamesBox);
	}
	
	/**
	 * Builds the bottom of the calendar with the grid of day tiles
	 */
	private void buildDayTiles(VBox vBox) {
		//adding a TilePane to the vBox to act as a grid for our days
		dayTilePane = new TilePane();
		dayTilePane.setPadding(new Insets(5, 5, 5, 7));
		dayTilePane.setPrefColumns(7);
		dayTilePane.setVgap(1);
		dayTilePane.setHgap(1);
		vBox.getChildren().add(dayTilePane);
		
		//Insert day tiles into the tile pane
		refreshDayTiles();
	}
	
	/**
	 * Clears the current day tile pane and puts in a new set of day tiles according to the selected month
	 */
	private void refreshDayTiles() {
		//Clear the DayTiles out of the tile pane
		dayTilePane.getChildren().clear();
		
		//add in day tiles
		for(int i = 0; i < calendar.getMonth().days.length; i++) {
			//Setup DayTile
			DayTile dayTile = new DayTile(calendar.getDay(i), (width - 10.0)/7.0 - 4, (width - 10.0)/7.0 - 4);
			
			//setup an event handler for clicking on a tile
			dayTile.setOnMouseClicked(event -> {
				System.out.println("Day " + (dayTile.day.getDay() - 1) + " clicked");
				//DayNode dayNode= new DayNode(dayTile.day);
				//this.getChildren().add(dayNode);
				dayTile.day.printDate();
			});
			
			//Setup an event handler for hovering over the tile
			dayTile.setOnMouseEntered(event -> {
				System.out.println("Mouse hover detected on dayTile: " + (dayTile.day.getDay() - 1));
				dayTile.setOpacity(.4);
			});
			
			//setup an event handle for exiting a hover over a tile
			dayTile.setOnMouseExited(event -> {
				System.out.println("Mouse exit detected on dayTile: " + (dayTile.day.getDay() - 1));
				dayTile.setOpacity(1);
			});
			
			//add the tile(rectangle) to the tilePane
			dayTilePane.getChildren().add(dayTile);
		}
		
		//add in extra days to the tilePane so that their is an even grid
		int temp_currentDays = dayTilePane.getChildren().size();
		for (int i = 0; i < 35 - temp_currentDays; i++) {
			Rectangle r = new Rectangle(0, 0, (width - 10.0)/7.0 - 4, (width - 10.0)/7.0 - 4);
			r.setFill(Color.GRAY);
			r.setOpacity(.3);
			r.setStroke(Color.BLACK);
			r.setStrokeWidth(2);
			dayTilePane.getChildren().add(r);
			System.out.println("extra day added");
		}
	}
	
	/**
	 * Moves the calendar to the next month and updates visuals
	 */
	private void nextMonth() {
		calendar.nextMonth();
		monthNameText.setText(calendar.selectedMonth.name);
		refreshDayTiles();
	}
	
	/**
	 * Moves the calendar to the previous month and updates visuals
	 */
	private void previousMonth() {
		calendar.previousMonth();
		monthNameText.setText(calendar.selectedMonth.name);
		refreshDayTiles();
	}
}
