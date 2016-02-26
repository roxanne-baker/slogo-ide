package Model;

import java.util.List;

import Controller.TurtleTracker;

public class YCor extends Command implements Executable{

	TurtleTracker turtleTracker;
	public YCor(TurtleTracker turtleController) {
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
