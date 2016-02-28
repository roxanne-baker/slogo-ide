
package View;
import javafx.scene.Group;

import javafx.scene.image.ImageView;
import javafx.scene.shape.Line;


public class Drawer {
	private Group group;
	public Drawer(Group g){
		group = g;

	}
	public void drawLine(double startX,double startY,double endX, double endY){
		Line line = new Line();
		line.setStartX(startX);
		line.setStartY(startY);
		line.setEndX(endX);
		line.setEndY(endY);
		group.getChildren().add(line);
	}
	/**
	 * 
	 * Need to make a copy of ImageView to stamp it otherwise run into duplicate children issue
	 */
	public void stampImage(ImageView img, double posX, double posY, int size){
		img.setX(posX);
		img.setY(posY);
		group.getChildren().add(img);
	}
	
	public void moveImage(ImageView img, double posX, double posY){
		group.getChildren().remove(img);
		img.setX(posX);
		img.setY(posY);
		group.getChildren().add(img);
	}
	public void removeImage(ImageView agentView) {
		group.getChildren().remove(agentView);
	}
}