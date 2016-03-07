package controller;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import view.Agent;
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
		
		addAgent("Melissa"); //always start with one agent
		

		
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		} 


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
		updateAgentMapInViews();
		if (getNumAgents()==1){
			setCurrentAgent(agentName);
		}
	}

	@Override
	public void removeAgent(String agentName) {
		agentMap.remove(agentName);
		if(currentAgentNameProperty.getValue().equals(agentName)){
			currentAgentNameProperty.setValue(null);
		}
		updateAgentMapInViews();

		
	}

	private void updateAgentMapInViews() {
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
		updateAgentMapInViews();


		
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
		agentMap.get(currentAgentNameProperty).setImagePath(imagePath);		
	}

//	public ImageView getCurrentAgentImageView(ImageView image) {
//		return agentMap.get(currentAgentNameProperty).getImageView();
//	}

	@Override
	public void setCurrentAgentPenUp(boolean isUp) {
		agentMap.get(currentAgentNameProperty).setPenUp(isUp);		
	}

	@Override
	public boolean isCurrentAgentPenUp() {
		return agentMap.get(currentAgentNameProperty).isPenUp();
	}

	@Override
	public void setCurrentAgentVisible(boolean isVisible) {
		agentMap.get(currentAgentNameProperty).setVisible(isVisible);
	}

	@Override
	public void changeCurrentAgentOrientation(double changeDegrees) {
		agentMap.get(currentAgentNameProperty).changeOrientation(changeDegrees);
	}

	@Override
	public double getCurrentAgentOrientation() {
		return agentMap.get(currentAgentNameProperty).getOrientation();
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
		agentMap.get(currentAgentNameProperty).leaveStamp();
		
	}
	
	@Override
	public void changeCurrentAgentSize(double size) {
		agentMap.get(currentAgentNameProperty).setSize(size);		
	}
	@Override
	public double getCurrentAgentSize() {
		return agentMap.get(currentAgentNameProperty).getSize();		
	}
	@Override
	public double getCurrentAgentXPosition() {
		return agentMap.get(currentAgentNameProperty).getXPosition();
	}
	@Override
	public double getCurrentAgentYPosition() {
		return agentMap.get(currentAgentNameProperty).getYPosition();
	}
	@Override
	public Agent getCurrentAgent(String agentName) {
		return agentMap.get(currentAgentNameProperty);
	}
	@Override
	public String getCurrentAgentName() {
		return currentAgentNameProperty.getValue();
	}
	@Override
	public void moveCurrentAgent(double changeX, double changeY) {
		agentMap.get(currentAgentNameProperty).movePosition(changeX, changeY);
		
	}

	@Override
	public void setCurrentAgentPenColor(int colorIndex) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void setCurrentAgentPenThickness(int thickness) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void setCurrentAgentShape(int shapeIndex) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public int getCurrentAgentColorIndex() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int getCurrentAgentShapeIndex() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void clearStamps() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void setColorPalette(int colorIndex, int red, int green, int blue) {
		// TODO Auto-generated method stub
		
	}



	
	

}
