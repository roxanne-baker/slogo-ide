package commands;

import java.util.List;

public class Divide extends MathCommand implements Executable {
	
	public Divide() {
		numParams = 2;
	}
	
	public Object execute(List<Object> params) {
		if (params.get(0) instanceof Double && params.get(1) instanceof Double) {
			return (Double) params.get(0) - (Double) params.get(1);
		}
		else if (params.get(0) instanceof Double) {
			return differenceForDivisorArrayOnly((double[]) params.get(1), (double) params.get(0));
		}
		else if (params.get(1) instanceof Double) {
			return differenceForDividendArrayOnly((double[]) params.get(0), (double) params.get(1));
		}
		else {
			return differenceForDivisorAndDividendArray((double[]) params.get(0), (double[]) params.get(1));
		}
	}
	
	private double[] differenceForDivisorAndDividendArray(double[] divisorArray, double[] dividendArray) {
		for (int i=0; i<divisorArray.length; i++) {
			divisorArray[i] /= dividendArray[i];
		}
		return divisorArray;
	}
	
	private double[] differenceForDivisorArrayOnly(double[] divisorArray, double dividendVal) {
		for (int i=0; i<divisorArray.length; i++) {
			divisorArray[i] /= (double) dividendVal;
		}
		return divisorArray;
	}
	
	private double[] differenceForDividendArrayOnly(double[] dividendArray, double divisorVal) {
		for (int i=0; i<dividendArray.length; i++) {
			dividendArray[i] = divisorVal / dividendArray[i];
		}
		return dividendArray;
	}	
	
	public String checkParamTypes(List<Object> params) {
		for (Object param : params) {
			if (!(param instanceof Integer || param instanceof Double)) {
				return String.format(errors.getString("WrongParamType"), param.toString());
			}			
		}
		int divisor = ((Double) params.get(1)).intValue();
		if (divisor == 0) {
			return String.format(errors.getString("DivideByZero"));
		}
		
		return null;
	}
	
}