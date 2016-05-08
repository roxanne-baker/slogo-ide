
package commands;


import java.util.List;

import controller.ControllerTurtle;
import model.Agent;

public class Wrap extends TurtleQueryCommands implements Executable {

	public Wrap(ControllerTurtle turtleController) {
		super(turtleController);
		numParams = 0;
	}
	
	public Object execute(List<Object> params) {
		getTurtleController().changeProperty((Agent agent) -> agent.setWindowBehavior(1));
		return 1;
	}
}