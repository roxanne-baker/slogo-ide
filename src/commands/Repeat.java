
package commands;

import java.util.List;

import model.Interpreter;



public class Repeat extends Command implements Executable {

	Interpreter interpreter;
	
	public Repeat(Interpreter interpreter) {
		this.interpreter = interpreter;
		numParams = 2;
	}
	
	public double execute(List<Object> params) {
		double numRepeats = (double) params.get(0);
		String commands = (String) params.get(1);
		for (int i=0; i<numRepeats; i++) {
			interpreter.run(commands);		
		}
		return interpreter.getReturnResult();
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
