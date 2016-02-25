import java.util.HashMap;
import java.util.Observable;

import javafx.scene.control.*;
import javafx.scene.layout.*;

public class SavedView extends View {
	private int width;
	private int height;
	VBox vars = new VBox();
	VBox methods = new VBox();
		
	public SavedView(String id, int height, int width,HashMap<String,View> viewCollection) {
		super(id, height, width,viewCollection);
		this.width = width;
		this.height = height;
	}

	@Override
	public void update(Observable savedObj, Object arg) {
		if(arg=="NEWVAR"){
			vars.getChildren().add(((SavedVariable) savedObj).getVar());
		}
		else if(arg=="NEWMETHOD"){
			methods.getChildren().add(((SavedMethod) savedObj).getText());
		}

	}

	@Override
	public Region getView() {
		vars.setPrefWidth(width/2);
		methods.setPrefWidth(width/2);
		ScrollPane varScroll = new ScrollPane(vars);
		ScrollPane methodScroll = new ScrollPane(methods);
		varScroll.setMaxHeight(height);
		methodScroll.setMaxHeight(height);
		HBox hb = new HBox(varScroll,methodScroll);
		return hb;
	}

}
