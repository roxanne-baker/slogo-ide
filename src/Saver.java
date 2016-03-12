import java.io.File;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Document;

import javafx.stage.FileChooser;
import javafx.stage.FileChooser.ExtensionFilter;
import javafx.stage.Stage;

public abstract class Saver {
	private Document doc;	
	private Stage window;
	private String extensionDescription;
	private String extension;
	
	public Saver(Stage stage, String extensionDescription, String extension) {
		window = stage;
		this.extensionDescription = extensionDescription;
		this.extension = extension;
		String file = chooseFile();
		saveFile(file);
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
	
	private void saveFile(String filePath){
		try{
	        DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
	        dbFactory.setIgnoringElementContentWhitespace(true);
	        DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();	        
	        doc = dBuilder.newDocument();  
	        
	        saveInfo(doc);
	        
	        TransformerFactory transformerFactory =
	                TransformerFactory.newInstance();
            Transformer transformer =
            transformerFactory.newTransformer();
            DOMSource source = new DOMSource(doc);
            StreamResult result = new StreamResult(new File(filePath));
            transformer.transform(source, result);
	        
	        
		} catch(Exception e){	
		}			
	}
	
	public abstract void saveInfo(Document doc);
	
}
