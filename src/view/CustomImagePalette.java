package view;
import java.util.Arrays;
import java.util.List;

import javafx.scene.Group;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;

public class CustomImagePalette extends Palette{
	private static final List<Object> DEFAULT_IMAGEPATHS = Arrays.asList("turtle.png","dot.png");
	private static final int SIZE = 10;

	public CustomImagePalette() {
		super();
		super.paletteName = getResourceBundle().getString("IMAGES");
		setNewPaletteList(DEFAULT_IMAGEPATHS);
	}
	
	public Group getPaletteViewGroup(){
		HBox hbox = new HBox();
		for (int index =0; index< getPaletteList().size();index++){
			ImageView imageView = new ImageView(new Image(getClass().getClassLoader().getResourceAsStream((String) getPaletteObject(index)),SIZE,SIZE,true,true));
			hbox.getChildren().add(imageView);
		}
		super.paletteGroup.getChildren().add(hbox);
		return super.paletteGroup;
	}

}
