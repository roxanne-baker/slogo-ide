package view;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Observable;
import java.util.ResourceBundle;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import GUI.GuiObject;
import GUI.GuiObjectFactory;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.scene.Node;
import javafx.scene.control.ComboBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import model.Agent;


/**
 * This class displays all the Agents properties with some components that can/cannot be changed by the user. 
 * @author Melissa Zhang
 *
 */
public class ViewAgentPreferences extends View{
	private HashMap<Integer, Agent> agentMap;
	private IntegerProperty currentAgentIDProperty;
	private CustomColorPalette colorPalette;
	

	private VBox allPreferencesBox = new VBox();
	private static final ResourceBundle UPDATE_RESOURCES = ResourceBundle.getBundle("updateObserver");

	private ResourceBundle cssResources = ResourceBundle.getBundle("CSSClasses");
	private ComboBox<String> agentDropDown;
	
	public ViewAgentPreferences(ViewType ID, Preferences savedPreferences) {
		super(ID, savedPreferences);
		setPane(allPreferencesBox);
		allPreferencesBox.getStyleClass().addAll(cssResources.getString("DISPLAYVIEW"),cssResources.getString("VBOXVIEW"));

		agentMap = new HashMap<Integer,Agent>();
		currentAgentIDProperty = new SimpleIntegerProperty();

		addListenerToCurrentAgentProperty(currentAgentIDProperty);


	}

	private void addListenerToCurrentAgentProperty(IntegerProperty property) {
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

		if (currentAgentIDProperty.getValue()!=null && agentMap.get(currentAgentIDProperty.getValue())!=null){
			populateObserverLabelList(agentMap.get(currentAgentIDProperty.getValue()), observerLabelList);
			populateMutableGuiObjectList(agentMap.get(currentAgentIDProperty.getValue()),mutableGuiObjectList);
				
			addToAgentPrefBox(observerBox, observerLabelList);
			addToAgentPrefBox(allPreferencesBox,mutableGuiObjectList);
			
		}
	}

	private void setUpAgentDropDown() {
		agentDropDown = new ComboBox<String>();
		for (Integer name: agentMap.keySet()){
			agentDropDown.getItems().add(""+name);
		}
		agentDropDown.setValue(currentAgentIDProperty.getValue().toString());
		agentDropDown.valueProperty().addListener(new ChangeListener<String>() {
            @Override public void changed(ObservableValue ov, String oldValue, String newValue) {
            	currentAgentIDProperty.setValue(Integer.parseInt(agentDropDown.getValue()));
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
	public void updateAgentMap(HashMap<Integer,Agent> newAgentMap){
		agentMap = (HashMap<Integer, Agent>) newAgentMap;
		updateView();
	}


	public IntegerProperty getCurrentAgentNameProperty() {
		return currentAgentIDProperty;
	}

	public void updateCurrentAgentSelection() {
		updateView();
	}

	public CustomColorPalette getColorPalette() {
		return colorPalette;
	}

	@Override
	public int getX() {
		return COORD00[0];
	}

	@Override
	public int getY() {
		return COORD00[1];
	}


}
