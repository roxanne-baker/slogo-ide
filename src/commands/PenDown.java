
package commands;

import java.util.List;

import controller.TurtleController;

public class PenDown extends TurtleQueryCommands implements Executable {

	public PenDown(TurtleController turtleController) {
		super(turtleController);
		numParams = 0;
	}
	
	public double execute(List<Object> params) {
		// need to figure out how to communicate with front-end
		getTurtleController().setCurrentAgentPenUp(false);
		return 1;
	}
}