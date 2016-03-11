package controller;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.function.BiConsumer;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.ResourceBundle;

import view.Agent;
import view.CustomColorPalette;
import view.CustomImagePalette;
import view.Palette;
import view.Turtle;
import view.ViewAgents;
import view.ViewAgentPreferences;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;


public class TurtleController extends Controller implements IAgentController{

	private HashMap<Integer,Agent> agentMap;
	private IntegerProperty currentAgentNameProperty;
	private static final String PALETTE_PROPERTIES = "Palettes";

	private ViewAgentPreferences preferencesView;
	private ViewAgents agentView;
	private double observerWidth;
	private double observerHeight;
	private double offsetX;
	private double offsetY;
	private List<Integer> activeAgentList;

	private CustomColorPalette colorPalette;
	private CustomImagePalette imagePalette;
	private ResourceBundle paletteResources;
	
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
		paletteResources = ResourceBundle.getBundle(PALETTE_PROPERTIES);

		//bind CurrentAgentNameProperty to agentView and prefView currentAgentProperty
		currentAgentNameProperty.bindBidirectional(prefView.getCurrentAgentNameProperty());
		currentAgentNameProperty.bindBidirectional(obsView.getCurrentAgentNameProperty());
		
		activeAgentList.add(1);

		
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		} 
	}
	
	@Override
	public void setActiveAgents(List<Integer> activeAgents) {
		activeAgentList = activeAgents;
		System.out.println(activeAgentList);
		for (Integer agentID : activeAgents) {
			if (!agentMap.containsKey(agentID) ) {
				addAgent(agentID);
			}
		}
	}
	
	@Override
	public List<Integer> getActiveAgents() {
		return activeAgentList;
	}

	@Override
	public int getNumTotalAgents() {
		return agentMap.keySet().size();
	}
	
	@Override
	public int getNumActiveAgents() {
		return activeAgentList.size();
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
		Turtle newTurtle = new Turtle(agentName, offsetX, offsetY); //starts in middle of screen

		newTurtle.addObserver(agentView);
		newTurtle.initialize();

		agentMap.put(agentName, newTurtle);
		updateAgentMapInDisplayViews();
		if (getNumTotalAgents()==1){
			setCurrentAgent(agentName);
		}
		newTurtle.setColorPalette(colorPalette);
		newTurtle.setImagePalette(imagePalette);
	}

	@Override
	public void removeAgent(Integer agentName) {
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
	
//	public void renameAgent(Integer oldName, Integer newName){ //needs to throw an error
//		if (isValidAgentName(newName)){
//		Agent keepAgent = agentMap.get(oldName);
//		keepAgent.changeName(newName);
//		agentMap.remove(oldName);
//		agentMap.put(newName, keepAgent);
//		}
//		if(currentAgentNameProperty.getValue().equals(oldName)){
//			currentAgentNameProperty.setValue(newName);
//		}
//		updateAgentMapInDisplayViews();
//	}
	
	@Override
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
	
	public double[] getAgentProperties(Function<Agent, Double> propertyToGet) {
		double[] allAgentVals = new double[activeAgentList.size()];

		for (int i=0; i < allAgentVals.length; i++) {
			setCurrentAgent(activeAgentList.get(i));
			allAgentVals[i] = propertyToGet.apply(agentMap.get(currentAgentNameProperty.getValue()));
		}
		return allAgentVals;
	}
	
	public void changeTurtleProperty(double[] changePropertyValues, BiConsumer<Agent, Double> changeProperty) {
		for (int i=0; i<activeAgentList.size(); i++) {
			setCurrentAgent(activeAgentList.get(i));
			changeProperty.accept(agentMap.get(currentAgentNameProperty.getValue()), changePropertyValues[i]);
		}
	}
	
	@Override
	public void setCurrentAgent(Integer agentName) {
		currentAgentNameProperty.setValue(agentName);	
		preferencesView.updateCurrentAgentSelection();
		agentView.updateCurrentAgentView();
	}

	@Override
	public boolean isValidAgentName(Integer name) {
		if(isAgent(name)){
			return false;
		}
		return true;
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
	public void moveCurrentAgent(double[] changeX, double[] changeY) {
		for (int i=0; i<changeX.length; i++) {
			setCurrentAgent(activeAgentList.get(i));
			agentMap.get(currentAgentNameProperty.getValue()).movePosition(changeX[i], changeY[i]);			
		}

	}

	public void setColorPalette(CustomColorPalette customColorPalette) {
		colorPalette = customColorPalette;
		addPaletteToTurtles(customColorPalette);
	}


	public void setImagePalette(CustomImagePalette customImagePalette) {
		imagePalette = customImagePalette;
		addPaletteToTurtles(customImagePalette);
		
	}
	
	private void addPaletteToTurtles(Palette palette) {
		for (Integer name: agentMap.keySet()){
			if (palette.getPaletteName() == paletteResources.getString("CUSTOMCOLORS")){
				agentMap.get(name).setColorPalette((CustomColorPalette) palette);
				
			}if (palette.getPaletteName() == paletteResources.getString("IMAGES")){
				agentMap.get(name).setImagePalette((CustomImagePalette) palette);
			}
		}
		
	}
}
