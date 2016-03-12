package commands;

import java.util.List;
import controller.ControllerTurtle;

public abstract class TurtleQueryCommands extends Command implements Executable{

	private ControllerTurtle turtleController;
	
	protected TurtleQueryCommands(ControllerTurtle turtleController) {
		this.turtleController = turtleController;
		numParams = 0;
	}
	
	public String checkParamTypes(List<Object> params) {
		return null;
	}

	protected ControllerTurtle getTurtleController() {
		return turtleController;
	}
	
}