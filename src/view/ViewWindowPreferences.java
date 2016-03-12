package view;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.ResourceBundle;

import GUI.GuiObject;
import GUI.GuiObjectFactory;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.scene.Node;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Control;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
/**
 * This class displays all the window preferences for a workspace, such as language. The user can also upload a new Command or XML file here which will update the entire workspace. 
 * @author Melissa Zhang
 *
 */

public class ViewWindowPreferences extends ViewInterpretable{
	private static final int ELEMENT_WIDTH = 240;
	private static final List<String> PREFERENCES_LIST = Arrays.asList("HELP");
	private static final List<String> LANGUAGES_LIST = Arrays.asList("Chinese","English","French","German","Italian","Portuguese","Russian","Spanish");
	
	private List<Node> guiList;
	private String currentLanguage;
	private VBox windowPreferencesBox;
	private ComboBox<String> languageDropDown;
	private Preferences savedPreferences;
	private final ResourceBundle cssResources = ResourceBundle.getBundle("CSSClasses");
	

	public ViewWindowPreferences(ViewType ID, Preferences savedPreferences) {
		super(ID, savedPreferences);
		this.savedPreferences = savedPreferences;
		guiList = new ArrayList<Node>();
		windowPreferencesBox = new VBox();
		windowPreferencesBox.getStyleClass().addAll(cssResources.getString("VBOXVIEW"),cssResources.getString("WINDOWVIEW"));
		setPane(windowPreferencesBox);
		currentLanguage = savedPreferences.getPreference("language").toString();
		languageDropDown = new ComboBox<String>();
		createView();
	}


	private void createView() {
		createLanguagesComboBox();
		createHelpButton();
		for(Node node: windowPreferencesBox.getChildren()){
			((Control)node).setPrefWidth(ELEMENT_WIDTH);
		}
	}


	private void createHelpButton() {
		GuiObjectFactory guiFactory = new GuiObjectFactory();

		for(String pref: PREFERENCES_LIST){
			GuiObject guiObject = guiFactory.createNewGuiObject(pref, null);
			if (guiObject!= null){
				guiList.add((Node) guiObject.createObjectAndReturnObject());
			
			}
		}
		for (Node node: guiList){
			windowPreferencesBox.getChildren().add(node);
		}
	}


	private void createLanguagesComboBox() {
		Label label = new Label("Choose a language for commands");
		windowPreferencesBox.getChildren().add(label);
		
		for (String language: LANGUAGES_LIST){
			languageDropDown.getItems().add(language);
		}
		languageDropDown.setValue(currentLanguage);
		languageDropDown.valueProperty().addListener(new ChangeListener<String>() {
            @Override public void changed(ObservableValue ov, String t, String t1) {                
                currentLanguage = t1;
                savedPreferences.setPreference("language", currentLanguage);
				getInterpreter().addLang(currentLanguage);
            }
		});
		windowPreferencesBox.getChildren().add(languageDropDown);
	}
	
	public String getLanguage(){
		return languageDropDown.getValue();
	}


	@Override
	public int getX() {
		return COORD12[0];
	}


	@Override
	public int getY() {
		return COORD12[1];
	}

}
