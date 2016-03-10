
package commands;

import java.util.List;

import controller.TurtleController;
import view.Agent;

public class Left extends TurtleCommand implements Executable {

	public Left(TurtleController turtleController) {
		setTurtleController(turtleController);
		numParams = 1;
	}
	
	public double execute(List<Object> params) {
		double changeDegrees = (Double) params.get(0) % 360;
		getTurtleController().changeProperty(((Agent agent) -> agent.changeOrientation(-changeDegrees)));	
		return changeDegrees;
	}
}