package view;
import java.io.File;
import java.util.ResourceBundle;

import javafx.collections.ObservableList;
import javafx.scene.Node;
import javafx.scene.control.Alert.AlertType;
//import javafx.scene.control.Alert.AlertType;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
/**
 * This is a palette for all the images available for the user. 
 * @author Melissa Zhang
 *
 */
public class CustomImagePalette extends Palette{

	private static final String IMAGES_DIR = "images/";
	private static final int SIZE = 20;
	private static final ResourceBundle DIALOG_RESOURCES = ResourceBundle.getBundle("DialogBox");

	public CustomImagePalette(ObservableList<Object> images) {
		super(images);
		super.paletteName = getResourceBundle().getString("IMAGES");
	}

	@Override
	public Node getPaletteObjectView(int index) {
		String imagePath = (String) getPaletteObject(index);
		File f = new File(IMAGES_DIR+ imagePath);
		ImageView imageView = null;
		if (f.isFile()){
			imageView = new ImageView(new Image(getClass().getClassLoader().getResourceAsStream(imagePath),SIZE,SIZE,true,true));
		}else{
			File imageFile = new File((String) getPaletteObject(index));
			if (imageFile.isFile()){
				imageView = new ImageView(new Image(imageFile.toURI().toString(),SIZE,SIZE,true,true));
			}else {
				new DialogBox(AlertType.ERROR,DIALOG_RESOURCES.getString("IMAGENOTFOUND"),imagePath);
				removeFromPalette(index);
			}
			
		}
			
		return imageView;
	}

	public void removeFromPalette(String imagePath) {
		getPaletteList().remove(imagePath);
		
	}





}
