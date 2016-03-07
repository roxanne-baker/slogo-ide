package view;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;

import javafx.scene.Group;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;

public class MethodsView extends View{
	private HashSet<TextBox> methods = new HashSet<TextBox>();
	private VBox methodViews = new VBox();

	public MethodsView(ViewType ID){
		super(ID);
	}
 	
	public void update(List<MethodElem> methodList){
		methodViews.getChildren().clear();
		for(MethodElem method: methodList){
			methodViews.getChildren().add(method.getMethodV());
		}
		setChanged();
		notifyObservers();
	}
	
 	public void addMethodView(String method){
 		TextBox methodView = new TextBox(method);
 		if(!methods.contains(methodView)){
 			methods.add(methodView);
 			methodViews.getChildren().add(methodView.getTextBox());
 		}
 	}
 
 	@Override
 	public Pane getView() {
 		Group group = new Group();
 		methodViews.setPrefSize(View.NARROW_WIDTH,View.WIDE_WIDTH);
 		ScrollPane sp = new ScrollPane(methodViews);
 		group.getChildren().add(sp);
 		Pane pane = new Pane(group);
		setStyleClass(pane);
 		return pane;
  	}
}
// 	public Group getView() {
// 		Group group = new Group();
// 		methodViews.setPrefSize(View.NARROW_WIDTH,View.WIDE_WIDTH);
// 		ScrollPane sp = new ScrollPane(methodViews);
// 		group.getChildren().add(sp);
// 		return group;
//  	}

