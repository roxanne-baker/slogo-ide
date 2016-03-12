package commands;

import java.util.List;

import controller.ControllerVariables;
import model.Interpreter;

public class DoTimes extends ControlCommand implements Executable {
	Interpreter interpreter;
	ControllerVariables variableController;
	
	public DoTimes(Interpreter interpreter, ControllerVariables variableController) {
		this.interpreter = interpreter;
		this.variableController = variableController;
		numParams = 2;
	}
	
	public Object execute(List<Object> params) {
		String varLimitExpr = (String) params.get(0);
		//System.out.println(varLimitExpr);
		String varName = varLimitExpr.split(" ")[0];
		int endVarNameIndex = varLimitExpr.indexOf(" ");

		String varLimitCommand = varLimitExpr.substring(endVarNameIndex+1).trim();
		interpreter.run(varLimitCommand);
		double maxValue = Double.parseDouble(interpreter.getReturnResult());
		
		String loopCommands = (String) params.get(1);
		for (int i=1; i<=maxValue; i++) {
			variableController.addVariable(varName, ""+i);
			interpreter.run(loopCommands);
		}
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
