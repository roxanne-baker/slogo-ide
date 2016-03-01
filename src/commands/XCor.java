package commands;

import java.util.List;

import controller.TurtleController;

public class XCor extends Command implements Executable{

	TurtleController turtleTracker;
	public XCor(TurtleController turtleController) {
		turtleTracker = turtleController;
		numParams = 0;
	}
	
	public double execute(List<Object> params) {
		return turtleTracker.getCurrentAgentXPosition();
	}
	
	public String checkParamTypes(List<Object> params) {
		return null;
	}	
}

