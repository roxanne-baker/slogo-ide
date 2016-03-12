package controller;
import java.util.List;
import java.util.function.BiConsumer;
import java.util.function.Consumer;
import java.util.function.Function;

import javafx.collections.ObservableList;
import view.Agent;


/**
 * This external interface is for tracking agents on the screen. API calls can change the current agent (i.e. current turtle), remove agents, add agents, get list of agents, etc. You can also change the properties of the current agent. 
 * @author Melissa Zhang
 *
 */
public interface ControllerAgents {
	int getNumTotalAgents();
	int getNumActiveAgents();
	List<Agent> getAgents();
	List<Integer> getAgentNames();
	
	void setActiveAgents(List<Integer> activeAgents);
	List<Integer> getActiveAgents();
	
	void addAgent(Integer agentName); 
	void removeAgent(Integer agentName);
	boolean isAgent(Integer name); 
	boolean isValidAgentName(Integer name);
	
	//Specific to current agent
	void setCurrentAgent(Integer agentName);

	Integer getCurrentAgent();
	void moveCurrentAgent(double[] changeX,double[] changeY);

	Agent getCurrentAgent(Integer agentName);
	Integer getCurrentAgentName();

	//change agent properties
	void changeProperty(Consumer<Agent> turtleMethod);
	double[] getAgentProperties(Function<Agent, Double> propertyToGet);
	void changeTurtleProperty(double[] changePropertyValues, BiConsumer<Agent, Double> changeProperty);
	
		
	
	
}
