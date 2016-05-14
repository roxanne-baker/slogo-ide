package commands;

import java.util.List;

import controller.ControllerVariables;
import parsing.Interpreter;


public class For extends Command implements Executable {

	Interpreter interpreter;
	ControllerVariables variableController;
	
	public For(Interpreter interpreter, ControllerVariables variableController) {
		this.interpreter = interpreter;
		this.variableController = variableController;
		numParams = 2;
	}
	
	public Object execute(List<Object> params) {
		// WANT TO GET MAX VALUE FOR VARIABLE
		String variableStartEndIncrement = (String) params.get(0);
		String[] forLoopCondition = variableStartEndIncrement.split(" ");
		String varName = forLoopCondition[0];
		double start = Double.parseDouble(forLoopCondition[1]);
		double end = Double.parseDouble(forLoopCondition[2]);
		double increment = Double.parseDouble(forLoopCondition[3]);
		
		String commands = (String) params.get(1);
		System.out.println(variableController == null);
		System.out.println(varName);		
		for (double i=start; i<end; i+= increment) {
			variableController.addVariable(varName, ""+i);
			interpreter.run(commands);
		}

		//NEED TO ADD IN RETURN VALUE
		return interpreter.getReturnResult();
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