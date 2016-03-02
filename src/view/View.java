package view;
import java.util.Observer;
import java.util.Observable;
import javafx.scene.Group;

public abstract class View extends Observable implements Observer{
	public static int WIDE_WIDTH = 400;
	public static int NARROW_WIDTH = 200;
	
	private String viewID;


	public View(String id){
		viewID = id;
	}
	
	public abstract Group getView();
	
	public void update(Observable o, Object arg){
	}
	
	public String getID(){
		return viewID;
	}
}