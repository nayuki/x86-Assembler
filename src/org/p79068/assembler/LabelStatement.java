package org.p79068.assembler;


public class LabelStatement extends Statement {
	
	private String name;
	
	
	
	public LabelStatement(String name) {
		if (name == null)
			throw new NullPointerException();
		this.name = name;
	}
	
	
	
	public String getName() {
		return name;
	}
	
	
	public String toString() {
		return name;
	}
	
}
