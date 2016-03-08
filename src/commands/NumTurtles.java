package commands;

import java.util.List;

import controller.TurtleController;

public class NumTurtles {

	TurtleController turtleController;
	public NumTurtles(TurtleController turtleController) {
		this.turtleController = turtleController;
	}
	
	public double execute(List<Object> params) {
		return turtleController.getNumAgents();
	}
	
	
}
