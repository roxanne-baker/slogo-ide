import java.util.*;

import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.layout.Region;

public class TurtleUI {

	public TurtleUI() {

	}
	
	public Scene init(){
		Group root = new Group();
		View history = ViewFactory.createProduct("History", "History", 300, 200);
		Region hv = history.getView();
		hv.setLayoutX(300);
		View console = ViewFactory.createProduct("Console", "Console", 100, 500);
		Region cv = console.getView();
		cv.setLayoutY(300);
		View saved = ViewFactory.createProduct("Saved", "Saved", 200, 500);
		Region sv = saved.getView();
		sv.setLayoutY(450);
		root.getChildren().addAll(hv,cv,sv);
		return new Scene(root,500,500);
	}
	
	
	

}
