package view;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Observable;
import java.util.ResourceBundle;

import GUI.GuiObject;
import GUI.GuiObjectFactory;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.scene.Node;
import javafx.scene.control.ComboBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;


/**
 * This class displays all the Agents properties with some components that can/cannot be changed by the user. 
 * @author Melissa Zhang
 *
 */
public class ViewAgentPreferences extends View{

	private static final int CONSOLEX = 0;
	private static final int CONSOLEY = MENU_OFFSET;
	private HashMap<String, Agent> agentMap;
	private VBox allPreferencesBox = new VBox();
	private StringProperty currentAgentNameProperty;
	private static final ResourceBundle UPDATE_RESOURCES = ResourceBundle.getBundle("updateObserver");
	private ResourceBundle cssResources = ResourceBundle.getBundle("CSSClasses");

	
	public ViewAgentPreferences(ViewType ID, Preferences savedPreferences) {
		super(ID, savedPreferences);
		setX(CONSOLEX);
		setY(CONSOLEY);
		setPane(allPreferencesBox);
		allPreferencesBox.getStyleClass().addAll(cssResources.getString("DISPLAYVIEW"),cssResources.getString("VBOXVIEW"));
		agentMap = new HashMap<String,Agent>();
		currentAgentNameProperty = new SimpleStringProperty();
		addListenerToCurrentAgentProperty(currentAgentNameProperty);

	}

	private void addListenerToCurrentAgentProperty(StringProperty property) {
			property.addListener(new ChangeListener<Object>(){

			@Override
			public void changed(ObservableValue<? extends Object> ov,
					Object oldValue, Object newValue) {
					updateView();
			}
		});
	
	}

	@Override
	public void update(Observable agent, Object updateType) {
		if (updateType == UPDATE_RESOURCES.getString("COLORPALETTE") ){
			updateView();
		}else if (updateType == UPDATE_RESOURCES.getString("IMAGEPALETTE")){
			updateView();
		}
		
	}

	@Override
	public Pane getView() {
		updateView();
		return super.getView();
	}
	private void updateView() {

		allPreferencesBox.getChildren().clear();
		allPreferencesBox.setPrefSize(NARROW_WIDTH, WIDE_WIDTH);
		
		setUpAgentDropDown();
		
		VBox observerBox = new VBox();
		allPreferencesBox.getChildren().add(observerBox);

		List<Node> observerLabelList = new ArrayList<Node>();
		List<Node> mutableGuiObjectList = new ArrayList<Node>();

		
		if(currentAgentNameProperty.getValue()!=null){
			populateObserverLabelList(agentMap.get(currentAgentNameProperty.getValue()), observerLabelList);
			populateMutableGuiObjectList(agentMap.get(currentAgentNameProperty.getValue()),mutableGuiObjectList);
			
			addToAgentPrefBox(observerBox, observerLabelList);
			addToAgentPrefBox(allPreferencesBox,mutableGuiObjectList);
			}
	}

	private void setUpAgentDropDown() {
		ComboBox<String> agentDropDown = new ComboBox<String>();
		for (String name: agentMap.keySet()){
			agentDropDown.getItems().add(name);
		}
		agentDropDown.setValue(currentAgentNameProperty.getValue());
		agentDropDown.valueProperty().addListener(new ChangeListener<String>() {
            @Override public void changed(ObservableValue ov, String oldValue, String newValue) {                
            	currentAgentNameProperty.setValue(newValue);
				updateView();
            }
		});
		allPreferencesBox.getChildren().add(agentDropDown);
	}


	private void addToAgentPrefBox(Pane agentPrefBox,List<Node> ObjectList) {
		for (Object object: ObjectList){
			agentPrefBox.getChildren().add((Node)object);
		}
	}

	private void populateMutableGuiObjectList(Agent agent, List<Node> mutableGuiObjectList) {
		GuiObjectFactory objectFactory = new GuiObjectFactory();
		for (String property: agent.getMutableProperties()){
			Object guiObject= objectFactory.createNewGuiObject(property,agent);
			if (guiObject!= null){
				mutableGuiObjectList.add((Node) ((GuiObject) guiObject).createObjectAndReturnObject());
			
			}
		}
	}

	private void populateObserverLabelList(Agent agent, List<Node> observerLabelList) {
		for (String property: agent.getObserverProperties()){
			ObserverLabelFactory labelFactory = new ObserverLabelFactory();
			Object newLabel = labelFactory.createNewObserverLabel(property, agent);
			
			if (newLabel!= null){
				observerLabelList.add((Node) newLabel);
			}
		}
	}
	public void updateAgentMap(Map<String,Agent> newAgentMap){
		agentMap = (HashMap<String, Agent>) newAgentMap;
		updateView();
	}


	public StringProperty getCurrentAgentNameProperty() {
		return currentAgentNameProperty;
	}

	public void updateCurrentAgentSelection() {
		updateView();
	}


}
