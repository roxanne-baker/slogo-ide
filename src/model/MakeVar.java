package model;

import java.util.List;

import controller.VariableController;

public class MakeVar extends Command implements Executable {

	VariableController variableController;
	
	public MakeVar(VariableController variableController) {
		this.variableController = variableController;
		numParams = 2;
	}
	
	public double execute(List<Object> params) {
		// need to figure out how to communicate with front-end
		// what if expression not a number?  need to account for later
		String name = (String) params.get(0);
		double valueNum = (double) params.get(1);
		String value = Double.toString(valueNum);
		variableController.addVariable(name, value);

		return valueNum;
	}
	
	public String checkParamTypes(List<Object> params) {
		for (Object param : params) {
			if (!(param instanceof Integer || param instanceof Double)) {
				return String.format(errors.getString("WrongParamType"), param.toString());
			}	
		}
		return null;
	}
	
}
