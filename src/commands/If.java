package commands;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import controller.TurtleController;
import model.Interpreter;



public class If extends ControlCommand implements Executable {

	Interpreter interpreter;
	TurtleController turtleController;
	public If(Interpreter interpreter, TurtleController turtleController) {
		this.interpreter = interpreter;
		this.turtleController = turtleController;
		numParams = 2;
	}
	
	public Object execute(List<Object> params) {
		for (Object param : params) {
			System.out.println("param: "+param+" "+param.getClass().getName());
		}
		String commands = (String) params.get(1);
		if (params.get(0) instanceof Double) {
			Double ifCondition = (double) params.get(0);
			if (!isEqual(ifCondition, 0)) {
				interpreter.run(commands);
			}
		}
		else {
			// MAKE TEMPORARY NEW LIST OF ACTIVE AGENTS
			handleMultTurtlesCase(commands, (double[]) params.get(0));
		}
	
		return interpreter.getReturnResult();
	}	
	
	private void handleMultTurtlesCase(String commands, double[] ifConds) {
		List<Integer> activeAgents = turtleController.getActiveAgents();
		List<Integer> tempInactiveAgents = new ArrayList<>();

		for (int i=0; i<activeAgents.size(); i++) {
			Double ifCondition = ifConds[i];
			if (isEqual(ifCondition, 0)) {
				System.out.println("remove: "+i);
				tempInactiveAgents.add(activeAgents.get(i));
			}
		}
		activeAgents.removeAll(tempInactiveAgents);
		interpreter.run(commands);
		activeAgents.addAll(tempInactiveAgents);
	}
}
