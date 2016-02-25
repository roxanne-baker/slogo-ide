package Model;

import java.util.List;


public class ParseNode { 
	private Command c; 
	private ParseNode parent; 
	private List<ParseNode> params; 
	private Object value; 
	private boolean paramsEnough; 
	
	public ParseNode(Command c) { 
		this.c = c; 
		this.value = null; 
		params = null; 
	}
	
	public ParseNode(Object value){ 
		this.value = value; 
	}
	
	public ParseNode(Command c, List<ParseNode> params) { 
		this.c = c;
		this.params = params;  	
		value = null; 
		paramsEnough = false;
	}
	
	public void setValue(Object value) { 
		this.value = value;
		c = null; 
		params = null; 
	}
	
	public ParseNode getParent() { 
		return parent;
	}
	
	public void setParent(ParseNode node) {
		this.parent = node; 
	}
	
	public boolean paramsEnough() { 
		return paramsEnough; 
	}
	public List<ParseNode> getParams() { 
		return params; 
	}
	public Command getCommand() { 
		return c; 
	}
	
	public Object getValue() { 
		return value;
	}
}