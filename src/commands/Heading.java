
package commands;

import java.util.List;

import controller.TurtleController;
import view.Agent;

public class Heading extends TurtleQueryCommands implements Executable{
	
	public Heading(TurtleController turtleController) {
		super(turtleController);
	}
	
	public Object execute(List<Object> params) {
		return getTurtleController().getAgentProperties((Agent agent) -> agent.getOrientation());
	}
}
