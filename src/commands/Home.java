package commands;

import java.util.List;

import controller.ControllerTurtle;
import view.Agent;

public class Home extends TurtleQueryCommands implements Executable {

	public Home(ControllerTurtle turtleController) {
		super(turtleController);
		numParams = 0;
	}

	public Object execute(List<Object> params) {		
		double[] currX = getTurtleController().getAgentProperties((Agent agent) -> -agent.getXPosition());
		double[] currY = getTurtleController().getAgentProperties((Agent agent) -> -agent.getYPosition());
		
		double[] distanceMoved = new double[currX.length];
		for (int i=0; i<currX.length; i++) {
			distanceMoved[i] = Math.sqrt(Math.pow(currX[i], 2) + Math.pow(currY[i], 2));
		}
		getTurtleController().moveCurrentAgent(currX, currY);
		
		return distanceMoved;
	}
}
