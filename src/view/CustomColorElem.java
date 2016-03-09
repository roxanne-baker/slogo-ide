package view;

import javafx.scene.Group;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class CustomColorElem{
	private CustomColor colorObject;
	private Rectangle colorSquare;
	private int colorSize;
	private Group colorGroup;
	public CustomColorElem(CustomColor customColor, int size){
		colorObject = customColor;
		colorSquare = new Rectangle(size,size);
		colorSize = size;
		colorSquare.fillProperty().bind(customColor.getColorProperty());
	}
	public Group getView(){
		colorSquare.setStroke(Color.BLACK);
		colorGroup = new Group();
		colorGroup.getChildren().add(colorSquare);
		return colorGroup;
	}
	public int getSize(){
		return colorSize;
		
	}
	
	public Object getColorObject(){
		return colorObject;
	}

	
}
