
package commands;

import java.util.List;
import controller.TurtleController;

public class Forward extends TurtleCommand implements Executable {


	public Forward(TurtleController turtleController) {
		numParams = 1;
		setTurtleController(turtleController);
	}
	
	public double execute(List<Object> params) {
		double distance = (Double) params.get(0);
		double orientation = getTurtleController().getCurrentAgentOrientation();
		double changeX = distance*Math.sin(Math.toRadians(orientation));
		double changeY = -distance*Math.cos(Math.toRadians(orientation));
		
		getTurtleController().moveCurrentAgent(changeX, changeY);
	
		return distance;
	}
}
