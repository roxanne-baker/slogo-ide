package model;

import java.util.List;

import view.TurtleTracker;

public class Back extends Command implements Executable {

	TurtleTracker turtleTracker;
	public Back(TurtleTracker turtleController) {
		numParams = 1;
		turtleTracker = turtleController;
	}
	
	public double execute(List<Object> params) {
		double distance = (Double) params.get(0);
		double orientation = turtleTracker.getCurrentAgentOrientation();
		double changeX = -distance*Math.sin(orientation);
		double changeY = -distance*Math.cos(orientation);
		
		turtleTracker.moveCurrentAgent(changeX, changeY);
	
		return distance;
	}
	
	public String checkParamTypes(List<Object> params) {
		Object paramValue = params.get(0);
		if (paramValue instanceof Integer || paramValue instanceof Double) {
			return null;
		}
		else {
			return String.format(errors.getString("WrongParamType"), paramValue.toString());
		}
	}
	
	
}
