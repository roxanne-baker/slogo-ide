package Model;

import java.util.List;

<<<<<<< HEAD
class ParseNode { 
		Command c; 
		List<ParseNode> params; 
		Object value; 
		
		public ParseNode(Command c, List<ParseNode> params) { 
			this.c = c;
			this.params = params;  	
			value = null; 
		}
		
		public Object getValue() {
			return this.value;
		}
		
		public void setValue(double value) { 
			this.value = value;
			c = null; 
			params = null; 
		}
	}
=======

class ParseNode { 
	private Command c; 
	private ParseNode parent; 
	private List<ParseNode> params; 
	private Object value; 
	private boolean paramsEnough; 
	
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
		paramsEnough = false;
	}
	
	void setValue(Object value) { 
		this.value = value;
		c = null; 
		params = null; 
	}
	
	ParseNode getParent() { 
		return parent;
	}
	
	void setParent(ParseNode node) {
		this.parent = node; 
	}
	
	boolean paramsEnough() { 
		return paramsEnough; 
	}
	List<ParseNode> getParams() { 
		return params; 
	}
	Command getCommand() { 
		return c; 
	}
	
	Object getValue() { 
		return value;
	}
}
>>>>>>> carolyn
