package geometry;
public class Rectangle extends Shape{
	
	private double width;
	private double height;
	Point topLeftPoint = new Point();
	
	public double getWidth() {
		return width;
	}
	
	public double getHeight() {
		return height;
	}
	
	public Point getPoint() {
		return topLeftPoint;
	}
	
	public void setPoint(Point p){
		topLeftPoint = p;
	}
	
	public void setWidth(double widthG){
		width = widthG;
	}
	
	public void setHeight(double heightG){
		height = heightG;
	}
			
	public double area() {
		double areaCalc = width * height;
		return areaCalc;
	}
	
	public double perimeter() {
		double perimeterCalc = width * 2 + 2 * height;
		return perimeterCalc;
	}
	
	public boolean contains(Point p1) {
		if(p1.getX() > topLeftPoint.getX() && p1.getX() < topLeftPoint.getX() + width && p1.getY() > topLeftPoint.getY() && p1.getY() < topLeftPoint.getY() + height){
			return true;
		}
		else {
			return false;
		}
	}
	
	public Point centroid(){
		Point centroidP = new Point();
		centroidP.setPosition(topLeftPoint.getX() + 0.5*width, topLeftPoint.getY() + 0.5*height);
		return centroidP;
	}
}