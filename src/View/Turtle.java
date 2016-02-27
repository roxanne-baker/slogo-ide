<<<<<<< HEAD:src/View/Turtle.java
package view;
=======
import java.util.Arrays;
import java.util.List;

>>>>>>> f499e3850c35348f2b479dd03ef0e17b982ec4a1:src/Turtle.java

public class Turtle extends Agent{
	private static final String RESOURCE_DIR = "TurtleProperties";
	private static final List<String> MUTABLE_LIST = Arrays.asList("VISIBLE","PENUP","IMAGEPATH","SIZE","ORIENTATION","NAME");
	public Turtle(String name, int xPos, int yPos, View obsView) {
		super(name, xPos, yPos, obsView);
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


}
