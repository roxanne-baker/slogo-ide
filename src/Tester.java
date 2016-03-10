import java.util.HashMap;

import view.VariablesView;
import view.ViewAgents;
import view.ViewType;
import view.ViewAgentPreferences;
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

		VariablesView vv = new VariablesView(ViewType.VARIABLES);
		VariablesController vc = new VariablesController(vm, vv);

		ViewAgents agentView = new ViewAgents(ViewType.AGENT);
		ViewAgentPreferences preferencesView = new ViewAgentPreferences(ViewType.PREFERENCES);
		TurtleController tc = new TurtleController(preferencesView,agentView);
		HashMap<String,Controller> hm = new HashMap<String,Controller>();
		hm.put("Variables",vc);
		hm.put("Agent", tc);
<<<<<<< HEAD
		//Interpreter ip = new Interpreter(hm);
=======
>>>>>>> 6d22c6fb214f8a7aa066d5805a11dec11430250c
		//consoleView.setInterpreter(ip);
		tc.addAgent("Melissa");
		tc.moveCurrentAgent(0, 500);
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