package commands;

import java.util.List;

import controller.TurtleController;
import view.Agent;

public class ShapeQuery {

	TurtleController turtleController;
	public ShapeQuery(TurtleController turtleController) {
		this.turtleController = turtleController;
	}
	
	public Object execute(List<Object> params) {
		return turtleController.getAgentProperties((Agent agent) -> agent.);
	}
	
}
