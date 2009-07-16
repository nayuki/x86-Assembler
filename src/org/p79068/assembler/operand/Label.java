package org.p79068.assembler.operand;

import java.util.Map;


/**
 * A label operand. A label is simply a string name. Immutable.
 */
public class Label extends Immediate {
	
	/** The name of the label. */
	private String name;
	
	
	
	/**
	 * Constructs a label with the specified name.
	 * @param name the name of the label
	 */
	public Label(String name) {
		if (name == null)
			throw new NullPointerException();
		this.name = name;
	}
	
	
	
	/**
	 * Returns the name of this label.
	 */
	public String getName() {
		return name;
	}
	
	
	public ImmediateValue getValue(Map<String,Integer> labelOffsets) {
		return new ImmediateValue(labelOffsets.get(name));
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
	
	
	/**
	 * Returns a string representation of this label operand. The format is subjected to change.
	 * @return a string representation of this label operand
	 */
	public String toString() {
		return name;
	}
	
}
