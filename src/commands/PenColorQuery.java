
package commands;

import java.util.List;
import controller.TurtleController;
import view.Agent;

public class PenColorQuery extends TurtleQueryCommands implements Executable{
	
	TurtleController turtleController;
	public PenColorQuery(TurtleController turtleController) {
		super(turtleController);
	}
	
	public Object execute(List<Object> params) {
		return getTurtleController().getAgentProperties((Agent agent) -> agent.getPenColorIndex()+0.0);
	}
}
