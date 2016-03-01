
package commands;

import java.util.List;

import controller.TurtleController;

public class PenUp extends Command implements Executable {

	TurtleController turtleController;
	public PenUp(TurtleController turtleController) {
		this.turtleController = turtleController;
		numParams = 1;
	}
	
	public double execute(List<Object> params) {
		// need to figure out how to communicate with front-end
		return 1;
	}
	
	public String checkParamTypes(List<Object> params) {
		return null;
	}
	
	
}