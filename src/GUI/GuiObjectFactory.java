package GUI;

import java.util.Arrays;
import java.util.List;

import model.Agent;

/**
 * This Factory class creates GuiObjects that are linked to an agent. 
 * @author Melissa Zhang
 *
 */


public class GuiObjectFactory {
	private static final String WINDOW_RESOURCES = "windowProperties";
	private static final List<String> PEN_STYLE_LIST = Arrays.asList("SOLID","DASHED","DOTTED");
	private static final String HELP_URL = "http://www.cs.duke.edu/courses/compsci308/spring16/assign/03_slogo/commands.php";

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

				return new GuiObjectColorPicker(type,agent.getResourceString(),agent,agent.getAgentView().getPenColor(), (a,b)-> ((Agent) a).getAgentView().setPenColor(b));
			}
			case("NAME"):{
				return new GuiObjectLabel(type, agent.getResourceString(),agent);
			}
			case("VISIBLE"):{
				return new GuiObjectCheckBox(type,agent.getResourceString(),agent,(a,b)->((Agent) a).setVisible(b));
			}
			case("IMAGEPATH"):{
				if (agent.getImagePalette()==null){return null;}
				return new GuiObjectDropDown(type, agent.getResourceString(), agent, agent.getImagePath(),agent.getImagePalette().getPaletteListProperty(), (a,b)->((Agent)a).setCurrentImageIndex(b));
			}

			case("HELP"):{
				return new GuiObjectURLOpener(type,WINDOW_RESOURCES, null , HELP_URL);
			}
			case("PENSTYLE"):{
				return new GuiObjectRadioButton(type,agent.getResourceString(), agent, agent.getPenStyle(), PEN_STYLE_LIST, (a,s)->((Agent) a).setPenStyle(s));
			}


			}
		
		return null;
	}
}