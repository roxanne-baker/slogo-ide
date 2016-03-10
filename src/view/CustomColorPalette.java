package view;

import java.util.Arrays;
import java.util.List;

import javafx.scene.Node;


public class CustomColorPalette extends Palette{
	private static final List<Object> DEFAULT_COLORS = Arrays.asList((Object)new CustomColor(50,80,200),(Object)new CustomColor(50,60,70));
	private static final int SIZE = 20;

	public CustomColorPalette() {
		super();
		super.paletteName = getResourceBundle().getString("CUSTOMCOLORS");

		setNewPaletteList(DEFAULT_COLORS);
	}


	@Override
	public Node getPaletteObjectView(int index) {
		CustomColorElem colorView = new CustomColorElem((CustomColor) getPaletteObject(index),SIZE);
		
		return colorView.getView();
	}



	
}
