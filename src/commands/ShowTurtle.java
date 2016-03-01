package commands;

import java.util.List;

import controller.TurtleController;

public class ShowTurtle extends Command implements Executable {

	TurtleController turtleTracker;
	public ShowTurtle(TurtleController turtleController) {
		turtleTracker = turtleController;
		numParams = 0;
	}
	
	public double execute(List<Object> params) {
		turtleTracker.setCurrentAgentVisible(true);
		return 1;
	}
	
	public String checkParamTypes(List<Object> params) {
		return null;
	}
}