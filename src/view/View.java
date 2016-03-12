
package view;
import java.util.*;

import javafx.scene.Node;
import javafx.scene.layout.Pane;

public abstract class View extends Observable implements Observer{
	private static final String CSS_CLASSES_PATH = "CSSClasses";
	public static final int WIDE_WIDTH = 500;
	public static final int NARROW_WIDTH = 250;
	public static final int MENU_OFFSET = 30;
	public static final int[] COORD00 = {0,MENU_OFFSET};
	public static final int[] COORD01 = {NARROW_WIDTH,MENU_OFFSET};
	public static final int[] COORD02 = {NARROW_WIDTH+WIDE_WIDTH,MENU_OFFSET};
	public static final int[] COORD03 = {NARROW_WIDTH*2+WIDE_WIDTH,MENU_OFFSET};
	public static final int[] COORD10 = {0,MENU_OFFSET+WIDE_WIDTH};
	public static final int[] COORD11 = {NARROW_WIDTH,MENU_OFFSET+WIDE_WIDTH};
	public static final int[] COORD12 = {NARROW_WIDTH+WIDE_WIDTH,MENU_OFFSET+WIDE_WIDTH};
	public static final int[] COORD13 = {NARROW_WIDTH*2+WIDE_WIDTH,MENU_OFFSET+WIDE_WIDTH};
	
	private ResourceBundle cssResources;
	private ViewType viewType;
	private Pane pane = new Pane();

	
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
	
	public void setPane(Node node){
		pane.getChildren().add(node);
	}
	
	public void update(Observable o, Object arg){
	}
	
	public abstract int getX();
	
	public abstract int getY();
}
