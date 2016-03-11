
package view;

import java.util.ArrayList;
import java.util.List;

import javafx.scene.Node;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;


public class Drawer {
	private static final String NO_SELECT_EFFECT = "-fx-effect: dropshadow(three-pass-box, rgba(0,0,0,0), 0, 0, 0, 0)";
	private static final String SELECT_EFFECT = "-fx-effect: dropshadow(three-pass-box, rgba(0,0,0,0.8), 10, 0, 0, 0)";
	private static final double OFFSET = 8;
	private Pane agentGroup;
	private List<ImageView> stampList;
	private List<Node> lineList;
	private List<ImageView> agentViewList;

	private final int OFFSET_X = 250;
	private final int OFFSET_Y = 250;
	
	public Drawer(Pane agentPane){
		agentGroup = agentPane;
		stampList = new ArrayList<ImageView>();
		lineList = new ArrayList<Node>();
		agentViewList = new ArrayList<ImageView>();
		

	}
	public void drawLine(double oldX,double oldY,double newX, double newY, double thickness, Color color, double dash){
		Line line = new Line();
		line.setStartX(oldX + OFFSET_X);
		line.setStartY(oldY + OFFSET_Y);
		line.setEndX(newX + OFFSET_X);
		line.setEndY(newY + OFFSET_Y);
		line.setStrokeWidth(thickness);
		line.setStroke(color);
		line.getStrokeDashArray().removeAll();
		line.getStrokeDashArray().add(dash);
		line.setStrokeDashOffset(OFFSET);
		lineList.add(line);
		agentGroup.getChildren().add(line);
	}
	/**
	 * 
	 * Need to make a copy of ImageView to stamp it otherwise run into duplicate children issue
	 */
	public void stampImage(ImageView img, double xPosition, double yPosition){
		setLocation(img, xPosition, yPosition);
		stampList.add(img);
		agentGroup.getChildren().add(img);
	}
	private void setLocation(ImageView img, double xPosition, double yPosition) {
		img.setLayoutX(xPosition + OFFSET_X -  img.getBoundsInParent().getWidth()/2);
		img.setLayoutY(yPosition + OFFSET_Y - img.getBoundsInParent().getHeight()/2);

	}
	
	public void moveImage(ImageView img, double xPosition, double yPosition){
		agentGroup.getChildren().remove(img);
		setLocation(img, xPosition, yPosition);
		agentGroup.getChildren().add(img);
		if (!agentViewList.contains(img)){
			agentViewList.add(img);
		}
	}

	public void addSelectEffect(ImageView img){
		img.setStyle(SELECT_EFFECT);
	}
	public void removeSelectEffect(ImageView imageView) {
		imageView.setStyle(NO_SELECT_EFFECT);
	}
	public void removeImage(ImageView agentView) {
		agentGroup.getChildren().remove(agentView);
		agentViewList.remove(agentView);
	}
	public void setNewImage(ImageView oldView,ImageView newView, double xPosition,double yPosition) {
		agentGroup.getChildren().remove(oldView);
		agentViewList.remove(oldView);
		setLocation(newView, xPosition, yPosition);
		agentGroup.getChildren().add(newView);
		agentViewList.add(newView);

		
	}

	public void clearAllLines() {
		for (Node line: lineList){
			agentGroup.getChildren().remove(line);
		}
	}
//<<<<<<< HEAD
	public int clearAllStamps(){
		if (!stampList.isEmpty()) {
			
			for (Node stamp: stampList){
				agentGroup.getChildren().remove(stamp);
			}
			stampList.clear();
			return 1;
//=======
//	public void clearAllStamps(){
//		for (ImageView stamp: stampList){
//			agentGroup.getChildren().remove(stamp);
//>>>>>>> refs/remotes/origin/master
		}
		return 0;
	}
	public void removeSelectEffectForNonSelectedTurtles(ImageView selectedImageView) {
		for (ImageView img: agentViewList){
			if (!img.equals(selectedImageView)){
				removeSelectEffect(img);
			}
		}
		
	}

}