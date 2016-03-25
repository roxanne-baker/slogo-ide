// This entire file is part of my masterpiece. 
// I've taken out the checkParamTypes method from this class, along with the MathCommands class
// and put it into the Commands superclass. 
// This way, there is no duplicated error-checking code. 

package commands;

import controller.ControllerTurtle;

public abstract class TurtleCommand extends Command implements Executable{

	private ControllerTurtle turtleController;
	
	public TurtleCommand(ControllerTurtle turtleController) {
		this.turtleController = turtleController;
	}

	protected void setTurtleController(ControllerTurtle turtleController) {
		this.turtleController = turtleController;
	}
	
	protected ControllerTurtle getTurtleController() {
		return turtleController;
	}
	
}
