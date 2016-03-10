package commands;

import java.util.List;

import controller.TurtleController;

public class SetHeading extends TurtleCommand implements Executable {

	public SetHeading(TurtleController turtleController) {
		setTurtleController(turtleController);
		numParams = 1;
	}
	
	public double execute(List<Object> params) {
		double orientationToSet = (double) params.get(0);
		double currOrientation = getTurtleController().getCurrentAgentOrientation();
		
		getTurtleController().changeCurrentAgentOrientation(orientationToSet-currOrientation);
		return (double) params.get(0);
	}
}
