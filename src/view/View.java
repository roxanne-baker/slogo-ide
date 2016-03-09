
package view;
import java.util.*;
import javafx.scene.layout.Pane;

public abstract class View extends Observable implements Observer{
	private static final String CSS_CLASSES_PATH = "CSSClasses";
	public static int WIDE_WIDTH = 500;
	public static int NARROW_WIDTH = 250;
	private ResourceBundle updateResources;
	private ViewType viewType;

	
	public View(ViewType ID, Map<String,List<Object>> savedPreferences){
		viewType = ID;
		updateResources = ResourceBundle.getBundle(CSS_CLASSES_PATH);
	}
	
	public ViewType getType(){
		return viewType;
	}
	
	public abstract Pane getView();
	
	
	public void update(Observable o, Object arg){
	}
	
	// added
	public void setStyleClass(Pane pane){
		pane.getStyleClass().add(updateResources.getString("VIEW"));
	}
}
