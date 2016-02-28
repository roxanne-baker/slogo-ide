package model;

import java.util.List;

import view.TurtleTracker;

public class Towards extends Command implements Executable {

	TurtleTracker turtleTracker;
	public Towards(TurtleTracker turtleController) {
		turtleTracker = turtleController;
		numParams = 2;
	}
	
	public double execute(List<Object> params) {
		double changeX = turtleTracker.getCurrentAgentXPosition() - ((double) params.get(0));
		double changeY = turtleTracker.getCurrentAgentYPosition() - ((double) params.get(1));
		
		double degrees;
		if (changeY == 0) {
			degrees = 180 + (90*(changeX / Math.abs(changeX)));
		}
		else {
			degrees = Math.toDegrees(Math.atan(changeX/changeY))* (changeY / Math.abs(changeY));	
		}
		
		double changeDegrees = degrees - (turtleTracker.getCurrentAgentOrientation() % 360);
		turtleTracker.changeCurrentAgentOrientation(changeDegrees);
		
		return (double) params.get(0);
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
