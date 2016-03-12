package model;

import java.util.Arrays;
import java.util.List;
/*
 * Turtle.java is a subclass of Agent. Turtles have a turtle image view and have certain properties that can be changed by the user.
 * @author Melissa Zhang
 */

public class Turtle extends Agent{
	private static final String RESOURCE_PROPERTIES = "TurtleProperties";
	private static final String TURTLE_IMAGE_PATH = "turtle.png";
	private static final List<String> MUTABLE_LIST = Arrays.asList("PENWIDTH","PENCOLOR","PENUP","IMAGEPATH", "PENSTYLE");
	private static final List<String> OBSERVER_LIST = Arrays.asList("NAME","VISIBLE","XPOSITION","YPOSITION","ORIENTATION","SIZE");

//<<<<<<< HEAD
	public Turtle(Integer name, double defaultXlocation, double defaultYlocation) {
//		super(name, defaultXlocation, defaultYlocation, obsView);
//		super.setImagePath(TURTLE_IMAGE_PATH);
//		super.addObserver(obsView);
//		ResourceBundle myResources = ResourceBundle.getBundle(OBSERVER_DIR);
//		setChanged();
//		notifyObservers(myResources.getString("INITIAL"));
//=======
//	public Turtle(String name, double defaultXlocation, double defaultYlocation) {
		super(name, defaultXlocation, defaultYlocation);
		super.setImagePath(TURTLE_IMAGE_PATH);		

//>>>>>>> refs/remotes/origin/master
	}

	@Override
	public List<String> getMutableProperties() {

		return MUTABLE_LIST;
	}
	
	@Override
	public String getResourceString(){
		return RESOURCE_PROPERTIES;
	}

	@Override
	public List<String> getObserverProperties() {
		return OBSERVER_LIST;
	}



}
