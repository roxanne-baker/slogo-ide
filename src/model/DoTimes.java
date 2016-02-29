
package model;

import java.util.List;



public class DoTimes extends Command implements Executable {

	Interpreter interpreter;
	
	public DoTimes(Interpreter interpreter) {
		this.interpreter = interpreter;
		numParams = 2;
	}
	
	public double execute(List<Object> params) {
		// WANT TO GET MAX VALUE FOR VARIABLE
		String varLimitExpr = (String) params.get(0);
		int endVarNameIndex = varLimitExpr.indexOf(" ");
		String varName = varLimitExpr.substring(0, endVarNameIndex);
		//String varLimitCommand = varLimitExpr.substring(endVarNameIndex+1);
		
		double maxValue = 0;
		//double maxValue = interpreter.run(varLimitCommand);
		
		String loopCommands = (String) params.get(1);
		for (int i=1; i<=maxValue; i++) {
			interpreter.run("MAKE "+varName+" "+i);
			interpreter.run(loopCommands);
		}

		//NEED TO ADD IN RETURN VALUE
		return 0;
	}	

	
	@Override
	public String checkNumParams(List<Object> params) {
		if (params.size() < numParams) {
			return String.format(errors.getString("MathTooFewParams"), params.size());
		}
		else {
			return null;
		}
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
