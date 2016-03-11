package view;

import java.util.HashMap;
import java.util.Observable;
import java.util.ResourceBundle;

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
import javafx.scene.layout.*;
import javafx.scene.paint.Color;

/**
 * This view displays all the agents and updates when the update method is called by the Agent Observables.
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
	private StringProperty currentAgentNameProperty;
	private HashMap<String, Agent> agentMap;
	private Preferences savedPreferences;
	private ViewAgentsUpdater viewUpdater;

	
	public ViewAgents(ViewType ID, Preferences savedPreferences) {
		super(ID, savedPreferences);
		setX(CONSOLEX);
		setY(CONSOLEY);
		isSelectedAgentToggle = true;
		
		currentAgentNameProperty = new SimpleStringProperty();
		addListenerToCurrentAgentProperty(currentAgentNameProperty);
		
		agentMap = new HashMap<String,Agent>();
		
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
		
		viewUpdater = new ViewAgentsUpdater(this,drawer);

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

	public StringProperty getCurrentAgentNameProperty() {
		return currentAgentNameProperty;
	}
	private void addListenerToCurrentAgentProperty(StringProperty property) {
		property.addListener(new ChangeListener<Object>(){

		@Override
		public void changed(ObservableValue<? extends Object> ov,
				Object oldValue, Object newValue) {
				update(agentMap.get(currentAgentNameProperty.getValue()),UPDATE_RESOURCES.getString("CURRENT"));
		}
	});

}

	private void setBackgroundColor(Color color){
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
                drawer.clearAllLines();  
                drawer.clearAllStamps();
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

	public void updateCurrentAgentView(){
		update(agentMap.get(currentAgentNameProperty.getValue()),UPDATE_RESOURCES.getString("CURRENT"));
	}
	public void updateAgentMap(HashMap<String, Agent> newAgentMap) {
		agentMap = newAgentMap;
		
	}

	public double getWidth() {
		return WIDE_WIDTH;
	}

	public double getHeight() {
		return WIDE_WIDTH;
	}

	public boolean getIsSelectedAgentToggle() {
		return isSelectedAgentToggle;
	}

	
}