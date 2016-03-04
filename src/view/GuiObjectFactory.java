package view;

import java.io.File;
import java.util.Arrays;
import java.util.List;
import java.util.ResourceBundle;

/**
 * This Factory class creates GuiObjects based on the type of gui object requested.
 * @author Melissa Zhang
 *
 */


public class GuiObjectFactory {
	private static final String WINDOW_RESOURCES = "windowProperties";
	private static final String HELP_FILE = "help.html";
	private static final String UPDATE_RESOURCES = "updateObserver";
	private static final List<String> PEN_STYLE_LIST = Arrays.asList("SOLID","DASHED","DOTTED");

	public GuiObjectFactory(){
	}
	public GuiObject createNewGuiObject(String type, Agent agent){
		switch(type){
			case("PENUP"):{
				return new GuiObjectCheckBox(type, agent.getResourceString(), agent, (a,b)->((Agent) a).setPenUp(b));
			}
			case("PENWIDTH"):{
				return new GuiObjectSlider(type,agent.getResourceString(),agent, 1,10,agent.getPenThickness(),1,(s,num)-> ((Agent) s).setPenThickness(num));
			}
			case("PENCOLOR"):{

				return new GuiObjectColorPicker(type,agent.getResourceString(),agent,agent.getPenColor(), (a,b)-> ((Agent) a).setPenColor(b));
			}
			case("NAME"):{
				return new GuiObjectLabel(type, agent.getResourceString(),agent);
			}
			case("VISIBLE"):{
				return new GuiObjectCheckBox(type,agent.getResourceString(),agent,(a,b)->((Agent) a).setVisible(b));
			}
			case("IMAGEPATH"):{
				return new GuiObjectInputBox(type, agent.getResourceString(), agent, (a,b)->((Agent)a).setImagePath(b));
			}
			case("HELP"):{
				return new GuiObjectFileOpener(type,WINDOW_RESOURCES, null , HELP_FILE);
			}
			case("PENSTYLE"):{
				return new GuiObjectRadioButton(type,agent.getResourceString(), agent, agent.getPenStyle(), PEN_STYLE_LIST, (a,s)->((Agent) a).setPenStyle(s));
			}




			}
		
		return null;
	}
}