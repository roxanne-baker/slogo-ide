package commands;

import java.util.Arrays;
import java.util.List;

public class Sum extends MathCommand implements Executable {

	public Sum() {
		numParams = 2;
	}
	
	@SuppressWarnings("unused")
	public Object execute(List<Object> params) {
		double sum = 0;
		double[] sumArray = null;
		sum = getSum(params);
		sumArray = getSumArray(params);
		
		if (sumArray != null) {
			for (int i=0; i<sumArray.length; i++) {
				sumArray[i] += sum;
			}
			System.out.println(Arrays.toString(sumArray));
			return sumArray;
		}
		return sum;
	}
	
	private double getSum(List<Object> params) {
		double sum = 0;
		for (Object param : params) {
			if ((param instanceof Double)) {
				sum += (double) param;
			}
		}
		return sum;		
	}
	
	private double[] getSumArray(List<Object> params) {
		double[] sumArray = null;
		for (Object param : params) {
			if (!(param instanceof Double)) {
				if (sumArray == null) {
					sumArray = (double[]) param;
				}
				else {
					double[] paramArray = (double[]) param;
					for (int i=0; i<paramArray.length; i++) {
						sumArray[i] += paramArray[i];
					}
				}
			}
		}
		return sumArray;
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
}
