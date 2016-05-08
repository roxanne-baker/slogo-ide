
package commands;


import java.util.List;

import controller.ControllerTurtle;
import model.Agent;

public class Fence extends TurtleQueryCommands implements Executable {

	public Fence(ControllerTurtle turtleController) {
		super(turtleController);
		numParams = 0;
	}
	
	public Object execute(List<Object> params) {
		getTurtleController().changeProperty((Agent agent) -> agent.setWindowBehavior(2));
		return 1;
	}
}