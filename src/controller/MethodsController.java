package controller;
import java.util.HashSet;

import model.MethodModel;
import view.TextBox;
import view.MethodsView;

public class MethodsController extends Controller{
	private MethodModel model;
	private MethodsView view;
	
	public MethodsController(MethodModel model,MethodsView view) {
		this.model = model;
		this.view = view;
	}
	
	public void addMethod(String method){
		model.addMethod(method);
		view.addMethodView(method);	
	}

}
