package controller;
import java.util.HashSet;

import model.MethodModel;
import view.ClickableText;
import view.MethodView;

public class MethodController extends Controller{
//	private MethodModel model;
	private HashSet<ClickableText> savedMethods = new HashSet<ClickableText>();
	private MethodView view;
	
	public MethodController(MethodView view) {
//		this.model = model;
		this.view = view;
	}
	
	public void addMethod(String method){
//		model.addMethod(method);
		ClickableText methodView = new ClickableText(method);
		if(!savedMethods.contains(methodView)){
			view.addMethodView(methodView);	
		}
	}

}
