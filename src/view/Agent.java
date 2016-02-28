
package view;
import java.util.List;
import java.util.Observable;
import java.util.ResourceBundle;

import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;

public abstract class Agent extends Observable{
	private static final String DEFAULT_IMAGE_PATH = "dot.png";
	private static final String UPDATE_PROPERTIES = "updateObserver";
	private DoubleProperty agentXPosition;
	private DoubleProperty agentYPosition;
	private boolean agentPenUp;
	private Color penColor;
	private ImageView agentImageView;
	private DoubleProperty orientation; //degrees going clockwise
	private StringProperty agentImagePath;
	private DoubleProperty oldYPosition;
	private DoubleProperty oldXPosition;
	private boolean isVisible;
	private double penThickness;
	private StringProperty nameProperty;
	private DoubleProperty sizeProperty;
	private ResourceBundle myResources;
	
	public Agent(String name, double defaultXlocation, double defaultYlocation,View obsView){
		agentXPosition = new SimpleDoubleProperty(defaultXlocation);
		agentYPosition = new SimpleDoubleProperty(defaultYlocation);
		oldXPosition = new SimpleDoubleProperty(defaultXlocation);
		oldYPosition = new SimpleDoubleProperty(defaultYlocation);
		agentPenUp = false; //default value pen is down
		penColor = Color.BLACK;
		penThickness = 2;
		orientation = new SimpleDoubleProperty(0); //vertical
		sizeProperty = new SimpleDoubleProperty(50);
		isVisible = true;
		agentImagePath = new SimpleStringProperty(DEFAULT_IMAGE_PATH);
		nameProperty = new SimpleStringProperty(name);
		myResources = ResourceBundle.getBundle(UPDATE_PROPERTIES);

	}
	public double getXPosition(){
		return agentXPosition.doubleValue();
	}
	public double getYPosition(){
		return agentYPosition.doubleValue();
	}
	
	public void movePosition(double x, double y){
		oldXPosition = agentXPosition;
		oldYPosition = agentYPosition;
		agentXPosition.setValue(agentXPosition.doubleValue() + x);
		agentYPosition.setValue(agentYPosition.doubleValue() + y);
		setChanged();
		notifyObservers("MOVE");
	}
	public void leaveStamp(){
		setChanged();
		notifyObservers(myResources.getString("STAMP"));
	}
	public boolean isPenUp(){
		return agentPenUp;
	}
	
	public void setPenUp(boolean penBool){
		agentPenUp = penBool;
	}
	public void setPenColor(Color color){
		penColor = color;
	}
	
	public Color getPenColor(){
		return penColor;
	}
	
	public void setPenThickness(double thickness){
		penThickness = thickness;

	}
	public double getPenThickness(){
		return penThickness;
	}
	

	public StringProperty getImagePathProperty(){
		return agentImagePath;
	}
	public String getImagePath(){;
		return agentImagePath.toString();
	}
	
	public void setImagePath(String imagePath){
		agentImageView = new ImageView(new Image(getClass().getClassLoader().getResourceAsStream(imagePath),sizeProperty.doubleValue(),sizeProperty.doubleValue(),true,true));
		agentImagePath.setValue(imagePath);
		setChanged();
		notifyObservers(myResources.getString("IMAGEVIEW"));

	}
	public ImageView getImageView(){
		return agentImageView;
	}
	public DoubleProperty getOrientationProperty(){
		return orientation;
	}
	public double getOrientation(){
		return orientation.doubleValue();
	}
	public void changeOrientation(double degreeChange){
		orientation.setValue(orientation.doubleValue() +  degreeChange);
		agentImageView.setRotate(orientation.doubleValue());
	}
	public DoubleProperty getSizeProperty(){
		return sizeProperty;
	}
	public double getSize(){
		return sizeProperty.doubleValue();
		
	}
	public void setSize(double newSize){
		sizeProperty.setValue(newSize);
	}
	public double getOldXPosition() {
		return oldXPosition.doubleValue();
	}
	public double getOldYPosition(){
		return oldYPosition.doubleValue();
	}
	public ImageView getImageCopy() {
		ImageView imgCopy = new ImageView(new Image(getClass().getClassLoader().getResourceAsStream(agentImagePath.getValue()),sizeProperty.doubleValue(),sizeProperty.doubleValue(),true,true));
		imgCopy.setRotate(orientation.doubleValue());
		return imgCopy;

	}
	public StringProperty getNameProperty() {
		return nameProperty;
	}
	
	public String getName(){
		return nameProperty.toString();
	}

	public void changeName(String newName) {
		nameProperty.set(newName);
		
	}
	public void setVisible(boolean isVis) {
		isVisible = isVis;
		setChanged();
		notifyObservers(myResources.getString("VISIBLE"));
		setChanged();
		notifyObservers(myResources.getString("UPDATE"));
		
	}
	public boolean isVisible(){
		return isVisible;
	}
	
	public abstract List<String> getMutableProperties();
	
	public abstract String getResourceString();

	public abstract List<String> getObserverProperties();
	
	public void setColor(String color) {
		// TODO Auto-generated method stub
		
	}
	public String getColor() {
		// TODO Auto-generated method stub
		return null;
	}

	
}	
