//import de.wwu.intro.prog.InputStringReader;
import java.awt.Color;
import java.io.IOException;
import geometry.*;
import providedcontent.*;

public class ReadInCities {

	public static void main(String[] args) {
		
		SimpleFrame frame = new SimpleFrame(1300,700);
		
		//Frame center point
		Point centerPoint = new Point();
		centerPoint.setX(350);
		centerPoint.setY(350);
		
		//Center point of the bounding box
		Point centerBB = new Point();
		
		//Reading in data and creating cities and city points
		String path = "./Citydata_work.txt";
		Point cityPoints [] = new Point[11];
		City cities [] = new City[11];
		
		//Creating city points for scaling
		Point scaledcityPoints[] = new Point[11];
		
		try{
			String[] cityLines = InputStringReader.readFileAsArray(path);

    		for (int i = 1; i < cityLines.length; i++) {
				cities[i] = new City();
				
				String cityProps [] = cityLines[i].split(",");
				
				for (int j = 0; j < cityProps.length; j++) {
					String coords [] = cityProps[1].split(" ");
					double xcoord = Double.parseDouble(coords[1]);
					double ycoord = Double.parseDouble(coords[0]);
					cityPoints[i] = new Point();
					cityPoints[i].setX(xcoord);
					cityPoints[i].setY(ycoord);
					cities[i].setPoint(cityPoints[i]);
					
					double area = Double.parseDouble(cityProps[2]);
					int pop = Integer.parseInt(cityProps[3]);
					double popdens = Double.parseDouble(cityProps[4]);
					double foreignp = Double.parseDouble(cityProps[5]);
					double gdp = Double.parseDouble(cityProps[6]);
					
					cities[i].setName(cityProps[0]);
					cities[i].setArea(area);
					cities[i].setPop(pop);
					cities[i].setPopdens(popdens);
					cities[i].setForeignp(foreignp);
					cities[i].setGDP(gdp);
				}
			}
			
    		//Variables for calculating the nothern/southern/wester/eastern-most coordinates
			double north = -1000;
			double south = 1000;
			double west = 1000;
			double east = -1000;
		
			for (int i = 1; i < cityLines.length; i++) {
				if (cities[i].getPoint().getX() > east) {
					east = cities[i].getPoint().getX();
				}
				if (cities[i].getPoint().getX() < west) {
					west = cities[i].getPoint().getX();
				}
				if (cities[i].getPoint().getY() > north) {
					north = cities[i].getPoint().getY();
				}
				if (cities[i].getPoint().getY() < south) {
					south = cities[i].getPoint().getY();
				}
			}
			
			//Calculating the scale
			double scale = 500/Math.max((south-north),(east-west));
			
			//Top left point of the bounding box
			Point topLeftPoint = new Point();
			topLeftPoint.setX(west);
			topLeftPoint.setY(north);
			
			//Calculating the center of the bounding box
			centerBB.setX((topLeftPoint.getX()+(0.5*(east-west)))*scale);
			centerBB.setY((topLeftPoint.getY()+(0.5*(south-north)))*scale);
			
			//Calculating normalization for the color value of the circles
			double popdensnorm [] = new double[11];
			double maxdensity = 0;
			double mindensity = 10000000;
			for (int i = 1; i < cityLines.length; i++) {
				if (cities[i].getPopdens() < mindensity) {
					mindensity = cities[i].getPopdens();
				}
				else if (cities[i].getPopdens() > maxdensity) {
					maxdensity = cities[i].getPopdens();
				}
			}
			maxdensity = maxdensity - mindensity;
			
			for (int i = 1; i < cityLines.length; i++) {
				popdensnorm[i] = cities[i].getPopdens() - mindensity;
				popdensnorm[i] = popdensnorm[i]/maxdensity;
			}
			
			//Calculating the GDP per capita
			double gdppercapita [] = new double[11];
			for (int i = 1; i < cityLines.length; i++) {
				gdppercapita[i] = (cities[i].getGDP()*1000000000)/cities[i].getPop();
			}
			
			//Applying the scale to the coordinates
			for (int i = 1; i < cityLines.length; i++) {
				scaledcityPoints[i] = new Point();
				scaledcityPoints[i].setX(cities[i].getPoint().getX()*scale);
				scaledcityPoints[i].setY(cities[i].getPoint().getY()*scale);
				cities[i].setPoint(scaledcityPoints[i]);
				
				//Setting labels
				Label cityname = new Label();
				cityname.setText(cities[i].getName());
				cityname.setPosition(cities[i].getPoint());
				frame.addToPlot(cityname);
				
				//Setting circles
				Circle pd = new Circle();
				pd.setPoint(cities[i].getPoint());
				pd.setDiameter(cities[i].getArea()/3.5);
				pd.setStrokeColor(Color.BLACK);
				pd.setStrokeWidth(1);
				pd.setFillColor(Color.getHSBColor(0f, (float)popdensnorm[i], .89f));
				frame.addToPlot(pd);
			}
			
			//Calculating translation for coordinates
			double xTranslation = centerPoint.getX() - centerBB.getX();
			double yTranslation = centerPoint.getY() - centerBB.getY();
			
			//Variable for drawing the bars in the diagrams
			int t = 0;
	
			//Setting the scaled points as points for the cities
			for (int i = 1; i < cityLines.length; i++) {
				cities[i].getPoint().setX(cities[i].getPoint().getX() + xTranslation);
				cities[i].getPoint().setY(cities[i].getPoint().getY() + yTranslation);
				cities[i].getPoint().setY(700 - cities[i].getPoint().getY());
				cities[i].getPoint().setStrokeColor(Color.BLACK);
				frame.addToPlot(cities[i].getPoint());
				
				//Creating points for the diagram of city population
				Point pnPoint = new Point();
				pnPoint.setX(870);
				pnPoint.setY(400+t);
				
				//Creating rectangle for the bars for the diagram of city population
				Rectangle pn = new Rectangle();
				pn.setPoint(pnPoint);
				pn.setWidth(cities[i].getPop()/10000);
				pn.setHeight(20);
				pn.setFillColor(Color.GREEN);
				pn.setStrokeColor(Color.BLACK);
				pn.setStrokeWidth(1);
				frame.addToPlot(pn);
				
				//Creating points for the diagram of the amount of foreign population in %
				Point pfPoint = new Point();
				pfPoint.setX(870+(cities[i].getPop()/10000));
				pfPoint.setY(400+t);
				
				//Creating rectangle for the bars for the diagram of the foreign population in %
				Rectangle pf = new Rectangle();
				pf.setPoint(pfPoint);
				pf.setWidth(cities[i].getForeignp());
				pf.setHeight(20);
				pf.setFillColor(Color.BLUE);
				pf.setStrokeColor(Color.BLACK);
				pf.setStrokeWidth(1);
				frame.addToPlot(pf);
				
				//Creating points for the diagram of GDP per capita
				Point gdpPoint = new Point();
				gdpPoint.setX(870);
				gdpPoint.setY(125+t);
				
				//Creating rectangle for the bars for the diagram of GDP per capita
				Rectangle gdp = new Rectangle();
				gdp.setPoint(gdpPoint);
				gdp.setWidth(gdppercapita[i]/1000);
				gdp.setHeight(20);
				gdp.setFillColor(Color.YELLOW);
				gdp.setStrokeColor(Color.BLACK);
				gdp.setStrokeWidth(1);
				frame.addToPlot(gdp);
				
				//Creating labels for the diagrams
				Point labelPoint = new Point();
				labelPoint.setX(800);
				labelPoint.setY(142+t);
				
				Label describeDiagram = new Label();
				describeDiagram.setPosition(labelPoint);
				describeDiagram.setText(cities[i].getName());
				frame.addToPlot(describeDiagram);
				
				Point labelPoint2 = new Point();
				labelPoint2.setX(800);
				labelPoint2.setY(417+t);
				
				Label describeDiagram2 = new Label();
				describeDiagram2.setPosition(labelPoint2);
				describeDiagram2.setText(cities[i].getName());
				frame.addToPlot(describeDiagram2);
				
				Point gdpValues = new Point();
				gdpValues.setX(gdp.getPoint().getX() + gdp.getWidth() + 15);
				gdpValues.setY(gdp.getPoint().getY()+17);
				
				Label gdpValuesLabel = new Label();
				gdpValuesLabel.setPosition(gdpValues);
				gdpValuesLabel.setText(String.valueOf((Math.round(gdppercapita[i]))) + " €");
				frame.addToPlot(gdpValuesLabel);
				
				Point popValues = new Point();
				popValues.setX(pn.getPoint().getX() + 15);
				popValues.setY(pn.getPoint().getY()+17);
				
				Label popValuesLabel = new Label();
				popValuesLabel.setPosition(popValues);
				popValuesLabel.setText(String.valueOf(cities[i].getPop()));
				frame.addToPlot(popValuesLabel);
				
				Point popFValues = new Point();
				popFValues.setX(pf.getPoint().getX() + pf.getWidth() + 15);
				popFValues.setY(pf.getPoint().getY()+17);
				
				Label popFValuesLabel = new Label();
				popFValuesLabel.setPosition(popFValues);
				popFValuesLabel.setText(String.valueOf(cities[i].getForeignp()) + "%");
				frame.addToPlot(popFValuesLabel);

				t = t + 20;
			}
		} catch (IOException ioe) {
			ioe.printStackTrace();
		}
		
		//Points and labels for the diagram headings
		Point gdpHeading = new Point();
		gdpHeading.setX(950);
		gdpHeading.setY(100);
		
		Label gdpHeadingLabel = new Label();
		gdpHeadingLabel.setPosition(gdpHeading);
		gdpHeadingLabel.setText("GDP per capita (rounded)");
		frame.addToPlot(gdpHeadingLabel);
		
		Point popHeading = new Point();
		popHeading.setX(890);
		popHeading.setY(380);
		
		Label popHeadingLabel = new Label();
		popHeadingLabel.setPosition(popHeading);
		popHeadingLabel.setText("Population and amount of foreign inhabitants in %");
		frame.addToPlot(popHeadingLabel);
		
		//Points and labels for project heading
		Point projectHeading = new Point();
		projectHeading.setX(30);
		projectHeading.setY(30);
		
		Label projectHeadingLabel = new Label();
		projectHeadingLabel.setText("Java Final Project by Raphael Witt");
		projectHeadingLabel.setPosition(projectHeading);
		frame.addToPlot(projectHeadingLabel);
		
		//Points and labels for description of the visual output
		Point outputDescription = new Point();
		outputDescription.setX(30);
		outputDescription.setY(50);
		
		Label outputDesctiptionLabel = new Label();
		outputDesctiptionLabel.setText("The circles' areas represent the area of the cities and the colors represent the population density.");
		outputDesctiptionLabel.setPosition(outputDescription);
		frame.addToPlot(outputDesctiptionLabel);
		
		frame.drawAllFeature();
	}
}