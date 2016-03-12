//package view;
//
//import java.util.ResourceBundle;
//
//import javafx.scene.control.Alert;
//import javafx.scene.control.Alert.AlertType;
///**
// * This class opens a dialog box that displays error/information
// * @author Melissa Zhang
// *
// */
//public class DialogBox {
//	private static final ResourceBundle DIALOG_RESOURCES = ResourceBundle.getBundle("DialogBox");
//	
//	public DialogBox(AlertType alertType, String alertName, String info){
//		Alert alert = new Alert(alertType);
//		alert.setTitle(DIALOG_RESOURCES.getString(alertName+"TITLE"));
//		alert.setHeaderText(DIALOG_RESOURCES.getString(alertName+"HEADER"));
//		alert.setContentText(info);
//
//		alert.showAndWait();
//	}
//	
//}
