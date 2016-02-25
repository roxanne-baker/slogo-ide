import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Observable;

import javafx.scene.Group;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.scene.text.Font;

public class ConsoleView extends View {
	private int height;
	private int width;
	private HashMap<String,View> allViews;
	private SavedVariableM model = new SavedVariableM();
	private SavedVariableV view;
	private SavedVariableC controller;
	private SavedMethodM methodmodel = new SavedMethodM();
	private SavedMethodV methodview;
	private SavedMethodC methodcontroller;
	
	public ConsoleView(String id, int height, int width, HashMap<String,View> viewCollection) {
		super(id, height, width,viewCollection);
		this.height = height;
		this.width = width;
		allViews = viewCollection;
		view = ((SavedVariableV)allViews.get("SavedVar"));
		controller = new SavedVariableC(model,view);
		methodview = (SavedMethodV)allViews.get("SavedMethod");
		methodcontroller = new SavedMethodC(methodmodel,methodview);
	}
	
	@Override
	public void update(Observable o, Object arg) {

	}

	@Override
	public Group getView() {
		Group group = new Group();
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
				controller.addVariable(textList[1].substring(1),textList[2]);
			}//else if method, create method and add to saved methods
			else if(console.getText().contains("save")){
				String[] textList = console.getText().split(" ");
				methodcontroller.addMethod(textList[1]);
			}
			console.clear();
		});
		vb.getChildren().addAll(console,btn);
		group.getChildren().add(vb);
		return group;
	}

}
