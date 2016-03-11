package view;

import java.util.HashMap;
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
 * This view displays all the agents and updates when the update method is called by the Agent Observables. This class keeps tracks of all the images on the screen and maps them to an Agent object.
 * @author Melissa Zhang
 *
 */
public class ViewAgents extends View{
	private static final int CONSOLEX = NARROW_WIDTH;
	private static final int CONSOLEY = MENU_OFFSET;
	private static final ResourceBundle UPDATE_RESOURCES = ResourceBundle.getBundle("updateObserver");
	private static final ResourceBundle WINDOW_RESOURCES = ResourceBundle.getBundle("windowProperties");
	private static final double MAX_PREFERENCE_HEIGHT = 40;

	private Drawer drawer;
	private Color backgroundColor;

	private ResourceBundle cssResources = ResourceBundle.getBundle("CSSClasses");
	private HBox agentViewPreferences;
	private Pane agentPane;
	private Boolean isSelectedAgentToggle;
	private HashMap<ImageView,Agent> imageAgentMap;
	private IntegerProperty currentAgentNameProperty;
	protected HashMap<Integer, Agent> agentMap;
	private Preferences savedPreferences;

	
	public ViewAgents(ViewType ID, Preferences savedPreferences) {
		super(ID, savedPreferences);
		setX(CONSOLEX);
		setY(CONSOLEY);

		currentAgentNameProperty = new SimpleIntegerProperty();
		agentMap = new HashMap<Integer,Agent>();

		isSelectedAgentToggle = true;
		addListenerToCurrentAgentProperty(currentAgentNameProperty);
		imageAgentMap = new HashMap<ImageView,Agent>();

		agentPane = getPane();
		agentPane.setId((cssResources.getString("AGENTVIEW")));
		drawer = new Drawer(agentPane);

		agentPane.setPrefSize(WIDE_WIDTH, WIDE_WIDTH);

		agentViewPreferences = new HBox();
		agentViewPreferences.setMaxHeight(MAX_PREFERENCE_HEIGHT);
		agentViewPreferences.setLayoutY(WIDE_WIDTH-agentViewPreferences.getMaxHeight());
		setPane(agentViewPreferences);
		
		this.savedPreferences = savedPreferences;
		setBackgroundColor(Color.valueOf(savedPreferences.getPreference("background").toString()));


	}
	private void addListenerToCurrentAgentProperty(IntegerProperty property) {
		property.addListener(new ChangeListener<Object>(){

		@Override
		public void changed(ObservableValue<? extends Object> ov,
				Object oldValue, Object newValue) {
				update(agentMap.get(currentAgentNameProperty.getValue()),UPDATE_RESOURCES.getString("CURRENT"));
		}
	});

}

	public void setBackgroundColor(Color color){
		agentPane.setBackground(new Background(new BackgroundFill(color, CornerRadii.EMPTY, Insets.EMPTY)));
		backgroundColor = color;
		savedPreferences.setPreference("background", backgroundColor.toString());
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
	public void update(Observable agent, Object updateType) {
		AgentElem agentView = ((Agent) agent).getAgentView();

		if(((Agent) agent).isVisible()){
			if (updateType == UPDATE_RESOURCES.getString("STAMP")){
				drawer.stampImage(agentView.getImageCopy(), ((Agent) agent).getXPosition(), ((Agent) agent).getYPosition());

			}else if (updateType == UPDATE_RESOURCES.getString("MOVE")){
				drawer.moveImage(agentView.getImageView(), ((Agent) agent).getXPosition(), ((Agent) agent).getYPosition());
				if(!((Agent) agent).isPenUp()){
					drawer.drawLine(((Agent) agent).getOldXPosition(), ((Agent) agent).getOldYPosition(), ((Agent) agent).getXPosition(), ((Agent) agent).getYPosition(),((Agent) agent).getPenThickness(),agentView.getPenColor(),Integer.parseInt(UPDATE_RESOURCES.getString(((Agent) agent).getPenStyle()+"DASH")));
				}
			}else if (updateType == UPDATE_RESOURCES.getString("INITIAL")){ 
				ImageView agentImageView = createNewImageViewWithHandler(agent);
				drawer.moveImage(agentImageView, ((Agent) agent).getXPosition(), ((Agent) agent).getYPosition());

			}else if (updateType == UPDATE_RESOURCES.getString("IMAGEVIEW")){	
				imageAgentMap.remove(agentView.getOldImageView());
				ImageView newAgentImageView = createNewImageViewWithHandler(agent);
				drawer.setNewImage(agentView.getOldImageView(),newAgentImageView,((Agent) agent).getXPosition(), ((Agent) agent).getYPosition());
			}else if (updateType == UPDATE_RESOURCES.getString("CURRENT")){
				currentAgentNameProperty.setValue(((Agent) agent).getName());
				if(isSelectedAgentToggle){
					drawer.addSelectEffect(agentView.getImageView());
					drawer.removeSelectEffectForNonSelectedTurtles(agentView.getImageView());
				}else{
					drawer.removeSelectEffect(agentView.getImageView());
				}
			}
		}else if(updateType == UPDATE_RESOURCES.getString("VISIBLE")){
			if (((Agent) agent).isVisible()) {
				drawer.moveImage(agentView.getImageView(), ((Agent) agent).getXPosition(), ((Agent) agent).getYPosition());			
			}
			else {
				drawer.removeImage(agentView.getImageView());				
			}

			
		}

			
	}

	private ImageView createNewImageViewWithHandler(Observable agent) {
		ImageView newAgentImageView = (((Agent) agent).getAgentView().getImageView());
		addImageHandler(newAgentImageView);
		imageAgentMap.put(newAgentImageView, (Agent) agent);
		return newAgentImageView;
	}
			
	@Override
	public Pane getView() {
		setUpColorPicker();
		setUpClearButton();
		setUpSelectAgentToggle();
		return super.getView();
	}
	private void addImageHandler(ImageView img){
		img.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {

		     @Override
		     public void handle(MouseEvent event) {
		         update(imageAgentMap.get(img),UPDATE_RESOURCES.getString("CURRENT"));   
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
		Button clearButton = new Button(WINDOW_RESOURCES.getString("CLEARBUTTON"));
		clearButton.setOnAction(new EventHandler() {
            public void handle(Event t) {
                clearScreen();
            }
        });
		agentViewPreferences.getChildren().add(clearButton);
		
	}
	
	private void setUpSelectAgentToggle(){

		CheckBox agentToggle = new CheckBox();
		agentToggle = new CheckBox(UPDATE_RESOURCES.getString("SELECTAGENTLABEL"));
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
		update(agentMap.get(currentAgentNameProperty.getValue()),UPDATE_RESOURCES.getString("CURRENT"));

	}
	public void updateAgentMap(HashMap<Integer, Agent> newAgentMap) {
		agentMap = newAgentMap;
		
	}

	
}