
package Model;

import java.util.List;

import Controller.TurtleTracker;

public class ShowTurtle extends Command implements Executable {

	TurtleTracker turtleTracker;
	public ShowTurtle(TurtleTracker turtleController) {
		turtleTracker = turtleController;
		numParams = 0;
	}
	
	public double execute(List<Object> params) {
		turtleTracker.setCurrentAgentVisible(true);
		return 1;
	}
	
	public String checkParamTypes(List<Object> params) {
		return null;
	}
	
	
}
