package org.p79068.libelf;


public enum ObjectType {
	
	ET_NONE(0), ET_REL(1), ET_EXEC(2), ET_DYN(3), ET_CORE(4);
	
	
	public final short value;
	
	
	private ObjectType(int value) {
		if ((short)value != value)
			throw new AssertionError();
		this.value = (short)value;
	}
	
}
