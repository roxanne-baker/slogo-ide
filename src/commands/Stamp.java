package commands;

import java.util.List;
import controller.TurtleController;
import view.Agent;

public class Stamp extends Command implements Executable {
	
	TurtleController turtleController;
	
	public Stamp(TurtleController turtleController) {
		numParams = 0;
		this.turtleController = turtleController;
	}
	
	public Object execute(List<Object> params) {
		turtleController.changeProperty(((Agent agent) -> agent.leaveStamp()));
		return turtleController.getAgentProperties((Agent agent) -> agent.getCurrentImageIndex()+0.0);
	}
	
	public String checkParamTypes(List<Object> params) {
		return null;
	}
	
}
