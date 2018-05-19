package geometry;
public class Circle extends Shape {
	
	private double diameter;
	Point topLeftPoint = new Point();
	
	public double getDiameter() {
		return diameter;
	}
	
	public Point getPoint() {
		return topLeftPoint;
	}
	
	public void setPoint(Point p){
		topLeftPoint = p;
	}
	
	public void setDiameter(double diameterC){
		diameter = diameterC;
	}
			
	public double area() {
		double areaCalc = ((diameter/2)*(diameter/2)) * Math.PI;
		return areaCalc;
	}
	
	public double perimeter() {
		double perimeterCalc = 2 * Math.PI * (diameter/2);
		return perimeterCalc;
	}
	
	public boolean contains(Point p1) {
		double containtest = p1.distance(topLeftPoint.getX(),topLeftPoint.getY());
		if( containtest < (diameter/2)){
			return true;
		}
		else {
			return false;
		}
	}
	
	public Point centroid(){
		Point centroidP = new Point();
		centroidP.setPosition(topLeftPoint.getX()+(diameter/2), topLeftPoint.getY()+(diameter/2));
		return centroidP;
	}

}