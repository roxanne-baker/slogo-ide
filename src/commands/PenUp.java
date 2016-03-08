
package commands;

import java.util.List;

import controller.TurtleController;

public class PenUp extends TurtleQueryCommands implements Executable {
	
	public PenUp(TurtleController turtleController) {
		super(turtleController);
	}
	
	public double execute(List<Object> params) {
		getTurtleController().setCurrentAgentPenUp(true);
		return 0;
	}	
}