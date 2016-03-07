
import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {

	public Main() {
		// TODO Auto-generated constructor stub
	}
	
	@Override
	public void start(Stage primaryStage) {



		Workspace UI = new Workspace(primaryStage);
		Scene myScene = UI.init();
		//Group root = ((Group)(myScene.getRoot())).getChildren().add(e)
        primaryStage.setScene(myScene);
        primaryStage.show();
//        Stage stage2 = new Stage();
//        stage2.setScene(new Workspace(stage2).init());
//        stage2.show();
        
        
    }
 
    public static void main(String[] args) {
        launch(args);
    }

}
