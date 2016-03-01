package controller;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import view.Agent;
import view.IAgentTracker;
import view.Turtle;
import view.ViewAgents;
import view.ViewPreferences;
import javafx.beans.InvalidationListener;
import javafx.scene.image.ImageView;



public class TurtleController extends Controller implements IAgentTracker{
	private static final double DEFAULT_XLOCATION = 100;
	private static final double DEFAULT_YLOCATION = 100;
	private double startX;
	private double startY;
	private HashMap<String,Agent> agentMap;
	private String currentAgent;
	private ViewPreferences preferencesView;
	private ViewAgents observerView;
	private double observerWidth;
	private double observerHeight;
	private double offsetX;
	private double offsetY;
	private int scale;
	
	public TurtleController(ViewPreferences prefView, ViewAgents obsView){
		preferencesView = prefView;
		observerView = obsView;
		agentMap = new HashMap<String,Agent>();
		observerWidth = obsView.getWidth();
		observerHeight = obsView.getHeight();
		offsetX = observerWidth/2;
		offsetY = observerHeight/2;
		addAgent("Melissa");
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		moveCurrentAgent(100,100);

	}
	@Override
	public int getNumAgents() {
		return agentMap.size();
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
		Turtle newTurtle = new Turtle(agentName, offsetX, offsetY,observerView); //starts in middle of screen
		agentMap.put(agentName, newTurtle);
		preferencesView.updateAgentMap(agentMap);
		if (getNumAgents()==1){
			setCurrentAgent(agentName);
		}
		

	}

	@Override
	public void removeAgent(String agentName) {
		agentMap.remove(agentName);
		if(currentAgent.equals(agentName)){
			currentAgent = null;
		}
		preferencesView.updateAgentMap(agentMap);

		
	}
	public void renameAgent(String oldName, String newName){ //needs to throw an error
		if (isValidAgentName(newName)){
		Agent keepAgent = agentMap.get(oldName);
		keepAgent.changeName(newName);
		agentMap.remove(oldName);
		agentMap.put(newName, keepAgent);
		}
		if(currentAgent.equals(oldName)){
			currentAgent = newName;
		}
		preferencesView.updateCurrentAgentAndAgentMap(newName,agentMap);


		
	}
	public String getCurrentAgent() { //needs to throw an error if null
		if (currentAgent==null){
			return null;
		}
		return currentAgent;
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
		preferencesView.updateCurrentAgent(agentName);
		currentAgent = agentName;
		
	}





	@Override
	public void setCurrentAgentImage(String imagePath) {
		agentMap.get(currentAgent).setImagePath(imagePath);		
	}

	public ImageView getCurrentAgentImageView(ImageView image) {
		return agentMap.get(currentAgent).getImageView();
	}

	@Override
	public void setCurrentAgentPenUp(boolean isUp) {
		agentMap.get(currentAgent).setPenUp(isUp);		
	}

	@Override
	public boolean isCurrentAgentPenUp() {
		return agentMap.get(currentAgent).isPenUp();
	}

	@Override
	public void setCurrentAgentVisible(boolean isVisible) {
		agentMap.get(currentAgent).setVisible(isVisible);
	}




	@Override
	public void changeCurrentAgentOrientation(double changeDegrees) {
		agentMap.get(currentAgent).changeOrientation(changeDegrees);
	}




	@Override
	public double getCurrentAgentOrientation() {
		return agentMap.get(currentAgent).getOrientation();
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
		agentMap.get(currentAgent).leaveStamp();
		
	}
	@Override
	public void changeCurrentAgentSize(double size) {
		agentMap.get(currentAgent).setSize(size);		
	}
	@Override
	public double getCurrentAgentSize() {
		return agentMap.get(currentAgent).getSize();		
	}
	@Override
	public double getCurrentAgentXPosition() {
		return agentMap.get(currentAgent).getXPosition();
	}
	@Override
	public double getCurrentAgentYPosition() {
		return agentMap.get(currentAgent).getYPosition();
	}
	@Override
	public Agent getCurrentAgent(String agentName) {
		return agentMap.get(currentAgent);
	}
	@Override
	public String getCurrentAgentName() {
		return currentAgent;
	}
	@Override
	public void moveCurrentAgent(double changeX, double changeY) {
		agentMap.get(currentAgent).movePosition(changeX, changeY);
		
	}
	@Override
	public void setCurrentAgentColor(String color) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public String getCurrentAgentColor() {
		// TODO Auto-generated method stub
		return null;
	}



	public void test() { 
		addAgent("Melissa");
		System.out.println(getCurrentAgentName());
		addAgent("Bob");
		setCurrentAgent("Bob");
		System.out.println(getCurrentAgentName());
		System.out.println(getAgentNames());
		setCurrentAgentPenUp(true);
		moveCurrentAgent(100, 100);
		changeCurrentAgentOrientation(90);
		setCurrentAgent("Melissa");
		stampCurrentAgent();
		moveCurrentAgent(150, 80);
		//tTracker.setCurrentAgentVisible(false);
		renameAgent("Melissa", "Colette");
		System.out.println(getCurrentAgentName());
	}


	
	

}
