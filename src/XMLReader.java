import java.io.File;
import java.util.*;

import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.DocumentBuilder;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

import javafx.scene.paint.Color;
import javafx.stage.FileChooser;
import javafx.stage.FileChooser.ExtensionFilter;

public class XMLReader {
	private String[] xmlElems = new String[]{"background","images","turtles","language"};
	private String file;
	private Document doc;	
	private Element rootElem;

	public XMLReader() {
		file = chooseFile();
		readFile();
		Map<String,Object> prefMap = getPreferences();
	}

	public String chooseFile(){
		FileChooser fileChooser = new FileChooser();
		fileChooser.setTitle("Open XML File");		
		fileChooser.getExtensionFilters().addAll(
		        new ExtensionFilter("XML Files", "*.xml"));		
		File file = fileChooser.showOpenDialog(null);
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
			//
		}			
	}
	
	private boolean isSingleElem(String tagName){	
		int length = rootElem.getElementsByTagName(tagName).item(0).getChildNodes().getLength();
		return length==1;
	}
	
	private String getNodeValue(Element nodeElem, String tagName){ 
		return nodeElem.getElementsByTagName(tagName).item(0).getChildNodes().item(0).getNodeValue().trim();

		//return Arrays.asList(new String[]{nodeElem.getElementsByTagName(tagName).item(0).getChildNodes().item(0).getNodeValue().trim()});
	}
	
	private List<String> getListElem(Element nodeElem,String tagName){
		List<String> elems = new ArrayList<String>();
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
		for(String tagName: xmlElems){
			if(isSingleElem(tagName)){
				preferences.put(tagName, getNodeValue(rootElem,tagName));
			}
			else{
				preferences.put(tagName, getListElem(rootElem,tagName));

			}
		}
		return preferences;
	}
	
//	public Color getBackground(){
//		return Color.valueOf(getNodeValue(rootElem,"background"));
//	}
	
	public List<String> getImageList(){
		ArrayList<String> images = new ArrayList<String>();
		Element imagesElem = (Element) rootElem.getElementsByTagName("images").item(0);
		NodeList imagesList = imagesElem.getElementsByTagName("row");
		for(int i=0; i<imagesList.getLength(); i++){
			String imgName = imagesList.item(i).getNodeValue().trim();
			images.add(imgName);
		}
		return images;
	}
	
//	public int getTurtleCount(){
//		return Integer.parseInt(getNodeValue(rootElem,"turtles"));
//	}
//	
//	public String getLanguage(){
//		return getNodeValue(rootElem,"language");
//	}
}
	


	