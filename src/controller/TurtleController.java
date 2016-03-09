package controller;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import view.Agent;
import view.CustomColorPalette;
import view.CustomImagePalette;
import view.Turtle;
import view.ViewAgents;
import view.ViewAgentPreferences;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;



public class TurtleController extends Controller implements IAgentController{

	private HashMap<String,Agent> agentMap;
	private StringProperty currentAgentNameProperty;
	private ViewAgentPreferences preferencesView;
	private ViewAgents agentView;
	private double observerWidth;
	private double observerHeight;
	private double offsetX;
	private double offsetY;
	private CustomColorPalette colorPalette;
	private CustomImagePalette imagePalette;

	
	public TurtleController(ViewAgentPreferences prefView, ViewAgents obsView){
		preferencesView = prefView;
		agentView = obsView;
		agentMap = new HashMap<String,Agent>();
		observerWidth = obsView.getWidth();
		observerHeight = obsView.getHeight();
		offsetX = observerWidth/2;
		offsetY = observerHeight/2;
		currentAgentNameProperty = new SimpleStringProperty();
		
		//bind CurrentAgentNameProperty to agentView and prefView currentAgentProperty
		currentAgentNameProperty.bindBidirectional(prefView.getCurrentAgentNameProperty());
		currentAgentNameProperty.bindBidirectional(obsView.getCurrentAgentNameProperty());
		
		addAgent("Melissa"); //always start with one agent on screen
		


	}
	
	@Override
	public int getNumAgents() {
		return agentMap.keySet().size();
	}

	@Override
	public List<Agent> getAgents() {
		ArrayList<Agent> agentList = new ArrayList<Agent>();
		for(String key: agentMap.keySet()){
			agentList.add(agentMap.get(key));
		}
		return agentList;
	}

	@Override
	public List<String> getAgentNames() {
		ArrayList<String> agentNames = new ArrayList<String>();
		for (String key: agentMap.keySet()){
			agentNames.add(key);
		}
		return agentNames;
	}

	@Override
	public void addAgent(String agentName) {
		Turtle newTurtle = new Turtle(agentName, offsetX, offsetY,agentView); //starts in middle of screen
		agentMap.put(agentName, newTurtle);
		updateAgentMapInDisplayViews();
		if (getNumAgents()==1){
			setCurrentAgent(agentName);
		}
		newTurtle.setColorPalette(colorPalette);
		newTurtle.setImagePalette(imagePalette);
	}

	@Override
	public void removeAgent(String agentName) {
		agentMap.remove(agentName);
		if(currentAgentNameProperty.getValue().equals(agentName)){
			currentAgentNameProperty.setValue(null);
		}
		updateAgentMapInDisplayViews();

		
	}

	private void updateAgentMapInDisplayViews() {
		preferencesView.updateAgentMap(agentMap);
		agentView.updateAgentMap(agentMap);
	}
	public void renameAgent(String oldName, String newName){ //needs to throw an error
		if (isValidAgentName(newName)){
		Agent keepAgent = agentMap.get(oldName);
		keepAgent.changeName(newName);
		agentMap.remove(oldName);
		agentMap.put(newName, keepAgent);
		}
		if(currentAgentNameProperty.getValue().equals(oldName)){
			currentAgentNameProperty.setValue(newName);
		}
		updateAgentMapInDisplayViews();
		
	}
	public String getCurrentAgent() { //needs to throw an error if null
		if (currentAgentNameProperty.getValue()==null){
			return null;
		}
		return currentAgentNameProperty.getValue();
	}
	@Override
	public boolean isAgent(String name) {
		for (String key: agentMap.keySet()){
			if (key.equals(name)){
				return true;
			}
		}
		return false;
	}

	@Override
	public void setCurrentAgent(String agentName) {
		currentAgentNameProperty.setValue(agentName);	
		preferencesView.updateCurrentAgentSelection();
		agentView.updateCurrentAgentView();
	}

	@Override
	public void setCurrentAgentImage(String imagePath) {
		agentMap.get(currentAgentNameProperty.getValue()).setImagePath(imagePath);		
	}


	@Override
	public void setCurrentAgentPenUp(boolean isUp) {
		agentMap.get(currentAgentNameProperty.getValue()).setPenUp(isUp);		
	}

	@Override
	public boolean isCurrentAgentPenUp() {
		return agentMap.get(currentAgentNameProperty.getValue()).isPenUp();
	}

	@Override
	public void setCurrentAgentVisible(boolean isVisible) {
		agentMap.get(currentAgentNameProperty.getValue()).setVisible(isVisible);
	}

	@Override
	public void changeCurrentAgentOrientation(double changeDegrees) {
		agentMap.get(currentAgentNameProperty.getValue()).changeOrientation(changeDegrees);
	}

	@Override
	public double getCurrentAgentOrientation() {
		return agentMap.get(currentAgentNameProperty.getValue()).getOrientation();
	}
	@Override
	public boolean isValidAgentName(String name) {
		if(isAgent(name)){
			return false;
		}
		return true;
	}
	
	@Override
	public void stampCurrentAgent() {
		agentMap.get(currentAgentNameProperty.getValue()).leaveStamp();
		
	}
	
	@Override
	public void changeCurrentAgentSize(double size) {
		agentMap.get(currentAgentNameProperty.getValue()).setSize(size);		
	}
	@Override
	public double getCurrentAgentSize() {
		return agentMap.get(currentAgentNameProperty.getValue()).getSize();		
	}
	@Override
	public double getCurrentAgentXPosition() {
		return agentMap.get(currentAgentNameProperty.getValue()).getXPosition();
	}
	@Override
	public double getCurrentAgentYPosition() {
		return agentMap.get(currentAgentNameProperty.getValue()).getYPosition();
	}
	@Override
	public Agent getCurrentAgent(String agentName) {
		return agentMap.get(currentAgentNameProperty.getValue());
	}
	@Override
	public String getCurrentAgentName() {
		return currentAgentNameProperty.getValue();
	}
	@Override
	public void moveCurrentAgent(double changeX, double changeY) {
		agentMap.get(currentAgentNameProperty.getValue()).movePosition(changeX, changeY);
		
	}

	@Override
	public void setCurrentAgentPenColor(int colorIndex) {
		agentMap.get(currentAgentNameProperty.getValue()).setPenColorIndex(colorIndex);
		
	}
	@Override
	public int getCurrentAgentColorIndex() {
		return agentMap.get(currentAgentNameProperty.getValue()).getPenColorIndex();
	}


	@Override
	public void setCurrentAgentPenThickness(double thickness) {
		agentMap.get(currentAgentNameProperty.getValue()).setPenThickness(thickness);;
		
	}

	@Override
	public void setCurrentAgentShapeIndex(int shapeIndex) {
		agentMap.get(currentAgentNameProperty.getValue()).setCurrentImageIndex(shapeIndex);
		
	}


	@Override
	public int getCurrentAgentShapeIndex() {
		return 	agentMap.get(currentAgentNameProperty.getValue()).getCurrentImageIndex();

	}

	@Override
	public void clearStamps() {
		// TODO Auto-generated method stub
		
	}

	public void setColorPalette(CustomColorPalette customColorPalette) {
		colorPalette = customColorPalette;
	}

	public void setImagePalette(CustomImagePalette customImagePalette) {
		imagePalette = customImagePalette;
		
	}
	






	
	

}
