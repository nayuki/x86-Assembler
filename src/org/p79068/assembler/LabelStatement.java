package org.p79068.assembler;


/**
 * A label statement. It contains the name of the label. Immutable.
 */
public class LabelStatement extends Statement {
	
	/**
	 * The name of the label.
	 */
	private String name;
	
	
	
	/**
	 * Constructs a label statement with the specified name.
	 * @param name the name of the label
	 * @throws NullPointerException if the name is {@code null}
	 */
	public LabelStatement(String name) {
		if (name == null)
			throw new NullPointerException();
		this.name = name;
	}
	
	
	
	/**
	 * Returns the name of the label.
	 * @return the name of the label
	 */
	public String getName() {
		return name;
	}
	
	
	/**
	 * Compares this label statement to the specified object for equality. Returns {@code true} if the specified object is a label statement with the same name. Otherwise returns {@code false}.
	 * @param obj the object to compare this label statement against
	 * @return {@code true} if the object is a label statement with the same name, {@code false} otherwise
	 */
	@Override
	public boolean equals(Object obj) {
		if (!(obj instanceof LabelStatement))
			return false;
		else
			return name.equals(((LabelStatement)obj).name);
	}
	
	
	/**
	 * Returns the hash code for this label statement.
	 * @return the hash code for this label statement
	 */
	@Override
	public int hashCode() {
		return name.hashCode();
	}
	
	
	/**
	 * Returns a string representation of this label statement. The format is subjected to change.
	 * @return a string representation of this label statement
	 */
	@Override
	public String toString() {
		return name;
	}
	
}
