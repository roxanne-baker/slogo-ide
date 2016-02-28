
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
	public void drawLine(double d,double e,double f, double g, double h, Color color){
		Line line = new Line();
		line.setStartX(d);
		line.setStartY(e);
		line.setEndX(f);
		line.setEndY(g);
		line.setStrokeWidth(h);
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