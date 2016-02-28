package model;

import java.util.List;

import controller.TurtleTracker;

public class SetXY extends Command implements Executable {
	TurtleTracker turtleTracker;
	
	public SetXY(TurtleTracker turtleController) {
		turtleTracker = turtleController;
		numParams = 2;
	}

	public double execute(List<Object> params) {
		double changeX = (double) params.get(0) - turtleTracker.getCurrentAgentXPosition();
		double changeY = (double) params.get(1) - turtleTracker.getCurrentAgentYPosition();

		turtleTracker.moveCurrentAgent(changeX, changeY);
		double distanceMoved = Math.sqrt(Math.pow(changeX, 2) + Math.pow(changeY, 2));
		
		return distanceMoved;
	}

	public String checkParamTypes(List<Object> params) {
		for (Object param : params) {
			if (!(param instanceof Integer || param instanceof Double)) {
				return String.format(errors.getString("WrongParamType"), param.toString());
			}			
		}
		return null;
	}
}
