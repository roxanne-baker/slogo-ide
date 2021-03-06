import java.io.File;
import java.util.*;

import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.DocumentBuilder;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Node;
import javafx.scene.paint.Color;
import javafx.stage.FileChooser;
import javafx.stage.FileChooser.ExtensionFilter;
import javafx.stage.Stage;

public class XMLReader {
	private String file;
	private Document doc;	
	private Element rootElem;
	private Stage window;

	public XMLReader(Stage stage, boolean defaultPrefs) {
		window = stage;
		if(defaultPrefs){
			file = "xml/default.xml";
		}
		else{
			file = chooseFile();
		}
		readFile();
	}

	public String chooseFile(){
		FileChooser fileChooser = new FileChooser();
		fileChooser.setTitle("Open XML File");		
		fileChooser.getExtensionFilters().addAll(
		        new ExtensionFilter("XML Files", "*.xml"));		
		File file = fileChooser.showOpenDialog(window);
		String fileName = "";
		if (file != null) {
			fileName = file.getPath();
		}
		return fileName;
	}
	

	/**
	 * @return
	 * This method reads the XML file and calls a simulation specific method to return a simulation based on the information contained within the file.
	 * If the XML file contains faulty user input, the method will instead return an error message to be displayed on the Stage in the main method.
	 */
	
	private void readFile(){
		try{
			File inputFile = new File(file);
	        DocumentBuilderFactory dbFactory 
	            = DocumentBuilderFactory.newInstance();
	        dbFactory.setIgnoringElementContentWhitespace(true);
	        DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();	        
	        doc = dBuilder.parse(inputFile);       
	        rootElem = (Element) doc.getElementsByTagName("preferences").item(0);
	        
		} catch(Exception e){	
		}			
	}
	
	private boolean isSingleElem(String tagName){	
		int length = rootElem.getElementsByTagName(tagName).item(0).getChildNodes().getLength();
		return length==1;
	}
	
	private String getNodeValue(Element nodeElem, String tagName){ 
		return nodeElem.getElementsByTagName(tagName).item(0).getChildNodes().item(0).getNodeValue().trim();
	}
	
	private ObservableList<String> getListElem(Element nodeElem,String tagName){
		ObservableList<String> elems = FXCollections.observableArrayList();
		Element parentElem = (Element) rootElem.getElementsByTagName(tagName).item(0);
		NodeList elemList = parentElem.getElementsByTagName("row");
		for(int i=0; i<elemList.getLength(); i++){
			String val = elemList.item(i).getTextContent().trim();
			elems.add(val);
		}
		return elems;
	}
	
	public Map<String,Object> getPreferences(){
		Map<String,Object> preferences = new HashMap<String,Object>();
		for(int i=0; i<rootElem.getChildNodes().getLength();i++){
			String tagName = rootElem.getChildNodes().item(i).getNodeName();
        	if(tagName!="#text"){
        		if(isSingleElem(tagName)){
    				preferences.put(tagName, getNodeValue(rootElem,tagName));
    			}
    			else{
    				preferences.put(tagName, getListElem(rootElem,tagName));
    
    			}
        	}
        }
		return preferences;
	}
}
	


	