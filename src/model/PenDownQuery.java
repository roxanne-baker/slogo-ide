package model;

import java.util.List;

import controller.TurtleTracker;

public class PenDownQuery extends Command implements Executable{

	TurtleTracker turtleTracker;
	
	public PenDownQuery(TurtleTracker turtleController) {
		turtleTracker = turtleController;
		numParams = 0;
	}
	
	public double execute(List<Object> params) {
		if (turtleTracker.isCurrentAgentPenUp()) {
			return 0;
		}
		else {
			return 1;
		}
	}
	
	public String checkParamTypes(List<Object> params) {
		return null;
	}	
}
