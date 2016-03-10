package GUI;

import java.io.File;
import java.util.Observable;
import java.util.ResourceBundle;
import java.util.function.BiConsumer;

import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Labeled;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import view.Agent;

public class GuiObjectInputBox extends GuiObject{

	private TextField userInputFileString;
	private Button initializeButton;
	private Labeled fileErrorLabel;
	private boolean boolInit;
	private boolean isNewSelection;
	private static final double PADDING = 10;
	private static final String FILE_DIRECTORY = "images/";
	private static final String FILE_TYPE = ".png";
	private static final double MAXWIDTH = 150;
	private BiConsumer<Observable,String> setValueFunction;
	
	public GuiObjectInputBox(String name, 
			String resourceBundle, Agent agent, BiConsumer<Observable, String> myFunction) {
		super(name,resourceBundle, agent);
		setValueFunction = myFunction;
	}

	@Override
	public Object createObjectAndReturnObject() {
		ResourceBundle resources = getResourceBundle();
		fileErrorLabel = new Label();
		fileErrorLabel.setVisible(false);
		fileErrorLabel.setMaxWidth(MAXWIDTH);
		fileErrorLabel.setWrapText(true);
		userInputFileString = new TextField(((Agent) getObservable()).getImagePath());
		userInputFileString.setMaxWidth(MAXWIDTH);
		initializeButton = new Button(resources.getString(getObjectName()+"BUTTON"));
		initializeButton.setOnAction(evt -> {if (checkIfValid(resources,userInputFileString.getText())){
			setIsNewSelection(true);
			setValueFunction.accept(getObservable(),userInputFileString.getText());
		}});
		initializeButton.setMaxWidth(MAXWIDTH);
		
		VBox XMLControls = new VBox();
		XMLControls.getChildren().addAll(userInputFileString, initializeButton,fileErrorLabel);
		XMLControls.setSpacing(5);
		XMLControls.setPadding(new Insets(0,PADDING,PADDING,PADDING));
		
		return XMLControls;
	}

	private boolean checkIfValid(ResourceBundle resources, String filePath) {
		File f = new File(FILE_DIRECTORY+filePath);
		
		if (f.isFile()){
			fileErrorLabel.setText(resources.getString("ValidFileType"));
			fileErrorLabel.setVisible(true);
			return true;
		}else{
			if (filePath.length()< 4 || !filePath.substring(filePath.length()-4,filePath.length()).equals(FILE_TYPE)){
				
			fileErrorLabel.setText(resources.getString("NotValidFileType"));
		}else{
			fileErrorLabel.setText(resources.getString("FileNotFound"));
		}
		}
		fileErrorLabel.setVisible(true);
		return false;
		
	}

	public boolean getInitValue(){
		return boolInit;
	}

	public void setValue(boolean b) {
		boolInit = b;
	
	}
	public String getChosenFileString(){
		return userInputFileString.getText();
	}

	@Override
	public boolean isNewSelected() {
		return isNewSelection;
	}
                          
	@Override
	public void setIsNewSelection(boolean b) {
		isNewSelection = b;
	}

}
