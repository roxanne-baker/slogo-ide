package commands;

import java.util.ArrayList;
import java.util.List;

import parsing.Interpreter;
import controller.ControllerTurtle;

public class Ask extends Command implements Executable {
	
	ControllerTurtle turtleController;
	Interpreter interpreter;
	public Ask(Interpreter interpreter, ControllerTurtle turtleController) {
		this.turtleController = turtleController;
		this.interpreter = interpreter;
		numParams = 2;
	}
	
	public Object execute(List<Object> params) {
		String turtlesString = (String) params.get(0);
		String commandsToRun = (String) params.get(1);
		List<Integer> turtlesBeingAsked = getTurtleList(turtlesString);
		List<Integer> currentlyActiveAgents = turtleController.getActiveAgents();
		
		turtleController.setActiveAgents(turtlesBeingAsked);
		interpreter.run(commandsToRun);
		turtleController.setActiveAgents(currentlyActiveAgents);
		return interpreter.getReturnResult();
	}
	
	private List<Integer> getTurtleList(String turtlesBeingAsked) {
		String[] turtleStringArray = turtlesBeingAsked.split(" ");
		List<Integer> turtleIntegerList = new ArrayList<>();
		for (int i=0; i<turtleStringArray.length; i++) {
			turtleIntegerList.add(Integer.parseInt(turtleStringArray[i]));
		}
		return turtleIntegerList;
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
