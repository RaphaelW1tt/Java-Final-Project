package geometry;
public class Point extends Shape {
	
	private double x;
	private double y;
	
	public double getX() {
		return x;
	}
	
	public double getY() {
		return y;
	}
	
	public void setX(double value) {
		x = value;
	}
	
	public void setY(double value) {
		y = value;
	}
	
	public void setPosition(double xValue, double yValue) {
		this.setX(xValue);
		this.setY(yValue);
	}
		
	public String toString() {
		String descriptionOfPoint = "Point (" + x + "," + y + ")";
		return descriptionOfPoint;
	}
	
	public double distance(double xValue, double yValue) {
		double distanceValue = Math.sqrt((x - xValue)* (x - xValue) + (y - yValue) * (y - yValue));
		return distanceValue;
	}
}