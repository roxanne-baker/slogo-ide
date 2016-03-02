package view;

import java.util.Observable;
import java.util.ResourceBundle;

import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Group;
import javafx.scene.control.Button;
import javafx.scene.control.ColorPicker;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.Region;
import javafx.scene.layout.RowConstraints;
import javafx.scene.paint.Color;

/**
 * This view displays all the agents and updates when the update method is called by the Agent Observables.
 * @author Melissa Zhang
 *
 */
public class ViewAgents extends View{
	private static final Color DEFAULT_COLOR = Color.WHITE;
	private static final String UPDATE_PROPERTIES = "updateObserver";
	private static final String WINDOW_PROPERTIES = "windowProperties";
	private Drawer drawer;
	private Group agentGroup;
	private Color backgroundColor;
	private Group viewGroup;
	private ResourceBundle updateResources;
	private ResourceBundle windowResources;
	private HBox agentViewPreferences;
	private Pane agentPane;
	private GridPane agentGrid;
	
	public ViewAgents(String id) {
		super(id);
		backgroundColor = DEFAULT_COLOR;
		updateResources = ResourceBundle.getBundle(UPDATE_PROPERTIES);
		windowResources = ResourceBundle.getBundle(WINDOW_PROPERTIES);

		drawer = new Drawer(agentPane);

		agentPane = new Pane();
		agentPane.setPrefSize(WIDE_WIDTH, WIDE_WIDTH);
		agentPane.getChildren().add(agentGroup);

		viewGroup = new Group();
		viewGroup.getChildren().add(agentPane);

		
		agentViewPreferences = new HBox();
		agentViewPreferences.setLayoutY(WIDE_WIDTH);
		agentPane.getChildren().add(agentViewPreferences);

	}
	public void setBackgroundColor(Color color){
		agentPane.setBackground(new Background(new BackgroundFill(color, CornerRadii.EMPTY, Insets.EMPTY)));
		backgroundColor = color;
	}
	public Color getBackgroundColor(){
		return backgroundColor;
	}
	
	public void setUpColorPicker(){
		ColorPicker colorPicker = new ColorPicker(backgroundColor);
        colorPicker.setOnAction(new EventHandler() {
            public void handle(Event t) {
                setBackgroundColor(colorPicker.getValue());      
            }
        });

        agentViewPreferences.getChildren().add(colorPicker);

	}
	@Override
	public void update(Observable agent, Object obj) {

		if(((Agent) agent).isVisible()){
			if (obj == updateResources.getString("STAMP")){
				drawer.stampImage(((Agent) agent).getImageCopy(), ((Agent) agent).getXPosition(), ((Agent) agent).getYPosition());
			
			}else if (obj == updateResources.getString("MOVE")){
				drawer.moveImage(((Agent) agent).getImageView(), ((Agent) agent).getXPosition(), ((Agent) agent).getYPosition());
				if(!((Agent) agent).isPenUp()){
					drawer.drawLine(((Agent) agent).getOldXPosition(), ((Agent) agent).getOldYPosition(), ((Agent) agent).getXPosition(), ((Agent) agent).getYPosition(),((Agent) agent).getPenThickness(),((Agent) agent).getPenColor());
				
				}
			}else if (obj == updateResources.getString("INITIAL")){ 
				drawer.moveImage(((Agent) agent).getImageView(), ((Agent) agent).getXPosition(), ((Agent) agent).getYPosition());
			
			}else if (obj == updateResources.getString("IMAGEVIEW")){
				drawer.setNewImage(((Agent) agent).getOldImageView(),((Agent) agent).getImageView(),((Agent) agent).getXPosition(), ((Agent) agent).getYPosition());
			}
		}else if(obj == updateResources.getString("VISIBLE")){
			drawer.removeImage(((Agent) agent).getImageView());
			
		}
		System.out.println(agentPane.getHeight());

			
	}
			
	@Override
	public Group getView() {
		setUpColorPicker();
		setUpClearButton();
		return viewGroup;
	}
	

	private void setUpClearButton() {
		Button clearButton = new Button(windowResources.getString("CLEARBUTTON"));
		clearButton.setOnAction(new EventHandler() {
            public void handle(Event t) {
                drawer.clearAllLines();  
                drawer.clearAllStamps();
            }
        });
		agentViewPreferences.getChildren().add(clearButton);
		
	}
	public double getWidth() {
		return WIDE_WIDTH;
	}
	public double getHeight() {
		return WIDE_WIDTH;
	}

	
}