package commands;

import java.util.List;

import controller.ControllerTurtle;

public class NumTurtles extends TurtleQueryCommands implements Executable{

	public NumTurtles(ControllerTurtle turtleController) {
		super(turtleController);
	}
	
	public Object execute(List<Object> params) {
		return getTurtleController().getNumTotalAgents()+0.0;
	}
	
	
}
