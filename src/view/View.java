package view;
import java.util.Observer;
import java.util.ResourceBundle;
import java.util.Observable;
import javafx.scene.Group;
import javafx.scene.layout.Pane;

public abstract class View extends Observable implements Observer{
//	public static int WIDE_WIDTH = 400;
//	public static int NARROW_WIDTH = 200;
	private static final String CSS_CLASSES_PATH = "CSSClasses";
	public static int WIDE_WIDTH = 500;
	public static int NARROW_WIDTH = 200;
	private ResourceBundle updateResources;
	private Pane pane;
	
	private String viewID;


//	public View(String id){
//		viewID = id;
//	}
	
	public View(String id){
		viewID = id;
		updateResources = ResourceBundle.getBundle(CSS_CLASSES_PATH);
	}
	
	public abstract Pane getView();
	
//	public abstract Group getView();
	
	public void update(Observable o, Object arg){
	}
	
	// added
	public void setStyleClass(Pane pane){
		pane.getStyleClass().add(updateResources.getString("VIEW"));
	}
	
	public String getID(){
		return viewID;
	}
}