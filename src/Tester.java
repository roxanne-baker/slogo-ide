import java.util.ArrayList;
import java.util.HashMap;

import view.ConsoleView;
import view.HistoryView;
import view.VariablesView;
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

		VariablesView vv = new VariablesView("HI");
		VariablesController vc = new VariablesController(vm, vv);

		ViewAgents agentView = new ViewAgents("view");
		HistoryView historyView = new HistoryView("view");
		ConsoleView consoleView = new ConsoleView("view", historyView);
		ViewAgentPreferences preferencesView = new ViewAgentPreferences("view");
		TurtleController tc = new TurtleController(preferencesView,agentView);
		HashMap<String,Controller> hm = new HashMap<String,Controller>();
		hm.put("Variables",vc);
		hm.put("Agent", tc);
		//Interpreter ip = new Interpreter(hm);
		//consoleView.setInterpreter(ip);
		tc.addAgent("Melissa");
		tc.moveCurrentAgent(0, 500);
		ViewWindowPreferences windowPrefView = new ViewWindowPreferences("HI");
		Group root = new Group();
		VBox vbox = new VBox();
		vbox.getChildren().addAll(agentView.getView());
		root.getChildren().addAll(vbox);
        Scene myScene = new Scene(root, 500,500);
		primaryStage.setScene(myScene);
        primaryStage.show();
    }
 
    public static void main(String[] args) {
        launch(args);
    }
}