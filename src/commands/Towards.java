package commands;

import java.util.List;

import controller.TurtleController;
import view.Agent;

public class Towards extends TurtleCommand implements Executable {
	
	public Towards(TurtleController turtleController) {
		setTurtleController(turtleController);
		numParams = 2;
	}
	
	public double execute(List<Object> params) {
//		double changeX = getTurtleController().getCurrentAgentXPosition() - ((double) params.get(0));
//		double changeY = getTurtleController().getCurrentAgentYPosition() - ((double) params.get(1));
//		
//		double degrees;
//		if (changeY == 0) {
//			degrees = 180 + (90*(changeX / Math.abs(changeX)));
//		}
//		else {
//			degrees = Math.toDegrees(Math.atan(changeX/changeY))* (changeY / Math.abs(changeY));	
//		}
//		
//		double changeDegrees = degrees - (getTurtleController().getCurrentAgentOrientation() % 360);
//		getTurtleController().changeCurrentAgentOrientation(changeDegrees);
		
		getTurtleController().changeProperty((Agent agent) -> {
			double changeX = getTurtleController().getCurrentAgentXPosition() - ((double) params.get(0));
			double changeY = getTurtleController().getCurrentAgentYPosition() - ((double) params.get(1));
			
			double degrees;
			if (changeY == 0) {
				degrees = 180 + (90*(changeX / Math.abs(changeX)));
			}
			else {
				degrees = Math.toDegrees(Math.atan(changeX/changeY))* (changeY / Math.abs(changeY));	
			}
			
			double changeDegrees = degrees - (getTurtleController().getCurrentAgentOrientation() % 360);	
			agent.changeOrientation(changeDegrees);
		});
		
		return (double) params.get(0);
	}	
}
