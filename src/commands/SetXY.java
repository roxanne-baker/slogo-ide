package commands;

import java.util.List;

import controller.TurtleController;

public class SetXY extends TurtleCommand implements Executable {
	
	public SetXY(TurtleController turtleController) {
		setTurtleController(turtleController);
		numParams = 2;
	}

	public double execute(List<Object> params) {
		double changeX = (double) params.get(0) - getTurtleController().getCurrentAgentXPosition();
		double changeY = (double) params.get(1) - getTurtleController().getCurrentAgentYPosition();

		getTurtleController().moveCurrentAgent(changeX, changeY);
		double distanceMoved = Math.sqrt(Math.pow(changeX, 2) + Math.pow(changeY, 2));
		
		return distanceMoved;
	}
}
