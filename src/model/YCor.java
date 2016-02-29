
package model;

import java.util.List;

import controller.TurtleController;

public class YCor extends Command implements Executable{

	TurtleController turtleTracker;
	public YCor(TurtleController turtleController) {
		turtleTracker = turtleController;
		numParams = 0;
	}
	
	public double execute(List<Object> params) {
		return turtleTracker.getCurrentAgentYPosition();
	}
	
	public String checkParamTypes(List<Object> params) {
		return null;
	}
	
	
}