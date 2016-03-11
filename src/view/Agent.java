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

import javafx.scene.paint.Color;
/**
 * This is the super class for all agents. This class keeps track of all the properties of an agent. 
 * @author Melissa Zhang
 *
 */
public abstract class Agent extends Observable{
	private static final String DEFAULT_IMAGE_PATH = "dot.png";
	private static final String UPDATE_PROPERTIES = "updateObserver";
	private static final int DEFAULT_PEN_THICKNESS = 2;
	private static final double DEFAULT_SIZE = 50;
	private static final double DEFAULT_ORIENTATION = 0;//vertical, going clockwise
	private static final int DEFAULT_INDEX = 0;
	private IntegerProperty currentImageIndex;
	private DoubleProperty agentXPosition;
	private DoubleProperty agentYPosition;
	private BooleanProperty agentPenUp;
	private IntegerProperty  penColorIndex;
	private DoubleProperty orientation; 
	private StringProperty agentImagePath;
	private DoubleProperty oldYPosition;
	private DoubleProperty oldXPosition;
	private BooleanProperty isVisible;
	private DoubleProperty penThickness;

	private IntegerProperty idProperty;

	private DoubleProperty sizeProperty;
	private ResourceBundle updateResources;
	private StringProperty penStyle;
	private AgentElem agentView;
	private CustomColorPalette myColorPalette;
	private CustomImagePalette myImagePalette;
	
	public Agent(Integer name, double defaultXlocation, double defaultYlocation){
		agentXPosition = new SimpleDoubleProperty(0);
		agentYPosition = new SimpleDoubleProperty(0);
		oldXPosition = new SimpleDoubleProperty(0);
		oldYPosition = new SimpleDoubleProperty(0);

		agentImagePath = new SimpleStringProperty(DEFAULT_IMAGE_PATH);
		agentXPosition = new SimpleDoubleProperty(defaultXlocation);
		agentYPosition = new SimpleDoubleProperty(defaultYlocation);
		oldXPosition = new SimpleDoubleProperty(defaultXlocation);
		oldYPosition = new SimpleDoubleProperty(defaultYlocation);
		agentPenUp = new SimpleBooleanProperty(false); //default value pen is down
		penColorIndex = new SimpleIntegerProperty(DEFAULT_INDEX); 
		penThickness = new SimpleDoubleProperty(DEFAULT_PEN_THICKNESS);
		orientation = new SimpleDoubleProperty(DEFAULT_ORIENTATION); 
		sizeProperty = new SimpleDoubleProperty(DEFAULT_SIZE);
		isVisible = new SimpleBooleanProperty(true);
		currentImageIndex = new SimpleIntegerProperty(DEFAULT_INDEX); 
		idProperty = new SimpleIntegerProperty(name);

		updateResources = ResourceBundle.getBundle(UPDATE_PROPERTIES);
		penStyle = new SimpleStringProperty(updateResources.getString("SOLID"));

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
		return agentPenUp.getValue();
	}
	
	public void setPenUp(boolean penBool){
		agentPenUp.setValue(penBool);
	}

	
	public void setPenThickness(double thickness){
		penThickness.setValue(thickness);

	}
	public double getPenThickness(){
		return penThickness.getValue();
	}
	public void setPenStyle(String style){
		penStyle.setValue(style);
	}
	public String getPenStyle(){
		return penStyle.getValue();
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

	public IntegerProperty getIDProperty() {
		return idProperty;
	}
	
	public Integer getName(){
		return idProperty.getValue();
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
		return currentImageIndex.getValue();
	}

	public void setCurrentImageIndex(int imageIndex){
		currentImageIndex.setValue(imageIndex);
		setImagePath((String) myImagePalette.getPaletteObject(currentImageIndex.getValue()));

	}
	public void setPenColorIndex(int colorIndex) {
		penColorIndex.setValue(colorIndex);
		agentView.setPenColor((Color) ((CustomColor) myColorPalette.getPaletteObject(penColorIndex.getValue())).getColor());

	}
	public int getPenColorIndex() {
		return penColorIndex.getValue();
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
	public void setDefaultImageIndex() {
		currentImageIndex.setValue(DEFAULT_INDEX);
		
	}

}	
