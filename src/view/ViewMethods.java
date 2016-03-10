package view;

import java.util.*;
import javafx.scene.control.*;
import javafx.scene.layout.*;

public class ViewMethods extends View{
	private static final int CONSOLEX = NARROW_WIDTH+WIDE_WIDTH+NARROW_WIDTH;
	private static final int CONSOLEY = MENU_OFFSET;
	private HashSet<TextBox> methods = new HashSet<TextBox>();
	private VBox methodViews = new VBox();

	public ViewMethods(ViewType ID, Map<String,List<Object>> savedPreferences){
		super(ID, savedPreferences);
		setX(CONSOLEX);
		setY(CONSOLEY);
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
}

