
package view;
import java.util.List;
import java.util.Observable;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public abstract class Agent extends Observable{
	private static final String DEFAULT_IMAGE_PATH = "dot.png";
	private int agentXPosition;
	private int agentYPosition;
	private boolean agentPenUp;
	private String agentColor;
	private ImageView agentImageView;
	private double orientation; //degrees going clockwise
	private int agentSize;
	private String agentImagePath;
	private int oldYPosition;
	private int oldXPosition;
	private String agentName;
	private boolean isVisible;
	
	public Agent(String name, int xPos, int yPos,View obsView){
		agentXPosition= xPos;
		agentYPosition = yPos;
		agentPenUp = false; //default value pen is down
		agentColor = "Black";
		orientation = 0;
		agentSize = 50;
		agentName = name;
		isVisible = true;
		setImagePath(DEFAULT_IMAGE_PATH);

	}
	public int getXPosition(){
		return agentXPosition;
	}
	public int getYPosition(){
		return agentYPosition;
	}
	
	public void movePosition(int x, int y){
		oldXPosition = agentXPosition;
		oldYPosition = agentYPosition;
		agentXPosition = agentXPosition + x;
		agentYPosition = agentYPosition + y;
		setChanged();
		notifyObservers("MOVE");
	}
	public void leaveStamp(){
		setChanged();
		notifyObservers("STAMP");
	}
	public boolean isPenUp(){
		return agentPenUp;
	}
	
	public void setPenUp(boolean penBool){
		agentPenUp = penBool;
	}
	
	public void setColor(String color){
		agentColor = color;
	}
	
	public String getColor(){
		return agentColor;
	}
	

	
	public String getImagePath(){;
		return agentImagePath;
	}
	
	public void setImagePath(String imagePath){
		agentImageView = new ImageView(new Image(getClass().getClassLoader().getResourceAsStream(imagePath),agentSize,agentSize,true,true));
		agentImagePath = imagePath;
		setChanged();
		notifyObservers("IMAGEVIEW");

	}
	public ImageView getImageView(){
		return agentImageView;
	}
	public double getOrientation(){
		return orientation;
	}
	public void changeOrientation(double degreeChange){
		orientation = orientation +  degreeChange;
		agentImageView.setRotate(orientation);
	}
	
	public int getSize(){
		return agentSize;
		
	}
	public void setSize(int newSize){
		agentSize = newSize;
	}
	public int getOldXPosition() {
		return oldXPosition;
	}
	public int getOldYPosition(){
		return oldYPosition;
	}
	public ImageView getImageCopy() {
		ImageView imgCopy = new ImageView(new Image(getClass().getClassLoader().getResourceAsStream(agentImagePath),agentSize,agentSize,true,true));
		imgCopy.setRotate(orientation);
		return imgCopy;

	}
	public String getName() {
		// TODO Auto-generated method stub
		return agentName;
	}
	public void changeName(String newName) {
		agentName = newName;
		
	}
	public void setVisible(boolean isVis) {
		isVisible = isVis;
		System.out.println("hi");
		setChanged();
		notifyObservers("VISIBLE");
		setChanged();
		notifyObservers("UPDATE");
		
	}
	public boolean isVisible(){
		return isVisible;
	}
	
	public abstract List<String> getMutableProperties();
	
	public abstract String getResourceString();


}	
