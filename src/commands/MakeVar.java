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
	
	public Object execute(List<Object> params) {
		String name = (String) params.get(0);
		double valueNum;
		if (params.get(1) instanceof Double) {
			valueNum = (double) params.get(1);
		}
		else {
			double[] valueArray = (double[]) params.get(1);
			valueNum = valueArray[valueArray.length-1];
		}
		String value = Double.toString(valueNum);
		variableController.addVariable(name, value);
		return valueNum;
	}
	
	public String checkParamTypes(List<Object> params) {
		Object name = params.get(0);
		if (!(name instanceof String)) {
			return String.format(errors.getString("WrongParamType"), name.toString());
		}
		Object value = params.get(1);
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

