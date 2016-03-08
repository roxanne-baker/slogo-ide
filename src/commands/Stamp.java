package commands;

import java.util.List;
import controller.TurtleController;

public class Stamp extends Command implements Executable {
	
	TurtleController turtleController;
	
	public Stamp(TurtleController turtleController) {
		numParams = 0;
		this.turtleController = turtleController;
	}
	
	public double execute(List<Object> params) {
		turtleController.stampCurrentAgent();
		return turtleController.getCurrentAgentShapeIndex();
	}
	
	public String checkParamTypes(List<Object> params) {
		return null;
	}
	
}
