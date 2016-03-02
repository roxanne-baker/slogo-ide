
package commands;

import java.util.List;

import controller.TurtleController;

public class Heading extends TurtleQueryCommands implements Executable{
	
	public Heading(TurtleController turtleController) {
		super(turtleController);
	}
	
	public double execute(List<Object> params) {
		return getTurtleController().getCurrentAgentOrientation();
	}
}
