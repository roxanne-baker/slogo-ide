
package view;

/**
 * This Factory class creates GuiObjects based on the type of gui object requested.
 * @author Melissa Zhang
 *
 */



public class GuiObjectFactory {
	
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


			}
		
		return null;
	}
}
