
package commands;

import java.util.List;
import controller.ControllerTurtle;
import model.Agent;

public class Window extends TurtleQueryCommands implements Executable {

	public Window(ControllerTurtle turtleController) {
		super(turtleController);
	}
	
	public Object execute(List<Object> params) {
		getTurtleController().changeProperty((Agent agent) -> agent.setBorderProperty(2));
		return 3;
	}
	
	public String checkParamTypes(List<Object> params) {
		return null;
	}
}