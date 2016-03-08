
package view;

import java.util.ArrayList;
import java.util.List;

import javafx.event.EventHandler;
import javafx.scene.Node;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;


public class Drawer {
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
		setLocation(img, xPosition - OFFSET_X, yPosition - OFFSET_Y);
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
		System.out.println("Add select effect");
		img.setStyle("-fx-effect: dropshadow(three-pass-box, rgba(0,0,0,0.8), 10, 0, 0, 0)");
	}
	public void removeSelectEffect(ImageView imageView) {
		System.out.println("Remove select effect");
		imageView.setStyle("-fx-effect: dropshadow(three-pass-box, rgba(0,0,0,0), 0, 0, 0, 0)");
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
	public void clearAllStamps(){
		for (Node stamp: stampList){
			agentGroup.getChildren().remove(stamp);
		}
	}
	public void removeSelectEffectForNonSelectedTurtles(ImageView imageView) {
		// TODO Auto-generated method stub
		
	}

}