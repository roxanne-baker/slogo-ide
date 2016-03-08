import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.validation.Validator;
import javax.xml.parsers.DocumentBuilder;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.w3c.dom.Node;

import javafx.stage.FileChooser;
import javafx.stage.FileChooser.ExtensionFilter;

/**
 * 
 * @author Michelle
 *
 * This class reads in XML files and passes the user input to the simulations.
 * In the case of faulty user input, XMLReader utilizes the SimulationOptional class to throw an error message on the scene in the main class.
 * 
 * XML files contain the following info:
 * --name of the simulation it represents, as well as a title for the simulation and the simulation's "author"
 * --settings for global configuration parameters specific to the simulation
 * --dimensions of the grid and the initial configuration of the states for the cells in the grid
 * 
 */

public class XMLReader {
	private String file;
	private Document doc;	
	private Validator validator;
	
	private Integer gridSize;
	private Integer numCells;
	private Boolean gridType;
	private Boolean cellType;
	
	private String simType;
    private NodeList listParam;
    private Element attributes;
    
	private List<String> columns;
	private NodeList colTag;

	public XMLReader() {
		file = chooseFile();
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
	
	public void getSimulation(Map myParams){
		try{
			File inputFile = new File(file);
	        DocumentBuilderFactory dbFactory 
	            = DocumentBuilderFactory.newInstance();
	        dbFactory.setIgnoringElementContentWhitespace(true);
	        DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();	        
	        doc = dBuilder.parse(inputFile);       
	        //simulation type
	        Element simulation2 = (Element) doc.getElementsByTagName("info").item(0) ;
	        simType = simulation2.getElementsByTagName("name").item(0).getTextContent();	        	        
	        //getting nodes
			listParam = doc.getElementsByTagName("parameters");
	        attributes = (Element) listParam.item(0);
//	        if (attributes.getElementsByTagName("custom").getLength() == 0) {
//	        	parseRandom(myParams);
//		        return randomSim();
//	        } else {
//	        	parseCustom(attributes);
//	        	return customSim();
//	        }
		} catch(Exception e){	
			//return new SimulationOptional(null, e);
		}			
	}
}
	


	