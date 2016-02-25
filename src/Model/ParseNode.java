package Model;

import java.util.List;

class ParseNode { 
	private Command c; 
	private List<ParseNode> params; 
	private Object value; 
	
	ParseNode(Command c) { 
		this.c = c; 
		this.value = null; 
		params = null; 
	}
	
	ParseNode(Object value){ 
		this.value = value; 
	}
	
	ParseNode(Command c, List<ParseNode> params) { 
		this.c = c;
		this.params = params;  	
		value = null; 
	}
	
	void setValue(Object value) { 
		this.value = value;
		c = null; 
		params = null; 
	}
	
	List<ParseNode> getParams() { 
		return params; 
	}
	
	boolean paramsFilled() { 
		return params.size() == c.getNumParams();
	}
	
	Command getCommand() { 
		return c; 
	}
	
	Object getValue() { 
		return value;
	}
}