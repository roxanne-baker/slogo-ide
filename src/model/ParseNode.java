package model;

import java.util.ArrayList;
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
		params = new ArrayList<ParseNode>(); 
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

	boolean isCommand() { 
		return (c != null);
	}
//	List<ParseNode> getParams() { 
//		return params; 
//	}
	
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