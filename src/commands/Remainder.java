package commands;

import java.util.List;

public class Remainder extends MathCommand implements Executable {

	public Remainder() {
		numParams = 2;
	}
	
	public Object execute(List<Object> params) {
		if (params.get(0) instanceof Double && params.get(1) instanceof Double) {
			return (Double) params.get(0) % (Double) params.get(1);
		}
		else if (params.get(0) instanceof Double) {
			return remainderForDividendArrayOnly((double[]) params.get(1), (double) params.get(0));
		}
		else if (params.get(1) instanceof Double) {
			return remainderForDivisorArrayOnly((double[]) params.get(0), (double) params.get(1));
		}
		else {
			return remainderForDividendAndDivisorArray((double[]) params.get(0), (double[]) params.get(1));
		}
	}
	
	private double[] remainderForDividendAndDivisorArray(double[] dividendArray, double[] divisorArray) {
		for (int i=0; i<dividendArray.length; i++) {
			dividendArray[i] %= divisorArray[i];
		}
		return dividendArray;
	}
	
	private double[] remainderForDividendArrayOnly(double[] dividendArray, double divisorVal) {
		for (int i=0; i<dividendArray.length; i++) {
			dividendArray[i] %= (double) divisorVal;
		}
		return dividendArray;
	}
	
	private double[] remainderForDivisorArrayOnly(double[] divisorArray, double dividendVal) {
		for (int i=0; i<divisorArray.length; i++) {
			divisorArray[i] = dividendVal % divisorArray[i];
		}
		return divisorArray;
	}	
}
