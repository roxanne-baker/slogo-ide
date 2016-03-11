package commands;

import java.util.List;

import controller.TurtleController;
import view.Agent;

public class TurtleID extends TurtleQueryCommands implements Executable{

	public TurtleID(TurtleController turtleController) {
		super(turtleController);
	}
	
	public Object execute(List<Object> params) {
		return getTurtleController().getAgentProperties((Agent agent) -> agent.getName()+0.0);
	}
	
}
