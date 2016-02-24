

import java.util.HashMap;

import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;

public class ColorMap extends HashMap{
	
	public ColorMap(){
		this.put("RED", Color.RED);
		this.put("BLUE", Color.BLUE);
		this.put("GREY", Color.GREY);
		this.put("GREEN", Color.GREEN);
		this.put("YELLOW", Color.YELLOW);
		this.put("WHITE", Color.WHITE);
		this.put("BLACK",Color.BLACK);
		
		this.put(Color.RED, "RED");
		this.put(Color.BLUE, "BLUE");
		this.put(Color.GREY, "GREY");
		this.put(Color.GREEN, "GREEN");
		this.put(Color.YELLOW, "YELLOW");
		this.put(Color.WHITE, "WHITE");
		this.put(Color.BLACK, "BLACK");

	}

	@Override
	public Object get(Object key) {
		key = ((String) key).toUpperCase();
		return super.get(key);
	}
	
	public String getColorString(Object key){
		return (String) super.get(key);
	}


}
