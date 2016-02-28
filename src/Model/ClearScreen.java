package model;

import java.util.List;

import view.TurtleTracker;

public class ClearScreen extends Command implements Executable {

	TurtleTracker turtleTracker;
	public ClearScreen(TurtleTracker turtleController) {
		turtleTracker = turtleController;
		numParams = 0;
	}

	public double execute(List<Object> params) {
		// NEED TO DO
		double currX = turtleTracker.getCurrentAgentXPosition();
		double currY = turtleTracker.getCurrentAgentYPosition();

		turtleTracker.moveCurrentAgent(-currX, -currY);
		double distanceMoved = Math.sqrt(Math.pow(currX, 2) + Math.pow(currY, 2));
		
		return distanceMoved;
	}

	public String checkParamTypes(List<Object> params) {
		return null;
	}
}
