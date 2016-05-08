package model;

public class PositionIncrement {

	private double xChange; 
	private double yChange; 
	
	PositionIncrement(double xChange, double yChange) { 
		this.xChange = xChange; 
		this.yChange = yChange; 
	}
	
	public double getXChange() { 
		return xChange; 
	}
	
	public double getYChange() { 
		return yChange;
	}
}
