package view;

import java.util.HashMap;
import java.util.List;
import java.util.Observable;
import java.util.ResourceBundle;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ColorPicker;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
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
	private static final double MAX_PREFERENCE_HEIGHT = 40;

	private Drawer drawer;
	private Color backgroundColor;
	private ResourceBundle updateResources;
	private ResourceBundle windowResources;
	private HBox agentViewPreferences;
	private Pane agentPane;
	private Boolean isSelectedAgentToggle;
	private HashMap<ImageView,Agent> imageAgentMap;
	private IntegerProperty currentAgentNameProperty;
	protected HashMap<Integer, Agent> agentMap;
	private ColorPicker colorPicker;

	
	public ViewAgents(ViewType ID) {
		super(ID);
		backgroundColor = DEFAULT_COLOR;
		isSelectedAgentToggle = false;
		currentAgentNameProperty = new SimpleIntegerProperty();
		agentMap = new HashMap<Integer,Agent>();
		imageAgentMap = new HashMap<ImageView,Agent>();

		updateResources = ResourceBundle.getBundle(UPDATE_PROPERTIES);
		windowResources = ResourceBundle.getBundle(WINDOW_PROPERTIES);
		agentPane = new Pane();
		drawer = new Drawer(agentPane);

		agentPane.setPrefSize(WIDE_WIDTH, WIDE_WIDTH);
		agentPane.setBorder(new Border(new BorderStroke(Color.BLACK,BorderStrokeStyle.SOLID,CornerRadii.EMPTY,BorderWidths.DEFAULT)));
		setStyleClass(agentPane);

		agentViewPreferences = new HBox();
		agentViewPreferences.setMaxHeight(MAX_PREFERENCE_HEIGHT);
		agentViewPreferences.setLayoutY(WIDE_WIDTH-agentViewPreferences.getMaxHeight());
		agentPane.getChildren().add(agentViewPreferences);
		setUpColorPicker();
		setUpClearButton();
		setUpSelectAgentToggle();
		

	}
	public void setBackgroundColor(Color color){
		agentPane.setBackground(new Background(new BackgroundFill(color, CornerRadii.EMPTY, Insets.EMPTY)));
		backgroundColor = color;
	}
	public Color getBackgroundColor(){
		return backgroundColor;
	}
	
	public void setUpColorPicker(){
		colorPicker = new ColorPicker(backgroundColor);
        colorPicker.setOnAction(new EventHandler() {
            public void handle(Event t) {
                setBackgroundColor(colorPicker.getValue());      
            }
        });

        agentViewPreferences.getChildren().add(colorPicker);

	}

	@Override
	public void update(Observable agent, Object updateType) {
//		if(((Agent) agent).isVisible()){
		if (updateType == updateResources.getString("STAMP")){
			drawer.stampImage(((Agent) agent).getImageCopy(), ((Agent) agent).getXPosition(), ((Agent) agent).getYPosition());
		
		}else if (updateType == updateResources.getString("MOVE")){
			if (((Agent) agent).isVisible().compareTo(1.0) == 0) {
				drawer.moveImage(((Agent) agent).getImageView(), ((Agent) agent).getXPosition(), ((Agent) agent).getYPosition());					
			}
			if(((Agent) agent).isPenUp().compareTo(0.0) == 0){
				drawer.drawLine(((Agent) agent).getOldXPosition(), ((Agent) agent).getOldYPosition(), ((Agent) agent).getXPosition(), ((Agent) agent).getYPosition(),((Agent) agent).getPenThickness(),((Agent) agent).getPenColor(),Integer.parseInt(updateResources.getString(((Agent) agent).getPenStyle()+"DASH")));
			}

		}else if (updateType == updateResources.getString("INITIAL")){ 
			ImageView agentImageView = addToImageMapAndAddHandler(agent);
			drawer.moveImage(agentImageView, ((Agent) agent).getXPosition(), ((Agent) agent).getYPosition());
		
		}else if (updateType == updateResources.getString("IMAGEVIEW")){
			imageAgentMap.remove(((Agent) agent).getOldImageView());
			ImageView newAgentImageView = addToImageMapAndAddHandler(agent);
			drawer.setNewImage(newAgentImageView,((Agent) agent).getImageView(),((Agent) agent).getXPosition(), ((Agent) agent).getYPosition());
		
		}else if (updateType == updateResources.getString("CURRENT")){
			currentAgentNameProperty.setValue(((Agent) agent).getName());
			if(isSelectedAgentToggle){
				drawer.addSelectEffect(((Agent) agent).getImageView());
				drawer.removeSelectEffectForNonSelectedTurtles(((Agent) agent).getImageView());
			}else{
				drawer.removeSelectEffect(((Agent) agent).getImageView());
			}
		}
//		}
		if(updateType == updateResources.getString("VISIBLE")){
			if (((Agent) agent).isVisible().compareTo(1.0) == 0) {
				drawer.moveImage(((Agent) agent).getImageView(), ((Agent) agent).getXPosition(), ((Agent) agent).getYPosition());
			}
			else {
				drawer.removeImage(((Agent) agent).getImageView());
			}	
		}

			
	}
	private ImageView addToImageMapAndAddHandler(Observable agent) {
		ImageView newAgentImageView = ((Agent) agent).getImageView();
		addImageHandler(newAgentImageView);
		imageAgentMap.put(newAgentImageView, (Agent) agent);
		return newAgentImageView;
	}
			
	@Override
	public Pane getView() {
		return agentPane;

	}
	private void addImageHandler(ImageView img){
		img.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {

		     @Override
		     public void handle(MouseEvent event) {
		         System.out.println("Turtle selected");
		         update(imageAgentMap.get(img),updateResources.getString("CURRENT"));
		         //get rid of effect for other turtles
		     }


		});
	}
	
	
	public void clearScreen() {
		drawer.clearAllLines();
		drawer.clearAllStamps();
	}
	
	public int clearStamps() {
		return drawer.clearAllStamps();
	}
	

	private void setUpClearButton() {
		Button clearButton = new Button(windowResources.getString("CLEARBUTTON"));
		clearButton.setOnAction(new EventHandler() {
            public void handle(Event t) {
                clearScreen();
            }
        });
		agentViewPreferences.getChildren().add(clearButton);
		
	}
	
	private void setUpSelectAgentToggle(){
		CheckBox agentToggle = new CheckBox();
		agentToggle = new CheckBox(updateResources.getString("SELECTAGENTLABEL"));
		agentToggle.setSelected(isSelectedAgentToggle);
	    agentToggle.selectedProperty().addListener(new ChangeListener<Boolean>() {
	    
			public void changed(ObservableValue<? extends Boolean> ov,
	            Boolean old_val, Boolean new_val) {
	        		isSelectedAgentToggle = new_val;
	        		updateCurrentAgentView();
	        }
	    });
	    agentViewPreferences.getChildren().add(agentToggle);
	}
	public double getWidth() {
		return WIDE_WIDTH;
	}
	public double getHeight() {
		return WIDE_WIDTH;
	}
	public IntegerProperty getCurrentAgentNameProperty() {
		return currentAgentNameProperty;
	}
	public void updateCurrentAgentView(){
		update(agentMap.get(currentAgentNameProperty.getValue()),updateResources.getString("CURRENT"));

	}
	public void updateAgentMap(HashMap<Integer, Agent> newAgentMap) {
		agentMap = newAgentMap;
	}
	
	public List<Color> getCustomColors() {
	return colorPicker.getCustomColors();
	}
	
	public void setColor(Color color) {
		setBackgroundColor(color);
}

	
}