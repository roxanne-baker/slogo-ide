package view;
import java.util.List;
import java.util.Observable;
import java.util.ResourceBundle;

import javafx.beans.property.BooleanProperty;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
//<<<<<<< HEAD
import javafx.beans.property.IntegerProperty;
import javafx.beans.value.ObservableValue;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
//=======
//>>>>>>> refs/remotes/origin/master
import javafx.scene.paint.Color;

public abstract class Agent extends Observable{
	private static final String DEFAULT_IMAGE_PATH = "dot.png";
	private static final String UPDATE_PROPERTIES = "updateObserver";
	private static final int DEFAULT_PEN_THICKNESS = 2;
	private static final double DEFAULT_SIZE = 50;
	private static final double DEFAULT_ORIENTATION = 0;//vertical, going clockwise
	private int currentImageIndex;
	private DoubleProperty agentXPosition;
	private DoubleProperty agentYPosition;
	private boolean agentPenUp;
	private int  penColorIndex;
	private DoubleProperty orientation; 
	private StringProperty agentImagePath;
	private DoubleProperty oldYPosition;
	private DoubleProperty oldXPosition;
	private BooleanProperty isVisible;
	private double penThickness;
	private IntegerProperty nameProperty;
	private IntegerProperty idProperty;
	private DoubleProperty sizeProperty;
	private ResourceBundle updateResources;
	private String penStyle;
	private AgentElem agentView;
	private CustomColorPalette myColorPalette;
	private CustomImagePalette myImagePalette;
	
//<<<<<<< HEAD
	public Agent(Integer name, double defaultXlocation, double defaultYlocation){
		agentXPosition = new SimpleDoubleProperty(0);
		agentYPosition = new SimpleDoubleProperty(0);
		oldXPosition = new SimpleDoubleProperty(0);
		oldYPosition = new SimpleDoubleProperty(0);
//=======
//	public Agent(String name, double defaultXlocation, double defaultYlocation){

		agentImagePath = new SimpleStringProperty(DEFAULT_IMAGE_PATH);
//		agentXPosition = new SimpleDoubleProperty(defaultXlocation);
//		agentYPosition = new SimpleDoubleProperty(defaultYlocation);
//		oldXPosition = new SimpleDoubleProperty(defaultXlocation);
//		oldYPosition = new SimpleDoubleProperty(defaultYlocation);
//>>>>>>> refs/remotes/origin/master
		agentPenUp = false; //default value pen is down
		penColorIndex = -1; //no index chosen
		penThickness = DEFAULT_PEN_THICKNESS;
		orientation = new SimpleDoubleProperty(DEFAULT_ORIENTATION); 
		sizeProperty = new SimpleDoubleProperty(DEFAULT_SIZE);
		isVisible = new SimpleBooleanProperty(true);
//<<<<<<< HEAD
		agentImagePath = new SimpleStringProperty(DEFAULT_IMAGE_PATH);
//		agentImageView = new ImageView(new Image(getClass().getClassLoader().getResourceAsStream(agentImagePath.getValue()),sizeProperty.doubleValue(),sizeProperty.doubleValue(),true,true));
//		currentShapeIndex = 0;
//		oldImageView = agentImageView;
		nameProperty = new SimpleIntegerProperty(name);
//		myResources = ResourceBundle.getBundle(UPDATE_PROPERTIES);
//		penStyle = myResources.getString("SOLID");
//=======
		currentImageIndex = -1; 
//		nameProperty = new SimpleStringProperty(name);
		updateResources = ResourceBundle.getBundle(UPDATE_PROPERTIES);
		penStyle = updateResources.getString("SOLID");
//>>>>>>> refs/remotes/origin/master

		agentView = new AgentElem(this);
	
		
	}
	public void initialize() {
		setChanged();
		notifyObservers(updateResources.getString("INITIAL"));
		
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
		notifyObservers(updateResources.getString("MOVE"));

	}
	public void leaveStamp(){
		setChanged();
		notifyObservers(updateResources.getString("STAMP"));
	}
	public boolean isPenUp(){
		return agentPenUp;
	}
	
	public void setPenUp(boolean penBool){
		agentPenUp = penBool;
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
		agentImagePath.setValue(imagePath);
		agentView.updateImageView();
		setChanged();
		notifyObservers(updateResources.getString("IMAGEVIEW"));

	}

	public DoubleProperty getOrientationProperty(){
		return orientation;
	}
	public double getOrientation(){
		return orientation.doubleValue();
	}
	public void changeOrientation(double degreeChange){
		orientation.setValue(orientation.doubleValue() +  degreeChange);
		agentView.setRotate(orientation.doubleValue());
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
	public IntegerProperty getNameProperty() {
		return nameProperty;
	}
	
	public IntegerProperty getIDProperty() {
		return idProperty;
	}
	
	public Integer getName(){
		return nameProperty.getValue();
	}

	public void changeName(Integer newName) {
		nameProperty.set(newName);
		
	}
	public void setVisible(boolean isVis) {
		isVisible.setValue(isVis);
		setChanged();
		notifyObservers(updateResources.getString("VISIBLE"));
		setChanged();
		notifyObservers(updateResources.getString("UPDATE"));
		
	}
	public boolean isVisible(){
		return isVisible.getValue();
	}
	
	public abstract List<String> getMutableProperties();
	
	public abstract String getResourceString();

	public abstract List<String> getObserverProperties();
	


	public BooleanProperty getVisibleProperty() {
		return isVisible;
	}
	public int getCurrentImageIndex(){
		return currentImageIndex;
	}

	public void setCurrentImageIndex(int imageIndex){
		currentImageIndex = imageIndex;
		setImagePath((String) myImagePalette.getPaletteObject(currentImageIndex));

	}
	public void setPenColorIndex(int colorIndex) {
		penColorIndex = colorIndex;
		System.out.println("C ind: "+colorIndex);
		agentView.setPenColor((Color) ((CustomColor) myColorPalette.getPaletteObject(penColorIndex)).getColor());	
	}
	public int getPenColorIndex() {
		return penColorIndex;
	}
	public AgentElem getAgentView() {
		return agentView;
	}
	public void setColorPalette(CustomColorPalette colorPalette) {
		myColorPalette = colorPalette;
		setChanged();
		notifyObservers(updateResources.getString("COLORPALETTE"));
	}

	public void setImagePalette(CustomImagePalette imagePalette) {
		myImagePalette = imagePalette;
		setChanged();
		notifyObservers(updateResources.getString("IMAGEPALETTE"));
	}
	public CustomImagePalette getImagePalette(){
		return myImagePalette;
	}
	public CustomColorPalette getColorPalette(){
		return myColorPalette;
	}

}	
