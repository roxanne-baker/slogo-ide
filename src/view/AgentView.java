package view;

import javafx.scene.Group;

/**
 * This is the view for an agent. GetView() will return the image. You can also get the agent from the view. Shapes coorespond to shape
 * @author Melissa Zhang
 *
 */    
public class AgentView {
	private Agent agentObject;
	private Group agentGroup;
	private int agentSize;
	public AgentView(Agent agent,int size){
		agentObject = agent;
		agentSize = size;
		agentGroup = new Group();
	}
 	
	public Agent getAgent(){
		return agentObject;
	}
	public Group getView(){
		agentObject.getImagePath();
		return agentGroup;
	}
	public void updateView(){
		
	}
	public int getSize(){
		return agentSize;
		
	}


}
