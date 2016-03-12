package view;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Observable;

import GUI.GuiObject;
import GUI.GuiObjectFactory;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.control.ComboBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;

/**
 * This class is an extension of the View abstract class. It will display all the Agents properties and will be user interactive. 
 * @author Melissa Zhang
 *
 */
public class ViewPreferences extends View{
	private HashMap<String, Agent> agentMap;
	private Pane viewGroup;
	private VBox preferencesBox;
	private String currentAgent;
	private static final int PADDING = 10;
	
	public ViewPreferences(ViewType ID, Preferences savedPreferences) {
		super(ID, savedPreferences);
		viewGroup = new Pane();
		agentMap = new HashMap<String,Agent>();
		currentAgent = null;
	}

	@Override
	public void update(Observable agent, Object updateType) {

		
	}



	@Override
	public Pane getView() {
	updateView();
	return viewGroup;
	}

	private void updateView() {
		viewGroup.getChildren().remove(preferencesBox);
		preferencesBox = new VBox();
		preferencesBox.setPadding(new Insets(0,PADDING,PADDING,PADDING));
		ComboBox agentDropDown = new ComboBox();
		for (String name: agentMap.keySet()){
			agentDropDown.getItems().add(name);
		}
		agentDropDown.setValue(currentAgent);
		agentDropDown.valueProperty().addListener(new ChangeListener<String>() {
            @Override public void changed(ObservableValue ov, String t, String t1) {                
                currentAgent = t1;
				updateView();
            }
		});
		preferencesBox.getChildren().add(agentDropDown);
		VBox agentPrefBox = new VBox();
		List<Node> observerLabelList = new ArrayList<Node>();
			if(currentAgent!=null){
			populateObserverLabelList(agentMap.get(currentAgent), observerLabelList);
			
			List<Node> mutableGuiObjectList = new ArrayList<Node>();
			populateMutableGuiObjectList(agentMap.get(currentAgent),mutableGuiObjectList);
			
			addToAgentPrefBox(agentPrefBox, observerLabelList);
			addToAgentPrefBox(agentPrefBox, mutableGuiObjectList);
			}
		preferencesBox.getChildren().add(agentPrefBox);


		viewGroup.getChildren().add(preferencesBox);
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

	public void updateCurrentAgent(String agentName) {
		currentAgent = agentName;
		updateView();
	}

	public void updateCurrentAgentAndAgentMap(String newName,HashMap<String, Agent> newAgentMap) {
		agentMap = newAgentMap;
		currentAgent = newName;
		updateView();
	}


}