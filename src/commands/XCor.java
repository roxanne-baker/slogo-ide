package commands;

import java.util.List;

import controller.TurtleController;
import view.Agent;

public class XCor extends TurtleQueryCommands implements Executable{

	public XCor(TurtleController turtleController) {
		super(turtleController);
		numParams = 0; 
	}
	
	public Object execute(List<Object> params) {
		return getTurtleController().getAgentProperties((Agent agent) -> agent.getXPosition());
		
//		return getTurtleController().getCurrentAgentXPosition();
	}
}

