
package commands;

import java.util.List;
import controller.ControllerTurtle;
import view.Agent;

public class HideTurtle extends TurtleQueryCommands implements Executable {

	public HideTurtle(ControllerTurtle turtleController) {
		super(turtleController);
	}
	
	public Object execute(List<Object> params) {
		getTurtleController().changeProperty((Agent agent) -> agent.setVisible(false));
		return 0;
	}
	
	public String checkParamTypes(List<Object> params) {
		return null;
	}
}