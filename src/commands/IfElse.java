package commands;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import controller.ControllerTurtle;
import parsing.Interpreter;

public class IfElse extends ControlCommand implements Executable {

	Interpreter interpreter;
	ControllerTurtle turtleController;
	public IfElse(Interpreter interpreter, ControllerTurtle turtleController) {
		this.interpreter = interpreter;
		this.turtleController = turtleController;
		numParams = 3;
	}
	
	public Object execute(List<Object> params) {
		String trueCommands = (String) params.get(1);
		String falseCommands = (String) params.get(2);
		if (params.get(0) instanceof Double || params.get(0) instanceof Integer) {
			Integer ifCondition = (Integer) params.get(0);
			if (!isEqual(ifCondition, 0)) {
				interpreter.run(trueCommands);
			}
			else {
				interpreter.run(falseCommands);
			}
		}
		else {
			// MAKE TEMPORARY NEW LIST OF ACTIVE AGENTS
			handleMultTurtlesCase(trueCommands, falseCommands, (double[]) params.get(0));
		}
	
		return interpreter.getReturnResult();
	}	
	
	private void handleMultTurtlesCase(String trueCommands, String falseCommands, double[] ifConds) {
		List<Integer> activeAgents = turtleController.getActiveAgents();
		List<Integer> tempInactiveAgents = new ArrayList<>();
		System.out.println("BBB: "+Arrays.toString(ifConds));
		for (int i=0; i<activeAgents.size(); i++) {
			Double ifCondition = ifConds[i];
			if (ifCondition.compareTo(new Double(0.0)) == 0) {
				System.out.println("remove: "+i);
				tempInactiveAgents.add(activeAgents.get(i));
			}
		}
		activeAgents.removeAll(tempInactiveAgents);
		interpreter.run(trueCommands);
		List<Integer> currentlyActiveAgents = new ArrayList<>();
		currentlyActiveAgents.addAll(activeAgents);
		
		activeAgents.removeAll(activeAgents);
		activeAgents.addAll(tempInactiveAgents);
		interpreter.run(falseCommands);
		activeAgents.addAll(currentlyActiveAgents);
	}
	
	
}
