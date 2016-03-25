package view;
import javafx.beans.binding.Bindings;
import model.Agent;

/**
 * This is part of my masterpiece code. This factory class adds polymorphism when adding GUI elements. The labels are binded to properties in the Agent class so any changes to the model will propagate to the GUI. It is easily extendable and reusable in the future.
 * @author Melissa Zhang
 *
 */
public class ObserverLabelFactory {
	public ObserverLabelFactory(){
		
	}
	public Object createNewObserverLabel(String property, Agent agent){
		ObserverLabel labelObject = new ObserverLabel(property,agent.getResourceString(), agent.getName());
		Object myLabel = labelObject.createAndReturnObserverLabel();
		
		switch(property){
		case("NAME"):{
			labelObject.getObserverLabel().textProperty().bind(agent.getIDProperty().asString());
			return myLabel;
		}
		case("IMAGEPATH"):{
			labelObject.getObserverLabel().textProperty().bind(agent.getImagePathProperty());
			return myLabel;
		}
		case("SIZE"):{
			labelObject.getObserverLabel().textProperty().bind(Bindings.convert(agent.getSizeProperty()));
			return myLabel;
		}
		case("ORIENTATION"):{
			labelObject.getObserverLabel().textProperty().bind(Bindings.convert(agent.getOrientationProperty()));
			return myLabel;
		}
		case("XPOSITION"):{
			labelObject.getObserverLabel().textProperty().bind(Bindings.convert(agent.getXPositionProperty()));
			return myLabel;
		}
		case("YPOSITION"):{
			labelObject.getObserverLabel().textProperty().bind(Bindings.convert(agent.getYPositionProperty()));
			return myLabel;
			
		}case("VISIBLE"):{
			labelObject.getObserverLabel().textProperty().bind(Bindings.convert(agent.getVisibleProperty()));
			return myLabel;
		}
		}
		return null;
	}
}
