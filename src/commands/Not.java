package commands;

import java.util.List;

public class Not extends Command implements Executable {

	public Not() {
		numParams = 1;
	}
	
	public double execute(List<Object> params) {
		if ((double) params.get(0) == 0) {
			return 0;
		}
		return 1;
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