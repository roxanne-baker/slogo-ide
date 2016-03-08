package view;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Observable;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.geometry.Insets;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.control.ComboBox;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

/**
 * This class is an extension of the View abstract class. It will display all the Agents properties and will be user interactive. 
 * @author Melissa Zhang
 *
 */
public class ViewAgentPreferences extends View{
	private HashMap<Integer, Agent> agentMap;
	private Group viewGroup;
	private HBox allPreferencesBox;
	private IntegerProperty currentAgentNameProperty;
	private HBox customColorBox;
	private List<HBox> customColorBoxes;
	private CustomColorPalette colorPalette;
	List<GuiObject> guiObjects;
	
	private static final int PADDING = 10;
	private static final int COLOR_CELL_SIZE = 10;
	public ViewAgentPreferences(String id) {
		super(id);
		viewGroup = new Group();
		agentMap = new HashMap<Integer,Agent>();
		currentAgentNameProperty = new SimpleIntegerProperty();
		colorPalette = new CustomColorPalette();
		guiObjects = new ArrayList<>();
		customColorBoxes = new ArrayList<>();
	}

	@Override
	public void update(Observable agent, Object updateType) {

		
	}

	@Override
	public Pane getView() {
		updateView();
		Pane pane = new Pane(viewGroup);
		setStyleClass(pane);
		return pane;
	}
	private void updateView() {
		viewGroup.getChildren().remove(allPreferencesBox);
		allPreferencesBox = new HBox();
		allPreferencesBox.setPadding(new Insets(0,PADDING,PADDING,PADDING));
		
		setUpAgentDropDown();		
		VBox observerBox = new VBox();
		allPreferencesBox.getChildren().add(observerBox);

		List<Node> observerLabelList = new ArrayList<Node>();
		List<Node> mutableGuiObjectList = new ArrayList<Node>();

		
		if(currentAgentNameProperty.getValue()!=null && currentAgentNameProperty.getValue() != 0){
			populateObserverLabelList(agentMap.get(currentAgentNameProperty.getValue()), observerLabelList);
			populateMutableGuiObjectList(agentMap.get(currentAgentNameProperty.getValue()),mutableGuiObjectList);
			
			addToAgentPrefBox(observerBox, observerLabelList);
			addToAgentPrefBox(allPreferencesBox,mutableGuiObjectList);
			}

		viewGroup.getChildren().add(allPreferencesBox);
		
		setUpCustomColors();
		
	}

	private void setUpAgentDropDown() {
		ComboBox<String> agentDropDown = new ComboBox<String>();
		for (Integer name: agentMap.keySet()){
			agentDropDown.getItems().add(""+name);
		}
		agentDropDown.setValue(""+currentAgentNameProperty.getValue());
		agentDropDown.valueProperty().addListener(new ChangeListener<String>() {
            @Override public void changed(ObservableValue ov, String oldValue, String newValue) {                
            	currentAgentNameProperty.setValue(Integer.parseInt(newValue));
				updateView();
            }
		});
		allPreferencesBox.getChildren().add(agentDropDown);
	}
	public void setUpCustomColors(){
//		for (HBox customColorBox : customColorBoxes) {
			if (allPreferencesBox.getChildren().contains(customColorBox)) {
				allPreferencesBox.getChildren().remove(customColorBox);
			}			
//		}
		customColorBox = new HBox();
		for(Color color: colorPalette.getCustomColorList()){			
			Rectangle colorSquare = new Rectangle(10,10);
			colorSquare.setStroke(Color.BLACK);
			colorSquare.setFill(color);
			customColorBox.getChildren().add(colorSquare);
		}
//		customColorBoxes.add(customColorBox);
		allPreferencesBox.getChildren().add(customColorBox);
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
				guiObjects.add((GuiObject) guiObject);
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
		return currentAgentNameProperty;
	}

	public void updateCurrentAgentSelection() {
		updateView();
	}

	public CustomColorPalette getColorPalette() {
		return colorPalette;
	}
}
