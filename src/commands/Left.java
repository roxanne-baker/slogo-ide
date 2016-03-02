
package commands;

import java.util.List;

import controller.TurtleController;

public class Left extends TurtleCommand implements Executable {

	public Left(TurtleController turtleController) {
		setTurtleController(turtleController);
		numParams = 1;
	}
	
	public double execute(List<Object> params) {
		double changeDegrees = (Double) params.get(0) % 360;
		getTurtleController().changeCurrentAgentOrientation(-changeDegrees);
		System.out.println(getTurtleController().getCurrentAgentOrientation());
		return changeDegrees;
	}
}