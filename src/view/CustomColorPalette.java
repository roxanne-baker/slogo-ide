package view;

import java.util.Arrays;
import java.util.List;

import javafx.scene.Group;
import javafx.scene.layout.HBox;

public class CustomColorPalette extends Palette{
	private static final List<Object> DEFAULT_COLORS = Arrays.asList((Object)new CustomColor(40,50,60),(Object)new CustomColor(50,60,70));
	private static final int SIZE = 10;

	public CustomColorPalette() {
		super();
		super.paletteName = getResourceBundle().getString("IMAGES");

		setNewPaletteList(DEFAULT_COLORS);
	}


	public void replaceCustomColor(int index, CustomColor color){
		if (index > getPaletteList().size() || index < 0){
			//don't add color; maybe through error?
		}else{
			getPaletteList().set(index, color);
		}
	}
	@Override
	public Group getPaletteViewGroup(){
		HBox hbox = new HBox();
		for (int index = 0; index < getPaletteList().size(); index++){
			CustomColorElem colorView = new CustomColorElem((CustomColor) getPaletteObject(index),SIZE);

			hbox.getChildren().add(colorView.getView());
		}
		super.paletteGroup.getChildren().add(hbox);
		return super.paletteGroup;
	}



	
}
