package commands;

import java.util.List;

import controller.TurtleController;
import view.Agent;

public class Towards extends TurtleCommand implements Executable {
	
	public Towards(TurtleController turtleController) {
		super(turtleController);
		numParams = 2;
	}
	
	public Object execute(List<Object> params) {
			double[] changeX = getTurtleController().getAgentProperties((Agent agent) -> agent.getXPosition());
			double[] changeY = getTurtleController().getAgentProperties((Agent agent) -> agent.getYPosition());
			double[] orientation = getTurtleController().getAgentProperties((Agent agent) -> agent.getOrientation());
			changeX = setChangeXAndY(changeX, params.get(0));
			changeY = setChangeXAndY(changeY, params.get(1));
			double[] changeDegrees = getChangeDegrees(changeX, changeY, orientation);
			getTurtleController().changeTurtleProperty(changeDegrees, (Agent agent, Double degrees) -> agent.changeOrientation(degrees));
			
			return changeDegrees;
		}	
	
		private double[] getChangeDegrees(double[] changeX, double[] changeY, double[] orientation) {
			double[] changeDegrees = new double[changeX.length];
			
			for (int i=0; i<changeDegrees.length; i++) {
				if (changeY[i] == 0) {
					changeDegrees[i] = 180 + (90*(changeX[i] / Math.abs(changeX[i])));
				}
				else {
					changeDegrees[i] = Math.toDegrees(Math.atan(Math.abs(changeX[i])/Math.abs(changeY[i])));
					if (changeY[i] < 0 && changeX[i] < 0) {
						changeDegrees[i] *= -1;
					}
					else if (changeY[i] < 0 && changeX[i] > 0) {
						changeDegrees[i] = 180 - changeDegrees[i];
					}
				}
				changeDegrees[i] -= (orientation[i] % 360);
			}
			return changeDegrees;
		}
		
		private double[] setChangeXAndY(double[] changeCoord, Object param) {
//			double[] orientation = getTurtleController().getAgentProperties((Agent agent) -> agent.getOrientation());
			for (int i=0; i<changeCoord.length; i++) {
				if (param instanceof Double) {
					changeCoord[i] -= ((double) param)*-1;
				}
				else {
					changeCoord[i] -= (((double[]) param)[i])*-1;
				}
			}
			return changeCoord;
		}
//		
//		double changeX = getTurtleController().getCurrentAgentXPosition()[0] - ((double) params.get(0));
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
//		
//		return (double) params.get(0);
//	}	
//	
//	private void setChangeXAndY(double[] changeCoord, Object param) {
//		if (params.get(0) instanceof Double) {
//			changeCoord = getTurtleController().getAgentProperties((Agent agent) -> agent.getXPosition() - (double) params.get(0));
//		}
//		else {
//			changeCoord = getTurtleController().get
//		}
//	}
}
