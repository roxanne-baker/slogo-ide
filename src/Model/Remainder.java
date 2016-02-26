package Model;

import java.util.List;

public class Remainder extends Command implements Executable {

	public Remainder() {
		numParams = 2;
	}
	
	public double execute(List<Object> params) {
		// need to figure out how to communicate with front-end
		int dividend = (int) params.get(0);
		int divisor = (int) params.get(1);

		return (dividend % divisor);
	}
	
	public String checkParamTypes(List<Object> params) {
		for (Object param : params) {
			if (!(param instanceof Integer)) {
				return String.format(errors.getString("WrongParamType"), param.toString());
			}			
		}
		return null;
	}
	
	
}
