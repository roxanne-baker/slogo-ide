package view;
import java.util.Observer;

import javafx.scene.Group;
import javafx.scene.layout.Pane;

import java.util.Observable;

public abstract class View extends Observable implements Observer{
	public static int WIDE_WIDTH = 500;
	public static int NARROW_WIDTH = 200;
	
	private String viewID;


	public View(String id){
		viewID = id;
		//getView().getStylesheets().add("view");
	}
	
	public abstract Pane getView();
	
	public void update(Observable o, Object arg){
	}
	
	public String getID(){
		return viewID;
	}
}
