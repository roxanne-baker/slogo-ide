<<<<<<< HEAD:src/View/View.java
package view;
=======
import java.util.Observable;
>>>>>>> f499e3850c35348f2b479dd03ef0e17b982ec4a1:src/View.java
import java.util.Observer;

import javafx.scene.Group;
import javafx.scene.layout.*;
import java.util.*;



public abstract class View implements Observer{
	private String viewID;
	private int viewHeight;
	private int viewWidth;
	
	public View(String id){
		viewID = id;
//		viewHeight = height;
//		viewWidth = width;
	}

	
	public void update(Observable o, Object arg){
		System.out.println("here");
	}
	
	public abstract Group getView();
	
	public String getID(){
		return viewID;
	}
}
