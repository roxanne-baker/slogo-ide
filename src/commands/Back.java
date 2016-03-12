package commands;

import java.util.List;

import controller.ControllerTurtle;
import view.Agent;

public class Back extends TurtleCommand implements Executable {


	public Back(ControllerTurtle turtleController) {
		super(turtleController);
		numParams = 1;
	}
	
	public Object execute(List<Object> params) {
		double[] distance = new double[getTurtleController().getNumActiveAgents()];
		double[] orientation = getTurtleController().getAgentProperties((Agent agent) -> agent.getOrientation());
		double[] changeX = new double[getTurtleController().getNumActiveAgents()];
		double[] changeY = new double[getTurtleController().getNumActiveAgents()];
		for (int i=0; i<getTurtleController().getNumActiveAgents(); i++) {
			if (params.get(0) instanceof Double) {
				distance[i] = (Double) params.get(0);
			}
			else {
				distance[i] = ((double[]) params.get(0))[i];		
			}
			changeX[i] = -distance[i]*Math.sin(Math.toRadians(orientation[i]));
			changeY[i] = distance[i]*Math.cos(Math.toRadians(orientation[i]));	
		}		
		getTurtleController().moveCurrentAgent(changeX, changeY);			
		return distance;
	}
}
