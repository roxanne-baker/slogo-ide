package Model;

import java.util.List;

public class ArcTangent extends Command implements Executable {

	public ArcTangent() {
		numParams = 1;
	}
	
	public double execute(List<ParseNode> params) {
		double degrees = (double) params.get(0).getValue();
		return Math.atan(degrees);
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
