package geometry;
public class City {
	
	private String name;
	private Point coords;
	private double area;
	private int pop;
	private double popdens;
	private double foreignp;
	private double gdp;
	
	public String getName() {
		return name;
	}
	
	public Point getPoint() {
		return coords;
	}
	
	public double getArea() {
		return area;
	}
	
	public int getPop() {
		return pop;
	}
	
	public double getPopdens() {
		return popdens;
	}
	
	public double getForeignp() {
		return foreignp;
	}
	
	public double getGDP() {
		return gdp;
	}
	
	public String toString() {
		return this.getName() + " " + this.getPoint() + " " + this.getArea() + " " +  this.getPop() + " " + this.getPopdens() + " " + this.getForeignp() + " " + this.getGDP();
	}
	
	public void setName(String n) {
		name = n;
	}
	
	public void setPoint(Point p) {
		coords = p;
	}
	
	public void setArea(double a) {
		area= a;
	}
	
	public void setPop(int p) {
		pop = p;
	}
	
	public void setPopdens(double pd) {
		popdens = pd;
	}
	
	public void setForeignp(double fp) {
		foreignp = fp;
	}
	
	public void setGDP(double g) {
		gdp = g;
	}

}
