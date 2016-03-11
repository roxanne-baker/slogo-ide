package commands;

import java.util.List;

public class And extends Command implements Executable {

	public And() {
		numParams = 2;
	}
	
	public Object execute(List<Object> params) {
		double[] booleanConditionals = null;
		for (Object param : params) {
			if (param instanceof Double) {
				if ((double) param == 0) {
					return 0;
				}
			}
			else {
				booleanConditionals = getBooleanConditional(booleanConditionals, (double[]) param);
			}
		}
		return changeToZerosAndOnes(booleanConditionals);
	}
	
	private double[] changeToZerosAndOnes(double[] booleanConditional) {
		for (int i=0; i<booleanConditional.length; i++) {
			if (booleanConditional[i] != 0) {
				booleanConditional[i] = 1;
			}
		}
		return booleanConditional;
	}
	
	private double[] getBooleanConditional(double[] booleanConditionals, double[] param) {
		if (booleanConditionals == null) {
			booleanConditionals = (double[]) param;
		}
		else {
			double[] nextCondition = (double[]) param;
			for (int i=0; i<booleanConditionals.length; i++) {
				if (isEqual(nextCondition[i], 0)) {
					booleanConditionals[i] = 0;
				}
			}
		}
		return booleanConditionals;
	}
	
	@Override
	public String checkNumParams(List<Object> params) {
		if (params.size() < numParams) {
			return String.format(errors.getString("MathTooFewParams"), params.size());
		}
		else {
			return null;
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
