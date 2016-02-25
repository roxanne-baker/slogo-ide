import java.util.Observer;

import javafx.scene.Group;




public abstract class View implements Observer{
	private String viewID;
	private int viewHeight;
	private int viewWidth;
	public View(String id, int height, int width){
		viewID = id;
		viewHeight = height;
		viewWidth = width;
	}
	
	public abstract Group getView();
	
	public int getWidth(){
		return viewWidth;
	}
	
	public int getHeight(){
		return viewHeight;
	}
	public String getID(){
		return viewID;
	}
}
