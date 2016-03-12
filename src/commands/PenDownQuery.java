
package commands;

import java.util.List;
import controller.ControllerTurtle;
import model.Agent;

public class PenDownQuery extends TurtleQueryCommands implements Executable{
	
	public PenDownQuery(ControllerTurtle turtleController) {
		super(turtleController);
		numParams = 0; 
	}
	
	public Object execute(List<Object> params) {
		return getTurtleController().getAgentProperties((Agent agent) -> (agent.isVisible() ? 1.0 : 0.0));
	}
}
