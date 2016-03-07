package view;

import java.util.HashSet;
import java.util.List;

import javafx.scene.Group;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;

public class MethodsView extends View{
	private HashSet<TextBox> methods = new HashSet<TextBox>();
	private VBox methodViews = new VBox();
	private Pane pane;

	public MethodsView(ViewType ID){
		super(ID);
		init();
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
 		return pane;
  	}

	private void init() {
		Group group = new Group();
 		methodViews.setPrefSize(View.NARROW_WIDTH,View.WIDE_WIDTH);
 		ScrollPane sp = new ScrollPane(methodViews);
 		group.getChildren().add(sp);
 		pane = new Pane(group);
		setStyleClass(pane);
	}
}

