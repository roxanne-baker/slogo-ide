package view;

import java.util.*;
import javafx.scene.control.*;
import javafx.scene.layout.*;

public class ViewMethods extends View{
	private HashSet<TextBox> methods = new HashSet<TextBox>();
	private VBox methodViews = new VBox();
	private ResourceBundle cssResources = ResourceBundle.getBundle("CSSClasses");

	public ViewMethods(ViewType ID, Preferences savedPreferences){
		super(ID, savedPreferences);
		methodViews.getStyleClass().addAll(cssResources.getString("VBOX"),cssResources.getString("DISPLAYVIEW"));
		init();
	}
 	
	public void update(List<MethodElem> methodList){
		methodViews.getChildren().clear();
		for(MethodElem method: methodList){
			methodViews.getChildren().add(method.getMethodV());
		}
		setChanged();
		notifyObservers();
	}
	
 	public void addMethodView(String method){
 		TextBox methodView = new TextBox(method);
 		if(!methods.contains(methodView)){
 			methods.add(methodView);
 			methodViews.getChildren().add(methodView.getTextBox());
 		}
 	}

	private void init() {
 		methodViews.setPrefSize(View.NARROW_WIDTH,View.WIDE_WIDTH);
 		ScrollPane sp = new ScrollPane(methodViews);
 		setPane(sp);
	}

	@Override
	public int getX() {
		return COORD03[0];
	}

	@Override
	public int getY() {
		return COORD03[1];
	}
}

