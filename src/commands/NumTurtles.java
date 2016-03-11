package commands;

import java.util.List;

import controller.TurtleController;

public class NumTurtles extends TurtleQueryCommands implements Executable{

	public NumTurtles(TurtleController turtleController) {
		super(turtleController);
	}
	
	public Object execute(List<Object> params) {
		return getTurtleController().getNumAgents()+0.0;
	}
	
	
}
