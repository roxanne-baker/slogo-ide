import java.util.HashMap;
import java.util.HashSet;

import javafx.scene.Group;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.VBox;

public class MethodView extends View{
	private int width;
	private int height;
	private HashSet<String> methods = new HashSet<String>();
	private VBox savedMethods = new VBox();
	
	public MethodView(String id) {
		super(id);
//		this.width = width;
//		this.height = height;
	}
	
	public void addMethodView(String method){
		if(!methods.contains(method)){
			methods.add(method);
			savedMethods.getChildren().add(new ClickableText(method).getText());
		}
	}

	@Override
	public Group getView() {
		Group group = new Group();
		ScrollPane sp = new ScrollPane(savedMethods);
		group.getChildren().add(sp);
		return group;
	}
	

}
