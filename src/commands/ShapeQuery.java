package commands;

import java.util.List;

import controller.ControllerTurtle;
import view.Agent;

public class ShapeQuery {

	ControllerTurtle turtleController;
	public ShapeQuery(ControllerTurtle turtleController) {
		this.turtleController = turtleController;
	}
	
	public Object execute(List<Object> params) {
		return turtleController.getAgentProperties((Agent agent) -> agent.getCurrentImageIndex()+0.0);
	}
	
}
