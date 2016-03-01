package view;
import java.util.Observer;
import java.util.Observable;
import javafx.scene.Group;

public abstract class View implements Observer{
	public static int WIDE_WIDTH = 400;
	public static int NARROW_WIDTH = 200;
	public static int TALL_HEIGHT = 400;
	public static int SHORT_HEIGHT = 200;
	
	private String viewID;
	private int viewHeight;
	private int viewWidth;

	public View(String id){
		viewID = id;
	}
	
	public abstract Group getView();
	
	public void update(Observable o, Object arg){
		//System.out.println("here");
	}
	public String getID(){
		return viewID;
	}
}
