package geometry;
import java.awt.Color;

public class Shape {
	private double strokeWidth;
	private Color strokeColor;
	private Color fillColor;
	
	public double getStrokeWidth() {
		return strokeWidth;
	}
	
	public void setStrokeWidth(double strWidth){
		strokeWidth = strWidth;
	}
	
	public Color getStrokeColor() {
		return strokeColor;
	}
	
	public void setStrokeColor(Color strColor){
		strokeColor = strColor;
	}
	
	public Color getFillColor(){
		return fillColor;
	}
	
	public void setFillColor(Color fllClr){
		fillColor = fllClr;
	}
}