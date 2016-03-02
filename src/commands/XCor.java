package commands;

import java.util.List;

import controller.TurtleController;

public class XCor extends TurtleQueryCommands implements Executable{

	public XCor(TurtleController turtleController) {
		super(turtleController);
	}
	
	public double execute(List<Object> params) {
		return getTurtleController().getCurrentAgentXPosition();
	}
}

