import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Observable;

import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.scene.text.Font;

public class ConsoleView extends View {
	private int height;
	private int width;
	private HashMap<String,View> allViews;
	
	public ConsoleView(String id, int height, int width, HashMap<String,View> viewCollection) {
		super(id, height, width,viewCollection);
		this.height = height;
		this.width = width;
		allViews = viewCollection;
	}
	
	@Override
	public void update(Observable o, Object arg) {

	}

	@Override
	public Region getView() {
		VBox vb = new VBox();
		TextArea console = new TextArea();
		console.getStyleClass().add("code");
		console.setPrefSize(width, height);
		Button btn = new Button("Run");
		btn.setOnMouseClicked(e->{
			History hist = new History(console.getText(),allViews.get("History"));
			//if error, create error and add to history
			//else if variable, create variable and add to saved vars
			if(console.getText().contains("make '")){
				String[] textList = console.getText().split(" ");
				SavedVariable sv = new SavedVariable(textList[1].substring(1),textList[2],allViews.get("Saved"));
			}
			//else if method, create method and add to saved methods
			console.clear();
		});
		vb.getChildren().addAll(console,btn);
		return vb;
	}

}
