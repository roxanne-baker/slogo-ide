import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;


public class TurtleTester extends Application{

	@Override
	public void start(Stage stage) throws Exception {
		ViewAgents agentView = new ViewAgents("TurtleView");
		TurtleTracker tTracker = new TurtleTracker(agentView);
		tTracker.addAgent("Melissa");
		System.out.println(tTracker.getCurrentAgentName());
		tTracker.addAgent("Bob");
		tTracker.setCurrentAgent("Bob");
		System.out.println(tTracker.getCurrentAgentName());
		System.out.println(tTracker.getAgentNames());
		tTracker.setCurrentAgentPenUp(true);
		tTracker.moveCurrentAgent(100, 100);
		tTracker.changeCurrentAgentOrientation(90);
		printPosition(tTracker);
		tTracker.setCurrentAgent("Melissa");
		tTracker.stampCurrentAgent();
		tTracker.moveCurrentAgent(150, 80);
		tTracker.setCurrentAgentVisible(false);
		tTracker.renameAgent("Melissa", "Colette");
		System.out.println(tTracker.getCurrentAgentName());
		printPosition(tTracker);
		
		ViewPreferences prefView= new ViewPreferences("PrefView",tTracker.getAgents());
	//still need error throw when request doesn't exist...

		Scene s = new Scene(prefView.getView(), 500, 500);
		stage.setScene(s);
		stage.show();
	}

	private void printPosition(TurtleTracker tTracker) {
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
