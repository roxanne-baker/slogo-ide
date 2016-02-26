package View;

import java.util.ArrayList;
import java.util.Random;

import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.ScrollPane.ScrollBarPolicy;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.*;
import javafx.scene.text.*;
import javafx.stage.Stage;

public class Main extends Application {

	public Main() {
		// TODO Auto-generated constructor stub
	}
	
	@Override
	public void start(Stage primaryStage) {
//		ScrollPane region = new ScrollPane();
//        primaryStage.setTitle("java-buddy.blogspot.com");
//        Group root = new Group();
//         
//        TextArea textArea = new TextArea();
//        textArea.setWrapText(true);
//        
//        Button buttonSave = new Button("Save");
//        
//        VBox vb2 = new VBox(5);
//
//        vb2.setPrefSize(200,0);
//        VBox v3 = new VBox();
//        v3.setPrefSize(200,200);
//        //v3.getStyleClass().add("hbox");
//        region.setContent(vb2);
//        region.setVbarPolicy(ScrollBarPolicy.ALWAYS);
//		//region.setLayoutX(200);
//		region.setPrefSize(200,200);
//		region.setMaxHeight(200);
//		region.setVmax(200);
//        
//		textArea.setOnKeyPressed(e->{
//			if(e.getCode()==KeyCode.ENTER){
//		        Text ta = new Text();
//		        ta.setTextAlignment(TextAlignment.LEFT);
//		        ta.setText(textArea.getText());
//		        ta.setOnMouseClicked(e2->{
//		        	System.out.println(ta.getText());
//		        });
//		        textArea.clear();
//		        Random rnd = new Random();
//		        if(rnd.nextInt()%2==0){
//		        	v3.getChildren().add(ta);
//		        }
//		        else{
//		        	vb2.getChildren().add(ta);
//		        }
//		        
//			}
//		});
//        
//
//		Pane pane = new Pane();
//		pane.getChildren().addAll(region,v3);
//		pane.setLayoutX(200);
//        VBox vBox = new VBox();
//        vBox.setPrefWidth(200);
//        vBox.getChildren().addAll(textArea, buttonSave);
//         
//        Scene myScene = new Scene(root, 500, 400);
//        root.getChildren().addAll(pane,vBox);
//        myScene.getStylesheets().add("resources/style/style.css");
//		Group root = new Group();
//		
//		HistoryView hv = new HistoryView("HISTORY",200,200);
//		Region hvnode = hv.getView();
//		hvnode.setLayoutX(200);
//		
////		HistoryController hc = new HistoryController(hv.getVBox(),hv);
////		hc.addHistory(new History("fd 50").getText());
//		
//		
//		SavedView sv = new SavedView("SAVED",200,500);
//		Region svnode = sv.getView();
//		svnode.setLayoutY(320);
//		
////		SavedController sc = new SavedController(sv.getVars(),sv);
////		SavedVariable svar = new SavedVariable("x","10");
////		sc.addVar(svar.getVar());
//		
//		ConsoleView cv = new ConsoleView("CONSOLE",100,500);
//		Region cvnode = cv.getView();
//		cvnode.setLayoutY(200);
//		root.getChildren().addAll(hv.getView(),cv.getView(),sv.getView());
//		Scene myScene = new Scene(root, 500, 400); 
		ArrayList<String> viewlist = new ArrayList<String>();
		viewlist.add("History");
		viewlist.add("Console");
		viewlist.add("SavedVar");
		Workspace UI = new Workspace(viewlist);
		Scene myScene = UI.init();
		myScene.getStylesheets().add("resources/style/style.css");
        primaryStage.setScene(myScene);
        primaryStage.show();
    }
 
    public static void main(String[] args) {
        launch(args);
    }

}
