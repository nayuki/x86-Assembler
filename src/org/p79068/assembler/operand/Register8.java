package org.p79068.assembler.operand;


/**
 * An 8-bit register operand. Immutable.
 */
public final class Register8 extends Register {
	
	/** The AL register. */
	public static Register8 AL = new Register8("al", 0);
	
	/** The CL register. */
	public static Register8 CL = new Register8("cl", 1);
	
	/** The DL register. */
	public static Register8 DL = new Register8("dl", 2);
	
	/** The BL register. */
	public static Register8 BL = new Register8("bl", 3);
	
	/** The AH register. */
	public static Register8 AH = new Register8("ah", 4);
	
	/** The CH register. */
	public static Register8 CH = new Register8("ch", 5);
	
	/** The DH register. */
	public static Register8 DH = new Register8("dh", 6);
	
	/** The BH register. */
	public static Register8 BH = new Register8("bh", 7);
	
	
	
	private Register8(String name, int registerNumber) {
		super(name, registerNumber);
	}
	
}
