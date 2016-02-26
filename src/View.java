import java.util.Observable;
import java.util.Observer;

import javafx.scene.Group;
import javafx.scene.layout.*;
import java.util.*;


public abstract class View implements Observer{
	public static int WIDE_WIDTH = 400;
	public static int NARROW_WIDTH = 200;
	public static int TALL_HEIGHT = 400;
	public static int SHORT_HEIGHT = 200;
	
	private String viewID;
	
	public View(String id){
		viewID = id;
	}

	
	public void update(Observable o, Object arg){
		System.out.println("here");
	}
	
	public abstract Group getView();
	
	public String getID(){
		return viewID;
	}
}
