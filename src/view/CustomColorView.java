package view;

import javafx.scene.Group;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class CustomColorView{
	private CustomColor colorObject;
	private Rectangle colorSquare;
	private int colorSize;
	private Group colorGroup;
	public CustomColorView(CustomColor customColor, int size){
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

	
}
