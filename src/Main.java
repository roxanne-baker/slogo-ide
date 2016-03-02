import java.util.ArrayList;

import model.Interpreter;
import model.VariableModel;
import controller.TurtleController;
import controller.VariablesController;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {

	public Main() {
		// TODO Auto-generated constructor stub
	}
	
	@Override
	public void start(Stage primaryStage) {


//		ArrayList<String> viewlist = new ArrayList<String>();
//		viewlist.add("Agent");
//		viewlist.add("History");
//		viewlist.add("Console");
//		viewlist.add("SavedVar");
//		viewlist.add("SavedMethod");


		Workspace UI = new Workspace();
		Scene myScene = UI.init();
		myScene.getStylesheets().add("resources/style/style.css");
        primaryStage.setScene(myScene);
        primaryStage.show();
    }
 
    public static void main(String[] args) {
        launch(args);
    }

}
