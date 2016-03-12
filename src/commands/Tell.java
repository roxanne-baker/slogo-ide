package commands;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import controller.ControllerTurtle;

public class Tell extends ControlCommand implements Executable{

	ControllerTurtle turtleController;
	public Tell(ControllerTurtle turtleController) {
		this.turtleController = turtleController;
		numParams = 1;
	}
	
	public Object execute(List<Object> params) {
		List<Integer> turtleNums = stringToIntList((String) params.get(0));
		turtleController.setActiveAgents(turtleNums);
		
		return turtleNums.get(turtleNums.size()-1)*1.0;
	}
	
	private List<Integer> stringToIntList(String param) {
		String[] numsAsStringArray = param.split(" ");
		System.out.println("ayyy: "+Arrays.toString(numsAsStringArray)+" "+numsAsStringArray.length);
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
