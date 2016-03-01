package controller;
import view.VariableElem;
import view.VariableView;
import java.util.Observable;
import java.util.Observer;

import model.VariableModel;


public class VariableController extends Controller implements Observer {
	private VariableModel model;
	private VariableView view;
	
	public VariableController(VariableModel model, VariableView view) {
		this.model = model;
		this.view = view;
	}
	
	public void setVariableView(VariableView v) { 
		view = v;
	}
	
	public void addVariable(String name, String value){
		model.addVariable(name,value);
		view.addVariableView(name, value, new VariableElem(name,value,this));
	}
	
	@Override
	public void update(Observable savedObj, Object arg) {
		if(arg=="FIELDCHANGED"){
			addVariable(((VariableElem)savedObj).getName(),((VariableElem)savedObj).getValue());
		}
	}
	
	public Object getVariable(String name) { 
		return model.getVariable(name) == null? new Object(): model.getVariable(name);
	}

}