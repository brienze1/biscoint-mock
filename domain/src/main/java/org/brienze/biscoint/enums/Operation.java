package org.brienze.biscoint.enums;

public enum Operation {

	BUY("Buy"), SELL("Sell");
	
	private String name;
	
	Operation(String name){
		this.name = name;
	}
	
	public String getName(){
		return this.name;
	}

	public static Operation get(String name) {
		for (Operation operation : Operation.values()) {
			if(operation.getName().equalsIgnoreCase(name)) {
				return operation;
			}
		}
		
		return null;
	}
}

