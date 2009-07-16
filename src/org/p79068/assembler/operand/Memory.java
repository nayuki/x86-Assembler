package org.p79068.assembler.operand;


/**
 * A 32-bit memory reference. A memory operand has a base register (possibly {@code null}), an index register (possibly {@code null}), an index scale, and a displacement. A memory operand represents the address <var>base</var> + <var>index</var>*<var>scale</var> + <var>displacement</var>. Immutable.
 * @see Register32
 */
public final class Memory extends Operand {
	
	/** The base register, which may be {@code null}. */
	public final Register32 base;
	
	/** The index register, which is not {@link Register32}.ESP, and may be {@code null}. */
	public final Register32 index;
	
	/** The index scale, which is either 1, 2, 4, or 8. */
	public final int scale;
	
	/** The displacement, which is not {@code null}. */
	public final Immediate displacement;
	
	
	
	/**
	 * Constructs a memory operand with the specified base, index, scale, and displacement. The base register may be {@code null}. The index register may be {@code null}, but must not be {@link Register32}.ESP. The scale must be either 1, 2, 4, or 8. The displacement must not be {@code null}.
	 * @param base the base register, possibly {@code null}
	 * @param index the index register, possibly {@code null}
	 * @param scale the index scale, either 1, 2, 4, or 8
	 * @param displacement the displacement, which is not {@code null}
	 */
	public Memory(Register32 base, Register32 index, int scale, Immediate displacement) {
		if (index == Register32.ESP)
			throw new IllegalArgumentException("Invalid index register");
		if (scale != 1 && scale != 2 && scale != 4 && scale != 8)
			throw new IllegalArgumentException("Invalid scale");
		if (displacement == null)
			throw new NullPointerException();
		
		this.base = base;
		this.index = index;
		this.scale = scale;
		this.displacement = displacement;
	}
	
	
	
	/**
	 * Compares this memory operand to the specified object for equality. Returns {@code true} if the specified object is a memory operand with the same base, index, scale, and displacement. Otherwise returns {@code false}.
	 * @param obj the object to compare this memory operand against
	 * @return {@code true} if the object is a memory operand with the same parameters, {@code false} otherwise
	 */
	@Override
	public boolean equals(Object obj) {
		if (!(obj instanceof Memory))
			return false;
		else {
			Memory other = (Memory)obj;
			return base == other.base
			    && index == other.index
			    && scale == other.scale
			    && displacement.equals(other.displacement);
		}
	}
	
	
	/**
	 * Returns the hash code for this memory operand.
	 * @return the hash code for this memory operand
	 */
	@Override
	public int hashCode() {
		return base.hashCode() + index.hashCode() + scale + displacement.hashCode();
	}
	
	
	/**
	 * Returns a string representation of this memory reference. The format is subjected to change.
	 * @return a string representation of this memory reference
	 */
	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("[");
		boolean hasTerm = false;
		
		if (base != null) {
			if (hasTerm) sb.append("+");
			else hasTerm = true;
			sb.append(base);
		}
		
		if (index != null) {
			if (hasTerm) sb.append("+");
			else hasTerm = true;
			sb.append(index);
			if (scale != 1)
				sb.append("*").append(scale);
		}
		
		if (displacement instanceof Label || displacement instanceof ImmediateValue && ((ImmediateValue)displacement).getValue() != 0 || !hasTerm) {
			if (hasTerm) sb.append("+");
			else hasTerm = true;
			sb.append(displacement);
		}
		
		sb.append("]");
		return sb.toString();
	}
	
}
