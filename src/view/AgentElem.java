package view;

import java.io.File;
import java.util.ResourceBundle;

import javafx.scene.control.Alert.AlertType;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;
/**
 * This class keeps track of the agent imageview, its previous imageview, pencolor, and copy of imageview.
 * @author Melissa Zhang
 *
 */
public class AgentElem {
	private static final String IMAGES_DIR = "images/";
	Agent myAgent;
	private ImageView agentImageView;
	private ImageView oldImageView;
	private Color penColor;
	private ImageView imgCopy;
	private static final Color DEFAULT_PEN_COLOR = Color.BLACK;
	private static final ResourceBundle DIALOG_RESOURCES = ResourceBundle.getBundle("DialogBox");
	
	public AgentElem(Agent agent){
		myAgent = agent;
		agentImageView = getImageViewFromFile(agentImageView, myAgent.getImagePath());
		oldImageView = agentImageView;
		penColor = DEFAULT_PEN_COLOR;
		imgCopy = new ImageView();

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
		return agentImageView;
	}
	public void updateImageView(){	
		oldImageView = agentImageView;
		agentImageView = getImageViewFromFile(agentImageView, myAgent.getImagePath());
	}
	/*
	 * Gets the image view from a file specified by user. If the file is in the resource folder then load as stream, otherwise load as file.
	 */
	private  ImageView getImageViewFromFile(ImageView agentView, String imagePath) {
		File resourceFile = new File(IMAGES_DIR+ imagePath);
		if (resourceFile.isFile()){
			agentView = new ImageView(new Image(getClass().getClassLoader().getResourceAsStream(imagePath),myAgent.getSize(),myAgent.getSize(),true,true));
		}else{
			File imageFile = new File((String) myAgent.getImagePalette().getPaletteObject(myAgent.getCurrentImageIndex()));
			if (imageFile.isFile()){
				agentView = new ImageView(new Image(imageFile.toURI().toString(),myAgent.getSize(),myAgent.getSize(),true,true));

			}else {
				new DialogBox(AlertType.ERROR,DIALOG_RESOURCES.getString("IMAGENOTFOUND"),myAgent.getImagePath());
				myAgent.getImagePalette().removeFromPalette(imagePath);
				myAgent.setDefaultImageIndex();
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
