package Model;

import java.util.List;

public class CommandParseTree {
	ParseNode root; 
	
	public CommandParseTree(ParseNode pNode) {
		root = pNode;  
	}
	
	class ParseNode { 
		Command c; 
		List<ParseNode> params; 
		Double value; 
		ParseNode(Command c, List<ParseNode> params) { 
			this.c = c;
			this.params = params;  	
			value = null; 
		}
		
		void setValue(double value) { 
			this.value = value;
			c = null; 
			params = null; 
		}
	}

}
