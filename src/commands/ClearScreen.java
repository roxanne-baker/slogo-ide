package commands;

import java.util.List;

import controller.BackgroundController;
import controller.TurtleController;
import view.Agent;

public class ClearScreen extends Command implements Executable {

	BackgroundController backgroundController;
	TurtleController turtleController;
	
	public ClearScreen(TurtleController turtleController, BackgroundController bgController) {
		this.turtleController = turtleController;
		this.backgroundController = bgController;
		numParams = 0;
	}

	public Object execute(List<Object> params) {
		backgroundController.clearScreen();
		double[] currX = turtleController.getAgentProperties((Agent agent) -> -agent.getXPosition());
		double[] currY = turtleController.getAgentProperties((Agent agent) -> -agent.getYPosition());
		
		double[] distanceMoved = new double[currX.length];
		for (int i=0; i<currX.length; i++) {
			distanceMoved[i] = Math.sqrt(Math.pow(currX[i], 2) + Math.pow(currY[i], 2));
		}
		turtleController.moveCurrentAgent(currX, currY);
		
		return distanceMoved;
	}

	public String checkParamTypes(List<Object> params) {
		return null;
	}
}