
package commands;

import java.util.List;
import controller.TurtleController;

public class HideTurtle extends TurtleQueryCommands implements Executable {

	public HideTurtle(TurtleController turtleController) {
		super(turtleController);
	}
	
	public double execute(List<Object> params) {
		getTurtleController().setCurrentAgentVisible(false);
		return 0;
	}
	
	public String checkParamTypes(List<Object> params) {
		return null;
	}
}