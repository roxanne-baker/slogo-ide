package view;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.ResourceBundle;

import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;

public class ViewPalettes extends View{
	private static final String PALETTE_PROPERTIES = "Palettes";
	private List<Palette> paletteList;
	private ResourceBundle myResources;
	private Pane viewPane;
	private VBox vbox;

	public ViewPalettes(ViewType ID) {
		super(ID, null);
		viewPane = new Pane();
		paletteList = new ArrayList<Palette>();
		myResources = ResourceBundle.getBundle(PALETTE_PROPERTIES);
		vbox = new VBox();
		viewPane.getChildren().add(vbox);
	}
	
	public void setPaletteList(List<Palette> newPaletteList){
		paletteList = new ArrayList<Palette>();
		for (Palette palette: newPaletteList){
			paletteList.add(palette);
		}
	}
	@Override
	public Pane getView() {
		setUpPalettes();
		
		return viewPane;
	}

	private void setUpPalettes() {
		for (Palette palette: paletteList){
			Label label = new Label(myResources.getString(palette.getPaletteName() + "LABEL"));
			vbox.getChildren().addAll(label,palette.getPaletteViewGroup());	
		}
	}



}
