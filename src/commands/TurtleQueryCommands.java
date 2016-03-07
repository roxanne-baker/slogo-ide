package commands;

import java.util.List;
import controller.TurtleController;

public abstract class TurtleQueryCommands extends Command implements Executable{

	private TurtleController turtleController;
	
	protected TurtleQueryCommands(TurtleController turtleController) {
		this.turtleController = turtleController;
		numParams = 0;
	}
	
	public String checkParamTypes(List<Object> params) {
		return null;
	}

	protected TurtleController getTurtleController() {
		return turtleController;
	}
	
}