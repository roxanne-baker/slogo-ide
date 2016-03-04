package controller;
import view.MethodElem;
import view.MethodsView;
import view.VariableElem;
import java.util.*;

import model.MethodModel;


public class MethodsController extends Controller implements Observer {
	private MethodModel model;
	private MethodsView view;
	
	public MethodsController(MethodModel model, MethodsView view) {
		this.model = model;
		this.view = view;
	}
	
	public void addVariable(String name, String value){
		model.addMethod(name,value);
		
		Map<String,String> methodMap = model.getMethods();
		ArrayList<MethodElem> methodList = new ArrayList<MethodElem>();
		for(String key: methodMap.keySet()){
			methodList.add(new MethodElem(key,methodMap.get(key).toString(),this));
		}
		view.update(methodList);
	}
	
	@Override
	public void update(Observable o, Object arg) {
		if(arg=="FIELDCHANGED"){
			addVariable(((VariableElem)o).getName(),((VariableElem)o).getValue());
		}
	}
	
//	public Object getVariable(String name) { 
//		return model.getVariable(name) == null? null: model.getVariable(name);
//	}

}