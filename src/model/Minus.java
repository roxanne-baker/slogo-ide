
package model;

import java.util.List;

public class Minus extends Command implements Executable {

	public Minus() {
		numParams = 1;
	}
	
	public double execute(List<Object> params) {
		// need to figure out how to communicate with front-end
		return -((Double) params.get(0));
	}
	
	public String checkParamTypes(List<Object> params) {
		for (Object param : params) {
			Object paramValue = param;
			if (!(paramValue instanceof Integer || paramValue instanceof Double)) {
				return String.format(errors.getString("WrongParamType"), paramValue.toString());
			}			
		}
		return null;
	}
	
	
}
