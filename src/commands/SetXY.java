package commands;

import java.util.Arrays;
import java.util.List;

import controller.TurtleController;
import view.Agent;

public class SetXY extends TurtleCommand implements Executable {
	
	public SetXY(TurtleController turtleController) {
		super(turtleController);
		numParams = 2;
	}

	public Object execute(List<Object> params) {
		double[] changeX = getChangeXAndY(params.get(0));
		double[] changeY = getChangeXAndY(params.get(1));
		
		double[] xcor = getTurtleController().getAgentProperties((Agent agent) -> agent.getXPosition());
		double[] ycor = getTurtleController().getAgentProperties((Agent agent) -> agent.getYPosition());
		
		double[] distanceMoved = new double[changeX.length];

		for (int i=0; i<changeX.length; i++) {
			changeX[i] -= xcor[i];
			changeY[i] -= ycor[i];
			distanceMoved[i] = Math.sqrt(Math.pow(changeX[i], 2) + Math.pow(changeY[i], 2));
		}
		getTurtleController().moveCurrentAgent(changeX, changeY);
		
		return distanceMoved;
	}
	
	private double[] getChangeXAndY(Object param) {
		double[] changeCoord = new double[getTurtleController().getActiveAgents().size()];
		if (param instanceof Double) {
			Arrays.fill(changeCoord, (double) param);
		}
		else {
			changeCoord = (double[]) param;
		}
		return changeCoord;
	}
}
