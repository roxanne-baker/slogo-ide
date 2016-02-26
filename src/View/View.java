package View;
import java.util.Observable;
import java.util.Observer;

import javafx.scene.Group;
import javafx.scene.layout.*;
import java.util.*;




public abstract class View implements Observer{
	private String viewID;
	private int viewHeight;
	private int viewWidth;
	public View(String id){
		viewID = id;
//		viewHeight = height;
//		viewWidth = width;
	}
	
	public abstract Group getView();
	
	public void update(Observable o, Object arg){
		System.out.println("here");
	}
	public String getID(){
		return viewID;
	}
}
