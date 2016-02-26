package View;



import java.util.Observable;
import java.util.ResourceBundle;
import java.util.function.BiConsumer;

import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Labeled;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;

public class GuiObjectInputBox extends GuiObject{

	private TextField userInputFileString;
	private Button initializeButton;
	private Labeled fileErrorLabel;
	private boolean boolInit;
	private boolean isNewSelection;
	private static final double PADDING = 10;
	private BiConsumer<Observable,String> setValueFunction;
	
	public GuiObjectInputBox(String name, 
			String resourceBundle, Agent agent, BiConsumer<Observable, String> myFunction) {
		super(name,resourceBundle, agent);
		setValueFunction = myFunction;
	}

	@Override
	public Object createObjectAndReturnObject() {
		ResourceBundle resources = getResourceString();
		fileErrorLabel = new Label();
		fileErrorLabel.setVisible(false);
		
		userInputFileString = new TextField(((Agent) getObservable()).getImagePath());
		initializeButton = new Button(resources.getString(getObjectName()+"BUTTON"));
		initializeButton.setOnAction(evt -> {if (checkIfValid(resources)){
			setIsNewSelection(true);
			setValueFunction.accept(getObservable(),userInputFileString.getText());
		}});
		
		VBox XMLControls = new VBox();
		XMLControls.getChildren().addAll(userInputFileString, initializeButton,fileErrorLabel);
		XMLControls.setSpacing(5);
		XMLControls.setPadding(new Insets(0,PADDING,PADDING,PADDING));
		
		return XMLControls;
	}

//todo
	private boolean checkIfValid(ResourceBundle resources) {
		initializeButton.getText();
		return true;
		
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
