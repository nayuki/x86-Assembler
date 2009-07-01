package org.p79068.assembler.operand;

import org.p79068.assembler.Program;


public class Label extends Immediate {
	
	private String name;
	
	
	
	public Label(String name) {
		if (name == null)
			throw new NullPointerException();
		this.name = name;
	}
	
	
	
	public String getName() {
		return name;
	}
	
	
	public ImmediateValue getValue(Program program) {
		return new ImmediateValue(program.getLabelOffset(name));
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
