
package model;

import java.util.List;

import view.TurtleTracker;

public class XCor extends Command implements Executable{

	TurtleTracker turtleTracker;
	public XCor(TurtleTracker turtleController) {
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