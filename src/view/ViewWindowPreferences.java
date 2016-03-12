package view;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.ResourceBundle;
import java.util.Scanner;

import GUI.GuiObject;
import GUI.GuiObjectFactory;
import model.Interpreter;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.layout.VBox;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
/**
 * This class displays all the window preferences for a workspace, such as language. The user can also upload a new Command or XML file here which will update the entire workspace. 
 * @author Melissa Zhang
 *
 */

public class ViewWindowPreferences extends View{
	private static final String FILECHOOSER_FILTER = "SLOGO";
	private static final List<String> FILTERLIST = Arrays.asList("*.logo");
	private static final int CONSOLEX = NARROW_WIDTH+WIDE_WIDTH;
	private static final int CONSOLEY = MENU_OFFSET+WIDE_WIDTH;
	private static final List<String> PREFERENCES_LIST = Arrays.asList("HELP");
	private static final List<String> LANGUAGES_LIST = Arrays.asList("Chinese","English","French","German","Italian","Portuguese","Russian","Spanish");
	private static final double PADDING = 10;
	private static final ResourceBundle WINDOW_RESOURCES = ResourceBundle.getBundle("windowProperties");
	
	private List<Node> guiList;
	private String currentLanguage;
	private VBox windowPreferencesBox;
	private Interpreter myInterpreter;
	private ComboBox<String> languageDropDown;
	private Preferences savedPreferences;
	

	public ViewWindowPreferences(ViewType ID, Preferences savedPreferences) {
		super(ID, savedPreferences);
		this.savedPreferences = savedPreferences;
		setX(CONSOLEX);
		setY(CONSOLEY);
		guiList = new ArrayList<Node>();
		windowPreferencesBox = new VBox();
		windowPreferencesBox.getStyleClass().add("window-menu");
		setPane(windowPreferencesBox);
		myInterpreter = null;
		currentLanguage = savedPreferences.getPreference("language").toString();
		languageDropDown = new ComboBox<String>();
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
		Button fileSaver = new Button(WINDOW_RESOURCES.getString("COMMANDSSAVERBUTTON"));
		fileSaver.setOnAction(evt -> {
			
		});
		windowPreferencesBox.getChildren().add(fileSaver);

	}


	private void createCommandsFileChooser() {
		FileChooser fileChooser = new FileChooser();
		FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter(FILECHOOSER_FILTER, FILTERLIST);
		fileChooser.getExtensionFilters().add(extFilter);
		Stage stage = new Stage();
		Button fileButton = new Button(WINDOW_RESOURCES.getString("COMMANDSLOADERBUTTON"));
		fileButton.setOnAction(evt -> {
			File file = fileChooser.showOpenDialog(stage);
			myInterpreter.run(readText(file));
		});
		windowPreferencesBox.getChildren().add(fileButton);
	}
	
	private static String readText (File file) {
		StringBuilder sb = new StringBuilder();
		try { 
		    Scanner scan = new Scanner(file);
		    while(scan.hasNextLine()){
		        String line = scan.nextLine();
		        if (scan.next().equals("#")) continue;
		        sb.append(line);
		        sb.append("\n");
		    }
		} catch (FileNotFoundException e) { 
			System.out.println("couldn't find the file");
		}
		return sb.toString().trim();
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
