import java.util.Observable;
import java.util.Observer;

import javafx.scene.Group;
import javafx.scene.layout.*;
import java.util.*;



public abstract class View implements Observer{
	private String viewID;
	private int viewHeight;
	private int viewWidth;
	private HashMap<String,View> allViews;
	
	public View(String id, int height, int width,HashMap<String,View> viewCollection){
		viewID = id;
		viewHeight = height;
		viewWidth = width;
		allViews = viewCollection;
	}
	
	public View getView(String ID){
		return allViews.get(ID);
	}
	
	public void update(Observable o, Object arg){
		System.out.println("here");
	}
	
	public abstract Region getView();
	
	
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
