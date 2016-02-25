import java.util.HashMap;
import java.util.Observable;

import javafx.scene.Group;
import javafx.scene.control.*;
import javafx.scene.layout.*;

public class SavedView extends View {
	private int width;
	private int height;
	SavedVariableV sv = new SavedVariableV();
	VBox vars;
	VBox methods = new VBox();
		
	public SavedView(String id, int height, int width,HashMap<String,View> viewCollection) {
		super(id, height, width,viewCollection);
		this.width = width;
		this.height = height;
	}

	@Override
	public void update(Observable savedObj, Object arg) {

	}
	
	public SavedVariableV getSavedVars(){
		return sv;
	}
	
	@Override
	public Group getView() {
		Group group = new Group();
		vars = sv.getSavedVars();
		vars.setPrefWidth(width/2);
		methods.setPrefWidth(width/2);
		ScrollPane varScroll = new ScrollPane(vars);
		ScrollPane methodScroll = new ScrollPane(methods);
		varScroll.setMaxHeight(height);
		methodScroll.setMaxHeight(height);
		HBox hb = new HBox(varScroll,methodScroll);
		group.getChildren().add(hb);
		return group;
	}

}
