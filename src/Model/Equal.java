package Model;

import java.util.List;

public class Equal extends Command implements Executable {

	public Equal() {
		numParams = 2;
	}
	
	public double execute(List<Object> params) {
		// need to figure out how to communicate with front-end
		if ((Double) params.get(0) == (Double) params.get(1)) {
			return 1;
		}
		else {
			return 0;
		}
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
