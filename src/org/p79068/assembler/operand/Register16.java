package org.p79068.assembler.operand;


/**
 * A 16-bit register operand. Immutable.
 */
public final class Register16 extends Register {
	
	/** The AX register. */
	public static Register16 AX = new Register16("ax", 0);
	
	/** The CX register. */
	public static Register16 CX = new Register16("cx", 1);
	
	/** The DX register. */
	public static Register16 DX = new Register16("dx", 2);
	
	/** The BX register. */
	public static Register16 BX = new Register16("bx", 3);
	
	/** The SP register. */
	public static Register16 SP = new Register16("sp", 4);
	
	/** The BP register. */
	public static Register16 BP = new Register16("bp", 5);
	
	/** The SI register. */
	public static Register16 SI = new Register16("si", 6);
	
	/** The DI register. */
	public static Register16 DI = new Register16("di", 7);
	
	
	
	/**
	 * Constructs a 16-bit register with the specified name and register number.
	 * @param name the name of the register
	 * @param registerNumber the register number
	 */
	private Register16(String name, int registerNumber) {
		super(name, registerNumber);
	}
	
}
