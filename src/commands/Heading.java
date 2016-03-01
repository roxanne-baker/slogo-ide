
package commands;

import java.util.List;

import controller.TurtleController;

public class Heading extends Command implements Executable{
	TurtleController turtleTracker;
	
	public Heading(TurtleController turtleController) {
		turtleTracker = turtleController;
		numParams = 0;
	}
	
	public double execute(List<Object> params) {
		return turtleTracker.getCurrentAgentOrientation();
	}
	
	public String checkParamTypes(List<Object> params) {
		return null;
	}
}
