package view;
import java.io.File;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Node;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class CustomImagePalette extends Palette{
	private static final ObservableList<Object> DEFAULT_IMAGEPATHS = FXCollections.observableArrayList("turtle.png","dot.png");
	private static final int SIZE = 20;

	public CustomImagePalette() {
		super();
		super.paletteName = getResourceBundle().getString("IMAGES");
		super.setNewPaletteList(DEFAULT_IMAGEPATHS);
	}

	@Override
	public Node getPaletteObjectView(int index) {
		String imagePath = (String) getPaletteObject(index);
		File f = new File("images/"+ imagePath);
		ImageView imageView = null;
		if (f.isFile()){
			imageView = new ImageView(new Image(getClass().getClassLoader().getResourceAsStream(imagePath),SIZE,SIZE,true,true));
		}else{
			File imageFile = new File((String) getPaletteObject(index));
			if (imageFile.isFile()){
				imageView = new ImageView(new Image(imageFile.toURI().toString(),SIZE,SIZE,true,true));
			}else {
				//TODO Throw Image not found error
			}
			
		}
			
		return imageView;
	}




}
