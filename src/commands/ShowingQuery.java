package commands;

import java.util.List;

import controller.TurtleController;

public class ShowingQuery extends TurtleQueryCommands implements Executable{
	
	public ShowingQuery(TurtleController turtleController) {
		super(turtleController);
	}
	
	public double execute(List<Object> params) {
		// NEED CHECK FOR TURTLE SHOWING
		if (getTurtleController().isCurrentAgentPenUp()) {
			return 0;
		}
		else {
			return 1;
		}
	}
}
