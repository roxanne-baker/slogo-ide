package view;
import java.util.Observable;
import java.util.ResourceBundle;

import javafx.beans.property.StringProperty;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Group;
import javafx.scene.control.ColorPicker;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

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
	//private VBox vbox;
	private Group viewGroup;
	private ResourceBundle myResources;
	private ImageView turtleView; 
	
	public ViewAgents(String id) {
		super(id);
		agentGroup = new Group();
		drawer = new Drawer(agentGroup);
		//vbox = new VBox();
		//vbox.getChildren().add(agentGroup);
		viewGroup = new Group();
		viewGroup.getChildren().add(agentGroup);
//		Rectangle rect = new Rectangle(20, 40);
//		rect.setFill(Color.RED);
//		viewGroup.getChildren().add(rect);
		backgroundColor = Color.AQUAMARINE;
		myResources = ResourceBundle.getBundle(UPDATE_PROPERTIES);
		//drawer.drawLine(0, 0, 50, 50, 10, Color.BLACK);
	}
	
	public void addTurtleView() { 
		
		
	}
	

//	public StringProperty getImagePathProperty(){
//		return agentImagePath;
//	}
//	public String getImagePath(){;
//		return agentImagePath.toString();
//	}
	
	public void setImagePath(String imagePath){
		turtleView = new ImageView(new Image(getClass().getClassLoader().getResourceAsStream(imagePath),sizeProperty.doubleValue(),sizeProperty.doubleValue(),true,true));
		//agentImagePath.setValue(imagePath);
		//setChanged();
		//notifyObservers(myResources.getString("IMAGEVIEW"));

//	}
//	public ImageView getImageView(){
//		return agentImageView;
//	}
	public void setBackgroundColor(Color color){
		//vbox.setBackground(new Background(new BackgroundFill(color, CornerRadii.EMPTY, Insets.EMPTY)));
//		viewGroup.
//		backgroundColor = color;
	}
	public Color getBackgroundColor(){
		return backgroundColor;
	}
	
	@Override
	public void update(Observable agent, Object obj) {
		System.out.println(obj);
		if(((Agent) agent).isVisible()){
			if (obj == myResources.getString("STAMP")){
				drawer.stampImage(((Agent) agent).getImageCopy(), ((Agent) agent).getXPosition(), ((Agent) agent).getYPosition(), ((Agent) agent).getSize());
			
			}else if (obj == myResources.getString("MOVE")){
				drawer.moveImage(((Agent) agent).getImageView(), ((Agent) agent).getXPosition(), ((Agent) agent).getYPosition());
				if(!((Agent) agent).isPenUp()){
					drawer.drawLine(((Agent) agent).getOldXPosition(), ((Agent) agent).getOldYPosition(), ((Agent) agent).getXPosition(), ((Agent) agent).getYPosition(),((Agent) agent).getPenThickness(),((Agent) agent).getPenColor());
				
				}
			}else if (obj == myResources.getString("INITIAL") || obj == myResources.getString("IMAGEVIEW")){
				drawer.moveImage(((Agent) agent).getImageView(), ((Agent) agent).getXPosition(), ((Agent) agent).getYPosition());
			
			}
		}else if(obj == myResources.getString("VISIBLE")){
			drawer.removeImage(((Agent) agent).getImageView());
			
		}
			
	}
			

	@Override
	public Group getView() {
		return viewGroup;
	}

	
}