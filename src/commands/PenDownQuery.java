
package commands;

import java.util.List;
import controller.TurtleController;

public class PenDownQuery extends TurtleQueryCommands implements Executable{
	
	public PenDownQuery(TurtleController turtleController) {
		super(turtleController);
	}
	
	public double execute(List<Object> params) {
		if (getTurtleController().isCurrentAgentPenUp()) {
			return 0;
		}
		else {
			return 1;
		}
	}
}
