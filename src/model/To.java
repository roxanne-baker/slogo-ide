package model;

import java.util.List;

import controller.MethodController;

public class To extends Command implements Executable {

	MethodController methodController;
	
	public To(MethodController methodController) {
		this.methodController = methodController;
		numParams = 3;
	}
	
	public double execute(List<Object> params) {
//		String methodName = (String) params.get(0);
//		String varNamesString = (String) params.get(1);
//		String commands = (String) params.get(2);
//		
//		String[] varNames = varNamesString.split(" ");
		
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
