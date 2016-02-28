package Model;

import java.util.List;
import Controller.TurtleTracker;

public class HideTurtle extends Command implements Executable {

	TurtleTracker turtleTracker;
	public HideTurtle(TurtleTracker turtleController) {
		turtleTracker = turtleController;
		numParams = 0;
	}
	
	public double execute(List<Object> params) {
		turtleTracker.setCurrentAgentVisible(false);
		return 0;
	}
	
	public String checkParamTypes(List<Object> params) {
		return null;
	}
	
	
}