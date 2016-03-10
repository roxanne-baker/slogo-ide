
package commands;

import java.util.Arrays;
import java.util.List;

import controller.TurtleController;
import view.Agent;

public class Left extends TurtleCommand implements Executable {

	public Left(TurtleController turtleController) {
		setTurtleController(turtleController);
		numParams = 1;
	}
		
	public Object execute(List<Object> params) {
		double[] changeDegrees = new double[getTurtleController().getActiveAgents().size()];
		for (int i=0; i<changeDegrees.length; i++) {
			if (params.get(0) instanceof Double) {
				Arrays.fill(changeDegrees, (double) params.get(0));
			}
			else {
				changeDegrees = (double[]) params.get(0);
			}
		}			
		getTurtleController().changeTurtleProperty(changeDegrees, (Agent agent, Double degrees) -> agent.changeOrientation(-degrees));
		return changeDegrees;
	}
}