package commands;

import java.util.List;

import controller.TurtleController;
import view.Agent;

public class ShowTurtle extends TurtleQueryCommands implements Executable {

	public ShowTurtle(TurtleController turtleController) {
		super(turtleController);
	}
	
	public double execute(List<Object> params) {
//		getTurtleController().setCurrentAgentVisible(true);
		getTurtleController().changeProperty(((Agent agent) -> agent.setVisible(true)));
		return 1;
	}
}