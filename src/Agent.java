import java.util.Observable;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;


public abstract class Agent extends Observable{
	private int[] agentPosition;
	private boolean agentPenUp;
	private Paint agentColor;
	private ImageView agentImageView;
	private double orientation; //degrees going clockwise
	private int size;
	
	public Agent(String name, int xPos, int yPos){
		agentPosition = new int[2];
		agentPosition[0]= xPos;
		agentPosition[1] = yPos;
		agentPenUp = false; //default value pen is down
		agentColor = Color.BLACK;
		orientation = 0;
		size =10;
	}
	public int[] getPosition(){
		return agentPosition;
	}
	
	public void setPosition(int x, int y){
		agentPosition[0] = x;
		agentPosition[1] = y;
	}
	
	public boolean isPenUp(){
		return agentPenUp;
	}
	
	public void setPenUp(boolean penBool){
		agentPenUp = penBool;
	}
	
	public void setColor(Paint color){
		agentColor = color;
	}
	
	public Paint getColor(){
		return agentColor;
	}
	
	public boolean hasImageView(){
		if (agentImageView == null){
			return true;
		}
		return false;
	}
	public void setImageView(String imagePath){
		agentImageView = new ImageView(new Image(getClass().getClassLoader().getResourceAsStream(imagePath),size,size,false,false));
	}
	public ImageView getImageView(){
		if (hasImageView()){
			return agentImageView;
		
		}
		return null; //or throw error?
	}
	public Number getOrientation(){
		return orientation;
	}
	public void changeOrientation(double degreeChange){
		orientation = orientation +  degreeChange;
		agentImageView.setRotate(orientation);
	}
}
