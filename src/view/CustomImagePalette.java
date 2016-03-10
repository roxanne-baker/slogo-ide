package view;
import java.io.File;
import java.util.Arrays;
import java.util.List;

import javafx.scene.Node;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class CustomImagePalette extends Palette{
	private List<Object> imageList;
	private static final int SIZE = 20;

	public CustomImagePalette(List<Object> images) {
		super();
		super.paletteName = getResourceBundle().getString("IMAGES");
		imageList = images;
		super.setNewPaletteList(imageList);
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
	
	public void add(String image){
		imageList.add(image);
		super.setNewPaletteList(imageList);
	}

}
