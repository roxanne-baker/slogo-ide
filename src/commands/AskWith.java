package commands;

import java.util.ArrayList;
import java.util.List;

import commands.Command;
import commands.Executable;
import controller.ControllerTurtle;
import parsing.Interpreter;

public class AskWith extends Command implements Executable {
	
	ControllerTurtle turtleController;
	Interpreter interpreter;
	public AskWith(Interpreter interpreter, ControllerTurtle turtleController) {
		this.turtleController = turtleController;
		this.interpreter = interpreter;
		numParams = 2;
	}
	
	public Object execute(List<Object> params) {
		String turtleCondition = (String) params.get(0);
		String commandsToRun = (String) params.get(1);
		List<Integer> currentlyActiveAgents = turtleController.getActiveAgents();
		List<Integer> turtlesBeingAsked = getTurtleList(turtleCondition);
		
		turtleController.setActiveAgents(turtlesBeingAsked);
		interpreter.run(commandsToRun);
		turtleController.setActiveAgents(currentlyActiveAgents);
		return interpreter.getReturnResult();
	}
	
	private List<Integer> getTurtleList(String turtleCondition) {
		List<Integer> turtlesMeetingCondition = new ArrayList<>();
		List<Integer> allTurtles = turtleController.getAgentNames();
		
		List<Integer> currentAgent = new ArrayList<>();
		for (Integer agentID : allTurtles) {
			currentAgent.add(agentID);
			turtleController.setActiveAgents(currentAgent);
			interpreter.run(turtleCondition);
			if (!isEqual(Double.parseDouble(interpreter.getReturnResult()), 0)) {
				turtlesMeetingCondition.add(agentID);
			}
			currentAgent.remove(agentID);
		}
		return turtlesMeetingCondition;
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
