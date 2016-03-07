package view;

import java.util.ResourceBundle;

import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;

public class ViewPalettes extends View{
	private static final String PALETTE_PROPERTIES = "Palettes";
	private static final int PALETTE_SIZE = 15;
	private ResourceBundle myResources;
	private Pane viewPane;
	private VBox vbox;

	public ViewPalettes(ViewType ID) {
		super(ID);
		viewPane = new Pane();
		myResources = ResourceBundle.getBundle(PALETTE_PROPERTIES);
		viewPane.getChildren().add(vbox);
	}

	@Override
	public Pane getView() {
		setUpColorPalette();
		setUpShapePalette();
		
		return viewPane;
	}

	private void setUpShapePalette() {
		ShapePalette shapePalette = new ShapePalette(myResources.getString("SHAPES"),PALETTE_SIZE);
		vbox.getChildren().add(shapePalette.getPaletteViewGroup());
	}

	private void setUpColorPalette() {
		CustomColorPalette colorPalette = new CustomColorPalette(myResources.getString("CUSTOMCOLORS"),PALETTE_SIZE);
		vbox.getChildren().add(colorPalette.getPaletteViewGroup());
	}

}
