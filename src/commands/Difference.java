package commands;

import java.util.Arrays;
import java.util.List;

public class Difference extends MathCommand implements Executable {

	public Difference() {
		numParams = 2;
	}
	
	public Object execute(List<Object> params) {
		if (params.get(0) instanceof Double && params.get(1) instanceof Double) {
			return (Double) params.get(0) - (Double) params.get(1);
		}
		else if (params.get(0) instanceof Double) {
			return differenceForMinuendArrayOnly((double[]) params.get(1), (double) params.get(0));
		}
		else if (params.get(1) instanceof Double) {
			return differenceForSubtrahendArrayOnly((double[]) params.get(0), (double) params.get(1));
		}
		else {
			return differenceForMinuendAndSubtrahendArray((double[]) params.get(0), (double[]) params.get(1));
		}
	}
	
	private double[] differenceForMinuendAndSubtrahendArray(double[] minuendArray, double[] subtrahendArray) {
		for (int i=0; i<minuendArray.length; i++) {
			minuendArray[i] -= subtrahendArray[i];
		}
		return minuendArray;
	}
	
	private double[] differenceForMinuendArrayOnly(double[] minuendArray, double subtrahendVal) {
		for (int i=0; i<minuendArray.length; i++) {
			minuendArray[i] -= (double) subtrahendVal;
		}
		return minuendArray;
	}
	
	private double[] differenceForSubtrahendArrayOnly(double[] subtrahendArray, double minuendVal) {
		for (int i=0; i<subtrahendArray.length; i++) {
			subtrahendArray[i] = minuendVal - subtrahendArray[i];
		}
		return subtrahendArray;
	}	
}
