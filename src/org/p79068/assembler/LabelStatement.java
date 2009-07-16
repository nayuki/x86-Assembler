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
	
	
	@Override
	public boolean equals(Object obj) {
		if (!(obj instanceof LabelStatement))
			return false;
		else
			return name.equals(((LabelStatement)obj).name);
	}
	
	
	@Override
	public int hashCode() {
		return name.hashCode();
	}
	
	
	@Override
	public String toString() {
		return name;
	}
	
}
