package commands;

import java.util.List;
import java.util.Random;

import controller.TurtleController;

public class RandomCommand extends MathCommand implements Executable {

	TurtleController turtleController;
	public RandomCommand(TurtleController turtleController) {
		this.turtleController = turtleController;
		numParams = 1;
	}
	
	public Object execute(List<Object> params) {
		Random getRandInt = new Random();
		double[] randomArray = new double[turtleController.getActiveAgents().size()];
		for (int i=0; i<randomArray.length; i++) {
			if (params.get(0) instanceof Double) {
				Double maxDouble = (double) params.get(0);
				int max = maxDouble.intValue();
				randomArray[i] = getRandInt.nextInt(max);
			}
			else {
				double[] paramArray = (double[]) params.get(0);	
				int max = ((Double) paramArray[i]).intValue();
				randomArray[i] = getRandInt.nextInt(max);
			}
		}
		return randomArray;
	}	
}
