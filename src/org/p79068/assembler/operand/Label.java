package org.p79068.assembler.operand;


public class Label extends Operand {
	
	private String name;
	
	
	
	public Label(String name) {
		if (name == null)
			throw new NullPointerException();
		this.name = name;
	}
	
	
	
	public String getName() {
		return name;
	}
	
	
	public boolean equals(Object obj) {
		if (!(obj instanceof Label))
			return false;
		else
			return name.equals(((Label)obj).name);
	}
	
	
	public int hashCode() {
		return name.hashCode();
	}
	
	
	public String toString() {
		return name;
	}
	
}
