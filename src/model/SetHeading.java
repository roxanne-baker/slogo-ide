package model;

import java.util.List;

import view.TurtleTracker;

public class SetHeading extends Command implements Executable {

	TurtleTracker turtleTracker;
	public SetHeading(TurtleTracker turtleController) {
		turtleTracker = turtleController;
		numParams = 1;
	}
	
	public double execute(List<Object> params) {
		double orientationToSet = (double) params.get(0);
		double currOrientation = turtleTracker.getCurrentAgentOrientation();
		
		turtleTracker.changeCurrentAgentOrientation(orientationToSet-currOrientation);
		return (double) params.get(0);
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
