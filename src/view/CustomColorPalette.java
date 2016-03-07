package view;

import java.util.ArrayList;
import java.util.Arrays;

import javafx.scene.Group;
import javafx.scene.layout.HBox;

public class CustomColorPalette extends Palette{
	private static final ArrayList<CustomColor> DEFAULT_COLORS = (ArrayList<CustomColor>) Arrays.asList(new CustomColor(40,50,60),new CustomColor(50,60,70));
	private int paletteObjectSize;

	public CustomColorPalette(String id, int size) {
		super(id);
		paletteObjectSize = size;
		for (int index = 0; index < DEFAULT_COLORS.size(); index++){
			addToPalette(DEFAULT_COLORS.get(index),index);
		}
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
			CustomColorView colorView = new CustomColorView((CustomColor) getPaletteObject(index),paletteObjectSize);

			hbox.getChildren().add(colorView.getView());
		}
		super.paletteGroup.getChildren().add(hbox);
		return super.paletteGroup;
	}



	
}
