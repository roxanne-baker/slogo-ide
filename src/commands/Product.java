package commands;

import java.util.List;

public class Product extends MathCommand implements Executable {

	public Product() {
		numParams = 2;
	}
	
	public Object execute(List<Object> params) {
		double product = 0;
		double[] productArray = null;
		product = getProduct(params);
		productArray = getProductArray(params);
		
		if (productArray != null) {
			for (int i=0; i<productArray.length; i++) {
				productArray[i] *= product;
			}
			return productArray;
		}
		return product;
	}
	
	private double getProduct(List<Object> params) {
		double product = 1;
		for (Object param : params) {
			if ((param instanceof Double)) {
				product *= (double) param;
			}
		}
		return product;		
	}
	
	private double[] getProductArray(List<Object> params) {
		double[] productArray = null;
		for (Object param : params) {
			if (!(param instanceof Double)) {
				if (productArray == null) {
					productArray = (double[]) param;
				}
				else {
					double[] paramArray = (double[]) param;
					for (int i=0; i<paramArray.length; i++) {
						productArray[i] *= paramArray[i];
					}
				}
			}
		}
		return productArray;
	}
	
	
}