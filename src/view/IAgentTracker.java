package view;
import java.util.ArrayList;
import java.util.List;

import javafx.scene.image.ImageView;
import javafx.scene.paint.Paint;

/**
 * This external interface is for tracking agents on the screen. API calls can change the current agent (i.e. current turtle), remove agents, add agents, get list of agents, etc. You can also change the properties of the current agent. 
 * @author Melissa Zhang
 *
 */
public interface IAgentTracker{
	int getNumAgents();
	List<Agent> getAgents();
	List<String> getAgentNames();
	void addAgent(String agentName); //Throws error if agentName has already been created
	void removeAgent(String agentName);
	boolean isAgent(String name); 
	boolean isValidAgentName(String name);
	
	//Specific to current agent
	void setCurrentAgent(String agentName);

	String getCurrentAgent();
	void moveCurrentAgent(double changeX,double changeY);
	double getCurrentAgentXPosition();
	double getCurrentAgentYPosition();
	void setCurrentAgentColor(String color); //doesn't work yet
	String getCurrentAgentColor();

	void setCurrentAgentImage(String imagePath);
	void setCurrentAgentPenUp(boolean isUp);
	boolean isCurrentAgentPenUp();
	void setCurrentAgentVisible(boolean isVisible);
	void changeCurrentAgentOrientation(double changeDegrees);
	double getCurrentAgentOrientation();
	void stampCurrentAgent();

	void changeCurrentAgentSize(int size); //doesn't work yet
	double getCurrentAgentSize(int size); //doesn't work yet
	Agent getCurrentAgent(String agentName);
	String getCurrentAgentName();

	
	
	
}
