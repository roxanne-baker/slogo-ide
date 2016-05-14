
package commands;

import java.util.List;
import controller.ControllerTurtle;
import model.Agent;

public class Fence extends TurtleQueryCommands implements Executable {

	public Fence(ControllerTurtle turtleController) {
		super(turtleController);
	}
	
	public Object execute(List<Object> params) {
		getTurtleController().changeProperty((Agent agent) -> agent.setBorderProperty(3));
		return 3;
	}
	
	public String checkParamTypes(List<Object> params) {
		return null;
	}
}