package Model;

import java.util.List;


class ParseNode { 
	Command c; 
	List<ParseNode> params; 
	Object value; 
	boolean paramsGood; 
	
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
		paramsGood = false;
	}
	
	void setValue(Object value) { 
		this.value = value;
		c = null; 
		params = null; 
	}
	
	Object getValue() { 
		return value;
	}
}
