package commands;

import java.util.List;

import controller.ControllerTurtle;
import view.Agent;

public class ShowTurtle extends TurtleQueryCommands implements Executable {

	public ShowTurtle(ControllerTurtle turtleController) {
		super(turtleController);
	}
	
	public Object execute(List<Object> params) {
		getTurtleController().changeProperty((Agent agent) -> agent.setVisible(true));
		return 1;
	}
}