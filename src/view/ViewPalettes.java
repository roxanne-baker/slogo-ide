package view;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.ResourceBundle;

import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

public class ViewPalettes extends View{
	private static final String PALETTE_PROPERTIES = "Palettes";
	private List<Palette> paletteList;
	private ResourceBundle myResources;
	private Pane viewPane;
	private VBox vbox;
	private Button fileButton;
	private FileChooser fileChooser;

	public ViewPalettes(ViewType ID) {
		super(ID);
		viewPane = new Pane();
		paletteList = new ArrayList<Palette>();
		myResources = ResourceBundle.getBundle(PALETTE_PROPERTIES);
		fileChooser = new FileChooser();
		fileChooser.setTitle(myResources.getString("IMAGECHOOSERLABEL"));
		fileButton = new Button(myResources.getString("IMAGECHOOSERBUTTON"));
		vbox = new VBox();
		viewPane.getChildren().add(vbox);
	}
	
	public void setPaletteList(List<Palette> newPaletteList){
		paletteList = new ArrayList<Palette>();
		for (Palette palette: newPaletteList){
			paletteList.add(palette);
			
		}
		setUpPalettes();
		
	}
	@Override
	public Pane getView() {
		setUpPalettes();
		setUpImageChooser();
		return viewPane;
	}

	private void setUpImageChooser() {
		Stage stage = new Stage();
		fileButton.setOnAction(evt -> {
			List<File> fileList = fileChooser.showOpenMultipleDialog(stage);
			for (File file: fileList){
				//TODO use reflection here
				paletteList.get(1).addToPalette(file.getPath(), paletteList.get(1).getPaletteSize());
			}
		});	
		vbox.getChildren().add(fileButton);
	}

	private void setUpPalettes() {
		for (Palette palette: paletteList){
			Label label = new Label(myResources.getString(palette.getPaletteName() + "LABEL"));
			vbox.getChildren().addAll(label,palette.getPaletteViewGroup());	
		}
	}



}
