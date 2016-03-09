package view;
import java.util.Arrays;
import java.util.List;

import javafx.scene.Node;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class CustomImagePalette extends Palette{
	private static final List<Object> DEFAULT_IMAGEPATHS = Arrays.asList("turtle.png","dot.png");
	private static final int SIZE = 20;

	public CustomImagePalette() {
		super();
		super.paletteName = getResourceBundle().getString("IMAGES");
		super.setNewPaletteList(DEFAULT_IMAGEPATHS);
	}

	@Override
	public Node getPaletteObjectView(int index) {
		ImageView imageView = new ImageView(new Image(getClass().getClassLoader().getResourceAsStream((String) getPaletteObject(index)),SIZE,SIZE,true,true));
		return imageView;
	}

}
