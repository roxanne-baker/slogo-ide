package view;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import model.Interpreter;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.geometry.Insets;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.control.ComboBox;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;

public class ViewWindowPreferences extends View{
	private static final List<String> PREFERENCES_LIST = Arrays.asList("HELP");
	private static final List<String> LANGUAGES_LIST = Arrays.asList("Chinese","English","French","German","Italian","Portuguese","Russian","Spanish");
	private static final String DEFAULT_LANGUAGE = "English";
	private static final double PADDING = 10;
	private Group viewGroup;
	private List<Node> guiList;
	private String currentLanguage;
	private HBox windowPreferencesBox;
	private Interpreter myInterpreter;
	private ComboBox<String> languageDropDown;
	

	public ViewWindowPreferences(String id) {
		super(id);
		guiList = new ArrayList<Node>();
		viewGroup = new Group();
		windowPreferencesBox = new HBox();
		myInterpreter = null;
		currentLanguage = DEFAULT_LANGUAGE;
		languageDropDown = new ComboBox<String>();


	}

	@Override
	public Pane getView() {
		createView();
		Pane pane = new Pane(viewGroup);
		return pane;
		//return viewGroup;
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
		
		viewGroup.getChildren().add(windowPreferencesBox);
	}

	private void createLanguagesComboBox() {
		for (String language: LANGUAGES_LIST){
			languageDropDown.getItems().add(language);
		}
		languageDropDown.setValue(currentLanguage);
		languageDropDown.valueProperty().addListener(new ChangeListener<String>() {
            @Override public void changed(ObservableValue ov, String t, String t1) {                
                currentLanguage = t1;
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
