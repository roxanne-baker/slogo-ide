package model;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
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
import view.AgentElem;
import view.CustomColor;
import view.CustomColorPalette;
import view.CustomImagePalette;
/**
 * This is the super class for all agents. This class keeps track of all the properties of an agent. 
 * @author Melissa Zhang
 *
 */
public abstract class Agent extends Observable{
	private static final String DEFAULT_IMAGE_PATH = "dot.png";
	private static final String UPDATE_PROPERTIES = "updateObserver";
	private final ResourceBundle WINDOW_RESOURCES = ResourceBundle.getBundle("WorldProperties");
	private final double WORLD_DIMENSION = Double.parseDouble(WINDOW_RESOURCES.getString("WORLD_SIZE"));
	private static final int DEFAULT_PEN_THICKNESS = 2;
	private static final double DEFAULT_SIZE = 50;
	private static final double DEFAULT_ORIENTATION = 0;//vertical, going clockwise
	private static final int DEFAULT_INDEX = 0;
	private final int DEFAULT_WINDOW_BEHAVIOR = 0; 
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
	private IntegerProperty windowBehaviorProperty; 
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
		windowBehaviorProperty = new SimpleIntegerProperty(DEFAULT_WINDOW_BEHAVIOR);

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
	
	private void saveAgentPosition() { 
		oldXPosition.setValue(agentXPosition.getValue());
		oldYPosition.setValue(agentYPosition.getValue());
	}
	
	public void setMovePosition(Double x, Double y) { 
		saveAgentPosition();
		agentXPosition.setValue(agentXPosition.doubleValue() + x);
		agentYPosition.setValue(agentYPosition.doubleValue() + y);
		setChanged();
		notifyObservers(updateResources.getString("MOVE"));
	}
	
	public void moveWithWindowBehavior(Double x, Double y) {
		setVisible(!willCrossBounds(x,y));
		setMovePosition(x, y);
	}

	public void moveWithWrapBehavior(Double x, Double y) { 
		System.out.println("hey");
		double xChange = x; 
		double yChange = y; 
		if (willCrossBounds(x, 0)) {
			double moveFactor = (x < 0) ? 1 : -1;
			xChange = (moveFactor * (WORLD_DIMENSION - x)) + agentXPosition.getValue(); 
			System.out.println(xChange);
			System.out.println("what x " + agentXPosition.getValue() + xChange);
//			if ((x /-1) > 0) { 
//				xChange = 500 - (x - agentXPosition.getValue()); 
//			} else { 
//				xChange = x - (500 - agentXPosition.getValue()); 
//			}	
		}
		if (willCrossBounds(y, 0)) { 
			double moveFactor = (y < 0) ? 1 : -1;
			yChange = (moveFactor * (WORLD_DIMENSION - y)) + agentYPosition.getValue(); 
		}
		setMovePosition(xChange, yChange);
	}
	
	private boolean outOfBounds(double xPos, double yPos) { 
		return (xPos >= WORLD_DIMENSION || xPos < 0) ||
				(yPos >= WORLD_DIMENSION || xPos < 0);
	}
	
	private boolean willCrossBounds(double x, double y) { 
		return outOfBounds(agentXPosition.getValue() + x, agentYPosition.getValue() + y);
	}
	
	
	public void movePosition(double x, double y){
		java.lang.reflect.Method method = null;
		System.out.println(WINDOW_RESOURCES.getString(windowBehaviorProperty.getValue()+""));
		try {
			  method = Agent.class.getMethod(WINDOW_RESOURCES.getString(windowBehaviorProperty.getValue()+""), Double.class, Double.class);
		} catch (SecurityException e) {
			} catch (NoSuchMethodException e) {
				System.out.println("no such method");
			}
		try {
			  method.invoke(this, (Double) x, (Double) y);
			} catch (IllegalArgumentException e) { // exception handling omitted for brevity
			} catch (IllegalAccessException e) { // exception handling omitted for brevity
			} catch (InvocationTargetException e) { // exception handling omitted for brevity
			}
	}
		
	private Method methodReflection(String methodName, Object... args) { 
		java.lang.reflect.Method method = null;
		try {
			  method = this.getClass().getMethod(methodName, args[0].getClass(), args[1].getClass());
		} catch (SecurityException e) {		
		} catch (NoSuchMethodException e) {
				System.out.println("no such method");
		}
		return method;
	}
	
	private void reflectedMethodExecute(Method method, Object...args) { 
		try {
			  method.invoke(this, args);
		} catch (IllegalArgumentException e) { 
		} catch (IllegalAccessException e) { 
		} catch (InvocationTargetException e) { 
		}
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
	
	public void setWindowBehavior(int windowBehavior) { 
		windowBehaviorProperty.setValue(windowBehavior);
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
