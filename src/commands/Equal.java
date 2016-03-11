package commands;

import java.util.Arrays;
import java.util.List;

public class Equal extends Command implements Executable {

	public Equal() {
		numParams = 2;
	}
	
	public Object execute(List<Object> params) {
		if (params.get(0) instanceof Double && params.get(1) instanceof Double) {
			return isEqual((Double) params.get(0), (Double) params.get(1)) ? 1 : 0;
		}
		else if (params.get(1) instanceof Double) {
			return logicForFirstExprArrayOnly((double[]) params.get(0), (double) params.get(1));
		}
		else if (params.get(0) instanceof Double) {
			return logicForFirstExprArrayOnly((double[]) params.get(1), (double) params.get(0));
		}
		else {
			return logicForFirstExprAndSecondExprArray((double[]) params.get(0), (double[]) params.get(1));
		}
	}
	
	private double[] logicForFirstExprAndSecondExprArray(double[] firstExprArray, double[] secondExprArray) {
		for (int i=0; i<firstExprArray.length; i++) {
			firstExprArray[i] = isEqual(firstExprArray[i], secondExprArray[i]) ? 1 : 0;
		}
		return firstExprArray;
	}
	
	private double[] logicForFirstExprArrayOnly(double[] firstExprArray, double secondExprVal) {
		for (int i=0; i<firstExprArray.length; i++) {
			firstExprArray[i] = isEqual(firstExprArray[i], secondExprVal) ? 1 : 0;
		}
		System.out.println("AAA: "+Arrays.toString(firstExprArray)+" "+secondExprVal);
		return firstExprArray;
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