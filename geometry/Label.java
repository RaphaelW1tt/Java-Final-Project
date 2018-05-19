package geometry;
public class Label extends Shape {
	
	String text;
	Point position = new Point();
	
	public Point getPosition() {
		return position;
	}
	
	public String getText() {
		return text;
	}
	
	public void setPosition(Point p1) {
		position = p1;
	}
	
	public void setText(String input) {
		text = input;
	}
}