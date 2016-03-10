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
	List<Integer> getAgentNames();
	void addAgent(Integer agentName); 
	void removeAgent(Integer agentName);
	boolean isAgent(Integer name); 
	boolean isValidAgentName(Integer name);
	
	//Specific to current agent
	void setCurrentAgent(Integer agentName);

	Integer getCurrentAgent();
	void moveCurrentAgent(double[] changeX,double[] changeY);
	double[] getCurrentAgentXPosition();
	double getCurrentAgentYPosition();


	void setCurrentAgentImage(String imagePath);
	void setCurrentAgentPenUp(boolean isUp);
	double isCurrentAgentPenUp();
	void setCurrentAgentPenColor(int colorIndex);
	void setCurrentAgentPenThickness(double thickness);
	void setCurrentAgentShape(int shapeIndex);
	void setColorPalette(int colorIndex, int red, int green, int blue);
	int getCurrentAgentColorIndex();
	int getCurrentAgentShapeIndex();
	void setCurrentAgentVisible(boolean isVisible);
	void changeCurrentAgentOrientation(double changeDegrees);
	double getCurrentAgentOrientation();
	void stampCurrentAgent();
	void clearStamps(); //maybe somewhere else?

	void changeCurrentAgentSize(double size); //don't think we need these
	double getCurrentAgentSize(); //don't think we need these
	Agent getCurrentAgent(Integer agentName);
	Integer getCurrentAgentName();

	
	
	
}
