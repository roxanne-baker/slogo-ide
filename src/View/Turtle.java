package View;

public class Turtle extends Agent{
	public Turtle(String name, int xPos, int yPos, View obsView) {
		super(name, xPos, yPos, obsView);
		super.setImagePath("turtle.png");
		super.addObserver(obsView);
		setChanged();
		notifyObservers("INITIAL");
	}


}
