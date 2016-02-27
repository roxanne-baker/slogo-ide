package model;

import java.util.List;

import controller.TurtleController;
import view.Turtle;

public class Forward extends Command implements Executable {

	Turtle currentTurtle;
	public Forward(TurtleController turtleController) {
		numParams = 1;
	}
	
	public double execute(List<Object> params) {
		// need to figure out how to communicate with front-end
		return (double) params.get(0);
	}
	
	public String checkParamTypes(List<Object> params) {
		Object param = params.get(0);
		if (param instanceof Integer || param instanceof Double) {
			return null;
		}
		else {
			return String.format(errors.getString("WrongParamType"), param.toString());
		}
	}
	
	
}
