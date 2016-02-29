import java.util.*;

import javafx.scene.Scene;
import javafx.scene.layout.GridPane;
import view.View;
import view.ViewFactory;

public class Workspace {
	private List<String> views;
	
	public Workspace(List<String> viewTypes) {
		views = viewTypes;
	}
	
	public Scene init(){
		ViewFactory factory = new ViewFactory();
		GridPane root = new GridPane();
		for(String type: views){
			View view = factory.createView(type);
			switch(type){
			case "Agent":
				root.add(view.getView(),0,0);
				break;
			case "Console":
				root.add(view.getView(),0,1);
				break;
			case "History":
				root.add(view.getView(),1,0);
				break;
			case "SavedMethod":
				root.add(view.getView(),2,0);
				break;
			case "SavedVar":
				root.add(view.getView(),2,1);
				break;
			}
		}
		return new Scene(root);
	}
	
	
	

}