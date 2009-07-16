package org.p79068.assembler.operand;


/**
 * A 32-bit register operand. Immutable.
 */
public final class Register32 extends Register {
	
	/** The EAX register. */
	public static Register32 EAX = new Register32("eax", 0);
	
	/** The ECX register. */
	public static Register32 ECX = new Register32("ecx", 1);
	
	/** The EDX register. */
	public static Register32 EDX = new Register32("edx", 2);
	
	/** The EBX register. */
	public static Register32 EBX = new Register32("ebx", 3);
	
	/** The ESP register. */
	public static Register32 ESP = new Register32("esp", 4);
	
	/** The EBP register. */
	public static Register32 EBP = new Register32("ebp", 5);
	
	/** The ESI register. */
	public static Register32 ESI = new Register32("esi", 6);
	
	/** The EDI register. */
	public static Register32 EDI = new Register32("edi", 7);
	
	
	
	/**
	 * Constructs a 32-bit register with the specified name and register number.
	 * @param name the name of the register
	 * @param registerNumber the register number
	 */
	private Register32(String name, int registerNumber) {
		super(name, registerNumber);
	}
	
}
