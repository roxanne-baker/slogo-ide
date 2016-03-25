package view;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
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
 * This class is part of my masterpiece code. This code follows the Open/Closed Principle, has few dependencies, and introduces a lot of flexibility into the GUI. This class uses the GuiObjectFactory and ObserverLabelFactory to dynamically add new GUI elements that are user interactive/editable based on the MUTABLE_LIST and OBSERVER_LIST in the Turtle class. A GuiObject is editable by the user and propagates changes to the turtle model. An ObserverLabel displays information and is binded to a property in the turtle model. The factory classes add polymorphism so the view does not need to keep track of which GUI elements need to be created. The GUIObject and ObserverLabel classes also encapsulate any user interactivity so that this view only handles displaying the GUI elements. In addition, the displayed preferences dynamically update when the CurrentAgentProperty is changed in the ControllerTurtle class to reflect the model.   
 * This class displays all the Agents properties with some components that can/cannot be changed by the user.
 * @author Melissa Zhang
 *
 */
public class ViewAgentPreferences extends View{
	private HashMap<Integer, Agent> agentMap;
	private IntegerProperty currentAgentIDProperty;
	private VBox allPreferencesBox = new VBox();
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

	private void addListenerToCurrentAgentProperty(IntegerProperty property) {
			property.addListener(new ChangeListener<Object>(){

			@Override
			public void changed(ObservableValue<? extends Object> ov,
					Object oldValue, Object newValue) {
					updateView();
			}
		});
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



	@Override
	public int getX() {
		return COORD00[0];
	}

	@Override
	public int getY() {
		return COORD00[1];
	}


}
