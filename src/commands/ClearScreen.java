package commands;

import java.util.List;

import controller.BackgroundController;
import controller.TurtleController;

public class ClearScreen extends Command implements Executable {

	BackgroundController backgroundController;
	TurtleController turtleController;
	
	public ClearScreen(TurtleController turtleController, BackgroundController bgController) {
		this.turtleController = turtleController;
		this.backgroundController = bgController;
		numParams = 0;
	}

	public double execute(List<Object> params) {
		backgroundController.clearScreen();
		
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