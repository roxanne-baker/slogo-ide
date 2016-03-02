package commands;

import java.util.List;

import controller.VariablesController;
import model.Interpreter;

public class CreatedMethod extends Command implements Executable {

	private Interpreter interpreter;
	private VariablesController variablesController;
	private String[] paramNames;
	private String commands;
	// 
	
	
	public CreatedMethod(Interpreter interpreter, VariablesController variablesController, String[] paramNames, String commands) {
		this.interpreter = interpreter;
		this.variablesController = variablesController;
		this.paramNames = paramNames;
		this.commands = commands;
		numParams = 0;
		
		for (int i=0; i<paramNames.length; i++) {
			if (variablesController.getVariable(paramNames[i]) == null) {
				variablesController.addVariable(paramNames[i], "0");
			}
		}
		//numParams
	}
	
	public double execute(List<Object> params) {
//		double numRepeats = (double) params.get(0);
//		String commands = (String) params.get(1);
//		
//		for (int i=0; i<numRepeats; i++) {
//			interpreter.run(commands);		
//		}
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
