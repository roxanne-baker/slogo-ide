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
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
/**
 * This class displays all the palettes and allows the user to add colors and images to the palettes. 
 * @author Melissa Zhang
 *
 */

public class ViewPalettes extends View{
	private static final String FILECHOOSER_FILTER = "Images";
	private static final int BUTTON_WIDTH = 150;
	private static final ResourceBundle PALETTE_RESOURCES = ResourceBundle.getBundle("Palettes");
	private static final ResourceBundle DIALOG_RESOURCES = ResourceBundle.getBundle("DialogBox");
	private static final List<String> COLOR_COMPONENTS = Arrays.asList("RED","GREEN","BLUE");
	private static final double MAX_WIDTH = 50;
	private static final int RED_INDEX = 0;
	private static final int GREEN_INDEX = 1;
	private static final int BLUE_INDEX = 2;
	private static final List<String> FILTERLIST = Arrays.asList("*.png","*.jpg","*.jpeg", ".gif");
	private List<Palette> paletteList;
	private ResourceBundle cssResources = ResourceBundle.getBundle("CSSClasses");
	private VBox vbox;
	private int imagePaletteIndex;
	private int colorPaletteIndex;

	public ViewPalettes(ViewType ID, Preferences savedPreferences) {
		super(ID, savedPreferences);
		paletteList = new ArrayList<Palette>();

		vbox = new VBox();
		vbox.getStyleClass().addAll(cssResources.getString("VBOXVIEW"),cssResources.getString("DISPLAYVIEW"));
		vbox.setPrefSize(NARROW_WIDTH,NARROW_WIDTH);
		ScrollPane pane = new ScrollPane(vbox);
		setPane(pane);
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
		addColorButton.setPrefWidth(BUTTON_WIDTH);
		addColorButtonEventHandler(colorInputList, addColorButton);
		vbox.getChildren().addAll(colorAdderBox, addColorButton);
		
	}

	private void addColorButtonEventHandler(List<TextField> colorInputList,Button addColorButton) {
		
		addColorButton.setOnAction(evt -> {
			List<Double> valueList = new ArrayList<Double>();
			boolean isValidInputs = true;
			for (TextField n: colorInputList){
				String value = n.getText();
				if (checkValidInput(value)){
					valueList.add(Double.parseDouble(value));
				}else{
					isValidInputs= false; 
					break;
				}
			}
			if (isValidInputs){
				paletteList.get(colorPaletteIndex).addToPalette(Color.color(valueList.get(RED_INDEX)/255,valueList.get(GREEN_INDEX)/255,valueList.get(BLUE_INDEX)/255).toString(), paletteList.get(colorPaletteIndex).getPaletteSize());
			}
		});
	}

	private boolean checkValidInput(String value) {
		
		try{
			int intValue = Integer.parseInt(value);
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
		FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter(FILECHOOSER_FILTER, FILTERLIST);
		fileChooser.getExtensionFilters().add(extFilter);
		fileChooser.setTitle(PALETTE_RESOURCES.getString("IMAGECHOOSERLABEL"));
		Button fileButton = new Button(PALETTE_RESOURCES.getString("IMAGECHOOSERBUTTON"));
		fileButton.setPrefWidth(BUTTON_WIDTH);
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
			paletteDisplay.setPrefSize(NARROW_WIDTH, NARROW_WIDTH);
			paletteDisplay.getStyleClass().add(cssResources.getString("VBOX"));
			Label label = new Label(PALETTE_RESOURCES.getString(palette.getPaletteName() + "LABEL"));
			paletteDisplay.getChildren().addAll(label,palette.getPaletteViewGroup());	
			vbox.getChildren().add(paletteDisplay);
		}
	}

	@Override
	public int getX() {
		return COORD10[0];
	}

	@Override
	public int getY() {
		return COORD10[1];
	}



}
