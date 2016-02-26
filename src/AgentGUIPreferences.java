import java.util.ArrayList;
import java.util.List;


public abstract class AgentGUIPreferences {
	Agent agent;
	public AgentGUIPreferences(Agent a){
		agent = a;
	}
	
	public List<GuiObject> getGUIPreferenceObjects(){
		ArrayList<GuiObject> guiObjectList = new ArrayList<GuiObject>();
		agent.getMutableProperties();
		return null;
	}
	
}
