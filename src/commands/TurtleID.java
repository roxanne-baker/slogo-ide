package commands;

import java.util.List;

import controller.ControllerTurtle;
import model.Agent;

public class TurtleID extends TurtleQueryCommands implements Executable{

	public TurtleID(ControllerTurtle turtleController) {
		super(turtleController);
	}
	
	public Object execute(List<Object> params) {
		return getTurtleController().getAgentProperties((Agent agent) -> agent.getName()+0.0);
	}
	
}
