package commands;

import java.util.ArrayList;
import java.util.List;

import controller.TurtleController;

public class Tell extends ControlCommand implements Executable{

	TurtleController turtleController;
	public Tell(TurtleController turtleController) {
		this.turtleController = turtleController;
		numParams = 1;
	}
	
	public double execute(List<Object> params) {
		List<Integer> turtleNums = stringToIntList((String) params.get(0));
		System.out.println(params.get(0));
		turtleController.setActiveAgents(turtleNums);
		
		return turtleNums.get(turtleNums.size()-1);
	}
	
	private List<Integer> stringToIntList(String param) {
		String[] numsAsStringArray = param.split(" ");
		List<Integer> turtleNums = new ArrayList<Integer>();
		for (int i=0; i<numsAsStringArray.length; i++) {
			turtleNums.add(Integer.parseInt(numsAsStringArray[i]));
		}
		return turtleNums;
	}
	
	public String checkParamTypes(List<Object> params) {
		for (Object param : params) {
			if (!(param instanceof String)) {
				return String.format(errors.getString("WrongParamType"), param.toString());
			}
		}
		return null;
	}
	
	
}
