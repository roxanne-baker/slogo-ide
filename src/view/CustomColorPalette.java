package view;

import java.util.Arrays;
import java.util.List;
import javafx.scene.Node;


public class CustomColorPalette extends Palette{
	private static final List<Object> DEFAULT_COLORS = Arrays.asList((Object)new CustomColor(40,50,60),(Object)new CustomColor(50,60,70));
	private static final int SIZE = 20;

	public CustomColorPalette() {
		super();
		super.paletteName = getResourceBundle().getString("CUSTOMCOLORS");

		setNewPaletteList(DEFAULT_COLORS);
	}


	public void replaceCustomColor(int index, CustomColor color){
		if (index > getPaletteList().size()){
			addToPalette(color, index);
		}else if(index< 0){
			//Throw error?
		}
		else{
			getPaletteList().set(index, color);
		}
	}
	@Override
	public Node getPaletteObjectView(int index) {
		CustomColorElem colorView = new CustomColorElem((CustomColor) getPaletteObject(index),SIZE);
		
		return colorView.getView();
	}



	
}
