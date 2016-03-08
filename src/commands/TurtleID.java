package commands;

import java.util.List;

import controller.TurtleController;

public class TurtleID {

	TurtleController turtleController;
	public TurtleID(TurtleController turtleController) {
		this.turtleController = turtleController;
	}
	
	public double execute(List<Object> params) {
		return turtleController.getCurrentAgentName();
	}
	
	
}
