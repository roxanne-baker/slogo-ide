import java.util.ArrayList;
import java.util.HashMap;

import view.ConsoleView;
import view.HistoryView;
import view.VariableView;
import view.ViewAgents;
import view.ViewAgentPreferences;
import view.ViewWindowPreferences;
import model.Interpreter;
import model.VariableModel;
import controller.Controller;
import controller.TurtleController;
import controller.VariablesController;
import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class Tester extends Application{

	
	@Override
	public void start(Stage primaryStage) {
		VariableModel vm = new VariableModel();
<<<<<<< HEAD
		VariablesController vc = new VariablesController(vm);
=======
		VariableView vv = new VariableView("HI");
		VariableController vc = new VariableController(vm, vv);
>>>>>>> c03b3a5f4ce5f0b89f60de4c90ffb1fcf5423dd9
		ViewAgents agentView = new ViewAgents("view");
		HistoryView historyView = new HistoryView("view");
		ConsoleView consoleView = new ConsoleView("view", historyView);
		ViewAgentPreferences preferencesView = new ViewAgentPreferences("view");
		TurtleController tc = new TurtleController(preferencesView,agentView);
		HashMap<String,Controller> hm = new HashMap<String,Controller>();
		hm.put("Variables",vc);
		hm.put("Agent", tc);
		Interpreter ip = new Interpreter(hm);
		//consoleView.setInterpreter(ip);
		tc.addAgent("Melissa");
		
		ViewWindowPreferences windowPrefView = new ViewWindowPreferences("HI");
		Group root = new Group();
		VBox vbox = new VBox();
		vbox.getChildren().add(windowPrefView.getView());
		root.getChildren().addAll(vbox);
        Scene myScene = new Scene(root, 500,500);
		primaryStage.setScene(myScene);
        primaryStage.show();
    }
 
    public static void main(String[] args) {
        launch(args);
    }
}