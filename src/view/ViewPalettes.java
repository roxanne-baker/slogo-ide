package view;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;

import javafx.scene.control.Label;
import javafx.scene.layout.VBox;

public class ViewPalettes extends View{
	private static final int CONSOLEX = 0;
	private static final int CONSOLEY = MENU_OFFSET+WIDE_WIDTH;
	private static final String PALETTE_PROPERTIES = "Palettes";
	private List<Palette> paletteList;
	private ResourceBundle myResources;
	private VBox vbox;

	public ViewPalettes(ViewType ID, Map<String,List<Object>> savedPreferences) {
		super(ID, savedPreferences);
		setX(CONSOLEX);
		setY(CONSOLEY);
		paletteList = new ArrayList<Palette>();
		myResources = ResourceBundle.getBundle(PALETTE_PROPERTIES);
		vbox = new VBox();
		setPane(vbox);
		setUpPalettes();
	}
	
	public void setPaletteList(List<Palette> newPaletteList){
		paletteList = new ArrayList<Palette>();
		for (Palette palette: newPaletteList){
			paletteList.add(palette);
			
		}
		setUpPalettes();
		
	}

	private void setUpPalettes() {
		System.out.println(paletteList);
		for (Palette palette: paletteList){
			Label label = new Label(myResources.getString(palette.getPaletteName() + "LABEL"));
			vbox.getChildren().addAll(label,palette.getPaletteViewGroup());	
		}
	}



}
