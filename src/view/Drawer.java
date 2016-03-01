
package view;

import java.util.ArrayList;
import java.util.List;


import javafx.scene.Group;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;


public class Drawer {
	private Group group;
	private List<ImageView> stampList;
	public Drawer(Group g){
		group = g;
		stampList = new ArrayList<ImageView>();
		

	}
	public void drawLine(double oldX,double oldY,double newX, double newY, double thickness, Color color){
		System.out.println(oldX);
		System.out.println(oldY);
		System.out.println(newX);
		System.out.println(newY);

		Line line = new Line();
		line.setStartX(oldX);
		line.setStartY(oldY);
		line.setEndX(newX);
		line.setEndY(newY);
		line.setStrokeWidth(thickness);
		line.setStroke(color);
		group.getChildren().add(line);
	}
	/**
	 * 
	 * Need to make a copy of ImageView to stamp it otherwise run into duplicate children issue
	 */
	public void stampImage(ImageView img, double d, double e, double f){
		img.setX(d);
		img.setY(e);
		stampList.add(img);
		group.getChildren().add(img);
	}
	
	public void moveImage(ImageView img, double d, double e){
		group.getChildren().remove(img);
		img.setX(d);
		img.setY(e);
		group.getChildren().add(img);
	}
	public void removeImage(ImageView agentView) {
		group.getChildren().remove(agentView);
	}
}