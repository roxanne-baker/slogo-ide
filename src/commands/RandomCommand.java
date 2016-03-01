package commands;

import java.util.List;
import java.util.Random;

public class RandomCommand extends Command implements Executable {

	public RandomCommand() {
		numParams = 1;
	}
	
	public double execute(List<Object> params) {
		// need to figure out how to communicate with front-end
		int max = (int) params.get(0);
		Random getRandInt = new Random();
		return getRandInt.nextInt(max);
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
