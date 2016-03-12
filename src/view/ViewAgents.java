package view;

import java.util.HashMap;
import java.util.Observable;
import java.util.ResourceBundle;

import javafx.beans.property.Property;
import javafx.beans.property.SimpleListProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.ObservableList;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ColorPicker;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import model.Agent;

/**
 * This view displays all the agents and updates when the update method is called by the Agent Observables.
 * @author Melissa Zhang
 *
 */
public class ViewAgents extends View{
	private static final ResourceBundle UPDATE_RESOURCES = ResourceBundle.getBundle("updateObserver");
	private static final ResourceBundle WINDOW_RESOURCES = ResourceBundle.getBundle("windowProperties");
	private static final double MAX_PREFERENCE_HEIGHT = 40;

	private Drawer drawer;
	private Color backgroundColor;

	private ResourceBundle cssResources = ResourceBundle.getBundle("CSSClasses");
	private HBox agentViewPreferences;
	private Pane agentPane;
	private Boolean isSelectedAgentToggle;
	protected HashMap<Integer, Agent> agentMap;

	private Preferences savedPreferences;
	private ViewAgentsUpdater viewUpdater;
	private Property<ObservableList<Integer>> activeAgentsListProperty;

	
	public ViewAgents(ViewType ID, Preferences savedPreferences) {
		super(ID, savedPreferences);

		activeAgentsListProperty = new SimpleListProperty<Integer>();
		addListener(activeAgentsListProperty);
		agentMap = new HashMap<Integer,Agent>();

		isSelectedAgentToggle = true;

		agentPane = super.getView();
		agentPane.setId((cssResources.getString("AGENTVIEW")));
		drawer = new Drawer(agentPane);
		agentPane.setPrefSize(WIDE_WIDTH, WIDE_WIDTH);

		agentViewPreferences = new HBox();
		agentViewPreferences.getStyleClass().add(cssResources.getString("HBOX"));
		agentViewPreferences.setMaxHeight(MAX_PREFERENCE_HEIGHT);
		agentViewPreferences.setLayoutY(WIDE_WIDTH-agentViewPreferences.getMaxHeight());
		setPane(agentViewPreferences);
		
		this.savedPreferences = savedPreferences;
		setBackgroundColor(Color.valueOf(savedPreferences.getPreference("background").toString()));
		
		viewUpdater = new ViewAgentsUpdater(this,drawer);

	}
		
	private void addListener(Property<ObservableList<Integer>> listProperty) {
		listProperty.addListener(new ChangeListener<Object>(){
			@Override
			public void changed(ObservableValue<? extends Object> ov,
					Object oldValue, Object newValue) {
					updateActiveAgentViews();
			}
		});		
	}

	@Override
	public Pane getView() {
		setUpColorPicker();
		setUpClearButton();
		setUpSelectAgentToggle();
		return super.getView();
	}

	@Override
	public void update(Observable agent, Object updateType) {
		viewUpdater.updateView((Agent)agent, (String) updateType);
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

	private void setUpClearButton() {
		Button clearButton = new Button(WINDOW_RESOURCES.getString("CLEARBUTTON"));
		clearButton.setOnAction(new EventHandler() {
            public void handle(Event t) {
                clearScreen();
            }
        });
		agentViewPreferences.getChildren().add(clearButton);
		
	}
	public void clearScreen(){
		drawer.clearAllLines();
		drawer.clearAllStamps();
	}
	private void setUpSelectAgentToggle(){

		CheckBox agentToggle = new CheckBox();
		agentToggle = new CheckBox(UPDATE_RESOURCES.getString("SELECTAGENTLABEL"));
		agentToggle.setSelected(isSelectedAgentToggle);
	    agentToggle.selectedProperty().addListener(new ChangeListener<Boolean>() {
	    
			public void changed(ObservableValue<? extends Boolean> ov,
	            Boolean old_val, Boolean new_val) {
	        		isSelectedAgentToggle = new_val;
	        		updateActiveAgentViews();
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
	
	@Override
	public int getX(){
		return COORD01[0];
	}
	
	@Override
	public int getY(){
		return COORD01[1];
	}

	public boolean getIsSelectedAgentToggle() {
		return isSelectedAgentToggle;
	}

	public void updateActiveAgentViews(){
		for (Integer agentID: activeAgentsListProperty.getValue()){
		
			if (isSelectedAgentToggle){
				update(agentMap.get(agentID),UPDATE_RESOURCES.getString("ACTIVE"));
			}else {
				update(agentMap.get(agentID),UPDATE_RESOURCES.getString("NOTACTIVE"));
				
			}
		}
	}
	public void updateAgentMap(HashMap<Integer, Agent> newAgentMap) {
		agentMap = newAgentMap;
		

	}

	public double clearStamps() {
		return drawer.clearAllStamps();
	}



	public Property<ObservableList<Integer>> getActiveAgentListProperty() {
		return activeAgentsListProperty;
	}

	
}