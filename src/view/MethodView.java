
package view;
import java.util.HashSet;

import javafx.scene.Group;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.VBox;

public class MethodView extends View{
	private HashSet<ClickableText> methods = new HashSet<ClickableText>();
	private VBox savedMethods = new VBox();
	
	public MethodView(String id) {
		super(id);
	}
	
	public void addMethodView(String method){
		ClickableText methodView = new ClickableText(method);
		if(!methods.contains(methodView)){
			methods.add(methodView);
			savedMethods.getChildren().add(methodView.getTextBox());
		}
	}

	@Override
	public Group getView() {
		Group group = new Group();
		savedMethods.setPrefSize(View.NARROW_WIDTH,View.TALL_HEIGHT);
		ScrollPane sp = new ScrollPane(savedMethods);
		group.getChildren().add(sp);
		return group;
	}
	

}
