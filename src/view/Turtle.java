
package view;

import java.util.Arrays;
import java.util.List;


public class Turtle extends Agent{
	private static final String RESOURCE_DIR = "TurtleProperties";
	private static final List<String> MUTABLE_LIST = Arrays.asList("PENWIDTH","PENCOLOR","PENUP");
	private static final List<String> OBSERVER_LIST = Arrays.asList("NAME","VISIBLE","IMAGEPATH","SIZE","ORIENTATION");

	public Turtle(String name, double defaultXlocation, double defaultYlocation, View obsView) {
		super(name, defaultXlocation, defaultYlocation, obsView);
		super.setImagePath("turtle.png");
		super.addObserver(obsView);
		setChanged();
		notifyObservers("INITIAL");
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
