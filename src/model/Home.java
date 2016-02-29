package model;

import java.util.List;

import controller.TurtleController;

public class Home extends Command implements Executable {

	TurtleController turtleTracker;
	public Home(TurtleController turtleController) {
		turtleTracker = turtleController;
		numParams = 0;
	}

	public double execute(List<Object> params) {
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
