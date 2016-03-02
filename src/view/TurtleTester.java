package view;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;
import controller.TurtleController;
import controller.TurtleController;

public class TurtleTester extends Application{
	
	@Override
	public void start(Stage stage) throws Exception {
		ViewAgents agentView = new ViewAgents("TurtleView");

		ViewAgentPreferences prefView= new ViewAgentPreferences("PrefView");
		TurtleController tTracker = new TurtleController(prefView, agentView);
		tTracker.addAgent("Melissa");
		System.out.println(tTracker.getCurrentAgentName());
//		tTracker.addAgent("Bob");
//		tTracker.setCurrentAgent("Bob");
//		System.out.println(tTracker.getCurrentAgentName());
//		System.out.println(tTracker.getAgentNames());
//		tTracker.setCurrentAgentPenUp(true);
//		tTracker.moveCurrentAgent(100, 100);
//		tTracker.changeCurrentAgentOrientation(90);
//		tTracker.setCurrentAgent("Melissa");
//		tTracker.stampCurrentAgent();
//		tTracker.moveCurrentAgent(150, 80);
//		//tTracker.setCurrentAgentVisible(false);
//		tTracker.renameAgent("Melissa", "Colette");
//		System.out.println(tTracker.getCurrentAgentName());
//		

		Scene s = new Scene(agentView.getView(), 500, 500);
		stage.setScene(s);
		stage.show();
//		tTracker.changeCurrentAgentOrientation(90);
//		tTracker.setCurrentAgent("Colette");
//		tTracker.stampCurrentAgent();
//		tTracker.moveCurrentAgent(150, 80);
	}

	private void printPosition(TurtleController tTracker) {
//		System.out.println(tTracker.getCurrentAgentXPosition());
//		System.out.println(tTracker.getCurrentAgentYPosition());
	}

    /**
     * Start the program.
     */
    public static void main (String[] args) {
        launch(args);
    }

}
