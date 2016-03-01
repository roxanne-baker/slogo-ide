
package view;

import java.util.HashMap;
import java.util.HashSet;

import javafx.scene.Group;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.VBox;

public class MethodView extends View{
	private VBox methodViews = new VBox();
	
	public MethodView(String id) {
		super(id);
	}
	
	public void addMethodView(ClickableText method){
		methodViews.getChildren().add(method.getTextBox());
	}

	@Override
	public Group getView() {
		Group group = new Group();
		methodViews.setPrefSize(View.NARROW_WIDTH,View.TALL_HEIGHT);
		ScrollPane sp = new ScrollPane(methodViews);
		group.getChildren().add(sp);
		return group;
	}
	

}