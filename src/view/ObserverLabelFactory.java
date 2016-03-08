package view;
import javafx.beans.binding.Bindings;


public class ObserverLabelFactory {
	public ObserverLabelFactory(){
		
	}
	public Object createNewObserverLabel(String property, Agent agent){
		ObserverLabel labelObject = new ObserverLabel(property,agent.getResourceString(), agent.getName());
		Object myLabel = labelObject.createAndReturnObserverLabel();
		//binding switch cases
		switch(property){
		case("NAME"):{
			labelObject.getObserverLabel().textProperty().bind(agent.getNameProperty().asString());
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
