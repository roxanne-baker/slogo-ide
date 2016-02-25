package Model;

import java.util.List;

public class Product extends Command implements Executable {

	public Product() {
		numParams = 2;
	}
	
	public double execute(List<ParseNode> params) {
		// need to figure out how to communicate with front-end
		int product = 1;
		for (ParseNode param : params) {
			double val = (double) param.getValue();
			product *= val;
		}
		return product;
	}
	
	@Override
	public String checkNumParams(List<ParseNode> params) {
		if (params.size() < 2) {
			return String.format(errors.getString("MathTooFewParams"), params.size());
		}
		else {
			return null;
		}
	}
	
	public String checkParamTypes(List<ParseNode> params) {
		for (ParseNode param : params) {
			Object paramValue = param.getValue();
			if (!(paramValue instanceof Integer || paramValue instanceof Double)) {
				return String.format(errors.getString("WrongParamType"), paramValue.toString());
			}			
		}
		return null;
	}
	
	
}
