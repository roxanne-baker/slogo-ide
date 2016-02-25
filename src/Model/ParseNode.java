package Model;

import java.util.List;

class ParseNode { 
		Command c; 
		List<ParseNode> params; 
		Object value; 
		
		public ParseNode(Object value) {
			this.value = value;
		}
		
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