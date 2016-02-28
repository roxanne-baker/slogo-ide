
package Model;

import java.util.List;

import Controller.TurtleTracker;

public class PenDown extends Command implements Executable {

	public PenDown(TurtleTracker turtleController) {
		numParams = 0;
	}
	
	public double execute(List<Object> params) {
		// need to figure out how to communicate with front-end
		return 1;
	}
	
	public String checkParamTypes(List<Object> params) {
		return null;
	}
	
	
}