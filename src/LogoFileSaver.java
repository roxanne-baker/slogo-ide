import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;

import controller.MethodsController;
import controller.VariablesController;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import model.Parser;
import javafx.stage.FileChooser.ExtensionFilter;

public class LogoFileSaver {
	private Stage window;
	private VariablesController variableController; 
	private MethodsController methodController; 
	private Parser parser; 
	PrintWriter writer; 
	
	LogoFileSaver(Stage stage, VariablesController vc, MethodsController mc, Parser parser) { 
		window = stage;
		variableController = vc; 
		methodController = mc; 
		this.parser = parser;
	}
	
	public String chooseFile(){
		FileChooser fileChooser = new FileChooser();
		fileChooser.setTitle("Save File");		
		fileChooser.getExtensionFilters().addAll(new ExtensionFilter("logo", "*.logo"));		
		File file = fileChooser.showSaveDialog(window);
		String fileName = "";
		if (file != null) {
			fileName = file.getPath();
		}
		return fileName;
	}
	
	public void saveFile(String filePath) { 
		try {
			writer = new PrintWriter(filePath);
			
		} catch (FileNotFoundException e) {
			System.out.println("couldn't find file path to save");
		}
	}

}
