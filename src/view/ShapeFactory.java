package view;

import javafx.scene.shape.Polygon;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.Shape;

public class ShapeFactory {
	public ShapeFactory(){

	}
	
	public Shape createNewShape(String shape,double cellSize){
		switch(shape){
		case("SQUARE"):{
			return new Rectangle(cellSize,cellSize);
		}
		case("TRIANGLE"):{
			Polygon triangle = new Polygon();
			triangle.getPoints().addAll(0.0, 0.0,
			        cellSize, 0.0,
			        cellSize/2, cellSize);

			return triangle;
		}case("HEXAGON"):{
			double root3 = Math.sqrt(3);
			Polygon hexagon = new Polygon();
			double r = cellSize/2;

			hexagon.getPoints().addAll(0.0,r,
					root3*.5*r,.5*r,
					root3*.5*r,-0.5*r,
					0.0,-r,
					-root3*.5*r,-.5*r,
					-root3*.5*r,.5*r);

	    	return hexagon;
					
			
		}
		}
		return null;
	}
}
