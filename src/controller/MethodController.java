package controller;
import java.util.HashSet;

import model.MethodModel;
import view.ClickableText;
import view.MethodView;

public class MethodController extends Controller{
	private MethodModel model;
	private MethodView view;
	
	public MethodController(MethodModel model,MethodView view) {
		this.model = model;
		this.view = view;
	}
	
	public void addMethod(String method){
		model.addMethod(method);
		view.addMethodView(method);	
	}

}
