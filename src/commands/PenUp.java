
package commands;

import java.util.List;

import controller.ControllerTurtle;
import model.Agent;

public class PenUp extends TurtleQueryCommands implements Executable {
	
	public PenUp(ControllerTurtle turtleController) {
		super(turtleController);
		numParams = 0; 
	}
	
	public Object execute(List<Object> params) {
		getTurtleController().changeProperty((Agent agent) -> agent.setPenUp(true));
		return 0;
	}	
}