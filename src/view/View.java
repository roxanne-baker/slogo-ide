package view;
import java.util.Observer;
import java.util.ResourceBundle;

import javafx.scene.Group;
import javafx.scene.layout.Pane;

import java.util.Observable;

public abstract class View extends Observable implements Observer{
	private static final String CSS_CLASSES_PATH = "CSSClasses";
	public static int WIDE_WIDTH = 500;
	public static int NARROW_WIDTH = 200;
	private ResourceBundle myResources;
	private Pane pane;
	
	private String viewID;


	public View(String id){
		viewID = id;
		myResources = ResourceBundle.getBundle(CSS_CLASSES_PATH);
	}
	
	public abstract Pane getView();
	
	public void setStyleClass(Pane pane){
		pane.getStyleClass().add(myResources.getString("VIEW"));
	}
	
	public void update(Observable o, Object arg){
	}
	
	public String getID(){
		return viewID;
	}
}
