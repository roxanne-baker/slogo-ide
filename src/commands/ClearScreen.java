package commands;

import java.util.List;

import controller.TurtleController;

public class ClearScreen extends Command implements Executable {

	TurtleController turtleController;
	public ClearScreen(TurtleController turtleController) {
		this.turtleController = turtleController;
		numParams = 0;
	}

	public double execute(List<Object> params) {
		// NEED TO DO
		double currX = turtleController.getCurrentAgentXPosition();
		double currY = turtleController.getCurrentAgentYPosition();

		turtleController.moveCurrentAgent(-currX, -currY);
		double distanceMoved = Math.sqrt(Math.pow(currX, 2) + Math.pow(currY, 2));
		
		return distanceMoved;
	}

	public String checkParamTypes(List<Object> params) {
		return null;
	}
}
