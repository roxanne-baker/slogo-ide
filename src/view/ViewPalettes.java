package view;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.ResourceBundle;
import javafx.geometry.Pos;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
/**
 * This class displays all the palettes and allows the user to add colors and images to the palettes. 
 * @author Melissa Zhang
 *
 */

public class ViewPalettes extends View{
	private static final int CONSOLEX = 0;
	private static final int CONSOLEY = MENU_OFFSET+WIDE_WIDTH;
	private static final ResourceBundle PALETTE_RESOURCES = ResourceBundle.getBundle("Palettes");
	private static final ResourceBundle DIALOG_RESOURCES = ResourceBundle.getBundle("DialogBox");
	private static final List<String> COLOR_COMPONENTS = Arrays.asList("RED","GREEN","BLUE");
	private static final double MAX_WIDTH = 50;
	private static final int RED_INDEX = 0;
	private static final int GREEN_INDEX = 1;
	private static final int BLUE_INDEX = 2;
	private List<Palette> paletteList;
	private ResourceBundle cssResources = ResourceBundle.getBundle("CSSClasses");
	private VBox vbox;
	private int imagePaletteIndex;
	private int colorPaletteIndex;

	public ViewPalettes(ViewType ID, Preferences savedPreferences) {
		super(ID, savedPreferences);
		setX(CONSOLEX);
		setY(CONSOLEY);
		paletteList = new ArrayList<Palette>();

		vbox = new VBox();
		vbox.getStyleClass().addAll(cssResources.getString("VBOXVIEW"),cssResources.getString("DISPLAYVIEW"));
		vbox.setPrefSize(NARROW_WIDTH,NARROW_WIDTH);
		setPane(vbox);
		setUpPalettes();
		setUpColorAdder();
		setUpImageAdder();
		
	}
	
	private void setUpColorAdder() {
		HBox colorAdderBox = new HBox();
		List<TextField> colorInputList = new ArrayList<TextField>();
		for (String color: COLOR_COMPONENTS){
			VBox colorBox = new VBox();
			colorBox.setAlignment(Pos.CENTER);
			TextField colorInput = new TextField();
			colorInput.setMaxWidth(MAX_WIDTH);
			colorInputList.add(colorInput);
			Label colorLabel = new Label(PALETTE_RESOURCES.getString(color + "LABEL")); 
			colorBox.getChildren().addAll(colorInput, colorLabel);
			colorAdderBox.getChildren().add(colorBox);
		}
		Button addColorButton = new Button(PALETTE_RESOURCES.getString("COLORADDERBUTTON"));
		addColorButtonEventHandler(colorInputList, addColorButton);
		vbox.getChildren().addAll(colorAdderBox, addColorButton);
		
	}

	private void addColorButtonEventHandler(List<TextField> colorInputList,Button addColorButton) {
		
		addColorButton.setOnAction(evt -> {
			List<Integer> valueList = new ArrayList<Integer>();
			System.out.println("here");
			boolean isValidInputs = true;
			for (TextField n: colorInputList){
				String value = n.getText();
				if (checkValidInput(value)){
					valueList.add(Integer.parseInt(value));
				}else{
					isValidInputs= false;
					break;
				}
			}
			if (isValidInputs){
				paletteList.get(colorPaletteIndex).addToPalette(new CustomColor(valueList.get(RED_INDEX),valueList.get(GREEN_INDEX),valueList.get(BLUE_INDEX)), paletteList.get(imagePaletteIndex).getPaletteSize());
			}
		});
	}

	private boolean checkValidInput(String value) {
		
		try{
			int intValue = Integer.parseInt(value);
			System.out.println(intValue);
			if(intValue<0 || intValue>255){
				new DialogBox(AlertType.ERROR, DIALOG_RESOURCES.getString("COLOROUTOFBOUNDS"), value);
				return false;
			}
		}catch (Exception e){
			new DialogBox(AlertType.ERROR, DIALOG_RESOURCES.getString("INVALIDCOLORINPUT"), value);
			return false;
		}
		return true;

		 
		
	}

	public void setPaletteList(List<Palette> newPaletteList){
		paletteList = new ArrayList<Palette>();
		imagePaletteIndex = 0;
		imagePaletteIndex = 0;
		for (int i = 0; i < newPaletteList.size(); i++){
			if (newPaletteList.get(i).getPaletteName().equals(PALETTE_RESOURCES.getString("IMAGES"))){
				imagePaletteIndex = i;				
			}
			if(newPaletteList.get(i).getPaletteName().equals(PALETTE_RESOURCES.getString("CUSTOMCOLORS"))){
				colorPaletteIndex = i;
			}
			paletteList.add(newPaletteList.get(i));
		}

		setUpPalettes();
		
	}


	private void setUpImageAdder() {
		Stage stage = new Stage();
		FileChooser fileChooser = new FileChooser();
		FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter("Images", "*.png","*.jpg","*.jpeg", ".gif");
		fileChooser.getExtensionFilters().add(extFilter);
		fileChooser.setTitle(PALETTE_RESOURCES.getString("IMAGECHOOSERLABEL"));
		Button fileButton = new Button(PALETTE_RESOURCES.getString("IMAGECHOOSERBUTTON"));
		
		fileButton.setOnAction(evt -> {
			List<File> fileList = fileChooser.showOpenMultipleDialog(stage);
			if(fileList!=null){
				for (File file: fileList){
					paletteList.get(imagePaletteIndex).addToPalette(file.getPath(), paletteList.get(imagePaletteIndex).getPaletteSize());
	
				}
			}
		});	
		vbox.getChildren().add(fileButton);
	}

	private void setUpPalettes() {
		for (Palette palette: paletteList){
			VBox paletteDisplay = new VBox();
			paletteDisplay.getStyleClass().add(cssResources.getString("VBOX"));
			Label label = new Label(PALETTE_RESOURCES.getString(palette.getPaletteName() + "LABEL"));
			paletteDisplay.getChildren().addAll(label,palette.getPaletteViewGroup());	
			vbox.getChildren().add(paletteDisplay);
		}
	}



}
