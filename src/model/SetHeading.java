package model;

import java.util.List;

import controller.TurtleController;
import view.TurtleTracker;

public class SetHeading extends Command implements Executable {

	TurtleTracker turtleTracker;
	
	public SetHeading(TurtleTracker turtleController) {
		numParams = 1;
		turtleTracker = turtleController;
	}
	
	public double execute(List<Object> params) {
		// need to figure out how to communicate with front-end
		double orientationToSet = (double) params.get(0);
		double currOrientation = turtleTracker.getCurrentAgentOrientation();
		
		turtleTracker.changeCurrentAgentOrientation(orientationToSet-currOrientation);
		
		return orientationToSet;
	}
	
	public String checkParamTypes(List<Object> params) {
		Object param = params.get(0);
		if (param instanceof Integer || param instanceof Double) {
			return null;
		}
		else {
			return String.format(errors.getString("WrongParamType"), param.toString());
		}
	}
	
	
}
