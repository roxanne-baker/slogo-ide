package view;

public class GuiObjectFactory {
	
	public GuiObjectFactory(){
	}
	public GuiObject createNewGuiObject(String type, Agent agent){
		switch(type){
//			case("Percentage"):{
//				return new GuiObjectSlider(type,0,100,agent.getPercentageRule(),5,agent,agent.getName(), (s,num)-> s.setPercentageRule(num));
//			}
//			case("Probability"):{
//				return new GuiObjectSlider(type,0,100,agent.getProbabilityRule(),5,agent,agent.getName(), (s,num)-> s.setProbabilityRule(num));
//			}
//			case("Speed"):{
//				return new GuiObjectSlider(type,1,60,1,5, null, GUI_RESOURCE, null);
//			
//			}
//			case("NAME"):{
//				return new GuiObjectInputBox(type, null, GUI_RESOURCE);
//			}
			case("VISIBLE"):{
				return new GuiObjectCheckBox(type,agent.getResourceString(),agent,(a,b)->((Agent) a).setVisible(b));
			}
			case("IMAGEPATH"):{
				return new GuiObjectInputBox(type, agent.getResourceString(), agent, (a,b)->((Agent)a).setImagePath(b));
			}
//			case("GridShape"):{
//				return new GuiObjectRadioButton(type,agent,GUI_RESOURCE, agent.getGridShape(), SHAPE_LIST, (s,string)->s.setGridShape(string));
//
//			}case("Dimension"):{
//				return new GuiObjectSlider(type,25,500,agent.getDimension(),10,agent,GUI_RESOURCE, null);
//			}
//			case("CellSize"):{
//				return new GuiObjectSlider(type,5,50,agent.getCellSize(),1,agent, GUI_RESOURCE, null);
//			}
//			case("Neighbors"):{
//				return new GuiObjectRadioButton(type,agent,GUI_RESOURCE, agent.getNeighbors(), NEIGHBORS_LIST, (s,string)->s.setNeighbors(string));
//			}

			}
		
		return null;
	}
}
