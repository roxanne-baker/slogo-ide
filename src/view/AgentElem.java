package view;

import java.util.Observable;
import java.util.Observer;
import java.util.ResourceBundle;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;

public class AgentElem implements Observer{
	Agent myAgent;
	private ImageView agentImageView;
	private ImageView oldImageView;
	private ResourceBundle myResources;
	private Color penColor;
	private static final Color DEFAULT_PEN_COLOR = Color.BLACK;

	private static final String UPDATE_PROPERTIES = "updateObserver";
	public AgentElem(Agent agent){
		myAgent = agent;
		myResources = ResourceBundle.getBundle(UPDATE_PROPERTIES );
		agentImageView = new ImageView(new Image(getClass().getClassLoader().getResourceAsStream(myAgent.getImagePath()),myAgent.getSize(),myAgent.getSize(),true,true));
		oldImageView = agentImageView;
		penColor = DEFAULT_PEN_COLOR;


	}

	@Override
	public void update(Observable agent, Object updateType) {

		
	}
	
	public Agent getAgent(){
		return myAgent;
	}
	public ImageView getOldImageView() {
		return oldImageView;
	}
	public ImageView getImageCopy() {
		ImageView imgCopy = new ImageView(new Image(getClass().getClassLoader().getResourceAsStream(myAgent.getImagePath()),myAgent.getSize(),myAgent.getSize(),true,true));
		imgCopy.setRotate(myAgent.getOrientation());
		return imgCopy;

	}	
	public ImageView getImageView(){
		
		return agentImageView;
	}
	public void updateImageView(){	
		oldImageView = agentImageView;
		agentImageView = new ImageView(new Image(getClass().getClassLoader().getResourceAsStream(myAgent.getImagePath()),myAgent.getSize(),myAgent.getSize(),true,true));

	}

	public void setRotate(Double value) {
		agentImageView.setRotate(value);		
	}
	public void setPenColor(Color color){
		penColor = color;
	}
	public Color getPenColor(){
		return penColor;
	}
	
}
