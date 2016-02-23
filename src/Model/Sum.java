package Model;

public class Sum extends Command implements Executable {

	numParams = 2;
	
	public double execute(List<ParseNode> params) {
		// need to figure out how to communicate with front-end
		int sum = 0;
		for (ParseNode param : params) {
			double val = (double) params.getValue();
			sum += val;
		}
		return sum;
	}
	
	@Override
	public String checkNumParams(List<ParseNode> params) {
		if (params.size() < 2) {
			return String.format(errors.getString(MathTooFewParams), params.size());
		}
		else {
			return null;
		}
	}
	
	public String checkParamTypes(List<ParseNode> params) {
		for (ParseNode param : params) {
			Object paramValue = params.get(0).getValue();
			if (!(paramValue instanceof Integer || paramValue instanceof Double)) {
				return String.format(errors.getString(WrongParamType), paramValue.toString());
			}			
		}
		return null;
	}
	
	
}
