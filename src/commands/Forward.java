
package commands;

import java.util.List;
import controller.TurtleController;

public class Forward extends Command implements Executable {

	TurtleController turtleTracker;
	public Forward(TurtleController turtleController) {
		numParams = 1;
		turtleTracker = turtleController;
	}
	
	public double execute(List<Object> params) {
		double distance = (Double) params.get(0);
		double orientation = turtleTracker.getCurrentAgentOrientation();
		double changeX = distance*Math.sin(orientation);
		double changeY = distance*Math.cos(orientation);
		
		turtleTracker.moveCurrentAgent(changeX, changeY);
	
		return distance;
	}
	
	public String checkParamTypes(List<Object> params) {
		Object param = params.get(0);
		if (param instanceof Integer || param instanceof Double) {
			return null;
		}
		else {
			return String.format(errors.getString("WrongParamType"), param.toString());
		}
	}	
}