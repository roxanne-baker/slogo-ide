package view;

import java.util.HashMap;
import java.util.Observable;
import java.util.ResourceBundle;

import javafx.event.EventHandler;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;

/**
 * This class draws agents,maps imageview on screen to an agent, and updates the imageviews when they are selected or moved.
 * @author Melissa Zhang
 *
 */
public class ViewAgentsUpdater {
	ViewAgents myView;
	Drawer myDrawer;
	private HashMap<ImageView,Agent> imageAgentMap;

	private static final ResourceBundle UPDATE_RESOURCES = ResourceBundle.getBundle("updateObserver");

	public ViewAgentsUpdater(ViewAgents viewAgents, Drawer drawer){
		myView = viewAgents;
		myDrawer = drawer;
		imageAgentMap = new HashMap<ImageView,Agent>();

	}
	public void updateView(Agent agent, String updateType){
		AgentElem agentView = ((Agent) agent).getAgentView();
		
		if(((Agent) agent).isVisible()){
			
			if (updateType == UPDATE_RESOURCES.getString("STAMP")){
				myDrawer.stampImage(agentView.getImageCopy(), ((Agent) agent).getXPosition(), ((Agent) agent).getYPosition());
			
			}else if (updateType == UPDATE_RESOURCES.getString("MOVE")){
				myDrawer.moveImage(agentView.getImageView(), ((Agent) agent).getXPosition(), ((Agent) agent).getYPosition());
				if(!((Agent) agent).isPenUp()){
					myDrawer.drawLine(((Agent) agent).getOldXPosition(), ((Agent) agent).getOldYPosition(), ((Agent) agent).getXPosition(), ((Agent) agent).getYPosition(),((Agent) agent).getPenThickness(),agentView.getPenColor(),Integer.parseInt(UPDATE_RESOURCES.getString(((Agent) agent).getPenStyle()+"DASH")));
				}

			}else if (updateType == UPDATE_RESOURCES.getString("INITIAL")){ 
				ImageView agentImageView = createNewImageViewWithHandler(agent);
				myDrawer.moveImage(agentImageView, ((Agent) agent).getXPosition(), ((Agent) agent).getYPosition());
			
			}else if (updateType == UPDATE_RESOURCES.getString("IMAGEVIEW")){	
				imageAgentMap.remove(agentView.getOldImageView());
				ImageView newAgentImageView = createNewImageViewWithHandler(agent);
				myDrawer.setNewImage(agentView.getOldImageView(),newAgentImageView,((Agent) agent).getXPosition(), ((Agent) agent).getYPosition());
				
			}else if (updateType == UPDATE_RESOURCES.getString("ACTIVE")){
				if(myView.getIsSelectedAgentToggle()){
					myDrawer.addSelectEffect(agentView.getImageView());
				}else{
					myDrawer.removeSelectEffect(agentView.getImageView());
				}
			}
		}else if(updateType == UPDATE_RESOURCES.getString("VISIBLE")){
			myDrawer.removeImage(agentView.getImageView());
			
		}
		
		if(updateType == UPDATE_RESOURCES.getString("CLEARSTAMPS")){
			myDrawer.clearAllStamps();
		}

	}
	
	private ImageView createNewImageViewWithHandler(Observable agent) {
		ImageView newAgentImageView = (((Agent) agent).getAgentView().getImageView());
		addImageHandler(newAgentImageView);
		imageAgentMap.put(newAgentImageView, (Agent) agent);
		return newAgentImageView;
	}
	private void addImageHandler(ImageView img){
		img.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
		     @Override
		     public void handle(MouseEvent event) {
		    	 myView.getActiveAgentListProperty().getValue().add(imageAgentMap.get(img).getName());
		         updateView(imageAgentMap.get(img),UPDATE_RESOURCES.getString("ACTIVE"));   
		     }
		});
	}

}
