package commands;

import java.util.List;

public class Power extends MathCommand implements Executable {

	public Power() {
		numParams = 2;
	}
	
//	public Object execute(List<Object> params) {
//		// need to figure out how to communicate with front-end
//		double base = (double) params.get(0);
//		double exponent = (double) params.get(1);
//		return Math.pow(base, exponent);
//	}	
//	
	
	public Object execute(List<Object> params) {
		if (params.get(0) instanceof Double && params.get(1) instanceof Double) {
			return Math.pow((Double) params.get(0), (Double) params.get(1));
		}
		else if (params.get(0) instanceof Double) {
			return powerForExponentArrayOnly((double[]) params.get(1), (double) params.get(0));
		}
		else if (params.get(1) instanceof Double) {
			return powerForBaseArrayOnly((double[]) params.get(0), (double) params.get(1));
		}
		else {
			return powerForExponentAndBaseArray((double[]) params.get(0), (double[]) params.get(1));
		}
	}
	
	private double[] powerForExponentAndBaseArray(double[] exponentArray, double[] baseArray) {
		for (int i=0; i<exponentArray.length; i++) {
			exponentArray[i] = Math.pow(baseArray[i], exponentArray[i]);
		}
		return exponentArray;
	}
	
	private double[] powerForExponentArrayOnly(double[] exponentArray, double baseVal) {
		for (int i=0; i<exponentArray.length; i++) {
			exponentArray[i] = Math.pow(baseVal, exponentArray[i]);
		}
		return exponentArray;
	}
	
	private double[] powerForBaseArrayOnly(double[] baseArray, double exponentVal) {
		for (int i=0; i<baseArray.length; i++) {
			baseArray[i] = Math.pow(baseArray[i], exponentVal);
		}
		return baseArray;
	}	
}
