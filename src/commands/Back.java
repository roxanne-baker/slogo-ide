package commands;

import java.util.List;

import controller.TurtleController;
import view.Agent;

public class Back extends TurtleCommand implements Executable {


	public Back(TurtleController turtleController) {
		numParams = 1;
		setTurtleController(turtleController);
	}
	
	public Object execute(List<Object> params) {
		double[] distance = new double[getTurtleController().getActiveAgents().size()];
		double[] orientation = getTurtleController().getAgentProperties((Agent agent) -> agent.getOrientation());
		double[] changeX = new double[getTurtleController().getActiveAgents().size()];
		double[] changeY = new double[getTurtleController().getActiveAgents().size()];
		
		List<Integer> activeAgents = getTurtleController().getActiveAgents();
		
		for (int i=0; i<activeAgents.size(); i++) {
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
