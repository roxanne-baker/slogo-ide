package model;

// While these constants should probably be contained in a
// resource file somewhere, they are not.  While having them
// here is not ideal, this is simply the alternative I chose
// to avoid having to change the front-end more extensively
// for what is intended as a back-end extension.
public class Coordinates {
	public static final double X_MIN = 0;
	public static final double X_MAX = 500;
	public static final double Y_MIN = 0;
	public static final double Y_MAX = 500;
	
	public static double[] setPoint(double xValue, double yValue, double slope, double oldX, double oldY) {
		double xVal = 0;
		double yVal = 0;
		if (xValue < X_MIN || xValue > X_MAX) {
			yVal = getYPoint(xValue, slope, oldX, oldY);
			xVal = getValue(xValue, X_MIN, X_MAX);
		}
		if (yValue < Y_MIN || yValue > Y_MAX) {
			xVal = getXPoint(yValue, slope, oldX, oldY);
			yVal = getValue(yValue, Y_MIN, Y_MAX);
		}
		return new double[]{xVal, yVal};
	}
	
	private static double getValue(double value, double min, double max) {
		if (value < min) {
			return min;
		}
		else {
			return max;
		}
	}
	
	private static double getXPoint(double yValue, double slope, double oldX, double oldY) {
		return ((yValue - oldY) / (slope)) + oldX;
	}
	
	private static double getYPoint(double xValue, double slope, double oldX, double oldY) {
		return slope*(xValue - oldX) + oldY;
	}
}
