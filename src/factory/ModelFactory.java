package factory;

import java.util.*;

import controller.Controller;
import controller.MethodsController;
import controller.TurtleController;
import controller.VariablesController;
import javafx.scene.paint.Color;
import model.Interpreter;
import model.MethodModel;
import model.Model;
import model.VariableModel;
import view.ConsoleView;
import view.HistoryView;
import view.MethodsView;
import view.VariablesView;
import view.View;
import view.ViewAgents;
import view.ViewAgentPreferences;

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