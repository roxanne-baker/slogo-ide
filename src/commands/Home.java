package commands;

import java.util.List;

import controller.TurtleController;

public class Home extends TurtleQueryCommands implements Executable {

	public Home(TurtleController turtleController) {
		super(turtleController);
	}

	public double execute(List<Object> params) {
		double currX = getTurtleController().getCurrentAgentXPosition();
		double currY = getTurtleController().getCurrentAgentYPosition();

		getTurtleController().moveCurrentAgent(-currX, -currY);
		double distanceMoved = Math.sqrt(Math.pow(currX, 2) + Math.pow(currY, 2));
		
		return distanceMoved;
	}
}
