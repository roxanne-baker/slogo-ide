import java.util.*;

import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.layout.GridPane;

public class TurtleUI {

	public TurtleUI() {

	}
	
	public Scene init(){
		GridPane root = new GridPane();
		View history = ViewFactory.createProduct("History", "History", 300, 200);
		Group hv = history.getView();
		hv.setLayoutX(300);
		View saved = ViewFactory.createProduct("SavedVar", "SavedVar", 200, 250);
		Group sv = saved.getView();
		sv.setLayoutY(450);
		View savedmethod = ViewFactory.createProduct("SavedMethod", "SavedMethod", 100, 250);
		Group sm = savedmethod.getView();
		View console = ViewFactory.createProduct("Console", "Console", 100, 500);
		Group cv = console.getView();
		cv.setLayoutY(300);
		root.add(hv,1,0);
		root.add(cv, 0, 1);
		root.add(sv, 0, 2);
		root.add(sm, 1, 2);
		return new Scene(root,500,500);
	}
	
	
	

}
