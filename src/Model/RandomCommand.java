package Model;

import java.util.List;
import java.util.Random;

public class RandomCommand extends Command implements Executable {

	public RandomCommand() {
		numParams = 1;
	}
	
	public double execute(List<ParseNode> params) {
		// need to figure out how to communicate with front-end
		int max = (int) params.get(0).getValue();
		Random getRandInt = new Random();
		return getRandInt.nextInt(max);
	}
	
	public String checkParamTypes(List<ParseNode> params) {
		for (ParseNode param : params) {
			Object paramValue = param.getValue();
			if (!(paramValue instanceof Integer)) {
				return String.format(errors.getString("WrongParamType"), paramValue.toString());
			}			
		}
		return null;
	}
	
	
}
