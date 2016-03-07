package view;
import java.util.List;
import java.util.Observable;
import java.util.ResourceBundle;

import javafx.beans.property.BooleanProperty;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.beans.value.ObservableValue;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;

public abstract class Agent extends Observable{
	private static final String DEFAULT_IMAGE_PATH = "dot.png";
	private static final String UPDATE_PROPERTIES = "updateObserver";
	private static final int DEFAULT_PEN_THICKNESS = 2;
	private static final Color DEFAULT_PEN_COLOR = Color.BLACK;
	private static final double DEFAULT_SIZE = 50;
	private static final double DEFAULT_ORIENTATION = 0;//vertical, going clockwise
	private static final String[] shapeList = {"IMAGE","SQUARE","TRIANGLE","HEXAGON"};
	private int currentShapeIndex;
	private DoubleProperty agentXPosition;
	private DoubleProperty agentYPosition;
	private boolean agentPenUp;
	private Color penColor;
	private ImageView agentImageView;
	private ImageView oldImageView;
	private DoubleProperty orientation; 
	private StringProperty agentImagePath;
	private DoubleProperty oldYPosition;
	private DoubleProperty oldXPosition;
	private BooleanProperty isVisible;
	private double penThickness;
	private StringProperty nameProperty;
	private DoubleProperty sizeProperty;
	private ResourceBundle myResources;
	private String penStyle;
	
	public Agent(String name, double defaultXlocation, double defaultYlocation,View obsView){
		agentXPosition = new SimpleDoubleProperty(defaultXlocation);
		agentYPosition = new SimpleDoubleProperty(defaultYlocation);
		oldXPosition = new SimpleDoubleProperty(defaultXlocation);
		oldYPosition = new SimpleDoubleProperty(defaultYlocation);
		agentPenUp = false; //default value pen is down
		penColor = DEFAULT_PEN_COLOR;
		penThickness = DEFAULT_PEN_THICKNESS;
		orientation = new SimpleDoubleProperty(DEFAULT_ORIENTATION); 
		sizeProperty = new SimpleDoubleProperty(DEFAULT_SIZE);
		isVisible = new SimpleBooleanProperty(true);
		agentImagePath = new SimpleStringProperty(DEFAULT_IMAGE_PATH);
		agentImageView = new ImageView(new Image(getClass().getClassLoader().getResourceAsStream(agentImagePath.getValue()),sizeProperty.doubleValue(),sizeProperty.doubleValue(),true,true));
		currentShapeIndex = 0;
		oldImageView = agentImageView;
		nameProperty = new SimpleStringProperty(name);
		myResources = ResourceBundle.getBundle(UPDATE_PROPERTIES);
		penStyle = myResources.getString("SOLID");

	}
	public double getXPosition(){
		return agentXPosition.doubleValue();
	}
	public double getYPosition(){
		return agentYPosition.doubleValue();
	}
	public DoubleProperty getXPositionProperty(){
		return agentXPosition;
		
	}
	public DoubleProperty getYPositionProperty(){
		return agentYPosition;
	}
	public void movePosition(double x, double y){
		oldXPosition.setValue(agentXPosition.getValue());
		oldYPosition.setValue(agentYPosition.getValue());
		agentXPosition.setValue(agentXPosition.doubleValue() + x);
		agentYPosition.setValue(agentYPosition.doubleValue() + y);
		setChanged();
		notifyObservers(myResources.getString("MOVE"));

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
	public void setPenStyle(String style){
		penStyle = style;
	}
	public String getPenStyle(){
		return penStyle;
	}
	
	public StringProperty getImagePathProperty(){
		return agentImagePath;
	}
	public String getImagePath(){;
		return agentImagePath.getValue();
	}
	
	public void setImagePath(String imagePath){
		oldImageView = agentImageView;
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
		return nameProperty.getValue();
	}

	public void changeName(String newName) {
		nameProperty.set(newName);
		
	}
	public void setVisible(boolean isVis) {
		isVisible.setValue(isVis);
		setChanged();
		notifyObservers(myResources.getString("VISIBLE"));
		setChanged();
		notifyObservers(myResources.getString("UPDATE"));
		
	}
	public boolean isVisible(){
		return isVisible.getValue();
	}
	
	public abstract List<String> getMutableProperties();
	
	public abstract String getResourceString();

	public abstract List<String> getObserverProperties();
	

	public ImageView getOldImageView() {
		return oldImageView;
	}
	public BooleanProperty getVisibleProperty() {
		return isVisible;
	}
	public int getCurrentShapeIndex(){
		return currentShapeIndex;
	}
	public String getCurrentShape(){
		return shapeList[currentShapeIndex];
	}
	public void setCurrentShapeIndex(int index){
		currentShapeIndex = index;
	}
	
}	
