import java.io.File;

import javafx.stage.FileChooser;
import javafx.stage.FileChooser.ExtensionFilter;
import javafx.stage.Stage;

public abstract class Saver {
	private Stage window;
	private String extensionDescription;
	private String extension;
	
	public Saver(Stage stage, String extensionDescription, String extension) {
		window = stage;
		this.extensionDescription = extensionDescription;
		this.extension = extension;
	}
	
	public String chooseFile(){
		FileChooser fileChooser = new FileChooser();
		fileChooser.setTitle("Save a File");		
		fileChooser.getExtensionFilters().addAll(
		        new ExtensionFilter(extensionDescription, extension));		
		File file = fileChooser.showSaveDialog(window);
		String fileName = "";
		if (file != null) {
			fileName = file.getPath();
		}
		return fileName;
	}
	
	public abstract void saveFile(String filePath);
	
}
