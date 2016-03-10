package commands;

import java.util.List;

import controller.TurtleController;

public class Right extends TurtleCommand implements Executable {

	public Right(TurtleController turtleController) {
		setTurtleController(turtleController);
		numParams = 1;
	}
	
	public double execute(List<Object> params) {
		double changeDegrees = (Double) params.get(0) % 360;
		getTurtleController().changeCurrentAgentOrientation(changeDegrees);
	
		return changeDegrees;
	}	
}