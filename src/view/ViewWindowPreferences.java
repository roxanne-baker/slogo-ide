package view;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;

import GUI.GuiObject;
import GUI.GuiObjectFactory;
import model.Interpreter;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ComboBoxBase;
import javafx.scene.layout.HBox;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

public class ViewWindowPreferences extends View{
	private static final int CONSOLEX = 0;
	private static final int CONSOLEY = 0;
	private static final List<String> PREFERENCES_LIST = Arrays.asList("HELP");
	private static final List<String> LANGUAGES_LIST = Arrays.asList("Chinese","English","French","German","Italian","Portuguese","Russian","Spanish");
	private static final double PADDING = 10;
	private static final String WINDOW_PROPERTIES = "windowProperties";
	private List<Node> guiList;
	private String currentLanguage;
	private HBox windowPreferencesBox;
	private Interpreter myInterpreter;
	private ComboBox<String> languageDropDown;
	private Preferences savedPreferences;
	private ResourceBundle windowResources;
	

	public ViewWindowPreferences(ViewType ID, Preferences savedPreferences) {
		super(ID, savedPreferences);
		this.savedPreferences = savedPreferences;
		setX(CONSOLEX);
		setY(CONSOLEY);
		guiList = new ArrayList<Node>();
		windowPreferencesBox = new HBox();
		windowPreferencesBox.getStyleClass().add("window-menu");
		setPane(windowPreferencesBox);
		myInterpreter = null;
		currentLanguage = savedPreferences.getPreference("language").toString();
		languageDropDown = new ComboBox<String>();
		windowResources = ResourceBundle.getBundle(WINDOW_PROPERTIES);
		createView();
	}


	private void createView() {
		GuiObjectFactory guiFactory = new GuiObjectFactory();
		windowPreferencesBox.setPadding(new Insets(0,PADDING,PADDING,PADDING));

		for(String pref: PREFERENCES_LIST){
			GuiObject guiObject = guiFactory.createNewGuiObject(pref, null);
			if (guiObject!= null){
				guiList.add((Node) guiObject.createObjectAndReturnObject());
			
			}
		}
		for (Node node: guiList){
			windowPreferencesBox.getChildren().add(node);
		}
		
		createLanguagesComboBox();
		createCommandsFileChooser();
		createFileSaver();

	}
	
	private void createFileSaver() {
		Button fileSaver = new Button(windowResources.getString("COMMANDSSAVERBUTTON"));
		fileSaver.setOnAction(evt -> {
			//TODO Carolyn add code
		
		});
		windowPreferencesBox.getChildren().add(fileSaver);

	}


	private void createCommandsFileChooser() {
		FileChooser fileChooser = new FileChooser();
		Stage stage = new Stage();
		Button fileButton = new Button(windowResources.getString("COMMANDSLOADERBUTTON"));
		fileButton.setOnAction(evt -> {
			File file = fileChooser.showOpenDialog(stage);
			//TODO: Carolyn add Code
		});
		windowPreferencesBox.getChildren().add(fileButton);
	}


	private void createLanguagesComboBox() {
		for (String language: LANGUAGES_LIST){
			languageDropDown.getItems().add(language);
		}
		languageDropDown.setValue(currentLanguage);
		languageDropDown.valueProperty().addListener(new ChangeListener<String>() {
            @Override public void changed(ObservableValue ov, String t, String t1) {                
                currentLanguage = t1;
                savedPreferences.setPreference("language", currentLanguage);
				myInterpreter.addLang(currentLanguage);
            }
		});
		windowPreferencesBox.getChildren().add(languageDropDown);
	}

	public void setInterpreter(Interpreter ip) {
		myInterpreter = ip;
	}
	public String getLanguage(){
		return languageDropDown.getValue();
	}

}
