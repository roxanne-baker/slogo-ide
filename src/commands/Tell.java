package commands;

import java.util.List;

import controller.TurtleController;

public class Tell {

	TurtleController turtleController;
	public Tell(TurtleController turtleController) {
		this.turtleController = turtleController;
	}
	
	public double execute(List<Object> params) {
		int[] turtleNums = stringToIntArray((String) params.get(0));
		
		
		return turtleController.getNumAgents();
	}
	
	public int[] stringToIntArray(String param) {
		String[] numsAsStringArray = param.split(" ");
		int[] turtleNums = new int[numsAsStringArray.length];
		for (int i=0; i<numsAsStringArray.length; i++) {
			turtleNums[i] = Integer.parseInt(numsAsStringArray[i]);
		}
		return turtleNums;
	}
	
	
}
