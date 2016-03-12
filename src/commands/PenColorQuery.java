
package commands;

import java.util.List;
import controller.ControllerTurtle;
import view.Agent;

public class PenColorQuery extends TurtleQueryCommands implements Executable{
	
	ControllerTurtle turtleController;
	public PenColorQuery(ControllerTurtle turtleController) {
		super(turtleController);
	}
	
	public Object execute(List<Object> params) {
		return getTurtleController().getAgentProperties((Agent agent) -> agent.getPenColorIndex()+0.0);
	}
}
