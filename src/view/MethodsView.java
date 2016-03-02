
package view;

import java.util.HashMap;
import java.util.HashSet;

import javafx.scene.Group;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.VBox;

public class MethodsView extends View{
	private HashSet<TextBox> methods = new HashSet<TextBox>();
	private VBox methodViews = new VBox();
	
	public MethodsView(String id) {
 		super(id);
 	}
 	
 	public void addMethodView(String method){
 		TextBox methodView = new TextBox(method);
 		if(!methods.contains(methodView)){
 			methods.add(methodView);
 			methodViews.getChildren().add(methodView.getTextBox());
 		}
 	}
 
 	@Override
 	public Group getView() {
 		Group group = new Group();
 		methodViews.setPrefSize(View.NARROW_WIDTH,View.WIDE_WIDTH);
 		ScrollPane sp = new ScrollPane(methodViews);
 		group.getChildren().add(sp);
 		return group;
  	}
	

}