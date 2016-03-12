import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;

import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.stage.FileChooser.ExtensionFilter;
import view.Preferences;

public class LogoFileSaver {
	private Stage window;
	
	LogoFileSaver(Stage stage) { 
		window = stage;
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
	
	public String text = 
	
	public void saveFile(String filePath) { 
		try {
			PrintWriter writer = new PrintWriter(filePath);
			
		} catch (FileNotFoundException e) {
		}
	}
}
