package view;
import java.util.ArrayList;
import java.util.List;
import java.util.Observable;

import javafx.geometry.Insets;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.layout.VBox;

/**
 * This class is an extension of the View abstract class. It will display all the Agents properties and will be user interactive. 
 * @author Melissa Zhang
 *
 */
public class ViewPreferences extends View{
	private List<Agent> agentList;
	private Group viewGroup;
	private VBox preferencesBox;
	private static final int PADDING = 10;
	public ViewPreferences(String id, List<Agent> aList) {
		super(id);
		agentList = aList;
		viewGroup = new Group();
	}

	@Override
	public void update(Observable updateType, Object agent) {
		System.out.println(updateType);
		if(updateType.equals("UPDATE")){ //don't think i need this
			updateView();
		}
		
	}



	@Override
	public Group getView() {
		updateView();
		return viewGroup;
	}

	private void updateView() {
		viewGroup.getChildren().remove(preferencesBox);
		GuiObjectFactory objectFactory = new GuiObjectFactory();
		preferencesBox = new VBox();
		System.out.println(agentList.size());
		for (Agent agent: agentList){
			List<Node> guiObjects = new ArrayList<Node>();
			VBox agentPrefBox = new VBox();
			for (String property: agent.getMutableProperties()){
				Object guiObject= objectFactory.createNewGuiObject(property,agent);
				if (guiObject!= null){
					guiObjects.add((Node) ((GuiObject) guiObject).createObjectAndReturnObject());
				
				}
			}
			for (Object guiObject: guiObjects){
				agentPrefBox.getChildren().add((Node)guiObject);
			}
			preferencesBox.setPadding(new Insets(0,PADDING,PADDING,PADDING));
			preferencesBox.getChildren().add(agentPrefBox);

		}


		viewGroup.getChildren().add(preferencesBox);
	}

}
