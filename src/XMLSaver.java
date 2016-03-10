import java.io.File;
import java.util.List;
import java.util.Map;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Document;
import org.w3c.dom.Element;

import javafx.stage.FileChooser;
import javafx.stage.FileChooser.ExtensionFilter;
import javafx.stage.Stage;
import view.Preferences;

public class XMLSaver {
	private Document doc;	
	private Element rootElem;
	private Stage window;
	private Preferences preferences;
	
	public XMLSaver(Stage stage, Preferences preferences) {
		this.preferences = preferences;
		window = stage;
		String file = chooseFile();
		saveFile(file);
	}
	
	public String chooseFile(){
		FileChooser fileChooser = new FileChooser();
		fileChooser.setTitle("Save XML File");		
		fileChooser.getExtensionFilters().addAll(
		        new ExtensionFilter("XML Files", "*.xml"));		
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
	        rootElem = doc.createElement("preferences");
	        doc.appendChild(rootElem);
	        
	        savePreferences();
	        
	        TransformerFactory transformerFactory =
	                TransformerFactory.newInstance();
            Transformer transformer =
            transformerFactory.newTransformer();
            DOMSource source = new DOMSource(doc);
            StreamResult result = new StreamResult(new File(filePath));
            transformer.transform(source, result);
	        
	        
		} catch(Exception e){	
			e.printStackTrace();
		}			
	}
	
	public void savePreferences(){
		Map<String,Object> prefMap = preferences.getPreferenceMap();
		for(String prefName: prefMap.keySet()){
			if(isSingleElem(preferences.getPreferenceMap().get(prefName))){
				saveSingleElem(prefName,prefMap.get(prefName));
			}
			else{
				saveListElem(prefName,(List<Object>)prefMap.get(prefName));
			}
		}
	}
	
	private boolean isSingleElem(Object obj){
		System.out.println(obj.toString()+" "+(obj.getClass()==String.class));
		return obj.getClass()==String.class;
	}
	
	private void saveSingleElem(String tagName, Object value){
		Element elem = doc.createElement(tagName);
		elem.appendChild(doc.createTextNode(value.toString()));
		rootElem.appendChild(elem);
	}
	
	private void saveListElem(String tagName, List<Object> values){
		Element elem = doc.createElement(tagName);
		for(Object val: values){
			Element row = doc.createElement("row");
			row.appendChild(doc.createTextNode(val.toString()));
			elem.appendChild(row);
		}
		rootElem.appendChild(elem);
	}
	
}
