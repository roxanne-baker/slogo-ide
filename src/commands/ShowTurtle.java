package commands;

import java.util.List;

import controller.TurtleController;

public class ShowTurtle extends TurtleQueryCommands implements Executable {

	public ShowTurtle(TurtleController turtleController) {
		super(turtleController);
	}
	
	public double execute(List<Object> params) {
		getTurtleController().setCurrentAgentVisible(true);
		return 1;
	}
}