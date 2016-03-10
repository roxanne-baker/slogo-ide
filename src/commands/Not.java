package commands;

import java.util.List;

public class Not extends Command implements Executable {

	public Not() {
		numParams = 1;
	}
	
//	public Object execute(List<Object> params) {
//		if ((double) params.get(0) == 0) {
//			return 0;
//		}
//		return 1;
//	}
	
	public Object execute(List<Object> params) {
		double[] booleanConditionals = null;
		if (params.get(0) instanceof Double) {
			if (((Double) params.get(0)).compareTo(new Double(0.0)) == 0) {
				return 1;
			}
			else {
				return 0;
			}
		}
		else {
			double[] notValues = (double[]) params.get(0);
			for (int i=0; i<notValues.length; i++) {
				notValues[i] = ((Double) notValues[i]).compareTo(new Double(0.0)) == 0 ? 1 : 0;
			}
			return notValues;
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
