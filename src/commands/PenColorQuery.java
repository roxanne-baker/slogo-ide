
package commands;

import java.util.List;
import controller.TurtleController;

public class PenColorQuery extends TurtleQueryCommands implements Executable{
	
	TurtleController turtleController;
	public PenColorQuery(TurtleController turtleController) {
		super(turtleController);
	}
	
	public double execute(List<Object> params) {
		return turtleController.getCurrentAgentColorIndex();
	}
}
