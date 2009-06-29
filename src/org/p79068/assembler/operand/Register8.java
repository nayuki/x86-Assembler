package org.p79068.assembler.operand;


/**
 * An 8-bit register operand. Immutable.
 */
public final class Register8 extends Register {
	
	/** The AL register. */
	public static Register8 AL_REGISTER = new Register8("%al", 0);
	
	/** The CL register. */
	public static Register8 CL_REGISTER = new Register8("%cl", 1);
	
	/** The DL register. */
	public static Register8 DL_REGISTER = new Register8("%dl", 2);
	
	/** The BL register. */
	public static Register8 BL_REGISTER = new Register8("%bl", 3);
	
	/** The AH register. */
	public static Register8 AH_REGISTER = new Register8("%ah", 4);
	
	/** The CH register. */
	public static Register8 CH_REGISTER = new Register8("%ch", 5);
	
	/** The DH register. */
	public static Register8 DH_REGISTER = new Register8("%dh", 6);
	
	/** The BH register. */
	public static Register8 BH_REGISTER = new Register8("%bh", 7);
	
	
	
	public static Register8 parseOperand(String name) {
		if (name.equals("%al")) return AL_REGISTER;
		if (name.equals("%ah")) return AH_REGISTER;
		if (name.equals("%bl")) return BL_REGISTER;
		if (name.equals("%bh")) return BH_REGISTER;
		if (name.equals("%cl")) return CL_REGISTER;
		if (name.equals("%ch")) return CH_REGISTER;
		if (name.equals("%dl")) return DL_REGISTER;
		if (name.equals("%dh")) return DH_REGISTER;
		return null;
	}
	
	
	
	private String name;
	
	
	
	private Register8(String name, int registerNumber) {
		super(registerNumber);
		this.name = name;
	}
	
	
	public String toString() {
		return name;
	}
	
}