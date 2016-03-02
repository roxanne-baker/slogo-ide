
package commands;

import java.util.List;

import model.Interpreter;



public class IfElse extends Command implements Executable {

	Interpreter interpreter;
	
	public IfElse(Interpreter interpreter) {
		this.interpreter = interpreter;
		numParams = 3;
	}
	
	public double execute(List<Object> params) {
		double ifCondition = (double) params.get(0);
		String trueCommands = (String) params.get(1);
		String falseCommands = (String) params.get(2);
		
		if (ifCondition != 0) {
			interpreter.run(trueCommands);
		}
		else {
			interpreter.run(falseCommands);
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
		Object param = params.get(0);
		if (!(param instanceof Integer || param instanceof Double)) {
			return String.format(errors.getString("WrongParamType"), param.toString());
		}
		for (int i=1; i<params.size(); i++) {
			Object command = params.get(i);
			if (!(command instanceof String)) {
				return String.format(errors.getString("WrongParamType"), param.toString());
			}
		}
		return null;
	}	
}
