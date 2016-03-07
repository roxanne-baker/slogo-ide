package factory;

import model.MethodModel;
import model.Model;
import model.VariableModel;

public class ModelFactory {
	
	public ModelFactory(){
	}
	
	public Model createModel(String ID){
		switch(ID){
		case "Variables":
			return new VariableModel();
		case "Methods":
			return new MethodModel();
		}
		return null;
	}

}