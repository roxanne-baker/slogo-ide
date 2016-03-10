
package commands;

import java.util.List;

import controller.TurtleController;
import view.Agent;

public class PenDown extends TurtleQueryCommands implements Executable {

	public PenDown(TurtleController turtleController) {
		super(turtleController);
		numParams = 0;
	}
	
	public Object execute(List<Object> params) {
		getTurtleController().changeProperty((Agent agent) -> agent.setPenUp(false));
		return 1;
	}
}