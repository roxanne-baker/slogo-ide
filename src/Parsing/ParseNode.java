package Parsing;

import java.util.ArrayList;
import java.util.List;

import commands.Command;

class ParseNode { 
	private Command c; 
	private List<ParseNode> params; 
	private Object value; 
	
	ParseNode(Command c) { 
		this.c = c; 
		this.value = null; 
		params = new ArrayList<ParseNode>(); 
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
	}

	boolean isCommand() { 
		return (c != null);
	}
	List<ParseNode> getParams() { 
		return params; 
	}
	
    boolean allParamsHaveValue() { 
    	for (ParseNode p: params) { 
    		if (p.getValue() == null) { 
    			return false; 
    		}
    	}
    	return true;
    }
	
	int getNumParamsFilled() { 
		return this.params.size();
	}
	
	List<Object> extractParamsFromNode() { 
		List<Object> paramVals = new ArrayList<Object>();
		for (ParseNode p: params) { 
			if (p.getValue() != null) { 
				paramVals.add(p.getValue());
			}
		}
		return paramVals;
	}
	
	boolean paramsFilled() { 
		return params.size() == c.getNumParams();
	}
	
	Command getCommand() { 
		return c; 
	}
	public Object getValue() { 
		return value;
	}
}