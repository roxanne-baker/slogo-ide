package view;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;

import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

public class ViewPalettes extends View{
	private static final int CONSOLEX = 0;
	private static final int CONSOLEY = MENU_OFFSET+WIDE_WIDTH;
	private static final String PALETTE_PROPERTIES = "Palettes";
	private List<Palette> paletteList;
	private ResourceBundle myResources;
	private ResourceBundle cssResources = ResourceBundle.getBundle("CSSClasses");
	private VBox vbox;
	private Button fileButton;
	private FileChooser fileChooser;

	public ViewPalettes(ViewType ID, Preferences savedPreferences) {
		super(ID, savedPreferences);
		setX(CONSOLEX);
		setY(CONSOLEY);
		paletteList = new ArrayList<Palette>();
		myResources = ResourceBundle.getBundle(PALETTE_PROPERTIES);
		fileChooser = new FileChooser();
		fileChooser.setTitle(myResources.getString("IMAGECHOOSERLABEL"));
		fileButton = new Button(myResources.getString("IMAGECHOOSERBUTTON"));
		vbox = new VBox();
		vbox.getStyleClass().addAll(cssResources.getString("VBOXVIEW"),cssResources.getString("DISPLAYVIEW"));
		vbox.setPrefSize(NARROW_WIDTH,NARROW_WIDTH);
		setPane(vbox);
		setUpPalettes();
		setUpImageChooser();
	}
	
	public void setPaletteList(List<Palette> newPaletteList){
		paletteList = new ArrayList<Palette>();
		for (Palette palette: newPaletteList){
			paletteList.add(palette);
			
		}
		setUpPalettes();
		
	}

	private void setUpImageChooser() {
		Stage stage = new Stage();
		fileButton.setOnAction(evt -> {
			List<File> fileList = fileChooser.showOpenMultipleDialog(stage);
			for (File file: fileList){
				//TODO use reflection here
				paletteList.get(1).addToPalette(file.getPath().substring(file.getPath().lastIndexOf('/')+1), paletteList.get(1).getPaletteSize());
			}
		});	
		vbox.getChildren().add(fileButton);
	}

	private void setUpPalettes() {
		for (Palette palette: paletteList){
			VBox paletteDisplay = new VBox();
			paletteDisplay.getStyleClass().add(cssResources.getString("VBOX"));
			Label label = new Label(myResources.getString(palette.getPaletteName() + "LABEL"));
			paletteDisplay.getChildren().addAll(label,palette.getPaletteViewGroup());	
			vbox.getChildren().add(paletteDisplay);
		}
	}



}
