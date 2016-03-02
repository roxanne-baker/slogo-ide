
package commands;

import java.util.List;

import controller.TurtleController;

public class PenUp extends TurtleQueryCommands implements Executable {


	public PenUp(TurtleController turtleController) {
		super(turtleController);
	}
	
	public double execute(List<Object> params) {
		// need to figure out how to communicate with front-end
		getTurtleController().setCurrentAgentPenUp(true);
		return 1;
	}	
}