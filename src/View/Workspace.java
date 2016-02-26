package View;

import java.util.*;

import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.layout.GridPane;

public class Workspace {
	private List<String> views;
	
	public Workspace(List<String> viewTypes) {
		views = viewTypes;
	}
	
	public Scene init(){
		ViewFactory factory = new ViewFactory();
		
		GridPane root = new GridPane();
		int i=0;
		for(String type: views){
			View view = factory.createView(type);
			root.add(view.getView(),i,0);
			i++;
		}
		return new Scene(root);
	}
	
	
	

}
