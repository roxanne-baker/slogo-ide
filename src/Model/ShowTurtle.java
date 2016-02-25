package Model;

import java.util.List;
import Controller.TurtleController;

public class ShowTurtle extends Command implements Executable {

	public ShowTurtle(TurtleController turtleController) {
		numParams = 0;
	}
	
	public double execute(List<Object> params) {
		// need to figure out how to communicate with front-end
		return 1;
	}
	
	public String checkParamTypes(List<Object> params) {
		return null;
	}
	
	
}
