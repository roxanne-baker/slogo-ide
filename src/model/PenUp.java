package model;

import java.util.List;

import controller.TurtleController;

public class PenUp extends Command implements Executable {

	public PenUp(TurtleController turtleController) {
		numParams = 1;
	}
	
	public double execute(List<Object> params) {
		// need to figure out how to communicate with front-end
		return 1;
	}
	
	public String checkParamTypes(List<Object> params) {
		return null;
	}
	
	
}
