package model;

import java.util.List;

import controller.TurtleController;

public class ShowingQuery extends Command implements Executable{

	TurtleController turtleTracker;
	
	public ShowingQuery(TurtleController turtleController) {
		turtleTracker = turtleController;
		numParams = 0;
	}
	
	public double execute(List<Object> params) {
		// NEED CHECK FOR TURTLE SHOWING
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
