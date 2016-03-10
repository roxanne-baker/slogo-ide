package controller;
import java.util.List;

import view.Agent;


/**
 * This external interface is for tracking agents on the screen. API calls can change the current agent (i.e. current turtle), remove agents, add agents, get list of agents, etc. You can also change the properties of the current agent. 
 * @author Melissa Zhang
 *
 */
public interface IAgentController {
	int getNumAgents();
	List<Agent> getAgents();
	List<String> getAgentNames();
	void addAgent(String agentName); 
	void removeAgent(String agentName);
	boolean isAgent(String name); 
	boolean isValidAgentName(String name);
	
	//Specific to current agent
	void setCurrentAgent(String agentName);

	String getCurrentAgent();
	void moveCurrentAgent(double changeX,double changeY);
	double getCurrentAgentXPosition();
	double getCurrentAgentYPosition();


	void setCurrentAgentImage(String imagePath);
	void setCurrentAgentPenUp(boolean isUp);
	boolean isCurrentAgentPenUp();
	void setCurrentAgentPenColorIndex(int colorIndex);
	void setCurrentAgentPenThickness(double thickness);
	void setCurrentAgentShapeIndex(int shapeIndex);
	int getCurrentAgentPenColorIndex();
	int getCurrentAgentShapeIndex();
	void setCurrentAgentVisible(boolean isVisible);
	void changeCurrentAgentOrientation(double changeDegrees);
	double getCurrentAgentOrientation();
	void stampCurrentAgent();
	void clearStamps(); //maybe somewhere else?

	void changeCurrentAgentSize(double size); //don't think we need these
	double getCurrentAgentSize(); //don't think we need these
	Agent getCurrentAgent(String agentName);
	String getCurrentAgentName();

	
	
	
}
