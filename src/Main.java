import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {

	public Main() {
	}
	
	@Override
	public void start(Stage primaryStage) {
		Workspace UI = new Workspace(primaryStage);
		Scene myScene = UI.init();
        primaryStage.setScene(myScene);
        primaryStage.show();
        
    }
 
    public static void main(String[] args) {
        launch(args);
    }

}
