
package commands;

import java.util.List;
import controller.TurtleController;

public class PenDownQuery extends Command implements Executable{

	TurtleController turtleTracker;
	
	public PenDownQuery(TurtleController turtleController) {
		turtleTracker = turtleController;
		numParams = 0;
	}
	
	public double execute(List<Object> params) {
		if (turtleTracker.isCurrentAgentPenUp()) {
			return 0;
		}
		else {
			return 1;
		}
	}
	
	public String checkParamTypes(List<Object> params) {
		return null;
	}	
}
