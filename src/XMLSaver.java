import java.io.File;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Document;
import org.w3c.dom.Element;

public class XMLSaver {
	private Document doc;	
	private Element rootElem;
	
	public XMLSaver() {
		// TODO Auto-generated constructor stub
	}
	
	private void saveFile(String filePath){
		try{
	        DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
	        dbFactory.setIgnoringElementContentWhitespace(true);
	        DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();	        
	        doc = dBuilder.newDocument();  
	        rootElem = doc.createElement("preferences");
	        doc.appendChild(rootElem);
	        
	        //save specific preferences
	        
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
