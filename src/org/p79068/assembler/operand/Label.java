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
	
	
	/**
	 * Returns the value of this label operand, given the specified label offset mapping. This method returns an {@link ImmediateValue} of {@code labelOffset.get(this.name)}.
	 * @param labelOffsets the mapping of label names to offset
	 * @return the offset of this label
	 */
	@Override
	public ImmediateValue getValue(Map<String,Integer> labelOffsets) {
		return new ImmediateValue(labelOffsets.get(name));
	}
	

	/**
	 * Compares this label to the specified object for equality. Returns {@code true} if the specified object is a label with the same name. Otherwise returns {@code false}.
	 * @param obj the object to compare this label against
	 * @return {@code true} if the object is a label with the same name, {@code false} otherwise
	 */
	@Override
	public boolean equals(Object obj) {
		if (!(obj instanceof Label))
			return false;
		else
			return name.equals(((Label)obj).name);
	}
	
	
	/**
	 * Returns the hash code for this label.
	 * @return the hash code for this label
	 */
	@Override
	public int hashCode() {
		return name.hashCode();
	}
	
	
	/**
	 * Returns a string representation of this label operand. The format is subjected to change.
	 * @return a string representation of this label operand
	 */
	@Override
	public String toString() {
		return name;
	}
	
}
