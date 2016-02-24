import javafx.scene.image.ImageView;
import javafx.scene.paint.Paint;

/**
 * This external interface is for tracking agents on the screen. API calls can change the current agent (i.e. current turtle), remove agents, add agents, get list of agents, etc. You can also change the properties of the current agent. 
 * @author Melissa Zhang
 *
 */
public interface IAgentTracker{
	int getNumAgents();
	Agent[] getAgents();
	String[] getAgentNames();
	void addAgent(String agentName); //Throws error if agentName has already been created
	void removeAgent(String agentName);
	boolean isAgent(String name); 
	
	//Specific to current agent
	void setCurrentAgent(String agentName);
	void moveCurrentAgent(int changeX,int changeY);
	void setCurrentAgentColor(Paint color);
	Paint getCurrentAgentColor();
	void setCurrentAgentImageView(ImageView image);
	ImageView getCurrentAgentImageView(ImageView image);
	void setCurrentAgentPenUp(boolean isUp);
	boolean isCurrentAgentPenUp();
	void setCurrentAgentVisible(boolean isVisible);
	void changeCurrentAgentOrientation(double degrees);
	double getCurrentAgentOrientation();
	
	
	
}
