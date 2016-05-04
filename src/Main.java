import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;
import view.Preferences;

public class Main extends Application {

	public Main() {
	}
	
	@Override
	public void start(Stage primaryStage) {
		XMLReader reader = new XMLReader(primaryStage,true);
		Workspace UI = new Workspace(primaryStage,new Preferences(reader.getPreferences()));
		Scene myScene = UI.init();
        primaryStage.setScene(myScene);
        primaryStage.show();
        
    }
 
    public static void main(String[] args) {
        launch(args);
    }

}