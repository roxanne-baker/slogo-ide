package View;
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
			labelObject.getObserverLabel().textProperty().bind(agent.getNameProperty());
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
		}
		return null;
	}
}
