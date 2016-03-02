package commands;

import java.util.List;

import controller.VariablesController;


public class MakeVar extends Command implements Executable {

	VariablesController variableController;
	private final int numStringParams = 1;

	public MakeVar(VariablesController variableController) {
		numParams = 2;
		needsVarName = true;
		this.variableController = variableController;
	}
	
	public double execute(List<Object> params) {
		// need to figure out how to communicate with front-end
		// what if expression not a number?  need to account for later
		System.out.println(params.get(0)+" "+params.get(1));
		String name = (String) params.get(0);
		double valueNum = (double) params.get(1);
		String value = Double.toString(valueNum);
		variableController.addVariable(name, value);
		return valueNum;
	}
	
	public String checkParamTypes(List<Object> params) {
		Object name = params.get(0);
		if (!(name instanceof String)) {
			return String.format(errors.getString("WrongParamType"), name.toString());
		}
		Object value = params.get(0);
		if (!(value instanceof Integer || value instanceof Double)) {
			return String.format(errors.getString("WrongParamType"), value.toString());
		}	
		return null;
	}
	
	@Override
	public void addParam(Object param) {
		params.add(param);
		if (params.size() == numStringParams) {
			needsVarName = false;
		}
		else if (params.size() == numParams) {
			needsVarName = true;
		}
	}
	
}

