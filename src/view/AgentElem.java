package view;

import java.io.File;
import java.util.Observable;
import java.util.Observer;
import java.util.ResourceBundle;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;

public class AgentElem implements Observer{
	private static final String IMAGES_DIR = "images/";
	Agent myAgent;
	private ImageView agentImageView;
	private ImageView oldImageView;
	private ResourceBundle myResources;
	private Color penColor;
	private ImageView imgCopy;
	private static final Color DEFAULT_PEN_COLOR = Color.BLACK;
	private static final String UPDATE_PROPERTIES = "updateObserver";
	public AgentElem(Agent agent){
		myAgent = agent;
		myResources = ResourceBundle.getBundle(UPDATE_PROPERTIES );
		agentImageView = getImageViewFromFile(agentImageView, myAgent.getImagePath());
		oldImageView = agentImageView;
		penColor = DEFAULT_PEN_COLOR;
		imgCopy = new ImageView();


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
		imgCopy = new ImageView();
		imgCopy = getImageViewFromFile(imgCopy, myAgent.getImagePath());
		imgCopy.setRotate(myAgent.getOrientation());
		return imgCopy;

	}	
	public ImageView getImageView(){
		System.out.println(agentImageView.getBoundsInParent().getWidth());
		return agentImageView;
	}
	public void updateImageView(){	
		oldImageView = agentImageView;
		agentImageView = getImageViewFromFile(agentImageView, myAgent.getImagePath());
	}

	private  ImageView getImageViewFromFile(ImageView agentView, String imagePath) {
		File resourceFile = new File(IMAGES_DIR+ imagePath);
		if (resourceFile.isFile()){
			agentView = new ImageView(new Image(getClass().getClassLoader().getResourceAsStream(imagePath),myAgent.getSize(),myAgent.getSize(),true,true));
		}else{
			File imageFile = new File((String) myAgent.getImagePalette().getPaletteObject(myAgent.getCurrentImageIndex()));
			if (imageFile.isFile()){
				agentView = new ImageView(new Image(imageFile.toURI().toString(),myAgent.getSize(),myAgent.getSize(),true,true));
			}else {
				//TODO Throw Image not found error
			}
			
		}return agentView;
		
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
