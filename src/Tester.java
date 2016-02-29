import java.util.ArrayList;

import view.ConsoleView;
import view.HistoryView;
import view.ViewAgents;
import view.ViewPreferences;
import model.Interpreter;
import model.VariableModel;
import controller.TurtleController;
import controller.VariableController;
import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class Tester extends Application{

	
	@Override
	public void start(Stage primaryStage) {
		VariableModel vm = new VariableModel();
		VariableController vc = new VariableController(vm);
		ViewAgents agentView = new ViewAgents("view");
		HistoryView historyView = new HistoryView("view");
		ConsoleView consoleView = new ConsoleView("view", historyView);
		ViewPreferences preferencesView = new ViewPreferences("view");
		TurtleController tc = new TurtleController(preferencesView,agentView);
		Interpreter ip = new Interpreter(tc, vc);
		consoleView.setInterpreter(ip);
		tc.addAgent("Melissa");

		Group root = new Group();
		VBox vbox = new VBox();
		vbox.getChildren().addAll(agentView.getView(),consoleView.getView());
		root.getChildren().addAll(vbox);
        Scene myScene = new Scene(root, 500,500);
		primaryStage.setScene(myScene);
        primaryStage.show();
    }
 
    public static void main(String[] args) {
        launch(args);
    }
}