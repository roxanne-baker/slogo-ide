package commands;

import java.util.Arrays;
import java.util.List;
import java.util.Random;

import controller.TurtleController;

public class RandomCommand extends Command implements Executable {

	TurtleController turtleController;
	public RandomCommand(TurtleController turtleController) {
		this.turtleController = turtleController;
		numParams = 1;
	}
	
	public Object execute(List<Object> params) {
		// need to figure out how to communicate with front-end
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
	
	public String checkParamTypes(List<Object> params) {
		for (Object param : params) {
			if (!(param instanceof Integer)) {
				return String.format(errors.getString("WrongParamType"), param.toString());
			}			
		}
		return null;
	}
	
	
}
