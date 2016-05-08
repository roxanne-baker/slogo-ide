
package commands;


import java.util.List;

import controller.ControllerTurtle;
import model.Agent;

public class Window extends TurtleQueryCommands implements Executable {

	public Window(ControllerTurtle turtleController) {
		super(turtleController);
		numParams = 0;
	}
	
	public Object execute(List<Object> params) {
		getTurtleController().changeProperty((Agent agent) -> agent.setWindowBehavior(3));
		return 3;
	}
}