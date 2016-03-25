package controller;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.function.BiConsumer;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.ResourceBundle;

import view.CustomColorPalette;
import view.CustomImagePalette;
import view.Palette;
import view.ViewAgents;
import view.ViewAgentPreferences;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.ListProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleListProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import model.Agent;
import model.Turtle;

/**
 * This class keeps track of all the turtles on the screen.
 * @author Melissa Zhang
 *
 */
public class ControllerTurtle extends Controller implements ControllerAgents{

	private static final ResourceBundle PALETTE_RESOURCES = ResourceBundle.getBundle("Palettes");
	private HashMap<Integer,Agent> agentMap;
	private IntegerProperty currentAgentIDProperty;
	private ListProperty<Integer> activeAgentListProperty;

	private ViewAgentPreferences preferencesView;
	private ViewAgents agentsView;
	private double observerWidth;
	private double observerHeight;
	private double offsetX;
	private double offsetY;
	private CustomColorPalette colorPalette;
	private CustomImagePalette imagePalette;
	
	public ControllerTurtle(ViewAgentPreferences prefView, ViewAgents obsView){
		preferencesView = prefView;
		agentsView = obsView;
		agentMap = new HashMap<Integer,Agent>();
		observerWidth = obsView.getWidth();
		observerHeight = obsView.getHeight();
		offsetX = observerWidth/2;
		offsetY = observerHeight/2;

		currentAgentIDProperty = new SimpleIntegerProperty();
		activeAgentListProperty = new SimpleListProperty<Integer>();
		
		//bind bidirectional currentAgentNameProperty to property in pref view
		currentAgentIDProperty.bindBidirectional(prefView.getCurrentAgentNameProperty());
		//bind bidirectional ActiveAgentListProperty to property in agentView
		activeAgentListProperty.bindBidirectional(obsView.getActiveAgentListProperty());
		
		
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		} 
	}
	
	
	public int getColorPaletteSize() {
		return colorPalette.getPaletteSize();
	}
	
	public int getImagePaletteSize() {
		return imagePalette.getPaletteSize();
	}
	

	@Override
	public void setActiveAgents(List<Integer> activeAgents) {
		ObservableList<Integer> observableList = FXCollections.observableArrayList(activeAgents);
		activeAgentListProperty.setValue(observableList);
		for (Integer agentID : activeAgents) {
			if (!agentMap.containsKey(agentID) ) {
				addAgent(agentID);
			}
		}

	}
	
	@Override
	public List<Integer> getActiveAgents() {
		List<Integer> activeAgentList = new ArrayList<Integer>();
		for (Integer item: activeAgentListProperty.getValue()){
			activeAgentList.add(item);
		}
		return activeAgentList;
	}

	@Override
	public int getNumTotalAgents() {
		return agentMap.keySet().size();
	}
	
	@Override
	public int getNumActiveAgents() {
		return activeAgentListProperty.size();
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
	public void addAgent(Integer agentID) {
		Turtle newTurtle = new Turtle(agentID, offsetX, offsetY); //starts in middle of screen

		newTurtle.addObserver(agentsView);
		newTurtle.initialize();

		agentMap.put(agentID, newTurtle);
		updateAgentMapInDisplayViews();
		if (getNumTotalAgents()==1){
			setCurrentAgent(agentID);
			activeAgentListProperty.setValue(FXCollections.observableArrayList(agentID));

		}
		newTurtle.setColorPalette(colorPalette);
		newTurtle.setImagePalette(imagePalette);
		
		
	}

	@Override
	public void removeAgent(Integer agentName) {
		agentMap.remove(agentName);
		if(currentAgentIDProperty.getValue().equals(agentName)){
			currentAgentIDProperty.setValue(null);
		}
		updateAgentMapInDisplayViews();
	}

	
	private void updateAgentMapInDisplayViews() {
		preferencesView.updateAgentMap(agentMap);
		agentsView.updateAgentMap(agentMap);
	}
	
	
	@Override
	public Integer getCurrentAgent() { //needs to throw an error if null
		if (currentAgentIDProperty.getValue()==null){
			return null;
		}
		return currentAgentIDProperty.getValue();
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
		for (Integer agentID : activeAgentListProperty.getValue()) {
			setCurrentAgent(agentID);
			turtleMethod.accept(agentMap.get(currentAgentIDProperty.getValue()));
		}
	}
	
	public double[] getAgentProperties(Function<Agent, Double> propertyToGet) {
		double[] allAgentVals = new double[activeAgentListProperty.getValue().size()];

		for (int i=0; i < allAgentVals.length; i++) {
			setCurrentAgent(activeAgentListProperty.getValue().get(i));
			allAgentVals[i] = propertyToGet.apply(agentMap.get(currentAgentIDProperty.getValue()));
		}
		return allAgentVals;
	}
	
	public void changeTurtleProperty(double[] changePropertyValues, BiConsumer<Agent, Double> changeProperty) {
		for (int i=0; i<activeAgentListProperty.getValue().size(); i++) {
			setCurrentAgent(activeAgentListProperty.getValue().get(i));
			changeProperty.accept(agentMap.get(currentAgentIDProperty.getValue()), changePropertyValues[i]);
		}
	}
	
	@Override
	public void setCurrentAgent(Integer agentName) {
		currentAgentIDProperty.setValue(agentName);	

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
		return agentMap.get(currentAgentIDProperty.getValue());
	}
	@Override
	public Integer getCurrentAgentName() {
		return currentAgentIDProperty.getValue();
	}
	
	@Override
	public void moveCurrentAgent(double[] changeX, double[] changeY) {
		for (int i=0; i<changeX.length; i++) {
			setCurrentAgent(activeAgentListProperty.getValue().get(i));
			agentMap.get(currentAgentIDProperty.getValue()).movePosition(changeX[i], changeY[i]);			
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
			if (palette.getPaletteName() == PALETTE_RESOURCES.getString("CUSTOMCOLORS")){
				agentMap.get(name).setColorPalette((CustomColorPalette) palette);
				
			}if (palette.getPaletteName() == PALETTE_RESOURCES.getString("IMAGES")){
				agentMap.get(name).setImagePalette((CustomImagePalette) palette);
			}
		}
		
	}



}
