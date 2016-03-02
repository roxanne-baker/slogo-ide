package view;

import java.util.Arrays;
import java.util.List;
import java.util.ResourceBundle;
/*
 * Turtle.java is a subclass of Agent. Turtles have a turtle image view and have certain properties that can be changed by the user.
 * @author Melissa Zhang
 */

public class Turtle extends Agent{
	private static final String RESOURCE_DIR = "TurtleProperties";
	private static final String OBSERVER_DIR = "updateObserver";
	private static final String TURTLE_IMAGE_PATH = "turtle.png";
	private static final List<String> MUTABLE_LIST = Arrays.asList("PENWIDTH","PENCOLOR","PENUP","IMAGEPATH");
	private static final List<String> OBSERVER_LIST = Arrays.asList("NAME","VISIBLE","SIZE","ORIENTATION");

	public Turtle(String name, double defaultXlocation, double defaultYlocation, View obsView) {
		super(name, defaultXlocation, defaultYlocation, obsView);
		super.setImagePath(TURTLE_IMAGE_PATH);
		super.addObserver(obsView);
		ResourceBundle myResources = ResourceBundle.getBundle(OBSERVER_DIR);
		setChanged();
		notifyObservers(myResources.getString("INITIAL"));
	}

	@Override
	public List<String> getMutableProperties() {

		return MUTABLE_LIST;
	}
	
	@Override
	public String getResourceString(){
		return RESOURCE_DIR;
	}

	@Override
	public List<String> getObserverProperties() {
		return OBSERVER_LIST;
	}

}
