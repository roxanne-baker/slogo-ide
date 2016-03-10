package commands;

import java.util.List;

import model.Interpreter;

public class DoTimes extends ControlCommand implements Executable {
	Interpreter interpreter;
	public DoTimes(Interpreter interpreter) {
		this.interpreter = interpreter;
		numParams = 2;
	}
	
	public double execute(List<Object> params) {
		String varLimitExpr = (String) params.get(0);
		//System.out.println(varLimitExpr);
		String varName = varLimitExpr.split(" ")[0];
		int endVarNameIndex = varLimitExpr.indexOf(" ");
		//String varName = varLimitExpr.substring(0, endVarNameIndex);
		String varLimitCommand = varLimitExpr.substring(endVarNameIndex+1).trim();
		interpreter.run(varLimitCommand);
		double maxValue = interpreter.getReturnResult();
		//double maxValue = interpreter.run(varLimitCommand);
		System.out.println(maxValue);
		
		String loopCommands = (String) params.get(1);
		for (int i=1; i<=maxValue; i++) {
			interpreter.run("MAKE "+varName+" "+i);
			interpreter.run(loopCommands);
		}
		return interpreter.getReturnResult();
	}	
	
	public String checkNumParams(List<Object> params) {
		for (Object param : params) {
			if (!(param instanceof String)) {
				return String.format(errors.getString("WrongParamType"), param.toString());
			}
		}
		return null;
	}

}
