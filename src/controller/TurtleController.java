package controller;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.function.BiConsumer;
import java.util.function.Consumer;

import view.Agent;
import view.Turtle;
import view.ViewAgents;
import view.ViewAgentPreferences;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.scene.paint.Color;



public class TurtleController extends Controller implements IAgentController{

	private HashMap<Integer,Agent> agentMap;
	private IntegerProperty currentAgentNameProperty;
	private ViewAgentPreferences preferencesView;
	private ViewAgents agentView;
	private double observerWidth;
	private double observerHeight;
	private double offsetX;
	private double offsetY;
	private List<Integer> activeAgentList;
	
	public TurtleController(ViewAgentPreferences prefView, ViewAgents obsView){
		preferencesView = prefView;
		agentView = obsView;
		agentMap = new HashMap<Integer,Agent>();
		observerWidth = obsView.getWidth();
		observerHeight = obsView.getHeight();
		offsetX = observerWidth/2;
		offsetY = observerHeight/2;
		activeAgentList  = new ArrayList<>();
		currentAgentNameProperty = new SimpleIntegerProperty();
		//bind CurrentAgentNameProperty to agentView and prefView currentAgentProperty
		currentAgentNameProperty.bindBidirectional(prefView.getCurrentAgentNameProperty());
		currentAgentNameProperty.bindBidirectional(obsView.getCurrentAgentNameProperty());
		
		addAgent(1); //always start with one agent
		activeAgentList.add(1);

		
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		} 


	}
	
	public void setActiveAgents(List<Integer> activeAgents) {
		activeAgentList = new ArrayList<>();
		List<Integer> agentNames = getAgentNames();
		for (Integer agentID : agentNames) {
			if (activeAgents.contains(agentID)) {
//				agentMap.get(agentID).setActive(true);
				activeAgentList.add(agentID);
				activeAgents.remove(agentID);
			}
//			else {
//				agentMap.get(agentID).setActive(false);
//			}
		}
		for (Integer agentToAdd : activeAgents) {
			addAgent(agentToAdd);
//			agentMap.get(agentToAdd).setActive(true);
			activeAgentList.add(agentToAdd);
		}
	}
	
	public List<Integer> getActiveAgents() {
		return activeAgentList;
	}
	
	
	
	@Override
	public int getNumAgents() {
		return agentMap.keySet().size();
	}

	@Override
	public List<Agent> getAgents() {
		ArrayList<Agent> agentList = new ArrayList<Agent>();
		for(Integer key: agentMap.keySet()){
			agentList.add(agentMap.get(key));
		}
		return agentList;
	}

	@Override
	public List<Integer> getAgentNames() {
		List<Integer> agentNames = new ArrayList<>();
		for (Integer key: agentMap.keySet()){
			agentNames.add(key);
		}
		return agentNames;
	}

	@Override
	public void addAgent(Integer agentName) {
		Turtle newTurtle = new Turtle(agentName, offsetX, offsetY,agentView); //starts in middle of screen
		agentMap.put(agentName, newTurtle);
		updateAgentMapInViews();
		if (getNumAgents()==1){
			setCurrentAgent(agentName);
		}
	}

	@Override
	public void removeAgent(Integer agentName) {
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
	public void renameAgent(Integer oldName, Integer newName){ //needs to throw an error
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
	
	public Integer getCurrentAgent() { //needs to throw an error if null
		if (currentAgentNameProperty.getValue()==null){
			return null;
		}
		return currentAgentNameProperty.getValue();
	}
	
	@Override
	public boolean isAgent(Integer name) {
		for (Integer key: agentMap.keySet()){
			if (key.equals(name)){
				return true;
			}
		}
		return false;
	}
	
	
	public void changeProperty(Consumer<Agent> turtleMethod) {
		for (Integer agentID : activeAgentList) {
			setCurrentAgent(agentID);
			turtleMethod.accept(agentMap.get(currentAgentNameProperty.getValue()));
		}
	}
	
	@Override
	public void setCurrentAgent(Integer agentName) {
		currentAgentNameProperty.setValue(agentName);	
		preferencesView.updateCurrentAgentSelection();
		agentView.updateCurrentAgentView();
	}

	@Override
	public void setCurrentAgentImage(String imagePath) {
		agentMap.get(currentAgentNameProperty.getValue()).setImagePath(imagePath);		
	}

//	public ImageView getCurrentAgentImageView(ImageView image) {
//		return agentMap.get(currentAgentNameProperty).getImageView();
//	}

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
		System.out.println(agentMap.get(currentAgentNameProperty.getValue()).getOrientation());
		return agentMap.get(currentAgentNameProperty.getValue()).getOrientation();
	}
	@Override
	public boolean isValidAgentName(Integer name) {
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
	public Agent getCurrentAgent(Integer agentName) {
		return agentMap.get(currentAgentNameProperty.getValue());
	}
	@Override
	public Integer getCurrentAgentName() {
		return currentAgentNameProperty.getValue();
	}
	
	@Override
	public void moveCurrentAgent(double changeX, double changeY) {
//		for (Integer agentID : activeAgentList) {
//			setCurrentAgent(agentID);
			agentMap.get(currentAgentNameProperty.getValue()).movePosition(changeX, changeY);
//		}
	}
	
	@Override
	public void setCurrentAgentPenColor(int colorIndex) {
		Color penColor = preferencesView.getColorPalette().getCustomColorList().get(colorIndex);
		agentMap.get(currentAgentNameProperty.getValue()).setPenColor(penColor);
	}

	@Override
	public void setCurrentAgentPenThickness(double thickness) {
		agentMap.get(currentAgentNameProperty.getValue()).setPenThickness(thickness);
	}

	@Override
	public void setCurrentAgentShape(int shapeIndex) {
		// TODO Auto-generated method stub
	}

	@Override
	public int getCurrentAgentColorIndex() {
		List<Color> customColors = preferencesView.getColorPalette().getCustomColorList();
		for (int i=0; i<customColors.size(); i++) {
			if (customColors.get(i).equals(agentMap.get(currentAgentNameProperty.getValue()).getPenColor())) {
				return i;
			}
		}
		//COLOR INDEX NOT FOUND
		return -1;
	}
	
	@Override
	public int getCurrentAgentShapeIndex() {
//		// TODO Auto-generated method stub
//		List<Color> customColors = preferencesView.getColorPalette().getCustomColorList();
//		for (int i=0; i<customColors.size(); i++) {
//			if (customColors.get(i).equals(agentMap.get(currentAgentNameProperty.getValue()).getPenColor())) {
//				return i;
//			}
//		}
		
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
