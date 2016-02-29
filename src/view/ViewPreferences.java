package view;
import java.util.ArrayList;
import java.util.List;
import java.util.Observable;

import javafx.geometry.Insets;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;

/**
 * This class is an extension of the View abstract class. It will display all the Agents properties and will be user interactive. 
 * @author Melissa Zhang
 *
 */
public class ViewPreferences extends View{
	private List<Agent> agentList;
	private Group viewGroup;
	private HBox preferencesBox;
	private static final int PADDING = 10;
	public ViewPreferences(String id, List<Agent> aList) {
		super(id);
		agentList = aList;
		viewGroup = new Group();
	}

	@Override
	public void update(Observable updateType, Object agent) {
		System.out.println(updateType);
		updateView();
		
	}



	@Override
	public Group getView() {
		updateView();
		return viewGroup;
	}
	private void updateView() {
		viewGroup.getChildren().remove(preferencesBox);
		preferencesBox = new HBox();
		preferencesBox.setPadding(new Insets(0,PADDING,PADDING,PADDING));
		
		System.out.println(agentList.size());
		for (Agent agent: agentList){
			VBox agentPrefBox = new VBox();
			List<Node> observerLabelList = new ArrayList<Node>();
			populateObserverLabelList(agent, observerLabelList);
			
			List<Node> mutableGuiObjectList = new ArrayList<Node>();
			populateMutableGuiObjectList(agent,mutableGuiObjectList);
			
			addToAgentPrefBox(agentPrefBox, observerLabelList);
			addToAgentPrefBox(agentPrefBox, mutableGuiObjectList);
			
			preferencesBox.getChildren().add(agentPrefBox);

		}


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

}
