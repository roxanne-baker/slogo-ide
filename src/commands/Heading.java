
package commands;

import java.util.List;

import controller.ControllerTurtle;
import view.Agent;

public class Heading extends TurtleQueryCommands implements Executable{
	
	public Heading(ControllerTurtle turtleController) {
		super(turtleController);
	}
	
	public Object execute(List<Object> params) {
		return getTurtleController().getAgentProperties((Agent agent) -> agent.getOrientation());
	}
}
