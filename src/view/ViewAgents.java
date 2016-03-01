package view;

import java.util.Observable;
import java.util.ResourceBundle;

import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Group;
import javafx.scene.control.ColorPicker;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;

/**
 * This view displays all the agents and updates when it is called by the Agent Observables
 * @author Melissa Zhang
 *
 */
public class ViewAgents extends View{
	private static final Color DEFAULT_COLOR = Color.WHITE;
	private static final String UPDATE_PROPERTIES = "updateObserver";
	private Drawer drawer;
	private Group agentGroup;
	private Color backgroundColor;
	private Pane pane;
	private Group viewGroup;
	private ResourceBundle myResources;
	
	public ViewAgents(String id) {
		super(id);
		agentGroup = new Group();
		drawer = new Drawer(agentGroup);

		pane = new Pane();
		pane.setPrefSize(WIDE_WIDTH, TALL_HEIGHT);
		pane.getChildren().add(agentGroup);

		viewGroup = new Group();
		viewGroup.getChildren().add(pane);
		backgroundColor = DEFAULT_COLOR;
		myResources = ResourceBundle.getBundle(UPDATE_PROPERTIES);
	}
	public void setBackgroundColor(Color color){
		pane.setBackground(new Background(new BackgroundFill(color, CornerRadii.EMPTY, Insets.EMPTY)));
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
        colorPicker.setLayoutY(TALL_HEIGHT);
        colorPicker.setLayoutX(200);
        pane.getChildren().add(colorPicker);
	}
	@Override
	public void update(Observable agent, Object obj) {
		System.out.println(obj);
		if(((Agent) agent).isVisible()){
			if (obj == myResources.getString("STAMP")){
				drawer.stampImage(((Agent) agent).getImageCopy(), ((Agent) agent).getXPosition(), ((Agent) agent).getYPosition());
			
			}else if (obj == myResources.getString("MOVE")){
				drawer.moveImage(((Agent) agent).getImageView(), ((Agent) agent).getXPosition(), ((Agent) agent).getYPosition());
				if(!((Agent) agent).isPenUp()){
					drawer.drawLine(((Agent) agent).getOldXPosition(), ((Agent) agent).getOldYPosition(), ((Agent) agent).getXPosition(), ((Agent) agent).getYPosition(),((Agent) agent).getPenThickness(),((Agent) agent).getPenColor());
				
				}
			}else if (obj == "INITIAL"){ //fix this resource stuff
				drawer.moveImage(((Agent) agent).getImageView(), ((Agent) agent).getXPosition(), ((Agent) agent).getYPosition());
			
			}else if (obj == myResources.getString("IMAGEVIEW")){
				drawer.setNewImage(((Agent) agent).getOldImageView(),((Agent) agent).getImageView(),((Agent) agent).getXPosition(), ((Agent) agent).getYPosition());
			}
		}else if(obj == myResources.getString("VISIBLE")){
			drawer.removeImage(((Agent) agent).getImageView());
			
		}
			
	}
			

	@Override
	public Group getView() {
		setUpColorPicker();
		return viewGroup;
	}
	public double getWidth() {
		return WIDE_WIDTH;
	}
	public double getHeight() {
		return TALL_HEIGHT;
	}

	
}