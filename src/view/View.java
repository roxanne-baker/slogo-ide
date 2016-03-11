
package view;
import java.util.*;

import javafx.scene.Node;
import javafx.scene.layout.Pane;

public abstract class View extends Observable implements Observer{
	private static final String CSS_CLASSES_PATH = "CSSClasses";
	public static int WIDE_WIDTH = 500;
	public static int NARROW_WIDTH = 250;
	public static int MENU_OFFSET = 40;
	private ResourceBundle cssResources;
	private ViewType viewType;
	private Pane pane = new Pane();
	private int x;
	private int y;

	
	public View(ViewType ID, Preferences savedPreferences){
		viewType = ID;
		cssResources = ResourceBundle.getBundle(CSS_CLASSES_PATH);
		pane.getStyleClass().add(cssResources.getString("DISPLAYVIEW"));
	}
	
	public ViewType getType(){
		return viewType;
	}
	
	public Pane getView(){
		return pane;
	}
	
	public Pane getPane(){
		return pane;
	}
	public void setPane(Node node){
		pane.getChildren().add(node);
	}
	
	public void update(Observable o, Object arg){
	}
	
	public void setX(int x){
		this.x=x;
	}
	
	public void setY(int y){
		this.y=y;
	}
	
	public int getX(){
		return x;
	}
	
	public int getY(){
		return y;
	}
}
