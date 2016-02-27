import java.util.List;

/**
 * This external interface is for tracking agents on the screen. API calls can change the current agent (i.e. current turtle), remove agents, add agents, get list of agents, etc. You can also change the properties of the current agent. 
 * @author Melissa Zhang
 *
 */
public interface IAgentTracker{
	int getNumAgents();
	List<String> getAgentNames();
	List<Agent> getAgents();
	void addAgent(String agentName); //Throws error if agentName has already been created
	void removeAgent(String agentName);
	boolean isAgent(String name); 
	boolean isValidAgentName(String name);
	Agent getCurrentAgent(String agentName);
	
	//Specific to current agent
	void setCurrentAgent(String agentName);
	String getCurrentAgentName();
	void moveCurrentAgent(int changeX,int changeY);
	double getCurrentAgentXPosition();
	double getCurrentAgentYPosition();
	void setCurrentAgentImage(String imagePath);
	void setCurrentAgentPenUp(boolean isUp);
	boolean isCurrentAgentPenUp();
	void setCurrentAgentVisible(boolean isVisible);
	void changeCurrentAgentOrientation(double changeDegrees);
	double getCurrentAgentOrientation();
	void stampCurrentAgent();
	void changeCurrentAgentSize(double size); //doesn't work yet
	double getCurrentAgentSize(); //doesn't work yet

	
	
	
}
