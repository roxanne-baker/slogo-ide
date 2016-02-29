
package model;

import java.util.List;
import controller.TurtleController;

public class HideTurtle extends Command implements Executable {

	TurtleController turtleTracker;
	public HideTurtle(TurtleController turtleController) {
		turtleTracker = turtleController;
		numParams = 0;
	}
	
	public double execute(List<Object> params) {
		turtleTracker.setCurrentAgentVisible(false);
		return 0;
	}
	
	public String checkParamTypes(List<Object> params) {
		return null;
	}
}